package com.lughtech.grana.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Gasto;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, Integer> {

	@Transactional(readOnly = true)
	List<Gasto> findByGrana(Integer idGrana);

}
