package com.lughtech.grana.configuracao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import com.lughtech.grana.seguranca.FiltroDeAutenticacaoJWT;
import com.lughtech.grana.seguranca.FiltroDeAutorizacaoJWT;
import com.lughtech.grana.seguranca.UtilitarioJWT;

@Configuration
@EnableWebSecurity
public class ConfiguracaoDeSeguranca extends WebSecurityConfigurerAdapter {

	@Autowired
	UserDetailsService detalhesDeUsuarioServico;
	@Autowired
	UtilitarioJWT utilitarioJWT;

	private static final String[] PUBLICOS = { "/usuario/**" };

	protected void configure(HttpSecurity segurancaHttp) throws Exception {
		segurancaHttp.cors().and().csrf().disable();
		segurancaHttp.authorizeRequests().antMatchers(PUBLICOS).permitAll().anyRequest().authenticated();
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
		final UrlBasedCorsConfigurationSource fonte = new UrlBasedCorsConfigurationSource();
		fonte.registerCorsConfiguration("/**", new CorsConfiguration().applyPermitDefaultValues());
		return fonte;
	}

	@Bean
	public BCryptPasswordEncoder codificacaoDeSenha() {
		return new BCryptPasswordEncoder();
	}
}
