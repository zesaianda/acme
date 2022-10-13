package com.acme.service;

import java.util.List;
import java.util.Set;

import com.acme.dto.CoverageDto;
import com.acme.dto.SimulationDto;

public interface SimulationService {
	Long simulationNumber = 0L;
	
	SimulationDto createNew(String email, Long productId, Set<CoverageDto> coveragesDto, Float insuredCapital);
	
	SimulationDto getSimulation(Long id);
	
	List<SimulationDto> getAllSimulationsDto();
}
