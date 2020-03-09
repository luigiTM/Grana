package com.lughtech.grana.seguranca.filtros;

import java.io.IOException;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import com.lughtech.grana.seguranca.UtilitarioJWT;

public class FiltroDeAutorizacaoJWT extends BasicAuthenticationFilter {

	private UtilitarioJWT utilitarioJWT;
	private UserDetailsService detalheDeUsuarioServico;

	public FiltroDeAutorizacaoJWT(AuthenticationManager gerenciadorDeAutenticacao, UtilitarioJWT utilitarioJWT, UserDetailsService detalheDeUsuarioServico) {
		super(gerenciadorDeAutenticacao);
		this.detalheDeUsuarioServico = detalheDeUsuarioServico;
		this.utilitarioJWT = utilitarioJWT;
	}

	@Override
	protected void doFilterInternal(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain cadeia) throws IOException, ServletException {

		String header = requisicao.getHeader("Authorization");
		if (header != null && header.startsWith("Bearer ")) {
			UsernamePasswordAuthenticationToken autenticacao = getAuthentication(header.substring(7));
			if (autenticacao != null) {
				SecurityContextHolder.getContext().setAuthentication(autenticacao);
			}
		}
		cadeia.doFilter(requisicao, resposta);
	}

	private UsernamePasswordAuthenticationToken getAuthentication(String token) {
		if (utilitarioJWT.tokenValido(token)) {
			String nomeDoUsusario = utilitarioJWT.getNomeDeUsuario(token);
			UserDetails usuario = detalheDeUsuarioServico.loadUserByUsername(nomeDoUsusario);
			return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
		}
		return null;
	}

}
