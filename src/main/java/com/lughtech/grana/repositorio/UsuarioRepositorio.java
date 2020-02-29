package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Usuario;

@Repository
public interface UsuarioRepositorio extends JpaRepository<Usuario, Integer> {

	@Transactional(readOnly = true)
	Usuario findByEmail(String email);

}
