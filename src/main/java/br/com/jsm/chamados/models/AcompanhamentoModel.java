package br.com.jsm.chamados.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.jsm.chamados.types.StChamadoType;

@Entity
@Table(name = "ACOMPANHAMENTO")
public class AcompanhamentoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idAcompanhamento;
	
	@ManyToOne()
	@JoinColumn(name = "idChamado")
	private ChamadoModel chamado;
	
	@NotBlank
	@Length(min=10, max=500)
	private String dsAcompanhamento;
	
	@OneToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioModel usuario;
	
	private Date dtAcompanhamento;
	
	@Enumerated(EnumType.ORDINAL)
	private StChamadoType stAcompanhamento;
	
	public int getIdAcompanhamento() {
		return idAcompanhamento;
	}
	public void setIdAcompanhamento(int idAcompanhamento) {
		this.idAcompanhamento = idAcompanhamento;
	}
	public ChamadoModel getChamado() {
		return chamado;
	}
	public void setChamado(ChamadoModel chamado) {
		this.chamado = chamado;
	}
	public String getDsAcompanhamento() {
		return dsAcompanhamento;
	}
	public void setDsAcompanhamento(String dsAcompanhamento) {
		this.dsAcompanhamento = dsAcompanhamento;
	}
	public UsuarioModel getUsuario() {
		return usuario;
	}
	public void setUsuario(UsuarioModel usuario) {
		this.usuario = usuario;
	}
	public Date getDtAcompanhamento() {
		return dtAcompanhamento;
	}
	public void setDtAcompanhamento(Date dtAcompanhamento) {
		this.dtAcompanhamento = dtAcompanhamento;
	}
	public StChamadoType getStAcompanhamento() {
		return stAcompanhamento;
	}
	public void setStAcompanhamento(StChamadoType stAcompanhamento) {
		this.stAcompanhamento = stAcompanhamento;
	}
	
}
