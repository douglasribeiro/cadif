package com.funcionario.cadif.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import com.funcionario.cadif.domain.Departamento;
import com.funcionario.cadif.domain.Funcionario;

public interface FuncionarioRepository extends JpaRepository<Funcionario, Integer> {

	@Transactional(readOnly=true)
	Funcionario findByFuncionarioNome(String nome);
	
	@Transactional(readOnly=true)
	List<Funcionario> findByDepartamento(int departamento);
	
	@Transactional(readOnly=true)
	@Query("FROM Funcionario WHERE departamento = ?1")
    public List<Funcionario> findByDepto(Departamento departamento);
	
}
