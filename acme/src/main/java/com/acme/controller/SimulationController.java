package com.acme.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.dto.SimulationDto;
import com.acme.service.SimulationService;

@RestController
@RequestMapping("/simulation")
public class SimulationController {

	@Autowired
	private SimulationService simulationService;
	
	
	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<SimulationDto> createNew(@RequestBody SimulationDto simulationDto){
		
		SimulationDto dto = simulationService.createNew(
				simulationDto.getProspectDto().getEmail(),simulationDto.getProductDto().getId(),
				simulationDto.getCoveragesDto(), simulationDto.getInsuredCapital());
		
		if (dto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(dto);
		}
		return null;
	}	
}
