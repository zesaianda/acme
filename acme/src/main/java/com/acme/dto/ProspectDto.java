package com.acme.dto;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class ProspectDto {
	private Long id;
	
	private Date birthDate;
	
	private String jobOccupation;
	
	private String email;
	
	private Set<SimulationDto> simulationsDto = new HashSet<>();
	
	public ProspectDto(Long id, Date birthDate, String jobOccupation, String email) {
		this.id = id;
		this.birthDate = birthDate;
		this.jobOccupation = jobOccupation;
		this.email = email;
	}	
}
