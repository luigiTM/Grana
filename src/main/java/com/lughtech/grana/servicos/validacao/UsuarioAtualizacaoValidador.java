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

public class UsuarioAtualizacaoValidador implements ConstraintValidator<UsuarioAtualizacao, UsuarioDTO> {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;
	@Autowired
	HttpServletRequest requisicao;

	@Override
	public void initialize(UsuarioAtualizacao usuarioInsercao) {
	}

	@Override
	public boolean isValid(UsuarioDTO novoUsuarioDTO, ConstraintValidatorContext context) {

		List<MensagemCampos> lista = new ArrayList<>();
		Map<String, String> mapaRequisicao = (Map<String, String>) requisicao.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
		Integer uriId = Integer.parseInt(mapaRequisicao.get("id"));

		Usuario usuarioExistente = usuarioRepositorio.findByEmail(novoUsuarioDTO.getEmail());
		if (usuarioExistente != null && !usuarioExistente.getIdUsuario().equals(uriId)) {
			lista.add(new MensagemCampos("email", "Email já existente"));
		}

		for (MensagemCampos mensagem : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}
}