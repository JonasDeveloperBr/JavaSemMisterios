package br.com.jsm.chamados.business;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Query;

import br.com.jsm.chamados.models.ChamadoModel;
//Basicamente o select da tabela CHAMADO
public class ChamadoBO {
	
	@SuppressWarnings("unchecked")
	public List<ChamadoModel> getListChamados(EntityManager entityManager){
		Query query = entityManager.createQuery("from ChamadoModel");
		List<ChamadoModel> chamado = query.getResultList();
		return chamado;
	}
}
