package com.acme.entity;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotBlank;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Prospect{
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@JsonFormat(pattern="yyyy-MM-dd")
	@Column(nullable = false)
	private Date birthDate;
	
	@NotBlank
	@Column(nullable = false)
	private String jobOccupation;
	
	@NotBlank
	@Column(nullable = false)
	private String email;
	
	@OneToMany(mappedBy = "prospect")
	private Set<Simulation> simulations = new HashSet<>();
}
