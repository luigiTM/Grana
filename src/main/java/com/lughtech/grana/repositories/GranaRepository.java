package com.lughtech.grana.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.lughtech.grana.domain.Grana;

@Repository
public interface GranaRepository extends JpaRepository<Grana, Integer> {

}
