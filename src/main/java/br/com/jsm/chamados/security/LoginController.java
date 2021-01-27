package br.com.jsm.chamados.security;

import javax.inject.Inject;
import javax.persistence.EntityManager;

import br.com.caelum.vraptor.Controller;
import br.com.caelum.vraptor.Result;
import br.com.caelum.vraptor.validator.I18nMessage;
import br.com.caelum.vraptor.validator.Validator;
import br.com.jsm.chamados.business.UsuarioBO;
import br.com.jsm.chamados.controllers.ChamadoController;
import br.com.jsm.chamados.models.UsuarioModel;
import br.com.jsm.chamados.types.StUsuarioType;

/*
 * Classe de Login/LogOut da aplicação 
 */

@Controller
public class LoginController {
	@Inject
	private EntityManager entityManager;

	@Inject
	private Result result;

	@Inject
	private Validator validator;

	@Inject
	private AuxSession auxSession;

	public void formLogin() {

	}

	public void check(String nmUser, String dsSenha) throws Exception {
		UsuarioBO usuarioBO = new UsuarioBO();

		// Criptografa a senha do usuário
		dsSenha = usuarioBO.encryptPassword(dsSenha);

		UsuarioModel usuario = usuarioBO.getUsuario(nmUser, dsSenha, entityManager);

		if (usuario == null || !usuario.getDsSenha().equals(dsSenha)) {
			// Será exibida mensagem conf. arq. messages_pt_BR.properties param
			// 'login.invalid' caso o usuário ñ informe senha ou a senha seja inválida
			validator.add(new I18nMessage("login", "login.invalid"));
		}

		if (!validator.hasErrors()) {
			if (usuario == null || !usuario.getStUsuario().equals(StUsuarioType.ATIVO)) {
				validator.add(new I18nMessage("login", "login.nao.ativo"));
			}
		}

		validator.onErrorForwardTo(LoginController.class).formLogin();

		auxSession.setUsuario(usuario);

		result.forwardTo(ChamadoController.class).list();
	}

	public void logout() {
		auxSession.endSession();
		result.forwardTo(LoginController.class).formLogin();
	}

}
