package com.test.dao.jpa;

import javax.ejb.Stateless;
import javax.ejb.TransactionManagement;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import com.test.dao.UtenteDao;
import com.test.dto.UtenteDto;
import com.test.exceptions.UserNotFoundException;
import com.test.model.UtenteEntity;

@Stateless
@TransactionManagement
public class UtenteDaoJPA implements UtenteDao {

	@PersistenceContext(unitName = "EjbComponentPU")
	private EntityManager entityManager;

	/*
	 * (non-Javadoc)
	 * 
	 * @see com.test.dao.UtenteDao#saveUtente(java.lang.String, java.lang.String)
	 */

	@Override
	public UtenteDto saveUtente(String nome, String cognome) {

		UtenteEntity utenteEntity = new UtenteEntity();
		utenteEntity.setNome(nome);
		utenteEntity.setCognome(cognome);
		this.entityManager.persist(utenteEntity);
		return new UtenteDto(nome, cognome, utenteEntity.getUserId());
	}

	@Override
	public UtenteDto findUser(Integer userId) throws UserNotFoundException {

		if (this.entityManager.find(UtenteEntity.class, userId) != null) {
			UtenteEntity entity = this.entityManager.find(UtenteEntity.class, userId);
			return new UtenteDto(entity.getNome(), entity.getCognome(), entity.getUserId());
		} else {
			throw new UserNotFoundException("Nessuna corrispondeza trovata");
		}

	}

	@Override
	public UtenteDto updateUser(Integer userId, String keyToUpdate, String toUpdate) throws UserNotFoundException {

		if (this.entityManager.find(UtenteEntity.class, userId) != null) {
			UtenteEntity entity = this.entityManager.find(UtenteEntity.class, userId);
			if (keyToUpdate == "nome") {
				entity.setNome(toUpdate);
			} else {
				entity.setCognome(toUpdate);	
			}
			
			this.entityManager.merge(entity);
			return new UtenteDto(entity.getNome(), entity.getCognome(), entity.getUserId());

		} else {
			throw new UserNotFoundException("Nessuna corrispondeza trovata");
		}
	}

	@Override
	public UtenteDto deleteUser(Integer userId) throws UserNotFoundException {
		if (this.entityManager.find(UtenteEntity.class, userId) != null) {
			UtenteEntity entity = this.entityManager.find(UtenteEntity.class, userId);
			this.entityManager.remove(entity);
			return new UtenteDto(entity.getNome(), entity.getCognome(), entity.getUserId());
		} else {
			throw new UserNotFoundException("Nessuna corrispondeza trovata");
		}
	}

}
