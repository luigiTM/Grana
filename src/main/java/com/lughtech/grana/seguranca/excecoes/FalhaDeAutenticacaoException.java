package com.lughtech.grana.seguranca.excecoes;

public class FalhaDeAutenticacaoException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public FalhaDeAutenticacaoException(String mensagem) {
		super(mensagem);
	}

}
