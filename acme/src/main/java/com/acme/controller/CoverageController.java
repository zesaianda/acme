package com.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.acme.dto.CoverageDto;
import com.acme.service.CoverageService;

@Controller
@RequestMapping("/coverage")
public class CoverageController {
	
	@Autowired
	private CoverageService coverageService;
	
	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<CoverageDto> createNew(@RequestBody CoverageDto coverageDto){
		CoverageDto covDto = coverageService.createNew(coverageDto);
		if (covDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(covDto);
		}
		return null;
	}
	
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoverageDto>> getAll(){
		List<CoverageDto> coverageDtos = (List<CoverageDto>) coverageService.getAll();
		
		return ResponseEntity.status(HttpStatus.OK).body(coverageDtos);
	}
}
