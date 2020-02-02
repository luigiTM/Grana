package com.lughtech.grana.repositorio;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.dominio.Gasto;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, Integer> {

}
