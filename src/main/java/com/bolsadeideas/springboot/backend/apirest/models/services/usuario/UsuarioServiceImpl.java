package com.bolsadeideas.springboot.backend.apirest.models.services.usuario;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IUsuarioDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Plan;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;

@Service
public class UsuarioServiceImpl implements IUsuarioService {

	@Autowired
	private IUsuarioDao usuarioDao;

	@Override
	@Transactional(readOnly = true)
	public List<Usuario> findAll() {
		return (List<Usuario>) usuarioDao.findAll();
	}

	@Override
	@Transactional(readOnly = true)
	public Page<Usuario> findAll(Pageable pageable) {
		return usuarioDao.findAll(pageable);
	}
	
	@Override
	@Transactional(readOnly = true)
	public Usuario findById(Long id) {
		return usuarioDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public Usuario save(Usuario cliente) {
		return usuarioDao.save(cliente);
	}

	@Override
	@Transactional
	public void delete(Long id) {
		usuarioDao.deleteById(id);
	}

	@Override
	@Transactional(readOnly = true)
	public List<Plan> findAllPlanes() {
		return usuarioDao.findAllPlanes();
	}

}
