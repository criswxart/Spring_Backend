package com.bolsadeideas.springboot.backend.apirest.models.services.profesional;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Profesional;

public interface IProfesionalService {

	public Profesional findByUsername(String email);

	Profesional findByEmail(String email);
}
