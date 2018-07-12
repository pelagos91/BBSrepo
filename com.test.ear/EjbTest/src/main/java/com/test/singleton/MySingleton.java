package com.test.singleton;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.Singleton;


@Singleton
public class MySingleton {

	private String status = null;
	
	public MySingleton() {}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
	public String getStatus() {
		return this.status;
	}
	
	@PostConstruct
	public void init() {
		this.status = "Singleton initialized...";
		System.out.println(this.status);
	}
	
	@PreDestroy
	public void tearDown() {
		this.status = "Singleton shutting down...";
		System.out.println(this.status);
	}
	
}
