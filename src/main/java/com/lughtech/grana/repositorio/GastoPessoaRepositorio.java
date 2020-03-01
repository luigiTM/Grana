package com.lughtech.grana.repositorio;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.GastoPessoa;

@Repository
public interface GastoPessoaRepositorio extends JpaRepository<GastoPessoa, Integer> {

	@Transactional(readOnly = true)
	@Query("SELECT DISTINCT gastoPessoa FROM GastoPessoa gastoPessoa WHERE gastoPessoa.gasto.id =:idGasto")
	List<GastoPessoa> buscarGastoPessoaPorIdGasto(@Param("idGasto") Integer idGasto);
	
	@Transactional(readOnly = true)
	@Query("SELECT gastoPessoa FROM GastoPessoa gastoPessoa WHERE gastoPessoa.gasto.id =:idGasto and gastoPessoa.pessoa.id =:idPessoa")
	List<GastoPessoa> buscarQantidadeGastoPessoa(@Param("idGasto") Integer idGasto, @Param("idPessoa") Integer idPessoa);

}
