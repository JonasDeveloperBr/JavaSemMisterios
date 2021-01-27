package br.com.jsm.chamados.controllers;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.models.AcompanhamentoModel;
import br.com.jsm.chamados.models.ChamadoModel;
import br.com.jsm.chamados.models.UsuarioModel;
import br.com.jsm.chamados.security.AuxSession;
import br.com.jsm.chamados.types.StChamadoType;

@Controller
public class AcompanhamentoController {

	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	@Inject
	private AuxSession auxSession;
	
	public void edit(ChamadoModel chamado) {
		/**
		  
		   Para a Combobox de situações do Acompanhamento, NÃO exibir a situação NOVO
		   Porque estamos gravando um acompanhamento do chamado e não um NOVO chamado.
		  
		  StChamadoType
		  0, SELECIONE
		  1, NOVO			Não mostrar essa situação na tela de Acompanhamentos
		  2, EM ANDAMENTO
		  3, ENCERRADO
		  4, CANCELADO
			
		 */
		
		//Map para guardar as situações
		//Chave = código da situação
		//Value = situação
		Map<Integer, StChamadoType> situacoes = new HashMap<Integer, StChamadoType>();
		
		for (StChamadoType stAcompanhamento : StChamadoType.values()) {
			situacoes.put(stAcompanhamento.getValue(), stAcompanhamento);
		}
		
		//Remove a situação Novo
		situacoes.remove(StChamadoType.NOVO.getValue());
		
		result.include("situacoes", situacoes.values());
		result.include("chamado", chamado);
	}
	
	public void clear() {
		result.forwardTo(this).edit(null);
	}
	
	public void save(AcompanhamentoModel acompanhamento) throws Exception  {
		validator.validate(acompanhamento);
		
		if (acompanhamento.getStAcompanhamento().equals(StChamadoType.SELECIONE)) {
			validator.add(new I18nMessage("stChamado", "not.blank"));
		}
		
		ChamadoModel chamado = this.entityManager.find(ChamadoModel.class, acompanhamento.getChamado().getIdChamado());
		
		if (chamado.getStChamado().equals(StChamadoType.ENCERRADO)) {
			validator.add(new I18nMessage("stChamado", "chamado.encerrado"));
		}
		
		validator.onErrorForwardTo(this).edit(acompanhamento.getChamado());
		
		if (acompanhamento.getIdAcompanhamento() == 0) {
			Date now = new Date();
			
			acompanhamento.setDtAcompanhamento(now);
			
			UsuarioModel usuario = auxSession.getUsuario();
			
			acompanhamento.setUsuario(usuario);
			
			create(acompanhamento);
		} 
	}
	
	private void create(AcompanhamentoModel acompanhamento) {
		this.entityManager.persist(acompanhamento);
		
		ChamadoModel chamado = acompanhamento.getChamado();
		
		ChamadoModel chamadoBD = this.entityManager.find(ChamadoModel.class, chamado.getIdChamado());
		chamadoBD.setStChamado(acompanhamento.getStAcompanhamento());
		this.entityManager.merge(chamadoBD);
		
		validator.add(new SimpleMessage("success", "success.create", Severity.SUCCESS));
		
		result.forwardTo(this).read(acompanhamento.getChamado());
	}
	
	public void read(ChamadoModel chamado) {
		ChamadoModel chamadoBD = this.entityManager.find(ChamadoModel.class, chamado.getIdChamado());
		result.forwardTo(this).edit(chamadoBD);
	}
	
}
