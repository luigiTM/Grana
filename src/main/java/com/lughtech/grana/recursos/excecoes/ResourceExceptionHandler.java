package com.lughtech.grana.recursos.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException objetoNaoEncontrado, HttpServletRequest requisicao) {
		ErroPadrao erro = new ErroPadrao(HttpStatus.NOT_FOUND.value(), objetoNaoEncontrado.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

	@ExceptionHandler(IntegridadeDeDadosException.class)
	public ResponseEntity<ErroPadrao> integridadeDeDados(IntegridadeDeDadosException integridadeDeDados, HttpServletRequest requisicao) {
		ErroPadrao erro = new ErroPadrao(HttpStatus.BAD_REQUEST.value(), integridadeDeDados.getMessage(), System.currentTimeMillis());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroDeValidacao> validacao(MethodArgumentNotValidException invalido, HttpServletRequest requisicao) {
		ErroDeValidacao erro = new ErroDeValidacao(HttpStatus.BAD_REQUEST.value(), "Erro de validação", System.currentTimeMillis());
		for (FieldError erroDeCampo : invalido.getBindingResult().getFieldErrors()) {
			erro.adicionarErro(erroDeCampo.getField(), erroDeCampo.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}

}
