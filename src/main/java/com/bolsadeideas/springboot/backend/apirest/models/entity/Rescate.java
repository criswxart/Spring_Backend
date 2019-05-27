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
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "rescates")
public class Rescate implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(unique = true, length = 20)
	private String tipo_rescate;

	@NotNull(message = "no puede estar vacio")
	@Column(name = "fecha")
	@Temporal(TemporalType.DATE)
	private Date fecha;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "plan_id")
	@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler" })
	private Plan plan;

	public Plan getPlan() {
		return plan;
	}

	public void setPlan(Plan plan) {
		this.plan = plan;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTipo_rescate() {
		return tipo_rescate;
	}

	public void setTipo_rescate(String tipo_rescate) {
		this.tipo_rescate = tipo_rescate;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFechaInicio(Date fecha) {
		this.fecha = fecha;
	}

	private static final long serialVersionUID = 1L;
}