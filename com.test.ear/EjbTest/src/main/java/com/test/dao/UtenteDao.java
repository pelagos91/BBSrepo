package com.test.dao;

import com.test.dto.UtenteDto;
import com.test.exceptions.UserNotFoundException;

public interface UtenteDao {

	UtenteDto saveUtente(String nome, String cognome);
	UtenteDto findUser(Integer userId) throws UserNotFoundException;
    UtenteDto updateUser(Integer userId, String keyToUpdate, String toUpdate) throws UserNotFoundException;
    UtenteDto deleteUser(Integer userId) throws UserNotFoundException;
}