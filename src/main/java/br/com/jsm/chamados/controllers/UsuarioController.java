package br.com.jsm.chamados.controllers;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.business.SetorBO;
import br.com.jsm.chamados.business.UsuarioBO;
import br.com.jsm.chamados.models.UsuarioModel;
import br.com.jsm.chamados.security.Admin;
import br.com.jsm.chamados.types.StUsuarioType;
import br.com.jsm.chamados.types.TpUsuarioType;

/**
 * 
 * @author luiz.lima
 *
 */

@Controller
public class UsuarioController {
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	private UsuarioBO usuarioBO;
	private SetorBO setorBO;
	
	@Admin
	public void edit(UsuarioModel usuario) {
		setorBO = new SetorBO();
		
		result.include("setores", setorBO.getListSetores(entityManager));
		result.include("situacoes", StUsuarioType.values());
		result.include("tipos", TpUsuarioType.values());
		result.include("usuario", usuario);
	}
	
	@Admin
	public void list() {
		usuarioBO = new UsuarioBO();
		
		result.include("usuarios", usuarioBO.getListUsuarios(entityManager));
	}
	
	@Admin
	public void clear() {
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void save(UsuarioModel usuario) throws Exception  {
		validator.validate(usuario);
		
		if (usuario.getSetor().getIdSetor() == 0) {
			validator.add(new I18nMessage("idSetor", "not.blank"));
		}
		
		if (usuario.getStUsuario() .equals(StUsuarioType.SELECIONE)) {
			validator.add(new I18nMessage("stUsuario", "not.blank"));
		}
		
		if (usuario.getTpUsuario() .equals(TpUsuarioType.SELECIONE)) {
			validator.add(new I18nMessage("tpUsuario", "not.blank"));
		}
		
		if (! validator.hasErrors()) {
			
			if (usuario.getDsSenha() != null && ! usuario.getDsSenha().equals("")) {
				
				if (! usuario.getDsSenha().equals(usuario.getDsSenhaConfirm())) {
					validator.add(new I18nMessage("dsSenha", "senha.nao.confere"));
				}
			}
		}
		
		usuarioBO = new UsuarioBO();
		
		if (! usuarioBO.checkEmailUnico(usuario.getIdUsuario(), usuario.getDsEmail(), entityManager)) {
			validator.add(new I18nMessage("dsEmail", "email.nao.unico"));
		}
		
		validator.onErrorForwardTo(this).edit(usuario);
		
		usuarioBO = new UsuarioBO();
		
		if (usuario.getDsSenha() != null && ! usuario.getDsSenha().equals("")) {
			String dsSenha = usuarioBO.encryptPassword(usuario.getDsSenha());
			usuario.setDsSenha(dsSenha);			
		} else {
			
			if (usuario.getIdUsuario() != 0) {
				UsuarioModel usuarioBD = this.entityManager.find(UsuarioModel.class, usuario.getIdUsuario());
				usuario.setDsSenha(usuarioBD.getDsSenha());
			}

		}
		
		if (usuario.getIdUsuario() == 0) {
			create(usuario);
		} else {
			update(usuario);
		}
	}
	
	@Admin
	private void create(UsuarioModel usuario) {
		this.entityManager.persist(usuario);
		
		validator.add(new SimpleMessage("success", "success.create", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void read(UsuarioModel usuario) {
		UsuarioModel usuarioBD = this.entityManager.find(UsuarioModel.class, usuario.getIdUsuario());
		result.forwardTo(this).edit(usuarioBD);
	}
	
	@Admin
	private void update(UsuarioModel usuario) {
		this.entityManager.merge(usuario);
		
		validator.add(new SimpleMessage("success", "success.update", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
	
	@Admin
	public void delete(UsuarioModel usuario) {
		if (usuario.getIdUsuario() == 0) {
			validator.add(new I18nMessage("idUsuario", "not.blank"));
		}
		
		validator.onErrorForwardTo(this).edit(usuario);
		
		UsuarioModel setorBD = this.entityManager.find(UsuarioModel.class, usuario.getIdUsuario());
		this.entityManager.remove(setorBD);
		
		validator.add(new SimpleMessage("success", "success.delete", Severity.SUCCESS));
		result.forwardTo(this).edit(null);
	}
}
