package com.lughtech.grana.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.GastoPessoa;
import com.lughtech.grana.dto.GastoPessoaDTO;
import com.lughtech.grana.servicos.GastoPessoaServico;

@RestController
@RequestMapping(value = "/gastoPessoa")
public class GastoPessoaRecurso {

	@Autowired
	private GastoPessoaServico gastoPessoaServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<GastoPessoa> buscarGastoPessoaPorId(@PathVariable Integer id) {
		GastoPessoa gastoPessoa = gastoPessoaServico.buscarGastoPessoaPorId(id);
		return ResponseEntity.ok().body(gastoPessoa);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirGasto(@Valid @RequestBody GastoPessoaDTO gastoPessoaDTO) {
		GastoPessoa gastoPessoa = gastoPessoaServico.deUmDTO(gastoPessoaDTO);
		gastoPessoa = gastoPessoaServico.salvarGastoPessoa(gastoPessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gastoPessoa.getIdGastoPessoa()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarGasto(@RequestBody GastoPessoaDTO gastoPessoaDTO, @PathVariable Integer id) {
		GastoPessoa gastoPessoa = gastoPessoaServico.deUmDTO(gastoPessoaDTO);
		gastoPessoa.setIdGastoPessoa(id);
		gastoPessoa = gastoPessoaServico.atualizarGastoPessoa(gastoPessoa);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/gasto/{id}", method = RequestMethod.GET)
	public ResponseEntity<List<GastoPessoaDTO>> buscarGastoPessoaPorGasto(@PathVariable Integer id) {
		List<GastoPessoa> gastosPessoas = gastoPessoaServico.buscarGastoPessoaPorGasto(id);
		List<GastoPessoaDTO> gastosPessoasDTO = gastosPessoas.stream().map(gastoPessoa -> new GastoPessoaDTO(gastoPessoa)).collect(Collectors.toList());
		return ResponseEntity.ok().body(gastosPessoasDTO);
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarGastoPorId(@PathVariable Integer id) {
		gastoPessoaServico.deletarGastoPessoaPorId(id);
		return ResponseEntity.noContent().build();
	}

}
