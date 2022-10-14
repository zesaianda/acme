package com.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.dto.ProspectDto;
import com.acme.service.ProspectService;

@RestController
@RequestMapping("/prospect")
public class ProspectController {
	
	@Autowired
	private ProspectService prospectService;

	
	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProspectDto> createNew(@RequestBody ProspectDto prospectDto){
		
		ProspectDto prospDto = prospectService.createNew(prospectDto);
		if (prospDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(prospDto);
		}
		return null;
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProspectDto> getProspect(@PathVariable("id") Long id){
		ProspectDto prospectDto = prospectService.getProspect(id);
		
		if (prospectDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prospectDto);
		}
		
		return null;
	}
	
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProspectDto>> getProspects(){
		List<ProspectDto> prospectsDto = prospectService.getAllProspects();
		
		if (prospectsDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(prospectsDto);
		}
		
		return null;
	}

}
