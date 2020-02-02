package com.lughtech.grana.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.repositorio.GastoRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GastoServico {

	@Autowired
	private GastoRepositorio gastoRepositorio;

	public Gasto buscarGastoPorId(Integer id) {
		Optional<Gasto> obj = gastoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Gasto.class.getSimpleName()));
	}

	public Gasto salvarGasto(Gasto Gasto) {
		Gasto.setIdGasto(null);
		return gastoRepositorio.save(Gasto);
	}

	public Gasto atualizarGasto(Gasto Gasto) {
		buscarGastoPorId(Gasto.getIdGasto());
		return gastoRepositorio.save(Gasto);
	}

	public void deletarGastoPorId(Integer id) {
		buscarGastoPorId(id);
		try {
			gastoRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}
}
