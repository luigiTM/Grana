package com.lughtech.grana.servicos.mock;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.mail.SimpleMailMessage;

import com.lughtech.grana.servicos.EmailServicoAbstrato;

public class EmailServicoMock extends EmailServicoAbstrato {

	private static final Logger LOG = LoggerFactory.getLogger(EmailServicoMock.class);

	@Override
	public void enviarEmail(List<SimpleMailMessage> mensagem) {
		LOG.info("Simulando envio de email");
		for (SimpleMailMessage simpleMailMessage : mensagem) {
			LOG.info(simpleMailMessage.toString());
		}
		LOG.info("Emails enviados");
	}

}
