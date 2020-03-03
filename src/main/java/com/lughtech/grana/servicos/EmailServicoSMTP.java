package com.lughtech.grana.servicos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.servicos.abstratos.EmailServicoAbstrato;

public class EmailServicoSMTP extends EmailServicoAbstrato {

	@Autowired
	private MailSender enviadorEmail;

	private static final Logger LOG = LoggerFactory.getLogger(EmailServicoSMTP.class);

	@Override
	public void enviarEmail(List<SimpleMailMessage> mensagem) {
		LOG.info("Enviando emails");
		for (SimpleMailMessage simpleMailMessage : mensagem) {
			enviadorEmail.send(simpleMailMessage);
		}
		LOG.info("Emails enviados");

	}

}
