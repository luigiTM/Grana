package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.dominio.GastoPessoa;

@Repository
public interface GastoPessoaRepositorio extends JpaRepository<GastoPessoa, Integer> {

}