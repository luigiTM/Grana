package com.lughtech.grana.recursos.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends ErroPadrao {

	private static final long serialVersionUID = 1L;

	public List<MensagemCampos> mensagensCampos = new ArrayList<MensagemCampos>();

	public ErroDeValidacao(Long timestamp, Integer status, String error, String message, String path) {
		super(timestamp, status, error, message, path);
	}

	public List<MensagemCampos> getMensagensCampos() {
		return mensagensCampos;
	}

	public void adicionarErro(String campo, String mensagem) {
		mensagensCampos.add(new MensagemCampos(campo, mensagem));
	}

}
