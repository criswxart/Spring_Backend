package com.bolsadeideas.springboot.backend.apirest.models.services.profesional;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.bolsadeideas.springboot.backend.apirest.models.dao.IProfesionalDao;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Profesional;

@Service
public class ProfesionalService implements IProfesionalService, UserDetailsService{
	
	private Logger logger = LoggerFactory.getLogger(ProfesionalService.class);

	@Autowired
	private IProfesionalDao profesionalDao;
	
	@Override
	@Transactional(readOnly=true)
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		Profesional profesional = profesionalDao.findByEmail(email);
		
		if(profesional == null) {
			logger.error("Error en el login: no existe el profesional con el correo '"+email+"' en el sistema!");
			throw new UsernameNotFoundException("Error en el login: no existe el profesional con el correo  '"+email+"' en el sistema!");
		}
		
		List<GrantedAuthority> authorities = profesional.getRoles()
				.stream()
				.map(role -> new SimpleGrantedAuthority(role.getNombre()))
				.peek(authority -> logger.info("Role: " + authority.getAuthority()))
				.collect(Collectors.toList());
		
		return new User(profesional.getEmail(), profesional.getPassword(), profesional.getEnabled(), true, true, true, authorities);
	}

	@Override
	@Transactional(readOnly=true)
	public Profesional findByEmail(String email) {
		return profesionalDao.findByEmail(email);
	}

	@Override
	public Profesional findByUsername(String email) {
		// TODO Auto-generated method stub
		return null;
	}

}
