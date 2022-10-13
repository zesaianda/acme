package com.acme.component;

import org.springframework.stereotype.Component;

import com.acme.service.ExternalSystem;
import com.acme.service.impl.ExternalSystemAImpl;
import com.acme.service.impl.ExternalSystemBImpl;

@Component
public class ExternalSystemFactory {
	 	
	 public static ExternalSystem getInstance(String system) {
		ExternalSystem externalSystem = null;
		
		switch (system) {
			case "System A":
				externalSystem = new ExternalSystemAImpl();
				break;
				
			case "System B":
				externalSystem = new ExternalSystemBImpl();
				break;

			default:
				break;
		}
		 
		 
		return externalSystem;
	 }
}
