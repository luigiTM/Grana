package com.lughtech.grana.configuracao;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lughtech.grana.seguranca.UtilitarioJWT;
import com.lughtech.grana.seguranca.filtros.FiltroDeAutenticacaoJWT;
import com.lughtech.grana.seguranca.filtros.FiltroDeAutorizacaoJWT;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService detalhesDeUsuarioServico;
	@Autowired
	UtilitarioJWT utilitarioJWT;
	@Autowired
	private Environment ambiente;

	private static final String[] PUBLICOS_POST = { "/usuario/**", "/autenticacao/**" };
	private static final String[] PUBLICOS_GET = {"/grana/codigoDeAcesso/**"};
	private static final String[] PUBLICOS = {};

	@Override
	protected void configure(HttpSecurity segurancaHttp) throws Exception {
		if (Arrays.asList(ambiente.getActiveProfiles()).contains("dev")) {
			segurancaHttp.headers().frameOptions().disable();
		}
		segurancaHttp.cors().and().csrf().disable();
		segurancaHttp.authorizeRequests().antMatchers(HttpMethod.POST, PUBLICOS_POST).permitAll().antMatchers(HttpMethod.GET, PUBLICOS_GET).permitAll().antMatchers(PUBLICOS).permitAll().anyRequest().authenticated();
		segurancaHttp.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
		segurancaHttp.addFilter(new FiltroDeAutenticacaoJWT(authenticationManager(), utilitarioJWT));
		segurancaHttp.addFilter(new FiltroDeAutorizacaoJWT(authenticationManager(), utilitarioJWT, detalhesDeUsuarioServico));
	}

	@Override
	public void configure(AuthenticationManagerBuilder autenticacao) throws Exception {
		autenticacao.userDetailsService(detalhesDeUsuarioServico).passwordEncoder(codificacaoDeSenha());
	}

	@Bean
	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuracaoCors = new CorsConfiguration().applyPermitDefaultValues();
		configuracaoCors.setAllowedMethods(Arrays.asList("POST","PUT","GET","DELETE","OPTIONS"));
		final UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
		fonte.registerCorsConfiguration("/**", configuracaoCors);
		return fonte;
	}

	@Bean
	public BCryptPasswordEncoder codificacaoDeSenha() {
		return new BCryptPasswordEncoder();
	}
}
