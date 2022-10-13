package com.acme;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.acme.entity.Coverage;
import com.acme.entity.Product;
import com.acme.repository.CoverageRepository;
import com.acme.repository.ProductRepository;

@Component
public class BootstrapCommandLineRunner implements CommandLineRunner {
	private CoverageRepository coverageRepository;
	private ProductRepository productRepository;

	
	@Autowired
	public BootstrapCommandLineRunner(CoverageRepository coverageRepository,
			ProductRepository productRepository) {
		this.coverageRepository = coverageRepository;
		this.productRepository = productRepository;
	}



	@Override
	public void run(String... args) throws Exception {
		Coverage cov1 = new Coverage("Death", "Death insurance", true, 45f);
		Coverage cov2 = new Coverage("Disability", "Disability insurance", false, 75f);
		Coverage cov3 = new Coverage("Total disability", "Total disability insurance", false, 25f);
		Coverage cov4 = new Coverage("Work Accidents", "Work Accidents insurance", false, 15f);
		
		coverageRepository.saveAll(List.of(cov1, cov2, cov3, cov4));
		
		Product prod1 = new Product("Product 1", "Prod1", "Jan-Jun");
		Product prod2 = new Product("Product 2", "Prod2", "Jul-Dec");
		
		Coverage savedCov1 = coverageRepository.findById((long) 1).get();
		Coverage savedCov2 = coverageRepository.findById((long) 2).get();
		Coverage savedCov3 = coverageRepository.findById((long) 3).get();
		Coverage savedCov4 = coverageRepository.findById((long) 4).get();
		
		prod1.getCoverages().addAll(List.of(savedCov1, savedCov2));
		prod2.getCoverages().addAll(List.of(savedCov1, savedCov3, savedCov4));
	
		productRepository.saveAll(List.of(prod1, prod2));
	}

}
