package com.lughtech.grana.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import com.lughtech.grana.domain.Grana;
import com.lughtech.grana.repositories.GranaRepository;

public class GranaService {

	@Autowired
	private GranaRepository granaRepository;

	public Grana buscarGranaPorId(Integer id) {
		Optional<Grana> obj = granaRepository.findById(id);
		return obj.orElse(null);
	}

}
