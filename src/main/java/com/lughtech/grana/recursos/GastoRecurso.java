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

import com.lughtech.grana.dominio.Gasto;
import com.lughtech.grana.dto.GastoDTO;
import com.lughtech.grana.servicos.GastoServico;

@RestController
@RequestMapping(value = "/gasto")
public class GastoRecurso {

	@Autowired
	private GastoServico gastoServico;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Gasto> buscarGastoPorId(@PathVariable Integer id) {
		Gasto gasto = gastoServico.buscarGastoPorId(id);
		return ResponseEntity.ok().body(gasto);
	}

	@GetMapping(value = "/grana/{idGrana}")
	public ResponseEntity<List<Gasto>> buscarGastoPorGrana(@PathVariable Integer idGrana) {
		List<Gasto> gastos = gastoServico.buscarGastosPorGrana(idGrana);
		return ResponseEntity.ok().body(gastos);
	}

	@PostMapping
	public ResponseEntity<Void> inserirGasto(@RequestBody GastoDTO gastoDTO) {
		Gasto gasto = gastoServico.deUmDTO(gastoDTO);
		gasto = gastoServico.salvarGasto(gasto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gasto.getIdGasto()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizarGasto(@RequestBody GastoDTO gastoDTO, @PathVariable Integer id) {
		Gasto gasto = gastoServico.deUmDTO(gastoDTO);
		gasto.setIdGasto(id);
		gastoServico.atualizarGasto(gasto);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarGastoPorId(@PathVariable Integer id) {
		gastoServico.deletarGastoPorId(id);
		return ResponseEntity.noContent().build();
	}

}
