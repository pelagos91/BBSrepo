package com.test.service.ejb;

import java.util.ArrayList;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import javax.ejb.EJB;
import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;

import org.apache.commons.lang3.StringUtils;

import com.test.dao.UtenteDao;
import com.test.dto.UtenteDto;
import com.test.exceptions.UserNotFoundException;
import com.test.exceptions.ValidationException;
import com.test.service.UtenteService;

@Stateless
@TransactionManagement
public class UtenteServiceEjb implements UtenteService {

	@EJB
	private UtenteDao utenteDAO;
	
	
	@PostConstruct
	public void init() {
		if (this.utenteDAO != null) {
			System.out.println("DAO inizializzato!!");
		}
	}

	@PreDestroy
	public void tearDown() {
		System.out.println("Sto per morire!!!");
	}
	
	protected Boolean inputVerify(String toVerify) throws ValidationException {

		if (toVerify == null || toVerify.isEmpty() || StringUtils.containsAny(toVerify, "0", "1", "2", "3", "4", "5", "6", "7", "8", "9")) {

			throw new ValidationException("Dati in input NON validi!", 400);
		}

		return true;

	}

	protected Boolean inputVerify(ArrayList<String> toVerify, Boolean isStrictly) throws ValidationException {

		if (!toVerify.isEmpty()) {
			Boolean flag = true;
			for (String item : toVerify) {
				if (isStrictly) {
					flag = flag && inputVerify(item);
				} else {
					flag = flag || inputVerify(item);
				}
			}
			return flag;
		}
		return false;
	}

	@Override
	public UtenteDto createUser(String nome, String cognome) throws ValidationException {

		ArrayList<String> toVerify = new ArrayList<String>() {
			private static final long serialVersionUID = -3939594439879451452L;

			{
				add(nome);
				add(cognome);
			}
		};
		inputVerify(toVerify, true);
		return this.utenteDAO.saveUtente(nome, cognome);

	}

	@Override
	public UtenteDto findUser(Integer userId) throws UserNotFoundException {

		return this.utenteDAO.findUser(userId);

	}

	@Override
	public UtenteDto updateUser(Integer userId, String keyToUpdate, String toUpdate)
			throws UserNotFoundException, ValidationException {

		inputVerify(toUpdate);
		return this.utenteDAO.updateUser(userId, keyToUpdate, toUpdate);
	}

	@Override
	public UtenteDto deleteUser(Integer userId) throws UserNotFoundException {

		return this.utenteDAO.deleteUser(userId);
	}

}
