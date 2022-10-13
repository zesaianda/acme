package com.acme.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashSet;
import java.util.List;
import java.util.Properties;
import java.util.Set;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.component.ExternalSystemFactory;
import com.acme.dto.CoverageDto;
import com.acme.dto.ProductDto;
import com.acme.dto.ProspectDto;
import com.acme.dto.SimulationDto;
import com.acme.entity.Coverage;
import com.acme.entity.Product;
import com.acme.entity.Prospect;
import com.acme.entity.Simulation;
import com.acme.exception.ProductNotFoundException;
import com.acme.exception.ProspectNotFoundException;
import com.acme.exception.SimulationProductCoveragesException;
import com.acme.exception.SimulationProductTrueCoveragesException;
import com.acme.global.GlobalSimulationNumber;
import com.acme.repository.ProductRepository;
import com.acme.repository.ProspectRepository;
import com.acme.repository.SimulationRepository;
import com.acme.service.ExternalSystem;
import com.acme.service.SimulationService;
import com.acme.utils.ValidatorCoverages;

@Service
public class SimulationServiceImpl implements SimulationService {
	
	@Autowired
	private ProspectRepository prospectRepository;
	
	@Autowired
	private ProductRepository productRepository;
	
	@Autowired
	private SimulationRepository simulationRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	
	private Float monthlyPremium = 0.0f;
	private Float yearlyPremium = 0.0f;


	@Override
	public SimulationDto createNew(String email, Long productId, Set<CoverageDto> coveragesDto, Float insuredCapital) {
		// TODO Auto-generated method stub
		
		SimulationDto simulationDto = new SimulationDto();
		
		Prospect prospect = prospectRepository.findByEmail(email);
		
		Product product = productRepository.findById(productId).get();
		
		if(prospect != null && product != null && !coveragesDto.isEmpty()) {
			Set<Coverage> simulationCoverages = new HashSet<>();
			coveragesDto.forEach(coverageDto -> simulationCoverages.add(modelMapper.map(coverageDto, Coverage.class)));
			ValidatorCoverages validatorCoverages = new ValidatorCoverages(simulationCoverages, product);
			Set<Coverage> failCoverages = validatorCoverages.getFailProd();
			Set<Coverage> failTrueCoverages = validatorCoverages.getFailTrue();
		
			if (failCoverages.isEmpty() && failTrueCoverages.isEmpty()) {		
				simulationDto.setProspectDto(modelMapper.map(prospect, ProspectDto.class));
				ProductDto productDto = modelMapper.map(product, ProductDto.class);
				product.getCoverages().forEach(coverage -> {
					CoverageDto coverageDto = modelMapper.map(coverage, CoverageDto.class);
					productDto.getCoveragesDto().add(coverageDto);
				});
				
				Long simulationNumber = ++GlobalSimulationNumber.simulationNumber;
				simulationDto.setSimulationNumber(simulationNumber);
				
				simulationDto.setProductDto(productDto);
				simulationDto.setCoveragesDto(coveragesDto);
				simulationDto.setInsuredCapital(insuredCapital);
				
				Properties props = new Properties();
				try (InputStream input = getClass().getClassLoader().getResourceAsStream("acme.properties")) {
					props.load(input);
					props.forEach((key, value) -> {
						if(key.toString().endsWith("a")) {
							ExternalSystem externalServiceA = ExternalSystemFactory.getInstance("System A");
							monthlyPremium += externalServiceA.premiumCalculation().getMonthlyPremium();
							simulationDto.setMonthlyPremium(monthlyPremium);
							yearlyPremium += externalServiceA.premiumCalculation().getYearlyPremium();
							simulationDto.setYearlyPremium(yearlyPremium);
						}
						
						
						if(key.toString().endsWith("b")) {
							ExternalSystem externalServiceB = ExternalSystemFactory.getInstance("System B");
							monthlyPremium += externalServiceB.premiumCalculation().getMonthlyPremium();
							simulationDto.setMonthlyPremium(monthlyPremium);
							yearlyPremium += externalServiceB.premiumCalculation().getYearlyPremium();
							simulationDto.setYearlyPremium(yearlyPremium);
						}
					});
				
					Simulation simulation = modelMapper.map(simulationDto, Simulation.class);
					Simulation savedSimulation = simulationRepository.save(simulation);
					simulationDto.setId(savedSimulation.getId());
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} 
			else {
				if(!failCoverages.isEmpty()) {
					String failAllCoveragesSimulation = "The product " + product.getName() + " does not have "
							+ "the following coverages: \n";
					final StringBuilder failAllCoveragesBuilder = new StringBuilder(failAllCoveragesSimulation);
					failCoverages.forEach(coverage -> {
						failAllCoveragesBuilder.append(coverage.getName());
						failAllCoveragesBuilder.append("\n");	
					});
					
					failAllCoveragesSimulation = failAllCoveragesBuilder.toString();
					
					throw new SimulationProductCoveragesException(failAllCoveragesSimulation);
				}
				
				
				if(!failTrueCoverages.isEmpty()) {
					String failTrueCoveragesSimulation = "The simulation does not have "
							+ "the following mandatory coverages of the product " + product.getName() + ":\n";
					final StringBuilder failTrueCoveragesBuilder = new StringBuilder(failTrueCoveragesSimulation);
					failTrueCoverages.forEach(coverage -> {
						failTrueCoveragesBuilder.append(coverage.getName());
						failTrueCoveragesBuilder.append("\n");	
					});
					
					failTrueCoveragesSimulation = failTrueCoveragesBuilder.toString();
					
					throw new SimulationProductTrueCoveragesException(failTrueCoveragesSimulation);
				}
				
			}
		}  
		else {
			if (product == null) {
				throw new ProductNotFoundException("There is no product with ID: " + productId);
			}
			
			if (prospect == null) {
				throw new ProspectNotFoundException("There is no prospect with email: " + email);
			}
		}
		 
		
		return simulationDto;
	}

	@Override
	public SimulationDto getSimulation(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<SimulationDto> getAllSimulationsDto() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
