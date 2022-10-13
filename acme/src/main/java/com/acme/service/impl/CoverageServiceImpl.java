package com.acme.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.dto.CoverageDto;
import com.acme.entity.Coverage;
import com.acme.exception.CoverageAlreadyExistsException;
import com.acme.repository.CoverageRepository;
import com.acme.service.CoverageService;

@Service
public class CoverageServiceImpl implements CoverageService {
	@Autowired
	private CoverageRepository coverageRepository;
	
	@Autowired
	private ModelMapper modelMapper;

	@Override
	public CoverageDto createNew(CoverageDto covDto) {
		// TODO Auto-generated method stub
		Coverage coverageDB = coverageRepository.findByName(covDto.getName());
		if (coverageDB == null) {
			Coverage cov = coverageRepository.save(modelMapper.map(covDto, Coverage.class));
			return modelMapper.map(cov, CoverageDto.class);
		} 
		else {
			throw new CoverageAlreadyExistsException("There is already a coverage with the name: " 
					+ covDto.getName());
		}
	}

	@Override
	public List<CoverageDto> getAll() {
		// TODO Auto-generated method stub
		List<Coverage> coverages = (List<Coverage>) coverageRepository.findAll();
		List<CoverageDto> coveragesDto = new ArrayList<>();
		coverages.forEach(coverage -> {
			CoverageDto coverageDto = modelMapper.map(coverage, CoverageDto.class);
			coveragesDto.add(coverageDto);
		});
		
		return coveragesDto;
	}
}
