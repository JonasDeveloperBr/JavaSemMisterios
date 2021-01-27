package br.com.jsm.chamados.controllers;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.business.SetorBO;
import br.com.jsm.chamados.models.SetorModel;
import br.com.jsm.chamados.security.Admin;

/**
 * Classe destinada para realizar o CRUD
 * C - Create
 * R - Read
 * U - Update
 * D - Delete
 * 
 * @author luiz.lima
 *
 */

@Controller
public class SetorController {
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	private SetorBO setorBO;
	
	@Admin
	public void edit(SetorModel setor) {
		result.include("setor", setor);
	}
	
	@Admin
	public void clear() {
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void list() {
		setorBO = new SetorBO();
		
		result.include("setores", setorBO.getListSetores(entityManager));
	}
	
	@Admin
	public void save(SetorModel setor) {
		validator.validate(setor);
		validator.onErrorForwardTo(this).edit(setor);
		
		if (setor.getIdSetor() == 0) {
			create(setor);
		} else {
			update(setor);
		}
	}
	
	@Admin
	private void create(SetorModel setor) {
		this.entityManager.persist(setor);
		validator.add(new SimpleMessage("success", "success.create", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void read(SetorModel setor) {
		SetorModel setorBD = this.entityManager.find(SetorModel.class, setor.getIdSetor());
		result.forwardTo(this).edit(setorBD);
	}
	
	@Admin
	private void update(SetorModel setor) {
		this.entityManager.merge(setor);
		validator.add(new SimpleMessage("success", "success.update", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void delete(SetorModel setor) {
		SetorModel setorBD = this.entityManager.find(SetorModel.class, setor.getIdSetor());
		this.entityManager.remove(setorBD);
		validator.add(new SimpleMessage("success", "success.delete", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
}
