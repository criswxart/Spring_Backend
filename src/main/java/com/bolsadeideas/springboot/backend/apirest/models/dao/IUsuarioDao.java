package com.bolsadeideas.springboot.backend.apirest.models.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Plan;

public interface IUsuarioDao extends JpaRepository<Usuario, Long>{

	@Query("from Plan")
	public List<Plan> findAllPlanes();
}
