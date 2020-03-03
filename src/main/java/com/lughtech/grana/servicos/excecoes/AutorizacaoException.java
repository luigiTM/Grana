package com.lughtech.grana.servicos.excecoes;

public class AutorizacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AutorizacaoException() {
		super("Acesso negado");
	}

	public AutorizacaoException(String entidade, Throwable causa) {
		super(entidade, causa);
	}

}
