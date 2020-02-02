package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.dominio.Grana;

@Repository
public interface GranaRepositorio extends JpaRepository<Grana, Integer> {

}
