package com.lughtech.grana.repositorio.usuario;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.transaction.Transactional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import com.lughtech.grana.dominio.Usuario;
import com.lughtech.grana.dominio.enumerados.Perfil;
import com.lughtech.grana.repositorio.UsuarioRepositorio;

@SpringBootTest
@ActiveProfiles(profiles = "test")
public class UsuarioRepositorioTest {

	@Autowired
	UsuarioRepositorio usuarioRepositorio;

	Usuario usuario;

	@BeforeEach
	private void setup() {
		usuario = new Usuario("Luigi", "luigitosin@hotmail.com", "1234567890");
		usuario.setCriadoEm(new Timestamp(System.currentTimeMillis()));
	}

	@Test
	@Transactional
	public void salvarUsuario() throws Exception {
		usuario = usuarioRepositorio.save(usuario);

		Set<Perfil> perfis = new HashSet<>();
		perfis.add(Perfil.USUARIO);

		Usuario doBanco = usuarioRepositorio.findById(usuario.getId()).get();
		assertEquals("Luigi", doBanco.getNome());
		assertEquals("luigitosin@hotmail.com", doBanco.getEmail());
		assertEquals("1234567890", "1234567890");
		assertEquals(usuario.getPerfis(), perfis);
//		assertEquals(usuario.getGranas(), new ArrayList<Grana>());
		assertNull(usuario.getUltimoAcesso());
	}

	@Test
	@Transactional
	public void atualizarUsuario() throws Exception {
		usuario = usuarioRepositorio.save(usuario);
		usuario.setNome("Luigi2");
		usuarioRepositorio.save(usuario);

		Set<Perfil> perfis = new HashSet<>();
		perfis.add(Perfil.USUARIO);

		Usuario doBanco = usuarioRepositorio.findById(usuario.getId()).get();
		assertEquals("Luigi2", doBanco.getNome());
		assertEquals("luigitosin@hotmail.com", doBanco.getEmail());
		assertEquals("1234567890", "1234567890");
		assertEquals(usuario.getPerfis(), perfis);
//		assertEquals(usuario.getGranas(), new ArrayList<Grana>());
		assertNull(usuario.getUltimoAcesso());
	}

	@Test
	@Transactional
	public void salvarUsuarioAdmin() throws Exception {
		usuario.adicionarPerfil(Perfil.ADMIN);
		usuario = usuarioRepositorio.save(usuario);

		Set<Perfil> perfis = new HashSet<>();
		perfis.add(Perfil.USUARIO);
		perfis.add(Perfil.ADMIN);

		Usuario doBanco = usuarioRepositorio.findById(usuario.getId()).get();
		assertEquals("Luigi", doBanco.getNome());
		assertEquals("luigitosin@hotmail.com", doBanco.getEmail());
		assertEquals("1234567890", "1234567890");
		assertEquals(usuario.getPerfis(), perfis);
//		assertEquals(usuario.getGranas(), new ArrayList<Grana>());
		assertNull(usuario.getUltimoAcesso());
	}
}
