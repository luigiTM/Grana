package com.lughtech.grana.services.exceptions;

public class IntegridadeDeDadosException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public IntegridadeDeDadosException(String entidade) {
		super("Não é possível remover o(a)" + entidade + " pois ela possui elementos relacionados!");
	}

	public IntegridadeDeDadosException(String entidade, Throwable causa) {
		super(entidade, causa);
	}

}
