package com.acme.service.impl;

import java.util.ArrayList;
import java.util.List;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.acme.dto.ProspectDto;
import com.acme.entity.Prospect;
import com.acme.exception.ProspectAlreadyExistsException;
import com.acme.exception.ProspectNotFoundException;
import com.acme.repository.ProspectRepository;
import com.acme.service.ProspectService;

@Service
public class ProspectServiceImpl implements ProspectService {
	
	@Autowired
	private ProspectRepository prospectRepository;
	
	@Autowired
	private ModelMapper modelMapper;
	
	@Override
	public ProspectDto createNew(ProspectDto prospectDto) {
		// TODO Auto-generated method stub
		Prospect prospectDB = prospectRepository.findByEmail(prospectDto.getEmail()); 
		if (prospectDB == null) {
			Prospect savedProspect = prospectRepository.save(modelMapper.map(prospectDto, Prospect.class));
			return modelMapper.map(savedProspect, ProspectDto.class);
		}
		else {
			throw new ProspectAlreadyExistsException("There is already a prospect with the email:" + prospectDto.getEmail());
		}
	}

	@Override
	public ProspectDto getProspect(Long id) {
		// TODO Auto-generated method stub
		Prospect prospectDB = prospectRepository.findById(id).get();
		if (prospectDB != null) {
			return modelMapper.map(prospectDB, ProspectDto.class);
		}
		else {
			throw new ProspectNotFoundException("There is no prospect with ID:" + id);
		}
	}

	@Override
	public List<ProspectDto> getAllProspects() {
		// TODO Auto-generated method stub
		List<Prospect> prospects = (List<Prospect>) prospectRepository.findAll();
		List<ProspectDto> prospectsDto = new ArrayList<ProspectDto>();
		prospects.forEach(prospect -> {
			ProspectDto prospectDto = modelMapper.map(prospect, ProspectDto.class);
			prospectsDto.add(prospectDto);
		});
		
		return prospectsDto;
	}
	
	
	
}
