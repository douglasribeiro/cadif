package com.funcionario.cadif.config;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.ContextRefreshedEvent;

import com.funcionario.cadif.domain.Cargo;
import com.funcionario.cadif.domain.Departamento;
import com.funcionario.cadif.domain.Funcionario;
import com.funcionario.cadif.repository.CargoRepository;
import com.funcionario.cadif.repository.DepartamentoRepository;
import com.funcionario.cadif.repository.FuncionarioRepository;
import com.funcionario.cadif.service.FuncionarioService;

@Configuration
public class CargaBanco implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	CargoRepository cargoRepository;
	
	@Autowired
	DepartamentoRepository departamentoRepository;
	
	@Autowired
	FuncionarioRepository funcionarioRepository;
	
	@Autowired
	FuncionarioService funcionarioService;
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		Cargo analista = new Cargo("Analista");
		Cargo desenvolvedor = new Cargo("Desenvolvedor");
		Cargo tester = new Cargo("Tester");
		
		cargoIfNotFound(analista);
		cargoIfNotFound(desenvolvedor);
		cargoIfNotFound(tester);
		
		Departamento analise = new Departamento("Analise");
		Departamento dev = new Departamento("Desenvolvimento");
		Departamento teste = new Departamento("Teste");
		
		departamentoIfNotFound(analise);
		departamentoIfNotFound(dev);
		departamentoIfNotFound(teste);
		
		LocalDate birthday = LocalDate.of(1995, Month.JUNE, 13);
		Funcionario joao = new Funcionario("João Silveira", 25, birthday, "123.456.78-90");
		birthday = LocalDate.of(1998, Month.OCTOBER, 19);
		Funcionario ana = new Funcionario("Ana Jorge", 22, birthday, "121.212.12-12");
		birthday = LocalDate.of(1990, Month.MARCH, 25);
		Funcionario joares = new Funcionario("Joares Fraga", 30, birthday, "232.323.23-23");
		
		funcionarioIfNotFound(joao);
		funcionarioIfNotFound(ana);
		funcionarioIfNotFound(joares);
		
		List<Departamento> depJoares = new ArrayList<Departamento>();
		depJoares.add(analise);
		depJoares.add(dev);
		depJoares.add(teste);
		
		joares.setCargoId(analista);
		joares.setDepartamentos(depJoares);
		joares.setDepartamento(dev);
		funcionarioRepository.save(joares);
		
		List<Departamento> depJoao = new ArrayList<Departamento>();
		depJoao.add(dev);
		depJoao.add(teste);
		
		joao.setCargoId(desenvolvedor);
		joao.setDepartamentos(depJoao);
		joao.setDepartamento(teste);
		funcionarioRepository.save(joao);
		
		List<Departamento> depAna = new ArrayList<Departamento>();
		depAna.add(teste);
		
		ana.setCargoId(tester);
		ana.setDepartamentos(depAna);
		ana.setDepartamento(teste);
		funcionarioRepository.save(ana);
		
		analise.setChefe(joares);
		dev.setChefe(joares);
		teste.setChefe(joao);
		
		departamentoRepository.save(analise);
		departamentoRepository.save(dev);
		departamentoRepository.save(teste);
		
	}
	
	private Cargo cargoIfNotFound(final Cargo cargo) {
		Optional<Cargo> obj = Optional.ofNullable(cargoRepository.findByCargoNome(cargo.getCargoNome()));
		if(obj.isPresent()) {
			return obj.get();
		}
		return cargoRepository.save(cargo);
	}
	
	private Departamento departamentoIfNotFound(final Departamento departamento) {
		Optional<Departamento> obj = Optional.ofNullable(departamentoRepository.findByDepartamentoNome(departamento.getDepartamentoNome()));
		if(obj.isPresent()) {
			return obj.get();
		}
		return departamentoRepository.save(departamento);
	}
	
	private Funcionario funcionarioIfNotFound(final Funcionario funcionario) {
		Optional<Funcionario> obj = Optional.ofNullable(funcionarioRepository.findByFuncionarioNome(funcionario.getFuncionarioNome()));
		if(obj.isPresent()) {
			return obj.get();
		}
		return funcionarioRepository.save(funcionario);
	}
	
}