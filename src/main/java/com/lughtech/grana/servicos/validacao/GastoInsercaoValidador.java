package com.lughtech.grana.servicos.validacao;

import java.util.ArrayList;
import java.util.List;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.lughtech.grana.dto.GastoDTO;
import com.lughtech.grana.recursos.excecoes.MensagemCampos;

public class GastoInsercaoValidador implements ConstraintValidator<GastoInsercao, GastoDTO> {

	@Override
	public void initialize(GastoInsercao gastoInsercao) {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean isValid(GastoDTO novoGastoDTO, ConstraintValidatorContext context) {

		List<MensagemCampos> lista = new ArrayList<>();

		if (novoGastoDTO.getValor() == null) {
			lista.add(new MensagemCampos("valor", "Valor do gasto não pode ser vazio"));
		} else {
			if (novoGastoDTO.getValor() < 0) {
				lista.add(new MensagemCampos("valor", "Valor do gasto não pode ser menor que zero"));
			}
		}

		if (novoGastoDTO.getDataGasto() == null) {
			lista.add(new MensagemCampos("dataGasto", "Data do gasto não pode ser vazia"));
		}

		for (MensagemCampos mensagem : lista) {
			context.disableDefaultConstraintViolation();
			context.buildConstraintViolationWithTemplate(mensagem.getMensagem()).addPropertyNode(mensagem.getCampo()).addConstraintViolation();
		}
		return lista.isEmpty();
	}
}