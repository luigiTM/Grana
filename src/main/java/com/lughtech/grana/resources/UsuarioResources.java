package com.lughtech.grana.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lughtech.grana.domain.Usuario;
import com.lughtech.grana.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResources {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Usuario obj = usuarioService.buscarPessoaPorId(id);
		return ResponseEntity.ok().body(obj);
	}
}
