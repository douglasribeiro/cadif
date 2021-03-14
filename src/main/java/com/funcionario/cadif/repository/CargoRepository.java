package com.funcionario.cadif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.funcionario.cadif.domain.Cargo;

public interface CargoRepository extends JpaRepository<Cargo, Integer> {

	@Transactional(readOnly=true)
	Cargo findByCargoNome(String nome);
}
