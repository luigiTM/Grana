package com.lughtech.grana.utilitarios;

import com.lughtech.grana.dto.EmailDto;

public class EmailUtils {
	
	public static EmailDto criarEmailNovoUsuario() {
		EmailDto email = new EmailDto();
		email.setAssunto("TEste");
		email.setConteudo("Teste");
		email.setDe("De");
		email.setPara("teste");
		return email;
	}
	
	public static EmailDto criarEmailNovaSenha() {
		return new EmailDto();
	}

}
