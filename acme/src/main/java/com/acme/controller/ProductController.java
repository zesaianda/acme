package com.acme.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.acme.dto.CoverageDto;
import com.acme.dto.ProductDto;
import com.acme.service.ProductService;

@RestController
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private ProductService productService;
			
	
	@PostMapping(value = "/new", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> createNew(@RequestBody ProductDto productDto){
		ProductDto prodDto = productService.createNew(productDto);
		if (prodDto != null) {
			return ResponseEntity.status(HttpStatus.CREATED).body(prodDto);
		}
		return null;
	}
	
	
	@GetMapping(value = "/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<ProductDto> getProduct(@PathVariable("id") Long id){
		ProductDto productDto = productService.getProduct(id);
		
		if (productDto != null) {
			return ResponseEntity.status(HttpStatus.OK).body(productDto);
		}
		
		return null;
	}
	
	
	@GetMapping(value = "/all", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<ProductDto>> getProducts(){
		List<ProductDto> productsDto = productService.getAllProducts();
		
		if (productsDto != null && !productsDto.isEmpty()) {
			return ResponseEntity.status(HttpStatus.OK).body(productsDto);
		}
		
		return null;
	}
	
	
	@GetMapping(value = "/{id}/coverages", produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<List<CoverageDto>> getCoveragesOfProduct(@PathVariable("id") Long productId){
		List<CoverageDto> allCoveragesOfProduct = productService.getAllCoveragesOfProduct(productId);
		return ResponseEntity.status(HttpStatus.OK).body(allCoveragesOfProduct);
	}
	
	
	
	@PutMapping(value = "/{prodId}/{covId}")
	public ResponseEntity<ProductDto> updateProduct(
				@PathVariable("prodId") Long prodId, 
				@PathVariable("covId") Long covId) {
		
		ProductDto updatedProductDto = productService.updateProduct(prodId, covId);
		
		return ResponseEntity.status(HttpStatus.NO_CONTENT).body(updatedProductDto);
	}
}
