package br.com.jsm.chamados.types;

/**
 * Classe que disponibiliza uma 'lista' com as situaões dos usuários
 *
 */
public enum StUsuarioType {
	SELECIONE(0), ATIVO(1), INATIVO(2), BLOQUEADO(3);

	private final int value;

	StUsuarioType(int value) {
		this.value = value;
	}

	public int getValue() {
		return this.value;
	}
}
