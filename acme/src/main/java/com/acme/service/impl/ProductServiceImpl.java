package com.acme.service.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.dto.CoverageDto;
import com.acme.dto.ProductDto;
import com.acme.entity.Coverage;
import com.acme.entity.Product;
import com.acme.exception.CoverageNotFoundException;
import com.acme.exception.ProductAlreadyExistsException;
import com.acme.exception.ProductNotFoundException;
import com.acme.repository.CoverageRepository;
import com.acme.repository.ProductRepository;
import com.acme.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	@Override
	public ProductDto createNew(ProductDto prodDto) {
		Product productDB = productRepository.findByName(prodDto.getName());
		if (productDB == null) {
			Product prod = productRepository.save(modelMapper.map(prodDto, Product.class));
			return modelMapper.map(prod, ProductDto.class);	
		}
		else {
			throw new ProductAlreadyExistsException("There is already a product with the name: " + prodDto.getName());
		}
	}


	@Override
	public ProductDto getProduct(Long id) {
		// TODO Auto-generated method stub
		Product productDB = productRepository.findById(id).get();
		
		if (productDB != null) {
			ProductDto productDto = modelMapper.map(productDB, ProductDto.class);
			Set<Coverage> coverages = productDB.getCoverages();
			Set<CoverageDto> coveragesDto = new HashSet<>();
			coverages.forEach(coverage -> coveragesDto.add(modelMapper.map(coverage, CoverageDto.class)));
					
			productDto.setCoveragesDto(coveragesDto);
			
			return productDto;
		} 
		else
			throw new ProductNotFoundException("There is no product with ID: " + id);
	}


	@Override
	public List<ProductDto> getAllProducts() {
		// TODO Auto-generated method stub
		List<Product> products = (List<Product>) productRepository.findAll();
		List<ProductDto> productsDto = new ArrayList<>();
		products.forEach(product -> {
			ProductDto productDto = modelMapper.map(product, ProductDto.class);
			Set<Coverage> coverages = product.getCoverages();
			Set<CoverageDto> coveragesDto = new HashSet<>();
			coverages.forEach(coverage -> {
				CoverageDto coverageDto = modelMapper.map(coverage, CoverageDto.class);
				coveragesDto.add(coverageDto);
			});
			productDto.setCoveragesDto(coveragesDto);
			
			productsDto.add(productDto);
		});
		
		return productsDto;
	}


	@Override
	public ProductDto updateProduct(Long prodId, Long convId) {
		// TODO Auto-generated method stub
		Product productDB = productRepository.findById(prodId).get();
		Coverage coverageDB = coverageRepository.findById(convId).get();
		if (productDB != null) {
			if(coverageDB != null) {
				Set<Coverage> coverages = productDB.getCoverages();
				if (!coverages.contains(coverageDB)) {
					coverages.add(coverageDB);
					productDB.setCoverages(coverages);
					Product updatedProduct = productRepository.save(productDB);
					ProductDto prodDto = modelMapper.map(updatedProduct, ProductDto.class);
					
					Set<CoverageDto> coveragesDto = new HashSet<>();
					coverages.forEach(coverage -> coveragesDto.add(modelMapper.map(coverage, CoverageDto.class)));
					
					prodDto.setCoveragesDto(coveragesDto);
					
					return prodDto;
				}
			}
			else
				throw new CoverageNotFoundException("There is no coverage with ID: " + convId);
		}
		throw new ProductNotFoundException("There is no product with ID: " + prodId);
	}


	@Override
	public List<CoverageDto> getAllCoveragesOfProduct(Long productId) {
		// TODO Auto-generated method stub
		Product product = productRepository.findById(productId).get();
		if (product != null) {
			List<CoverageDto> coveragesDto = new ArrayList<>();
			product.getCoverages().forEach(coverage -> {
				coveragesDto.add(modelMapper.map(coverage, CoverageDto.class));
			});
			return coveragesDto;
		} 
		else {
			throw new ProductNotFoundException("There is no product with ID: " + productId);
		}
	}
}
