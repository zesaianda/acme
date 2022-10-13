package com.acme;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;


import com.acme.entity.Product;
import com.acme.repository.ProductRepository;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class ProductRepositoryTest {

	@Autowired
	private TestEntityManager entityManager;
	
	@Autowired
	ProductRepository productRepository;
	
	
	public void saveProductTest() {
		Product product = new Product();
		product.setName("Product 1");
		product.setAcronym("Prod 1");
		product.setSellingPeriod("Jan-Jun");
		
		productRepository.save(product);
		System.out.println("ID: " + product.getId());
		assertThat(product.getId()).isGreaterThan(0);
	}
	
	
	@Test
	public void getProductTest() {
		Product product = productRepository.findById(1L).get();
		assertThat(product.getId()).isEqualTo(1L);
	}
	
	
	@Test
	public void getListOfProductsTest() {
		List<Product> products = (List<Product>) productRepository.findAll();
		assertThat(products.size()).isGreaterThan(0);
	}
	
	
	@Test
	public void updateProductTest() {
		Product product = productRepository.findById(1L).get();
		product.setAcronym("P1");
		Product productUpdated = productRepository.save(product);
		assertThat(productUpdated.getAcronym()).isEqualTo("P1");
	}
}
