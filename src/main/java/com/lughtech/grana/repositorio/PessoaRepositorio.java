package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

	@Transactional(readOnly = true)
	Pessoa findByEmail(String email);

}
