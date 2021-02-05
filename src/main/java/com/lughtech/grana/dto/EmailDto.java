package com.lughtech.grana.dto;

public class EmailDto {

	String de;
	String para;
	String assunto;
	String conteudo;

	public EmailDto() {
		super();
	}

	public EmailDto(String de, String para, String assunto, String conteudo) {
		this.de = de;
		this.para = para;
		this.assunto = assunto;
		this.conteudo = conteudo;
	}

	public String getDe() {
		return de;
	}

	public void setDe(String de) {
		this.de = de;
	}

	public String getPara() {
		return para;
	}

	public void setPara(String para) {
		this.para = para;
	}

	public String getAssunto() {
		return assunto;
	}

	public void setAssunto(String assunto) {
		this.assunto = assunto;
	}

	public String getConteudo() {
		return conteudo;
	}

	public void setConteudo(String conteudo) {
		this.conteudo = conteudo;
	}

}
