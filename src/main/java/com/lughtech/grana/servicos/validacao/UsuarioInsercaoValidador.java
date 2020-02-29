package com.lughtech.grana.servicos.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.NovoUsuarioDTO;
import com.lughtech.grana.recursos.excecoes.MensagemCampos;
import com.lughtech.grana.repositorio.UsuarioRepositorio;

public class UsuarioInsercaoValidador implements ConstraintValidator<UsuarioInsercao, NovoUsuarioDTO> {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	@Override
	public void initialize(UsuarioInsercao usuarioInsercao) {
	}

	@Override
	public boolean isValid(NovoUsuarioDTO novoUsuarioDTO, ConstraintValidatorContext context) {

		List<MensagemCampos> lista = new ArrayList<>();

		Usuario usuarioExistente = usuarioRepositorio.findByEmail(novoUsuarioDTO.getEmail());
		if (usuarioExistente != null) {
			lista.add(new MensagemCampos("email", "Email já existente"));
		}

		for (MensagemCampos mensagem : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}
}