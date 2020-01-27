package com.lughtech.grana.resources.exceptions;

import java.io.Serializable;

public class ErroPadrao implements Serializable {

	private static final long serialVersionUID = 1L;
	private Integer status;
	private String mensagem;
	private Long timestamp;

	public ErroPadrao() {
	}

	public ErroPadrao(Integer status, String mensagem, Long timestamp) {
		this.status = status;
		this.mensagem = mensagem;
		this.timestamp = timestamp;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getMensagem() {
		return mensagem;
	}

	public void setMensagem(String mensagem) {
		this.mensagem = mensagem;
	}

	public Long getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(Long timestamp) {
		this.timestamp = timestamp;
	}

}
