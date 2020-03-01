package com.lughtech.grana.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lughtech.grana.servicos.EmailServicoSMTP;
import com.lughtech.grana.servicos.interfaces.EmailServico;

@Configuration
@Profile("desenvolvimento")
public class ConfiguracaoDesenvolvimento {

	@Bean
	public EmailServico emailServico() {
		return new EmailServicoSMTP();
	}

}
