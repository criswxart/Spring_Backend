package com.bolsadeideas.springboot.backend.apirest.models.services.usuario;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Plan;

public interface IUsuarioService {

	public List<Usuario> findAll();
	
	public Page<Usuario> findAll(Pageable pageable);
	
	public Usuario findById(Long id);
	
	public Usuario save(Usuario cliente);
	
	public void delete(Long id);
	
	public List<Plan> findAllPlanes();

}
