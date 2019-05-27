package com.bolsadeideas.springboot.backend.apirest.models.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Profesional;

public interface IProfesionalDao extends CrudRepository<Profesional, Long>{
	
	public Profesional findByEmail(String email);
	
	@Query("select u from Profesional u where u.email=?1")
	public Profesional findByEmail2(String email);

}
