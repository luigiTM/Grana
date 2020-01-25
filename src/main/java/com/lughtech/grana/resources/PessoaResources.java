package com.lughtech.grana.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lughtech.grana.domain.Pessoa;
import com.lughtech.grana.services.PessoaService;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResources {

	@Autowired
	private PessoaService pessoaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Pessoa obj = pessoaService.buscarPessoa(id);
		return ResponseEntity.ok().body(obj);
	}
}
