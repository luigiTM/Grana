package com.lughtech.grana.servicos.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import org.springframework.beans.factory.annotation.Autowired;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dto.GastoPessoaDTO;
import com.lughtech.grana.recursos.excecoes.MensagemCampos;
import com.lughtech.grana.repositorio.GastoPessoaRepositorio;
import com.lughtech.grana.repositorio.GastoRepositorio;

public class GastoPessoaInsercaoValidador implements ConstraintValidator<GastoPessoaInsercao, GastoPessoaDTO> {

	@Autowired
	GastoRepositorio gastoRepositorio;
	@Autowired
	GastoPessoaRepositorio gastoPessoaRepositorio;

	@Override
	public void initialize(GastoPessoaInsercao gastoPessoaInsercao) {
	}

	@Override
	public boolean isValid(GastoPessoaDTO novoGastoPessoaDTO, ConstraintValidatorContext context) {

		List<MensagemCampos> lista = new ArrayList<>();

		Gasto gasto = gastoRepositorio.findById(novoGastoPessoaDTO.getGasto()).get();

		if (novoGastoPessoaDTO.getValorGasto() > gasto.getValor()) {
			lista.add(new MensagemCampos("valorGasto", "O valor da divisão não pode ser maior que o valor total do gasto"));
		}
		if (novoGastoPessoaDTO.getPercentualGasto() > 1) {
			lista.add(new MensagemCampos("percentualGasto", "O valor do percentual da divisão não pode ser maior que 100%"));
		}

		for (MensagemCampos mensagem : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}
}