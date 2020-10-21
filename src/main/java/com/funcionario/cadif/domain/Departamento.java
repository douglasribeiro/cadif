package com.funcionario.cadif.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Departamento implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int departamentoId;
	
	@Column(length = 50)
	private String departamentoNome;
	
	@JsonIgnore
	@ManyToOne
	@JoinColumn(name="funcionario_chefe")
	private Funcionario chefe;
	
	public Departamento() {}
	
	public Departamento(String departamentoNome) {
		super();
		this.departamentoNome = departamentoNome;
	}

	public int getDepartamentoId() {
		return departamentoId;
	}

	public void setDepartamentoId(int departamentoId) {
		this.departamentoId = departamentoId;
	}

	public String getDepartamentoNome() {
		return departamentoNome;
	}

	public void setDepartamentoNome(String departamentoNome) {
		this.departamentoNome = departamentoNome;
	}

	public Funcionario getChefe() {
		return chefe;
	}

	public void setChefe(Funcionario chefe) {
		this.chefe = chefe;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + departamentoId;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Departamento other = (Departamento) obj;
		if (departamentoId != other.departamentoId)
			return false;
		return true;
	}

	
	
	
}
