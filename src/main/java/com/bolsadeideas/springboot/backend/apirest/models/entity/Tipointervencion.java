package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "tipointervencion")
public class Tipointervencion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	private String nombre_tipo;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getNombreTipo() {
		return nombre_tipo;
	}

	public void setNombreTipo(String nombreTipo) {
		this.nombre_tipo = nombreTipo;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}