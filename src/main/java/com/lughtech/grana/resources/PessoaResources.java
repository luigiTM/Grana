package com.lughtech.grana.resources;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/pessoa")
public class PessoaResources {

	@RequestMapping(method = RequestMethod.GET)
	public String listar() {
		return "teste";
	}

}
