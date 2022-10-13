package com.acme.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Simulation {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Prospect prospect;
	
	@NotNull
	@ManyToOne(cascade = CascadeType.MERGE)
	private Product product;
	
	@ManyToMany()
	@JoinTable(name = "simulation_coverage" , joinColumns = {@JoinColumn(name = "simulation_id")}, 
		inverseJoinColumns = {@JoinColumn(name = "coverage_id")})
	private Set<Coverage> coverages = new HashSet<>();
	
	@Column(nullable = false)
	private Float insuredCapital;
	
	@Column(nullable = false)
	private Float monthlyPremium;
	
	@Column(nullable = false)
	private Float yearlyPremium;
	
	@Column(nullable = true)
	private Long simulationNumber;
}
