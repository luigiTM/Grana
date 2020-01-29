package com.lughtech.grana.resources.exceptions;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lughtech.grana.services.exceptions.IntegridadeDeDadosException;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

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

}
