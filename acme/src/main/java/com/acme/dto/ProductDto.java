package com.acme.dto;

import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@Data
public class ProductDto {
	
	private Long id;
	private String name;
	private String acronym;
	private String sellingPeriod;
	private Set<CoverageDto> coveragesDto = new HashSet<>();
	
	public ProductDto(Long id, String name, String acronym, String sellingPeriod) {
		this.id = id;
		this.name = name;
		this.acronym = acronym;
		this.sellingPeriod = sellingPeriod;
	}
}
