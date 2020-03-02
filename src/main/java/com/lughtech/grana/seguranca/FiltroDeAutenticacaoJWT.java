package com.lughtech.grana.seguranca;

import java.io.IOException;
import java.rmi.ServerException;
import java.util.ArrayList;
import java.util.Date;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.lughtech.grana.dto.CredenciaisDTO;

public class FiltroDeAutenticacaoJWT extends UsernamePasswordAuthenticationFilter {

	private UtilitarioJWT utilitarioJwt;

	private AuthenticationManager gerenciadorDeAutenticacao;

	public FiltroDeAutenticacaoJWT(AuthenticationManager gerenciadorDeAutenticacao, UtilitarioJWT utilitarioJWT) {
		setAuthenticationFailureHandler(new ManipuladorDeFalhaDeAutenticacaoJWT());
		this.utilitarioJwt = utilitarioJWT;
		this.gerenciadorDeAutenticacao = gerenciadorDeAutenticacao;
	}

	@Override
	public Authentication attemptAuthentication(HttpServletRequest requisicao, HttpServletResponse resposta) {
		try {
			CredenciaisDTO creds = new ObjectMapper().readValue(requisicao.getInputStream(), CredenciaisDTO.class);

			UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(creds.getEmail(), creds.getSenha(), new ArrayList<>());

			Authentication auth = gerenciadorDeAutenticacao.authenticate(authToken);
			return auth;
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	@Override
	public void successfulAuthentication(HttpServletRequest requisicao, HttpServletResponse resposta, FilterChain cadeiaDeFiltro, Authentication autenticacao) throws IOException, ServerException {
		String username = ((UsuarioSpringSecurity) autenticacao.getPrincipal()).getUsername();
		String token = utilitarioJwt.generateToken(username);
		resposta.addHeader("Authorization", "Bearer " + token);
		resposta.addHeader("access-control-expose-headers", "Authorization");
	}

	private class ManipuladorDeFalhaDeAutenticacaoJWT implements AuthenticationFailureHandler {

		@Override
		public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
			response.setStatus(401);
			response.setContentType("application/json");
			response.getWriter().append(json());
		}

		private String json() {
			long date = new Date().getTime();
			return "{\"timestamp\": " + date + ", " + "\"status\": 401, " + "\"error\": \"Não autorizado\", " + "\"message\": \"Email ou senha inválidos\", " + "\"path\": \"/login\"}";
		}
	}

}
