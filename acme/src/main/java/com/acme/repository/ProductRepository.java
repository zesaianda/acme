package com.acme.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.acme.entity.Product;

@Repository
public interface ProductRepository extends CrudRepository<Product, Long> {

	Product findByName(String name);

}
