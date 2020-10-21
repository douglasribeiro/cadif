package com.funcionario.cadif.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Transactional;

import com.funcionario.cadif.domain.Departamento;

public interface DepartamentoRepository extends JpaRepository<Departamento, Integer> {

	@Transactional(readOnly=true)
	Departamento findByDepartamentoNome(String nome);
	
}
