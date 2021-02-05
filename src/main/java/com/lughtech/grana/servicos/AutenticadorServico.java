package com.lughtech.grana.servicos;

import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.redis.RedisMessagePublisher;
import com.lughtech.grana.repositorio.UsuarioRepositorio;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;
import com.lughtech.grana.utilitarios.EmailUtils;

@Service
public class AutenticadorServico {

	@Autowired
	private UsuarioRepositorio usuarioRepositorio;
	@Autowired
	private BCryptPasswordEncoder codificadorDeSenha;
	@Autowired
	private RedisMessagePublisher messageGateway;

	private Random aleatorio;

	public void enviarNovaSenha(String email) {
		Usuario usuario = usuarioRepositorio.findByEmail(email);
		if (usuario == null) {
			throw new ObjetoNaoEncontradoException(Usuario.class.getSimpleName());
		}
		String novaSenha = gerarNovaSenha();
		usuario.setSenha(codificadorDeSenha.encode(novaSenha));
		messageGateway.publish(EmailUtils.criarEmailNovaSenha());
		usuarioRepositorio.save(usuario);
	}

	private String gerarNovaSenha() {
		char[] vetorCaracteres = new char[10];
		for (int i = 0; i < vetorCaracteres.length; i++) {
			vetorCaracteres[i] = caracterAleatorio();
		}
		return new String(vetorCaracteres);
	}

	private char caracterAleatorio() {
		Integer tipo = aleatorio.nextInt(3);
		if (tipo == 0) {
			return (char) (aleatorio.nextInt(10) + 48);
		} else if (tipo == 1) {
			return (char) (aleatorio.nextInt(26) + 65);
		} else {
			return (char) (aleatorio.nextInt(26) + 97);
		}
	}

}
