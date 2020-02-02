package com.lughtech.grana.recursos;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.servicos.GranaServico;

@RestController
@RequestMapping(value = "/grana")
public class GranaRecurso {

	@Autowired
	private GranaServico granaServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Grana> buscarGranaPoId(@PathVariable Integer id) {
		Grana grana = granaServico.buscarGranaPorId(id);
		return ResponseEntity.ok().body(grana);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirGrana(@RequestBody Grana grana) {
		grana = granaServico.salvarGrana(grana);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(grana.getIdGrana()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarGrana(@RequestBody Grana grana, @PathVariable Integer id) {
		grana = granaServico.atualizarGrana(grana);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarGranaPorId(@PathVariable Integer id) {
		granaServico.deletarGranaPorId(id);
		return ResponseEntity.noContent().build();
	}

}
