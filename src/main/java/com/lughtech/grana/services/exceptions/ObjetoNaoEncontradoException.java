package com.lughtech.grana.services.exceptions;

public class ObjetoNaoEncontradoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public ObjetoNaoEncontradoException(String entidade) {
		super("Não foi possível encontrar o(a) " + entidade + " especificado(a)!");
	}

	public ObjetoNaoEncontradoException(String entidade, Throwable causa) {
		super(entidade, causa);
	}

}
