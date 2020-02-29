package com.lughtech.grana.servicos;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dto.GastoDTO;
import com.lughtech.grana.repositorio.GastoRepositorio;
import com.lughtech.grana.repositorio.GranaRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GastoServico {

	@Autowired
	private GastoRepositorio gastoRepositorio;
	@Autowired
	private GranaRepositorio granaRepositorio;

	public Gasto buscarGastoPorId(Integer id) {
		Optional<Gasto> obj = gastoRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(Gasto.class.getSimpleName()));
	}

	public Gasto salvarGasto(Gasto gasto) {
		gasto.setIdGasto(null);
		return gastoRepositorio.save(gasto);
	}

	public Gasto atualizarGasto(Gasto gasto) {
		Gasto novoGasto = buscarGastoPorId(gasto.getIdGasto());
		atualizarInformacoesGasto(novoGasto, gasto);
		return gastoRepositorio.save(novoGasto);
	}

	public void deletarGastoPorId(Integer id) {
		buscarGastoPorId(id);
		try {
			gastoRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public Gasto deUmDTO(GastoDTO gastoDTO) {
		Gasto gasto;
		if (gastoDTO.getIdGrana() == null) {
			gasto = new Gasto(null, gastoDTO.getTipo(), gastoDTO.getValor(), gastoDTO.getDataGasto());
		} else {
			Grana grana = granaRepositorio.getOne(gastoDTO.getIdGrana());
			gasto = new Gasto(grana, gastoDTO.getTipo(), gastoDTO.getValor(), gastoDTO.getDataGasto());
		}
		return gasto;
	}

	private void atualizarInformacoesGasto(Gasto novoGasto, Gasto gasto) {
		novoGasto.setDataGasto((gasto.getDataGasto() == null) ? novoGasto.getDataGasto() : gasto.getDataGasto());
		novoGasto.setTipo((gasto.getTipo() == null) ? novoGasto.getTipo() : gasto.getTipo());
		novoGasto.setValor((gasto.getValor() == null) ? novoGasto.getValor() : gasto.getValor());
	}
}
