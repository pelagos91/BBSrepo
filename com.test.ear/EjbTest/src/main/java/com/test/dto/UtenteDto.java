package com.test.dto;

public class UtenteDto {
	
	private String nome;
	private String cognome;
	private final int userId;
	
	
	
	
	public UtenteDto(String nome, String cognome, int userId) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		this.userId = userId;
	}

	public String getNome() {
		return this.nome;
	}
	
	public String getCognome() {
		return this.cognome;
	}
	
	public void setNome(String name) {
		this.nome = name;
	}
	
	public void setCognome(String surname) {
		this.cognome = surname;
	}
	
    
    public int getId() {
    	return this.userId;
    }

	@Override
	public String toString() {
		return "UtenteDto [nome=" + nome + ", cognome=" + cognome + ", userId=" + userId + "]";
	}
	

	
}
