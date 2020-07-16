package com.lughtech.grana.servicos;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dto.GastoDTO;
import com.lughtech.grana.repositorio.GastoRepositorio;
import com.lughtech.grana.repositorio.GranaRepositorio;
import com.lughtech.grana.seguranca.UsuarioSpringSecurity;
import com.lughtech.grana.servicos.excecoes.AutorizacaoException;
import com.lughtech.grana.servicos.excecoes.IntegridadeDeDadosException;
import com.lughtech.grana.servicos.excecoes.ObjetoNaoEncontradoException;

@Service
public class GastoServico {

	@Autowired
	private GastoRepositorio gastoRepositorio;
	@Autowired
	private GranaRepositorio granaRepositorio;

	public Gasto buscarGastoPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Optional<Gasto> gasto = gastoRepositorio.findById(id);
		if (!gasto.get().equals(null)) {
			if (usuarioAtual == null || !gasto.get().getGrana().getUsuario().getIdUsuario().equals(usuarioAtual.getId())) {
				throw new AutorizacaoException();
			}
		}
		return gasto.orElseThrow(() -> new ObjetoNaoEncontradoException(Gasto.class.getSimpleName()));
	}

	public List<Gasto> buscarGastosPorGrana(Integer idGrana) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Optional<Grana> grana = granaRepositorio.findById(idGrana);
		if (!grana.get().equals(null)) {
			if (usuarioAtual == null || !grana.get().getUsuario().getIdUsuario().equals(usuarioAtual.getId())) {
				throw new AutorizacaoException();
			}
		}
		return gastoRepositorio.findByGrana(idGrana);
	}

	public Gasto salvarGasto(Gasto gasto) {
		gasto.setIdGasto(null);
		return gastoRepositorio.save(gasto);
	}

	public Gasto atualizarGasto(Gasto gasto) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Gasto novoGasto = buscarGastoPorId(gasto.getIdGasto());
		if (usuarioAtual == null || !novoGasto.getGrana().getUsuario().getIdUsuario().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
		atualizarInformacoesGasto(novoGasto, gasto);
		return gastoRepositorio.save(novoGasto);
	}

	public void deletarGastoPorId(Integer id) {
		UsuarioSpringSecurity usuarioAtual = UsuarioLogadoServico.usuarioLogado();
		Gasto gasto = buscarGastoPorId(id);
		if (usuarioAtual == null || !gasto.getGrana().getUsuario().getIdUsuario().equals(usuarioAtual.getId())) {
			throw new AutorizacaoException();
		}
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
