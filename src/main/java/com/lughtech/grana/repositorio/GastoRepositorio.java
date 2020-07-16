package com.lughtech.grana.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Gasto;

@Repository
public interface GastoRepositorio extends JpaRepository<Gasto, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT gasto FROM Gasto gasto WHERE gasto.grana.id =:idGrana")
	List<Gasto> findByGrana(@Param("idGrana") Integer idGrana);

}
