package com.funcionario.cadif.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.funcionario.cadif.domain.Funcionario;
import com.funcionario.cadif.service.FuncionarioService;

@RestController
@RequestMapping(value="/funcionario")
public class FuncionarioResource {
	
	@Autowired
	private FuncionarioService funcionarioService;

	@RequestMapping(method=RequestMethod.GET)
	public ResponseEntity<List<Funcionario>> findAll() {
		List<Funcionario> list = funcionarioService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@RequestMapping(value="/depto")
	public ResponseEntity<List<Funcionario>> find(@RequestParam(value="depto") String depto) {
		List<Funcionario> obj = funcionarioService.findFuncDepto(Integer.parseInt(depto));
		return ResponseEntity.ok().body(obj);
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.GET)
	public ResponseEntity<Funcionario> find(@PathVariable Integer id) {
		Funcionario obj = funcionarioService.find(id);
		return ResponseEntity.ok().body(obj);
	}
	
	
	@RequestMapping(method=RequestMethod.POST)
	public ResponseEntity<Void> insert(@Valid @RequestBody Funcionario obj) {
		obj = funcionarioService.insert(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
				.path("/{id}").buildAndExpand(obj.getFuncionarioId()).toUri();
		return ResponseEntity.created(uri).build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.PUT)
	public ResponseEntity<Void> update(@Valid @RequestBody Funcionario funcionario, @PathVariable Integer id) {
		funcionario.setFuncionarioId(id);
		funcionario = funcionarioService.update(funcionario);
		return ResponseEntity.noContent().build();
	}
	
	@RequestMapping(value="/{id}", method=RequestMethod.DELETE)
	public ResponseEntity<Void> delete(@PathVariable int id) {
		funcionarioService.delete(id);
		return ResponseEntity.noContent().build();
	}
	
}
