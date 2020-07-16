package com.lughtech.grana.recursos;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
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

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Gasto> buscarGastoPorId(@PathVariable Integer id) {
		Gasto gasto = gastoServico.buscarGastoPorId(id);
		return ResponseEntity.ok().body(gasto);
	}

	@RequestMapping(value = "/grana/{idGrana}", method = RequestMethod.GET)
	public ResponseEntity<List<Gasto>> buscarGastoPorGrana(@PathVariable Integer idGrana) {
		List<Gasto> gastos = gastoServico.buscarGastosPorGrana(idGrana);
		return ResponseEntity.ok().body(gastos);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirGasto(@Valid @RequestBody GastoDTO gastoDTO) {
		Gasto gasto = gastoServico.deUmDTO(gastoDTO);
		gasto = gastoServico.salvarGasto(gasto);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(gasto.getIdGasto()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarGasto(@Valid @RequestBody GastoDTO gastoDTO, @PathVariable Integer id) {
		Gasto gasto = gastoServico.deUmDTO(gastoDTO);
		gasto.setIdGasto(id);
		gasto = gastoServico.atualizarGasto(gasto);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarGastoPorId(@PathVariable Integer id) {
		gastoServico.deletarGastoPorId(id);
		return ResponseEntity.noContent().build();
	}

}
