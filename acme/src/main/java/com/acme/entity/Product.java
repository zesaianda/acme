package com.acme.entity;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.validation.constraints.NotBlank;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false)
	private String acronym;
	
	@NotBlank
	@Column(nullable = false)
	private String sellingPeriod;
	
	@ManyToMany()
	@JoinTable(name = "product_coverage" , joinColumns = {@JoinColumn(name = "product_id")}, inverseJoinColumns = {@JoinColumn(name = "coverage_id")})
	private Set<Coverage> coverages = new HashSet<>();

	
	
	public Product(String name, String acronym, String sellingPeriod) { 
		  this.name = name; 
		  this.acronym = acronym; 
		  this.sellingPeriod = sellingPeriod; 
	}
	 
	
	
	public Set<Coverage> getAllTrueCoverages(){
		Set<Coverage> allTrue = new HashSet<>();
		
		coverages.forEach(coverage -> {
			if (coverage.getMandatory()) {
				allTrue.add(coverage);
			}
		});
		
		return allTrue;
	}
}
