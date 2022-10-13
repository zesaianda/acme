package com.acme.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.Digits;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@Data
public class Coverage {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	
	@NotBlank
	@Column(nullable = false)
	private String name;
	
	@NotBlank
	@Column(nullable = false)
	private String description;
	
	@NotNull
	@Column(nullable = false)
	private Boolean mandatory;
	
	@Digits(integer = 10 /*precision*/, fraction = 2 /*scale*/)
	@Column(nullable = false)
	private Float insuredPercentage;

	public Coverage(String name, String description, Boolean mandatory, Float insuredPercentage) {
		this.name = name;
		this.description = description;
		this.mandatory = mandatory;
		this.insuredPercentage = insuredPercentage;
	}
}
