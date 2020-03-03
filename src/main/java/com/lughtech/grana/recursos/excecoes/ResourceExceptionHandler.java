package com.lughtech.grana.recursos.excecoes;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.lughtech.grana.servicos.excecoes.AutorizacaoException;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@ControllerAdvice
public class ResourceExceptionHandler {

	@ExceptionHandler(ObjetoNaoEncontradoException.class)
	public ResponseEntity<ErroPadrao> objetoNaoEncontrado(ObjetoNaoEncontradoException objetoNaoEncontrado, HttpServletRequest requisicao) {
		ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.NOT_FOUND.value(), "Não encontrado", objetoNaoEncontrado.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(erro);

	}

	@ExceptionHandler(IntegridadeDeDadosException.class)
	public ResponseEntity<ErroPadrao> integridadeDeDados(IntegridadeDeDadosException integridadeDeDados, HttpServletRequest requisicao) {
		ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.BAD_REQUEST.value(), "Integridade de dados", integridadeDeDados.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(erro);

	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ErroDeValidacao> validacao(MethodArgumentNotValidException invalido, HttpServletRequest requisicao) {
		ErroDeValidacao erro = new ErroDeValidacao(System.currentTimeMillis(), HttpStatus.UNPROCESSABLE_ENTITY.value(), "Erro de validação", invalido.getMessage(), requisicao.getRequestURI());
		for (FieldError erroDeCampo : invalido.getBindingResult().getFieldErrors()) {
			erro.adicionarErro(erroDeCampo.getField(), erroDeCampo.getDefaultMessage());
		}
		return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(erro);
	}

	@ExceptionHandler(AutorizacaoException.class)
	public ResponseEntity<ErroPadrao> autorizacao(AutorizacaoException objetoNaoEncontrado, HttpServletRequest requisicao) {
		ErroPadrao erro = new ErroPadrao(System.currentTimeMillis(), HttpStatus.FORBIDDEN.value(), "Acesso Negado", objetoNaoEncontrado.getMessage(), requisicao.getRequestURI());
		return ResponseEntity.status(HttpStatus.FORBIDDEN).body(erro);

	}

}
