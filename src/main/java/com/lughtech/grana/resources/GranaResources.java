package com.lughtech.grana.resources;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.lughtech.grana.domain.Grana;
import com.lughtech.grana.services.GranaService;

@RestController
@RequestMapping(value = "/grana")
public class GranaResources {

	@Autowired
	private GranaService granaService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<?> buscar(@PathVariable Integer id) {
		Grana obj = granaService.buscarGranaPorId(id);
		return ResponseEntity.ok().body(obj);
	}

}
