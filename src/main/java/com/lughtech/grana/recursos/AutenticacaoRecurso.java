package com.lughtech.grana.recursos;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.lughtech.grana.dto.EmailDto;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;
import com.lughtech.grana.seguranca.UtilitarioJWT;
import com.lughtech.grana.servicos.AutenticadorServico;
import com.lughtech.grana.servicos.UsuarioLogadoServico;

@RestController
@RequestMapping(value = "/autenticacao")
public class AutenticacaoRecurso {

	@Autowired
	private UtilitarioJWT utillitarioJWT;

	@Autowired
	private AutenticadorServico autenticadorServico;

	@PostMapping(value = "/atualizarToken")
	public ResponseEntity<Void> refreshToken(HttpServletResponse resposta) {
		UsuarioSpringSecurity usuario = UsuarioLogadoServico.usuarioLogado();
		String token = utillitarioJWT.generateToken(usuario.getUsername(), usuario.getId());
		resposta.addHeader("Authorization", "Bearer " + token);
		resposta.addHeader("access-control-expose-headers", "Authorization");
		return ResponseEntity.noContent().build();
	}

	@PostMapping(value = "/esqueciSenha")
	public ResponseEntity<Void> forgot(@RequestBody EmailDto emailDTO) {
		autenticadorServico.enviarNovaSenha(emailDTO.getPara());
		return ResponseEntity.noContent().build();
	}

}
