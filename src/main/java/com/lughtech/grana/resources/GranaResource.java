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

import com.lughtech.grana.domain.Grana;
import com.lughtech.grana.services.GranaService;

@RestController
@RequestMapping(value = "/grana")
public class GranaResource {

	@Autowired
	private GranaService granaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Grana> buscar(@PathVariable Integer id) {
		Grana obj = granaService.buscarGranaPorId(id);
		return ResponseEntity.ok().body(obj);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirUsuario(@RequestBody Grana grana) {
		grana = granaService.salvarGrana(grana);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(grana.getIdGrana()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarUsuario(@RequestBody Grana grana, @PathVariable Integer id) {
		grana = granaService.atualizarGrana(grana);
		return ResponseEntity.noContent().build();
	}

}
