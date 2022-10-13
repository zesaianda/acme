package com.acme.service;

import java.util.List;

import com.acme.dto.CoverageDto;

public interface CoverageService {
	CoverageDto createNew(CoverageDto covDto);
	
	List<CoverageDto> getAll();	
}
