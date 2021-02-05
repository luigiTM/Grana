package com.lughtech.grana.recursos;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.Pessoa;
import com.lughtech.grana.dto.PessoaDTO;
import com.lughtech.grana.servicos.PessoaServico;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaRecurso {

	@Autowired
	private PessoaServico pessoaServico;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Integer id) {
		Pessoa pessoa = pessoaServico.buscarPessoaPorId(id);
		return ResponseEntity.ok().body(pessoa);
	}

	@GetMapping(value = "/{idGrana}")
	public ResponseEntity<List<Pessoa>> buscarPessoasPorGrana(@PathVariable Integer idGrana) {
		List<Pessoa> pessoas = pessoaServico.buscarPessoasPorGrana(idGrana);
		return ResponseEntity.ok().body(pessoas);
	}

	@PostMapping
	public ResponseEntity<Void> inserirPessoa(@RequestBody PessoaDTO pessoaDTO) {
		Pessoa novaPessoa = pessoaServico.deUmDTO(pessoaDTO);
		novaPessoa = pessoaServico.salvarPessoa(novaPessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaPessoa.getId()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizarPessoa(@RequestBody PessoaDTO pessoaDTO, @PathVariable Integer id) {
		Pessoa pessoa = pessoaServico.deUmDTO(pessoaDTO);
		pessoa.setId(id);
		pessoaServico.atualizarPessoa(pessoa);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarPessoaPorId(@PathVariable Integer id) {
		pessoaServico.deletarPessoaPorId(id);
		return ResponseEntity.noContent().build();
	}

}
