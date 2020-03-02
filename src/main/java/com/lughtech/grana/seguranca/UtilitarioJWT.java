package com.lughtech.grana.seguranca;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class UtilitarioJWT {

	@Value("${jwt.secret}")
	private String segredo;

	@Value("${jwt.expiration}")
	private Long expiracao;

	public String generateToken(String nomeUsuraio) {
		return Jwts.builder().setSubject(nomeUsuraio).setExpiration(new Date(System.currentTimeMillis() + expiracao)).signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();
	}

	public boolean tokenValido(String token) {
		Claims reinvindicacoes = getReinvindicacoes(token);
		if (reinvindicacoes != null) {
			String nomeDeUsuario = reinvindicacoes.getSubject();
			Date dataExpiracao = reinvindicacoes.getExpiration();
			Date agora = new Date(System.currentTimeMillis());
			if (nomeDeUsuario != null && dataExpiracao != null && agora.before(dataExpiracao)) {
				return true;
			}
		}
		return false;
	}

	public String getNomeDeUsuario(String token) {
		Claims reinvindicacoes = getReinvindicacoes(token);
		if (reinvindicacoes != null) {
			return reinvindicacoes.getSubject();
		}
		return null;
	}

	private Claims getReinvindicacoes(String token) {
		try {
			return Jwts.parser().setSigningKey(segredo.getBytes()).parseClaimsJws(token).getBody();
		} catch (Exception e) {
			return null;
		}
	}
}
