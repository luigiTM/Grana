package com.lughtech.grana.servicos.validacao;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerMapping;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.UsuarioDTO;
import com.lughtech.grana.recursos.excecoes.MensagemCampos;
import com.lughtech.grana.repositorio.UsuarioRepositorio;

public class UsuarioInsercaoValidador implements ConstraintValidator<UsuarioInsercao, UsuarioDTO> {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Autowired
	HttpServletRequest requisicao;

	@Override
	public void initialize(UsuarioInsercao usuarioInsercao) {
	}

	@Override
	public boolean isValid(UsuarioDTO novoUsuarioDTO, ConstraintValidatorContext context) {

		List<MensagemCampos> lista = new ArrayList<>();

		if (requisicao.getMethod().equals("POST")) {
			Usuario usuarioExistente = usuarioRepositorio.findByEmail(novoUsuarioDTO.getEmail());
			if (usuarioExistente != null) {
				lista.add(new MensagemCampos("email", "Email já existente"));
			}
		}
		if (requisicao.getMethod().equals("PUT")) {
			Map<?, ?> mapaRequisicao = (Map<?, ?>) requisicao.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
			Integer uriId = Integer.parseInt((mapaRequisicao.get("id") == null ? "0" : (String) mapaRequisicao.get("id")));
			Usuario usuarioExistente = usuarioRepositorio.findByEmail(novoUsuarioDTO.getEmail());
			if (usuarioExistente != null && !usuarioExistente.getIdUsuario().equals(uriId)) {
				lista.add(new MensagemCampos("email", "Email já existente"));
			}
		}

		for (MensagemCampos mensagem : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}
}