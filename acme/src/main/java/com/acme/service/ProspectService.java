package com.acme.service;

import java.util.List;

import com.acme.dto.ProspectDto;

public interface ProspectService {
	ProspectDto createNew(ProspectDto prospectDto);
	
	ProspectDto getProspect(Long id);
	
	List<ProspectDto> getAllProspects();
}
