package br.com.jsm.chamados.types;

/**
 * Essa classe cria os tipos de usuários os quais serão listados no combobox na
 * tela de criação de usuário
 */
public enum TpUsuarioType {
	SELECIONE(0), ADMIN(1), USUARIO(2);

	private final int value;

	TpUsuarioType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
