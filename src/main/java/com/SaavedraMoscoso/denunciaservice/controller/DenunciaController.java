package com.SaavedraMoscoso.denunciaservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.SaavedraMoscoso.denunciaservice.converter.DenunciaConverter;
import com.SaavedraMoscoso.denunciaservice.dto.DenunciaDTO;
import com.SaavedraMoscoso.denunciaservice.entity.Denuncia;
import com.SaavedraMoscoso.denunciaservice.service.DenunciaService;

@RestController
@RequestMapping("/denuncias/")

public class DenunciaController {

	@Autowired
	private DenunciaService service;
	
	@Autowired
	private DenunciaConverter converter;
	
	@GetMapping()
	public ResponseEntity<List<DenunciaDTO>> findAll(
			@RequestParam(value = "dni", required = false, defaultValue = "") String dni,
			@RequestParam(value = "offset", required = false, defaultValue = "0") int pageNumber,
			@RequestParam(value = "limit", required = false, defaultValue = "5") int pageSize
			) {
		
		Pageable page = PageRequest.of(pageNumber, pageSize);
		List<Denuncia> denuncias;

		if (dni == null) {
			denuncias = service.findAll(page);

		} else {
			denuncias = service.findByDni(dni, page);

		}

		if (denuncias.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		List<DenunciaDTO> denunciasDTO = converter.fromEntity(denuncias);
        return ResponseEntity.ok(denunciasDTO);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<DenunciaDTO> findById(@PathVariable("id") int id) {
		Denuncia denuncia = service.findById(id);
		if (denuncia == null) {
			return ResponseEntity.notFound().build();

		}
		DenunciaDTO denunciasDTO = converter.fromEntity(denuncia);
        return ResponseEntity.ok(denunciasDTO);
	}
	
	@GetMapping(value = "/usuario/{dni}")
	public ResponseEntity<DenunciaDTO> findByDni(@PathVariable("dni") String dni) {
		Denuncia denuncia = service.findByDni(dni);
		if (denuncia == null) {
			return ResponseEntity.notFound().build();

		}
		DenunciaDTO denunciasDTO = converter.fromEntity(denuncia);
        return ResponseEntity.ok(denunciasDTO);
	}
	
	@PostMapping()
	public ResponseEntity<DenunciaDTO> save(@RequestBody DenunciaDTO denunciasDTO) {

		Denuncia registro = service.save(converter.fromDTO(denunciasDTO));
		DenunciaDTO registroDTO=converter.fromEntity(registro);
		return ResponseEntity.status(HttpStatus.CREATED).body(registroDTO);
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<DenunciaDTO> update(@PathVariable("id") int id, 
											@RequestBody DenunciaDTO denunciaDTO) {

		Denuncia registro = service.update(converter.fromDTO(denunciaDTO));
		if (registro == null) {
			return ResponseEntity.notFound().build();
		}
		DenunciaDTO registroDTO = converter.fromEntity(registro);
		return ResponseEntity.ok(registroDTO);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<DenunciaDTO> delete(@PathVariable("id") int id) {
		service.delete(id);
		return ResponseEntity.ok(null);
	}
	
}
