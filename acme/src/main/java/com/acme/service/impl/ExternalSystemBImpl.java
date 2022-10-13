package com.acme.service.impl;

import com.acme.service.ExternalSystem;
import com.acme.utils.Premium;

public class ExternalSystemBImpl implements ExternalSystem {

	@Override
	public Premium premiumCalculation() {
		// TODO Auto-generated method stub
		Premium premium = new Premium(45500F, 200000F);
		
		return premium;
	}

}
