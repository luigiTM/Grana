package com.lughtech.grana.recursos;

import java.net.URI;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dto.GranaDTO;
import com.lughtech.grana.servicos.GranaServico;
import com.lughtech.grana.servicos.interfaces.EmailServico;

@RestController
@RequestMapping(value = "/grana")
public class GranaRecurso {

	@Autowired
	private GranaServico granaServico;
	@Autowired
	private EmailServico emailServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Grana> buscarGranaPoId(@PathVariable Integer id) {
		Grana grana = granaServico.buscarGranaPorId(id);
		emailServico.enviarGranaParaPessoas(grana);
		return ResponseEntity.ok().body(grana);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirGrana(@Valid @RequestBody GranaDTO granaDTO) {
		Grana novoGrana = granaServico.deUmDTO(granaDTO);
		novoGrana = granaServico.salvarGrana(novoGrana);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(novoGrana.getIdGrana()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarGrana(@Valid @RequestBody GranaDTO granaDTO, @PathVariable Integer id) {
		Grana grana = granaServico.deUmDTO(granaDTO);
		grana.setIdGrana(id);
		grana = granaServico.atualizarGrana(grana);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarGranaPorId(@PathVariable Integer id) {
		granaServico.deletarGranaPorId(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/paginado", method = RequestMethod.GET)
	public ResponseEntity<Page<GranaDTO>> buscarUsuariosPaginado(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhaPagina", defaultValue = "24") Integer linhaPagina, @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao, @RequestParam(value = "pagina", defaultValue = "DESC") String direcao) {
		Page<Grana> granas = granaServico.buscarGranasPaginado(pagina, linhaPagina, ordenacao, direcao);
		Page<GranaDTO> granaDTO = granas.map(grana -> new GranaDTO(grana));
		return ResponseEntity.ok().body(granaDTO);
	}

}
