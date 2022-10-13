package com.acme.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class SimulationDto {
	
	private Long id;
	
	private ProspectDto prospectDto;
	
	private ProductDto productDto;
	
	private Set<CoverageDto> coveragesDto = new HashSet<>();
	
	private Float insuredCapital;
	
	private Float monthlyPremium;
	
	private Float yearlyPremium;
	
	private Long simulationNumber;
}
