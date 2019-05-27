package com.bolsadeideas.springboot.backend.apirest.controllers;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.Valid;

//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.bolsadeideas.springboot.backend.apirest.models.entity.Usuario;
import com.bolsadeideas.springboot.backend.apirest.models.entity.Plan;
import com.bolsadeideas.springboot.backend.apirest.models.services.upload.IUploadFileService;
import com.bolsadeideas.springboot.backend.apirest.models.services.usuario.IUsuarioService;

@CrossOrigin(origins = { "http://localhost:4200" })
@RestController
@RequestMapping("/api")
public class UsuarioRestController {

	@Autowired
	private IUsuarioService usuarioService;
	
	@Autowired
	private IUploadFileService uploadService;
	
	// private final Logger log = LoggerFactory.getLogger(ClienteRestController.class);

	@GetMapping("/usuarios")
	public List<Usuario> index() {
		return usuarioService.findAll();
	}
	
	@GetMapping("/usuarios/page/{page}")
	public Page<Usuario> index(@PathVariable Integer page) {
		Pageable pageable = PageRequest.of(page, 4);
		return usuarioService.findAll(pageable);
	}
	
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	@GetMapping("/usuarios/{id}")
	public ResponseEntity<?> show(@PathVariable Long id) {
		
		Usuario usuario = null;
		Map<String, Object> response = new HashMap<>();
		
		try {
			usuario = usuarioService.findById(id);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar la consulta en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		if(usuario == null) {
			response.put("mensaje", "El usuario ID: ".concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}
		
		return new ResponseEntity<Usuario>(usuario, HttpStatus.OK);
	}
	
	@Secured("ROLE_ADMIN")
	@PostMapping("/usuarios")
	public ResponseEntity<?> create(@Valid @RequestBody Usuario usuario, BindingResult result) {
		
		Usuario usuarioNew = null;
		Map<String, Object> response = new HashMap<>();
		
		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		try {
			usuarioNew = usuarioService.save(usuario);
		} catch(DataAccessException e) {
			response.put("mensaje", "Error al realizar el insert en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		
		response.put("mensaje", "El usuario ha sido creado con éxito!");
		response.put("usuario", usuarioNew);
		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
	@Secured("ROLE_ADMIN")
	@PutMapping("/usuarios/{id}")
	public ResponseEntity<?> update(@Valid @RequestBody Usuario usuario, BindingResult result, @PathVariable Long id) {

		Usuario usuarioActual = usuarioService.findById(id);

		Usuario usuarioUpdated = null;

		Map<String, Object> response = new HashMap<>();

		if(result.hasErrors()) {

			List<String> errors = result.getFieldErrors()
					.stream()
					.map(err -> "El campo '" + err.getField() +"' "+ err.getDefaultMessage())
					.collect(Collectors.toList());
			
			response.put("errors", errors);
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.BAD_REQUEST);
		}
		
		if (usuarioActual == null) {
			response.put("mensaje", "Error: no se pudo editar, el cliente ID: "
					.concat(id.toString().concat(" no existe en la base de datos!")));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.NOT_FOUND);
		}

		try {
			
			usuarioActual.setNombre(usuario.getNombre());
			usuarioActual.setApellidos(usuario.getApellidos());
			usuarioActual.setDireccion(usuario.getDireccion());
			usuarioActual.setTelefono(usuario.getTelefono());
			usuarioActual.setFechaNac(usuario.getFechaNac());
			usuarioActual.setFechaIngreso(usuario.getFechaIngreso());
			usuarioActual.setSexo(usuario.getSexo());

			usuarioUpdated = usuarioService.save(usuarioActual);

		} catch (DataAccessException e) {
			response.put("mensaje", "Error al actualizar el usuario en la base de datos");
			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}

		response.put("mensaje", "El usuario ha sido actualizado con éxito!");
		response.put("usuario", usuarioUpdated);

		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
	}
	
//	@Secured("ROLE_ADMIN")
//	@DeleteMapping("/clientes/{id}")
//	public ResponseEntity<?> delete(@PathVariable Long id) {
//		
//		Map<String, Object> response = new HashMap<>();
//		
//		try {
//			Usuario cliente = profesionalService.findById(id);
//			String nombreFotoAnterior = cliente.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//			
//		    profesionalService.delete(id);
//		} catch (DataAccessException e) {
//			response.put("mensaje", "Error al eliminar el cliente de la base de datos");
//			response.put("error", e.getMessage().concat(": ").concat(e.getMostSpecificCause().getMessage()));
//			return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//		}
//		
//		response.put("mensaje", "El cliente eliminado con éxito!");
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.OK);
//	}
	
//	@Secured({"ROLE_ADMIN", "ROLE_USER"})
//	@PostMapping("/clientes/upload")
//	public ResponseEntity<?> upload(@RequestParam("archivo") MultipartFile archivo, @RequestParam("id") Long id){
//		Map<String, Object> response = new HashMap<>();
//		
//		Usuario usuario = profesionalService.findById(id);
//		
//		if(!archivo.isEmpty()) {
//
//			String nombreArchivo = null;
//			try {
//				nombreArchivo = uploadService.copiar(archivo);
//			} catch (IOException e) {
//				response.put("mensaje", "Error al subir la imagen del cliente");
//				response.put("error", e.getMessage().concat(": ").concat(e.getCause().getMessage()));
//				return new ResponseEntity<Map<String, Object>>(response, HttpStatus.INTERNAL_SERVER_ERROR);
//			}
//			
//			String nombreFotoAnterior = usuario.getFoto();
//			
//			uploadService.eliminar(nombreFotoAnterior);
//						
//			usuario.setFoto(nombreArchivo);
//			
//			profesionalService.save(usuario);
//			
//			response.put("cliente", usuario);
//			response.put("mensaje", "Has subido correctamente la imagen: " + nombreArchivo);
//			
//		}
//		
//		return new ResponseEntity<Map<String, Object>>(response, HttpStatus.CREATED);
//	}
//	
//	@GetMapping("/uploads/img/{nombreFoto:.+}")
//	public ResponseEntity<Resource> verFoto(@PathVariable String nombreFoto){
//
//		Resource recurso = null;
//		
//		try {
//			recurso = uploadService.cargar(nombreFoto);
//		} catch (MalformedURLException e) {
//			e.printStackTrace();
//		}
//		
//		HttpHeaders cabecera = new HttpHeaders();
//		cabecera.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + recurso.getFilename() + "\"");
//		
//		return new ResponseEntity<Resource>(recurso, cabecera, HttpStatus.OK);
//	}
//	
//	@Secured("ROLE_ADMIN")
//	@GetMapping("/clientes/regiones")
//	public List<Region> listarRegiones(){
//		return profesionalService.findAllRegiones();
//	}
}
