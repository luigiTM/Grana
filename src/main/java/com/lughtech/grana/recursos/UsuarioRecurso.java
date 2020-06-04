package com.lughtech.grana.recursos;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dto.UsuarioDTO;
import com.lughtech.grana.servicos.UsuarioServico;

@RestController
@RequestMapping(value = "/usuario")
public class UsuarioRecurso {

	@Autowired
	private UsuarioServico usuarioServico;

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarUsuarioPorId(@PathVariable Integer id) {
		Usuario usuario = usuarioServico.buscarUsuarioPorId(id);
		return ResponseEntity.ok().body(usuario);
	}
	
	@RequestMapping(value = "/email/{email}", method = RequestMethod.GET)
	public ResponseEntity<Usuario> buscarUsuarioPorEmail(@PathVariable String email) {
		Usuario usuario = usuarioServico.buscarUsuarioPorEmail(email);
		return ResponseEntity.ok().body(usuario);
	}

	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<Void> inserirUsuario(@Valid @RequestBody UsuarioDTO UsuarioDTO) {
		Usuario usuario = usuarioServico.deUmDTO(UsuarioDTO);
		usuario = usuarioServico.salvarUsuario(usuario);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(usuario.getIdUsuario()).toUri();
		return ResponseEntity.created(uri).build();
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.PUT)
	public ResponseEntity<Void> atualizarUsuario(@Valid @RequestBody UsuarioDTO usuarioDTO, @PathVariable Integer id) {
		Usuario usuario = usuarioServico.deUmDTO(usuarioDTO);
		usuario.setIdUsuario(id);
		usuario = usuarioServico.atualizarUsuario(usuario);
		return ResponseEntity.noContent().build();
	}
	
	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Void> deletarUsuarioPorId(@PathVariable Integer id) {
		usuarioServico.deletarUsuarioPorId(id);
		return ResponseEntity.noContent().build();
	}

	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<UsuarioDTO>> buscarUsuarios() {
		List<Usuario> usuarios = usuarioServico.buscarUsuarios();
		List<UsuarioDTO> usuariosDTO = usuarios.stream().map(usuario -> new UsuarioDTO(usuario)).collect(Collectors.toList());
		return ResponseEntity.ok().body(usuariosDTO);
	}

	@PreAuthorize("hasAnyRole('ADMIN')")
	@RequestMapping(value = "/paginado", method = RequestMethod.GET)
	public ResponseEntity<Page<UsuarioDTO>> buscarUsuariosPaginado(@RequestParam(value = "pagina", defaultValue = "0") Integer pagina, @RequestParam(value = "linhasPagina", defaultValue = "24") Integer linhaPagina, @RequestParam(value = "ordenacao", defaultValue = "nome") String ordenacao, @RequestParam(value = "direcao", defaultValue = "ASC") String direcao) {
		Page<Usuario> usuarios = usuarioServico.buscarUsuariosPaginado(pagina, linhaPagina, ordenacao, direcao);
		Page<UsuarioDTO> usuariosDTO = usuarios.map(usuario -> new UsuarioDTO(usuario));
		return ResponseEntity.ok().body(usuariosDTO);
	}

}
