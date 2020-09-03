package com.lughtech.grana.recursos;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dto.GranaDto;
import com.lughtech.grana.servicos.GranaServico;

@RestController
@RequestMapping(value = "/grana")
public class GranaRecurso {

	@Autowired
	private GranaServico granaServico;

	@GetMapping(value = "/{id}")
	public ResponseEntity<Grana> buscarGranaPoId(@PathVariable Integer id) {
		Grana grana = granaServico.buscarGranaPorId(id);
		return ResponseEntity.ok().body(grana);
	}

	@GetMapping(value = "/codigoDeAcesso/{codigoDeAcesso}")
	public ResponseEntity<Grana> buscarGranaPoId(@PathVariable String codigoDeAcesso) {
		Grana grana = granaServico.buscarGranaPorCodigoDeAcesso(codigoDeAcesso);
		return ResponseEntity.ok().body(grana);
	}

	@PostMapping
	public ResponseEntity<Void> inserirGrana(@Valid @RequestBody GranaDto granaDTO) {
		Grana novoGrana = granaServico.deUmDTO(granaDTO);
		novoGrana = granaServico.salvarGrana(novoGrana);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoGrana.getIdGrana()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Void> atualizarGrana(@Valid @RequestBody GranaDto granaDTO, @PathVariable Integer id) {
		Grana grana = granaServico.deUmDTO(granaDTO);
		grana.setIdGrana(id);
		granaServico.atualizarGrana(grana);
		return ResponseEntity.noContent().build();
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deletarGranaPorId(@PathVariable Integer id) {
		granaServico.deletarGranaPorId(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping(value = "/paginado")
	public ResponseEntity<Page<GranaDto>> buscarGranasPaginado(@RequestParam(value = "usuario", defaultValue = "") Integer usuario, @RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhaPagina, @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao, @RequestParam(value = "direcao", defaultValue = "DESC") String direcao) {
		Page<Grana> granas = granaServico.buscarGranasPaginado(usuario, pagina, linhaPagina, ordenacao, direcao);
		Page<GranaDto> granaDTO = granas.map(GranaDto::new);
		return ResponseEntity.ok().body(granaDTO);
	}

}
