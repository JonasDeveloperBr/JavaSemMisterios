package br.com.jsm.chamados.controllers;

import java.util.Date;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.business.ChamadoBO;
import br.com.jsm.chamados.business.SetorBO;
import br.com.jsm.chamados.models.AcompanhamentoModel;
import br.com.jsm.chamados.models.ChamadoModel;
import br.com.jsm.chamados.models.UsuarioModel;
import br.com.jsm.chamados.security.AuxSession;
import br.com.jsm.chamados.types.StChamadoType;

@Controller
public class ChamadoController {
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	@Inject
	private AuxSession auxSession;
	
	private ChamadoBO chamadoBO;
	private SetorBO setorBO;
	
	public void edit(ChamadoModel chamado) {
		chamadoBO = new ChamadoBO();
		setorBO = new SetorBO();
	
		result.include("chamados", chamadoBO.getListChamados(entityManager));
		result.include("setores", setorBO.getListSetores(entityManager));
		result.include("situacoes", StChamadoType.values());
		result.include("chamado", chamado);
	}
	
	public void clear() {
		result.forwardTo(this).edit(null);
	}
	
	public void list() {
		chamadoBO = new ChamadoBO();
		result.include("chamados", chamadoBO.getListChamados(entityManager));
	}
	
	public void save(ChamadoModel chamado) throws Exception  {
		validator.validate(chamado);
		
		if (chamado.getSetorDestino().getIdSetor() == 0) {
			validator.add(new I18nMessage("idSetor", "not.blank"));
		}
		
		if (chamado.getStChamado().equals(StChamadoType.SELECIONE)) {
			validator.add(new I18nMessage("stUsuario", "not.blank"));
		}
		
		validator.onErrorForwardTo(this).edit(chamado);
		
		if (chamado.getIdChamado() == 0) {
			Date now = new Date();
			
			chamado.setDtChamado(now);
			
			UsuarioModel usuario = auxSession.getUsuario();
			
			chamado.setUsuarioSolicitante(usuario);
			
			create(chamado);
		} else {
			update(chamado);
		}
	}
	
	private void create(ChamadoModel chamado) {
		this.entityManager.persist(chamado);
		
		AcompanhamentoModel acompanhamento = new AcompanhamentoModel();
		acompanhamento.setChamado(chamado);
		acompanhamento.setDsAcompanhamento("Abertura do Chamado");
		acompanhamento.setDtAcompanhamento(chamado.getDtChamado());
		acompanhamento.setStAcompanhamento(StChamadoType.NOVO);
		acompanhamento.setUsuario(chamado.getUsuarioSolicitante());
		
		this.entityManager.persist(acompanhamento);
		
		validator.add(new SimpleMessage("success", "success.create", Severity.SUCCESS));
		
		result.forwardTo(this).edit(null);
	}
	
	public void read(ChamadoModel chamado) {
		ChamadoModel chamadoBD = this.entityManager.find(ChamadoModel.class, chamado.getIdChamado());
		result.forwardTo(this).edit(chamadoBD);
	}
	
	private void update(ChamadoModel chamado) {
		this.entityManager.merge(chamado);
		
		validator.add(new SimpleMessage("success", "success.update", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
	
	public void delete(ChamadoModel chamado) {
		if (chamado.getIdChamado() == 0) {
			validator.add(new I18nMessage("idUsuario", "not.blank"));
		}
		
		validator.onErrorForwardTo(this).edit(chamado);
		
		ChamadoModel setorBD = this.entityManager.find(ChamadoModel.class, chamado.getIdChamado());
		this.entityManager.remove(setorBD);
		
		validator.add(new SimpleMessage("success", "success.delete", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
}
