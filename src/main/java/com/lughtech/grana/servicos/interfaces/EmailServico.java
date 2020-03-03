package com.lughtech.grana.servicos.interfaces;

import java.util.List;

import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dominio.Usuario;

public interface EmailServico {

	void enviarGranaParaPessoas(Grana grana);

	void enviarEmail(List<SimpleMailMessage> mensagem);

	void enviarNovaSenha(Usuario usuario, String novaSenha);

//	void enviarGranaParaPessoasHTML(Grana grana);
//
//	void enviarEmailHTML(List<MimeMessage> mensagem);

}
