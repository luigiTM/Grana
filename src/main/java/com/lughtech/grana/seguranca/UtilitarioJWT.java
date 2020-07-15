package com.lughtech.grana.seguranca;

import java.util.Date;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Component
public class UtilitarioJWT {

	private String segredo = "SequenciaDeCaracteresParaAssinarToken";
	private Long expiracao = 86400000L;

	public String generateToken(String nomeUsuario, Integer idUsuario) {
		return Jwts.builder().setSubject(nomeUsuario).claim("user_id", idUsuario).setExpiration(new Date(System.currentTimeMillis() + expiracao)).signWith(SignatureAlgorithm.HS512, segredo.getBytes()).compact();
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
