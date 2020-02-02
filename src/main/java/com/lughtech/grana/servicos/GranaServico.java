package com.lughtech.grana.servicos;

import java.sql.Timestamp;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.repositorio.GranaRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GranaServico {

	@Autowired
	private GranaRepositorio granaRepositorio;

	public Grana buscarGranaPorId(Integer id) {
		Optional<Grana> obj = granaRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException("Objeto do tipo " + Grana.class.getSimpleName() + " não encontrado!"));
	}

	public Grana salvarGrana(Grana grana) {
		grana.setIdGrana(null);
		grana.setCriadoEm(new Timestamp(System.currentTimeMillis()));
		return granaRepositorio.save(grana);
	}

	public Grana atualizarGrana(Grana Grana) {
		buscarGranaPorId(Grana.getIdGrana());
		return granaRepositorio.save(Grana);
	}

	public void deletarGranaPorId(Integer id) {
		buscarGranaPorId(id);
		try {
			granaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

}
