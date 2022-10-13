package com.acme.service.impl;

import org.springframework.stereotype.Service;

import com.acme.service.ExternalSystem;
import com.acme.utils.Premium;

@Service
public class ExternalSystemAImpl implements ExternalSystem {

	@Override
	public Premium premiumCalculation() {
		// TODO Auto-generated method stub
		Premium premium = new Premium(35500F, 60000F);
		
		return premium;
	}

}
