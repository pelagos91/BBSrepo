package com.test.service;

import javax.ejb.Local;

import com.test.dto.UtenteDto;
import com.test.exceptions.*;

@Local
public interface UtenteService {
	
	public UtenteDto createUser(String nome, String cognome) throws ValidationException;
	public UtenteDto findUser(Integer userId) throws UserNotFoundException;
	public UtenteDto updateUser(Integer userId, String keyToUpdate, String toUpdate) throws UserNotFoundException, ValidationException;
	public UtenteDto deleteUser(Integer userId) throws UserNotFoundException;
	
	
}