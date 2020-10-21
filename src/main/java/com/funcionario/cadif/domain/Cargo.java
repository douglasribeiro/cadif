package com.funcionario.cadif.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Cargo implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer cargoId;
	
	@Column(length = 50)
	private String cargoNome;
	
	public Cargo() {}

	public Cargo(String cargoNome) {
		super();
		this.cargoNome = cargoNome;
	}

	public Integer getCargoId() {
		return cargoId;
	}

	public void setCargoId(Integer cargoId) {
		this.cargoId = cargoId;
	}

	public String getCargoNome() {
		return cargoNome;
	}

	public void setCargoNome(String cargoNome) {
		this.cargoNome = cargoNome;
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
		Cargo other = (Cargo) obj;
		if (cargoId == null) {
			if (other.cargoId != null)
				return false;
		} else if (!cargoId.equals(other.cargoId))
			return false;
		return true;
	}
	
	
}
