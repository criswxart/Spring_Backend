package com.bolsadeideas.springboot.backend.apirest.models.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "intervenciones")
public class Intervencion implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 12, message = "el tamaño tiene que estar entre 4 y 12")
	@Column(nullable = false)
	private String objetivo;

	@NotEmpty(message = "no puede estar vacio")
	@Size(min = 4, max = 200, message = "el tamaño tiene que estar entre 4 y 200")
	private String descripcion;

	@NotNull(message = "no puede estar vacio")
	@Column(name = "fecha_intervencion")
	@Temporal(TemporalType.DATE)
	private Date fechaIntervencion;

	@NotNull(message = "la región no puede ser vacia")
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Plan plan;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "tipointervencion_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Tipointervencion tipointervencion;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "profesional_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Profesional profesional;

	public Profesional getProfesional() {
		return profesional;
	}

	public void setProfesional(Profesional profesional) {
		this.profesional = profesional;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getObjetivo() {
		return objetivo;
	}

	public void setObjetivo(String objetivo) {
		this.objetivo = objetivo;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Date getFechaIntervencion() {
		return fechaIntervencion;
	}

	public void setFechaIntervencion(Date fechaIntervencion) {
		this.fechaIntervencion = fechaIntervencion;
	}

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public Tipointervencion getTipointervencion() {
		return tipointervencion;
	}

	public void setTipointervencion(Tipointervencion tipointervencion) {
		this.tipointervencion = tipointervencion;
	}

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
}
