package com.lughtech.grana.servicos.interfaces;

import java.util.List;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.dominio.Grana;

public interface EmailServico {

	void enviarGranaParaPessoas(Grana grana);

	void enviarEmail(List<SimpleMailMessage> mensagem);

	void enviarGranaParaPessoasHTML(Grana grana);

	void enviarEmailHTML(List<MimeMessage> mensagem);

}
