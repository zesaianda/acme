package com.acme.utils;

import java.util.HashSet;
import java.util.Set;

import com.acme.entity.Coverage;
import com.acme.entity.Product;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class ValidatorCoverages {
	private Set<Coverage> coveragesSimulation;
	private Product product;
	private Set<Coverage> failProd = new HashSet<Coverage>();
	private Set<Coverage> failTrue = new HashSet<Coverage>();
	
	
	public ValidatorCoverages(Set<Coverage> coverages, Product product) {
		this.coveragesSimulation = coverages;
		this.product = product;
	}
	 
	
	public void validateAllCoveragesInProduct() {
		Set<Coverage> coveragesProduct = product.getCoverages();
		this.coveragesSimulation.forEach(coverage -> {
			if (!coveragesProduct.contains(coverage)) {
				this.failProd.add(coverage);
			}
		});
	}
	
	
	public void validateAllTrueCoveragesInProduct() {
		Set<Coverage> allTrueCoveragesProduct = product.getAllTrueCoverages();
		allTrueCoveragesProduct.forEach(trueCoverage -> {
			if(!this.coveragesSimulation.contains(trueCoverage))
				this.failTrue.add(trueCoverage);
		});
	}
}
