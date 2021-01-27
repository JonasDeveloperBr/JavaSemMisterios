package br.com.jsm.chamados.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsm.chamados.models.SetorModel;

public class SetorBO {

	@SuppressWarnings("unchecked")
	public List<SetorModel> getListSetores(EntityManager entityManager) {
		Query query = entityManager.createQuery("from SetorModel");
		List<SetorModel> setores = query.getResultList();
		return setores;
	}
}
