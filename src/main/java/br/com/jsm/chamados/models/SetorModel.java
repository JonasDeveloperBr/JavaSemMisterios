package br.com.jsm.chamados.models;

import java.util.Collection;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.validator.constraints.NotBlank;

@Entity
@Table(name = "SETOR")
public class SetorModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idSetor;
	
	@NotBlank
	private String nmSetor;
	
	@OneToMany(mappedBy = "setor")
	private Collection<UsuarioModel> usuariosList;
	
	public int getIdSetor() {
		return idSetor;
	}
	public void setIdSetor(int idSetor) {
		this.idSetor = idSetor;
	}
	public String getNmSetor() {
		return nmSetor;
	}
	public void setNmSetor(String nmSetor) {
		this.nmSetor = nmSetor;
	}
	public Collection<UsuarioModel> getUsuariosList() {
		return usuariosList;
	}
	public void setUsuariosList(Collection<UsuarioModel> usuariosList) {
		this.usuariosList = usuariosList;
	}
	
}
