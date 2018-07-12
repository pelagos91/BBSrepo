package com.test.service.ejb;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Startup;
import javax.ejb.Stateful;


@Startup
@Stateful
public class AnotherEjb {
	
	private String status = "AnotherEjb Non yet initialized! You should never see this state in console!";
	
	
	public AnotherEjb() {}


	@PostConstruct
	public void init() {
		System.out.println(this.status = "AnotherEjb Initialized... (I start before singleton due to @Startup annotation)");
	}
	
	@PreDestroy
	public void tearDown() {
		System.out.println(this.status = "AnotherEjbShutting down...");
	}
	
	public String getStatus() {
		return status;
	}


	public void setStatus(String status) {
		this.status = status;
	}
	
	
	

}
