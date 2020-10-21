package com.funcionario.cadif.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.funcionario.cadif.domain.Departamento;
import com.funcionario.cadif.domain.Funcionario;
import com.funcionario.cadif.exception.DataIntegrityException;
import com.funcionario.cadif.exception.ObjectNotFoundException;
import com.funcionario.cadif.repository.FuncionarioRepository;

@Service
public class FuncionarioService {

	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Transactional
	public Funcionario insert(Funcionario funcionario) {
		funcionario.setFuncionarioId(null);
		funcionario = funcionarioRepository.save(funcionario);
		return funcionario;
	}
	
	public Funcionario find(int obj) {
		Optional<Funcionario> newFunc = funcionarioRepository.findById(obj);
		return newFunc.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + obj + ", Tipo: " + Funcionario.class.getName()));
	}
	
	private void updateData(Funcionario newObj, Funcionario obj) {
		newObj.setFuncionarioNome(obj.getFuncionarioNome() == null ? newObj.getFuncionarioNome() : obj.getFuncionarioNome());
		newObj.setFuncionarioDocument(obj.getFuncionarioDocument() == null ? newObj.getFuncionarioDocument() : obj.getFuncionarioDocument());
		newObj.setFuncionarioBirtday(obj.getFuncionarioBirtday() == null ? newObj.getFuncionarioBirtday() : obj.getFuncionarioBirtday());
		newObj.setFuncionarioAge(obj.getFuncionarioAge() == 0 ? newObj.getFuncionarioAge() : obj.getFuncionarioAge());
		newObj.setCargoId(obj.getCargoId() == null ? newObj.getCargoId() : obj.getCargoId());
		newObj.setDepartamento(obj.getDepartamento() == null ? newObj.getDepartamento() : obj.getDepartamento());
		if(obj.getDepartamento() != null) {
			List<Departamento> departamentos = newObj.getDepartamentos();
			departamentos.add(obj.getDepartamento());
		}
	}
	
	public Funcionario update(Funcionario obj) {
		Funcionario newObj = find(obj.getFuncionarioId());
		updateData(newObj, obj);
		return funcionarioRepository.save(newObj);
	}
	
	public void delete(int id) {
		find(id);
		try {
			funcionarioRepository.deleteById(id);
		}
		catch (DataIntegrityViolationException e) {
			throw new DataIntegrityException("Não é possível excluir o funcionario.");
		}
	}
	
	public List<Funcionario> findAll() {
		return funcionarioRepository.findAll();
	}
	
	public List<Funcionario> findFuncDepto(int depto){
		Departamento departamento = new Departamento();
		departamento.setDepartamentoId(depto);
		List<Funcionario> result = funcionarioRepository.findByDepto(departamento); 
		return result;
	}

	
}
