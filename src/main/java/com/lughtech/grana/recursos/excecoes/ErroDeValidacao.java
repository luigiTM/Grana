package com.lughtech.grana.recursos.excecoes;

import java.util.ArrayList;
import java.util.List;

public class ErroDeValidacao extends ErroPadrao {

	private static final long serialVersionUID = 1L;

	public List<MensagemCampos> mensagensCampos = new ArrayList<MensagemCampos>();

	public ErroDeValidacao(Integer status, String mensagem, Long timestamp) {
		super(status, mensagem, timestamp);
	}

	public List<MensagemCampos> getMensagensCampos() {
		return mensagensCampos;
	}

	public void adicionarErro(String campo, String mensagem) {
		mensagensCampos.add(new MensagemCampos(campo, mensagem));
	}

}
