package com.funcionario.cadif.domain;

import java.io.Serializable;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;


@Entity
public class Funcionario implements Serializable {
	private static final long serialVersionUID = 1L;
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer funcionarioId;

	@Column(length = 50)
	private String funcionarioNome;
	
	private int funcionarioAge;
	
	@JsonFormat(pattern="dd/MM/yyyy")
	@DateTimeFormat(pattern = "dd/MM/yyyy")
	private LocalDate funcionarioBirtday;
	
	@Column(length = 50)
	private String funcionarioDocument;
	
	@ManyToOne
	@JoinColumn(name="cargo_id")
	private Cargo cargoId;
	
	@ManyToMany
	@JoinTable(name = "FUNCIONARIO_DEPARTAMENTO",
		joinColumns = @JoinColumn(name = "funcionario_id"),
		inverseJoinColumns = @JoinColumn(name = "departamento_id")
	)
	private List<Departamento> departamentos = new ArrayList<>();
	
	@ManyToOne
	@JoinColumn(name="departamento_id")
	private Departamento departamento;

	public Funcionario() {}
	
	public Funcionario(String funcionarioNome, int funcionarioAge,LocalDate funcionarioBirtday,
			String funcionarioDocument) {
		super();
		this.funcionarioNome = funcionarioNome;
		this.funcionarioAge = funcionarioAge;
		this.funcionarioBirtday = funcionarioBirtday;
		this.funcionarioDocument = funcionarioDocument;
	}

	public Integer getFuncionarioId() {
		return funcionarioId;
	}

	public void setFuncionarioId(Integer funcionarioId) {
		this.funcionarioId = funcionarioId;
	}

	public String getFuncionarioNome() {
		return funcionarioNome;
	}

	public void setFuncionarioNome(String funcionarioNome) {
		this.funcionarioNome = funcionarioNome;
	}

	public int getFuncionarioAge() {
		return funcionarioAge;
	}

	public void setFuncionarioAge(int funcionarioAge) {
		this.funcionarioAge = funcionarioAge;
	}

	public LocalDate getFuncionarioBirtday() {
		return funcionarioBirtday;
	}

	public void setFuncionarioBirtday(LocalDate funcionarioBirtday) {
		this.funcionarioBirtday = funcionarioBirtday;
	}

	public String getFuncionarioDocument() {
		return funcionarioDocument;
	}

	public void setFuncionarioDocument(String funcionarioDocument) {
		this.funcionarioDocument = funcionarioDocument;
	}

	public Cargo getCargoId() {
		return cargoId;
	}

	public void setCargoId(Cargo cargoId) {
		this.cargoId = cargoId;
	}

	public List<Departamento> getDepartamentos() {
		return departamentos;
	}

	public void setDepartamentos(List<Departamento> departamentos) {
		this.departamentos = departamentos;
	}

	public Departamento getDepartamento() {
		return departamento;
	}

	public void setDepartamento(Departamento departamento) {
		this.departamento = departamento;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cargoId == null) ? 0 : cargoId.hashCode());
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
		Funcionario other = (Funcionario) obj;
		if (cargoId == null) {
			if (other.cargoId != null)
				return false;
		} else if (!cargoId.equals(other.cargoId))
			return false;
		return true;
	}
	
}
