package com.lughtech.grana.servicos;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.servicos.abstratos.EmailServicoAbstrato;

public class EmailServicoSendGrid extends EmailServicoAbstrato {

	private static final Logger LOG = LoggerFactory.getLogger(EmailServicoSendGrid.class);

	@Override
	public void enviarEmail(List<SimpleMailMessage> mensagem) {
		LOG.info("Enviando emails");
		LOG.info("Emails enviados");

	}

}
