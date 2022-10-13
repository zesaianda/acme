package com.acme.service;

import java.util.List;

import com.acme.dto.CoverageDto;
import com.acme.dto.ProductDto;

public interface ProductService {

	ProductDto createNew(ProductDto prodDto);

	ProductDto getProduct(Long id);
	
	List<ProductDto> getAllProducts();
	
	ProductDto updateProduct(Long prodId, Long convId);
	
	List<CoverageDto> getAllCoveragesOfProduct(Long productId);
}
