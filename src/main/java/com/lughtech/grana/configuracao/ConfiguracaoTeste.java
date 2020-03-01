package com.lughtech.grana.configuracao;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import com.lughtech.grana.servicos.interfaces.EmailServico;
import com.lughtech.grana.servicos.mock.EmailServicoMock;

@Configuration
@Profile("teste")
public class ConfiguracaoTeste {

	@Bean
	public EmailServico emailServico() {
		return new EmailServicoMock();
	}

}
