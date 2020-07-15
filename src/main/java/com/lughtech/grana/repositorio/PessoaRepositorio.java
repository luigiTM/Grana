package com.lughtech.grana.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Pessoa;

@Repository
public interface PessoaRepositorio extends JpaRepository<Pessoa, Integer> {

	@Transactional(readOnly = true)
	Pessoa findByEmail(String email);
	
//	@Transactional(readOnly = true)
//	@Query("SELECT pessoa FROM Pessoa pessoa JOIN Gasto gasto ON WHERE pessoa.id =:idGrana")
//	List<Pessoa> findByGrana(Integer idGrana);

}
