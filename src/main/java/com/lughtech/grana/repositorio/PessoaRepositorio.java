package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.dominio.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

}
