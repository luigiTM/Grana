package com.lughtech.grana.repositorio;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.lughtech.grana.dominio.Grana;
import com.lughtech.grana.dominio.Usuario;

@Repository
public interface GranaRepositorio extends JpaRepository<Grana, Integer> {

	@Transactional(readOnly = true)
	Page<Grana> findByUsuario(Usuario usuario, Pageable requisicaoPaginada);

	@Transactional(readOnly = true)
	@Query("SELECT grana FROM Grana grana WHERE grana.codigoDeAcesso =:codigoDeAcesso")
	Optional<Grana> findByCodigoDeACesso(@Param("codigoDeAcesso") String codigodeAcesso);

}
