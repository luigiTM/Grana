package com.lughtech.grana.resources;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.domain.Pessoa;
import com.lughtech.grana.services.PessoaService;

@RestController
@RequestMapping(value = "/Pessoa")
public class PessoaResource {

	@Autowired
	private PessoaService PessoaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Pessoa> buscarPessoaPorId(@PathVariable Integer id) {
		Pessoa Pessoa = PessoaService.buscarPessoaPorId(id);
		return ResponseEntity.ok().body(Pessoa);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirPessoa(@RequestBody Pessoa Pessoa) {
		Pessoa = PessoaService.salvarPessoa(Pessoa);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(Pessoa.getIdPessoa()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarPessoa(@RequestBody Pessoa Pessoa, @PathVariable Integer id) {
		Pessoa = PessoaService.atualizarPessoa(Pessoa);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarPessoaPorId(@PathVariable Integer id) {
		PessoaService.deletarPessoaPorId(id);
		return ResponseEntity.noContent().build();
	}

}
