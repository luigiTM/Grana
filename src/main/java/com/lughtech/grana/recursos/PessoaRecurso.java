package com.lughtech.grana.recursos;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Integer id) {
		Pessoa Pessoa = pessoaServico.buscarPessoaPorId(id);
		return ResponseEntity.ok().body(Pessoa);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirPessoa(@Valid @RequestBody PessoaDTO pessoaDTO) {
		Pessoa novaPessoa = pessoaServico.deUmDTO(pessoaDTO);
		novaPessoa = pessoaServico.salvarPessoa(novaPessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novaPessoa.getIdPessoa()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarPessoa(@RequestBody PessoaDTO pessoaDTO, @PathVariable Integer id) {
		Pessoa pessoa = pessoaServico.deUmDTO(pessoaDTO);
		pessoa.setIdPessoa(id);
		pessoa = pessoaServico.atualizarPessoa(pessoa);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPessoaPorId(@PathVariable Integer id) {
		pessoaServico.deletarPessoaPorId(id);
		return ResponseEntity.noContent().build();
	}

}
