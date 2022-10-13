package com.acme.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CoverageDto {
	
	private Long id;
	
	private String name;
	
	private String description;
	
	private Boolean mandatory;
	
	private Float insuredPercentage;

	public CoverageDto(String name, String description, Boolean mandatory, Float insuredPercentage) {
		this.name = name;
		this.description = description;
		this.mandatory = mandatory;
		this.insuredPercentage = insuredPercentage;
	}
}
