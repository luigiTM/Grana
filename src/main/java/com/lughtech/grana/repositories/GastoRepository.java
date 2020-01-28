package com.lughtech.grana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.domain.Gasto;

@Repository
public interface GastoRepository extends JpaRepository<Gasto, Integer> {

}
