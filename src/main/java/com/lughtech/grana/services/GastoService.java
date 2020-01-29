package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.domain.Gasto;
import com.lughtech.grana.repositories.GastoRepository;
import com.lughtech.grana.services.exceptions.IntegridadeDeDadosException;
import com.lughtech.grana.services.exceptions.ObjetoNaoEncontradoException;

@Service
public class GastoService {

	@Autowired
	private GastoRepository GastoRepository;

	public Gasto buscarGastoPorId(Integer id) {
		Optional<Gasto> obj = GastoRepository.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Gasto.class.getSimpleName()));
	}

	public Gasto salvarGasto(Gasto Gasto) {
		Gasto.setIdGasto(null);
		return GastoRepository.save(Gasto);
	}

	public Gasto atualizarGasto(Gasto Gasto) {
		buscarGastoPorId(Gasto.getIdGasto());
		return GastoRepository.save(Gasto);
	}

	public void deletarGastoPorId(Integer id) {
		buscarGastoPorId(id);
		try {
			GastoRepository.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}
}
