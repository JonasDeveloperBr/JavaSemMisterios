package br.com.jsm.chamados.security;

import java.io.Serializable;

import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import br.com.jsm.chamados.models.UsuarioModel;

/*
 * Essa classe tem a finalidade de escopo da sessão, usando a implementação Serializable para poder controlar a sessão do usuário
 */
@Named
@SessionScoped
public class AuxSession implements Serializable {
	private static final long serialVersionUID = 6304230357078893546L;

	private UsuarioModel usuario;

	public UsuarioModel getUsuario() {
		return usuario;
	}

	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public void endSession() {
		this.usuario = null;
	}

	public boolean isLogado() {
		if (usuario == null)
			return false;
		return true;
	}
}
