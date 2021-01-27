package br.com.jsm.chamados.controllers;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Path;
import br.com.caelum.vraptor.Post;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Severity;
import br.com.caelum.vraptor.validator.SimpleMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.business.SetorBO;
import br.com.jsm.chamados.business.UsuarioBO;
import br.com.jsm.chamados.models.UsuarioModel;
import br.com.jsm.chamados.security.AuxSession;

@Controller
public class MeuCadastroController {
	@Inject
	private EntityManager entityManager;
	
	@Inject
	private Result result;
	
	@Inject
	private Validator validator;
	
	private UsuarioBO usuarioBO;
	private SetorBO setorBO;
	
	@Inject
	private AuxSession auxSession;
	
	@Path("/meucadastro")
	public void edit() {
		setorBO = new SetorBO();
		
		result.include("setores", setorBO.getListSetores(entityManager));
		result.include("usuario", auxSession.getUsuario());
	}
	
	@Post("/savemeucadastro")
	public void save(UsuarioModel usuario) throws Exception {
		validator.validate(usuario);
		
		if (usuario.getSetor().getIdSetor() == 0) {
			validator.add(new I18nMessage("idSetor", "not.blank"));
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
		
		validator.onErrorForwardTo(this).edit();
		
		usuarioBO = new UsuarioBO();
		
		UsuarioModel usuarioBD = this.entityManager.find(UsuarioModel.class, usuario.getIdUsuario());
		
		if (usuario.getDsSenha() != null && ! usuario.getDsSenha().equals("")) {
			String dsSenha = usuarioBO.encryptPassword(usuario.getDsSenha());
			usuario.setDsSenha(dsSenha);			
		} else {
			usuario.setDsSenha(usuarioBD.getDsSenha());
		}

		usuario.setTpUsuario(usuarioBD.getTpUsuario());
		usuario.setStUsuario(usuarioBD.getStUsuario());
		
		update(usuario);
	}
	
	private void update(UsuarioModel usuario) {
		this.entityManager.merge(usuario);
		
		validator.add(new SimpleMessage("success", "success.update", Severity.SUCCESS));
		
		auxSession.setUsuario(usuario);
		
		result.forwardTo(this).edit();
	}
	
}
