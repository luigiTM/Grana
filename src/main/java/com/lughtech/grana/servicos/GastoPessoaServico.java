package com.lughtech.grana.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dominio.GastoPessoa;
import com.lughtech.grana.dominio.Pessoa;
import com.lughtech.grana.dto.GastoPessoaDTO;
import com.lughtech.grana.repositorio.GastoPessoaRepositorio;
import com.lughtech.grana.repositorio.GastoRepositorio;
import com.lughtech.grana.repositorio.PessoaRepositorio;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GastoPessoaServico {

	@Autowired
	private GastoPessoaRepositorio gastoPessoaRepositorio;
	@Autowired
	private GastoRepositorio gastoRepositorio;
	@Autowired
	private PessoaRepositorio pessoaRepositorio;

	public GastoPessoa buscarGastoPessoaPorId(Integer id) {
		Optional<GastoPessoa> obj = gastoPessoaRepositorio.findById(id);
		return obj.orElseThrow(() -> new ObjetoNaoEncontradoException(GastoPessoa.class.getSimpleName()));
	}

	public GastoPessoa salvarGastoPessoa(GastoPessoa gastoPessoa) {
		gastoPessoa.setIdGastoPessoa(null);
		return gastoPessoaRepositorio.save(gastoPessoa);
	}

	public GastoPessoa atualizarGastoPessoa(GastoPessoa GastoPessoa) {
		GastoPessoa novoGastoPessoa = buscarGastoPessoaPorId(GastoPessoa.getIdGastoPessoa());
		atualizarInformacoesGastoPessoa(novoGastoPessoa, GastoPessoa);
		return gastoPessoaRepositorio.save(novoGastoPessoa);
	}

	public List<GastoPessoa> buscarGastoPessoaPorGasto(Integer idGasto) {
		return null;
	}

	public void deletarGastoPessoaPorId(Integer id) {
		buscarGastoPessoaPorId(id);
		try {
			gastoPessoaRepositorio.deleteById(id);
		} catch (DataIntegrityViolationException integridadeException) {
			throw new IntegridadeDeDadosException("");
		}
	}

	public GastoPessoa deUmDTO(GastoPessoaDTO gastoPessoaDTO) {
		GastoPessoa gastoPessoa;
		if (gastoPessoaDTO.getGasto() == null || gastoPessoaDTO.getPessoa() == null) {
			gastoPessoa = new GastoPessoa(null, null, gastoPessoaDTO.getValorGasto(), gastoPessoaDTO.getPercentualGasto());
		} else {
			Pessoa pessoa = pessoaRepositorio.getOne(gastoPessoaDTO.getPessoa());
			Gasto gasto = gastoRepositorio.getOne(gastoPessoaDTO.getGasto());
			gastoPessoa = new GastoPessoa(gasto, pessoa, gastoPessoaDTO.getValorGasto(), gastoPessoaDTO.getPercentualGasto());
		}
		return gastoPessoa;
	}

	private void atualizarInformacoesGastoPessoa(GastoPessoa novoGastoPessoa, GastoPessoa GastoPessoa) {

	}
}
