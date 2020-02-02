package com.lughtech.grana.resources;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

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

import com.lughtech.grana.domain.Usuario;
import com.lughtech.grana.dto.UsuarioDTO;
import com.lughtech.grana.services.UsuarioService;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioResource {

	@Autowired
	private UsuarioService usuarioService;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioService.buscarUsuarioPorId(id);
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO) {
		Usuario usuario = usuarioService.deUmDTO(usuarioDTO);
		usuario = usuarioService.salvarUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Integer id) {
		Usuario usuario = usuarioService.deUmDTO(usuarioDTO);
		usuario = usuarioService.atualizarUsuario(usuario);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Integer id) {
		usuarioService.deletarUsuarioPorId(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
		List<Usuario> usuarios = usuarioService.buscarUsuarios();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuariosDTO);
	}

	@RequestMapping(value = "/paginado", method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> buscarUsuariosPaginado(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhaPagina", defaultValue = "24") Integer linhaPagina, @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao, @RequestParam(value = "pagina", defaultValue = "ASC") String direcao) {
		Page<Usuario> usuarios = usuarioService.buscarUsuariosPaginado(pagina, linhaPagina, ordenacao, direcao);
		Page<UsuarioDTO> usuariosDTO = usuarios.map(usuario -> new UsuarioDTO(usuario));
		return ResponseEntity.ok().body(usuariosDTO);
	}

}
