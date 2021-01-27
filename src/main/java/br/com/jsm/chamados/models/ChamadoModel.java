package br.com.jsm.chamados.models;

import java.util.Collection;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotBlank;

import br.com.jsm.chamados.types.StChamadoType;

@Entity //Anotação para indicar ao vraptor que essa classe é uma entidade no banco de dados
@Table(name = "CHAMADO")//define o nome da table que será criado no banco de dados
public class ChamadoModel {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int idChamado;

	@NotBlank
	@Length(min = 10, max = 500)
	private String dsChamado;

	@OneToOne
	@JoinColumn(name = "idUsuario")
	private UsuarioModel usuarioSolicitante;

	@OneToOne
	@JoinColumn(name = "idSetor")
	private SetorModel setorDestino;

	@Enumerated(EnumType.ORDINAL)
	private StChamadoType stChamado;

	private Date dtChamado;

	@OneToMany(mappedBy = "chamado")
	@OrderBy("dtAcompanhamento DESC")
	private Collection<AcompanhamentoModel> acompanhamentos;

	public Date getDtChamado() {
		return dtChamado;
	}

	public void setDtChamado(Date dtChamado) {
		this.dtChamado = dtChamado;
	}

	public Collection<AcompanhamentoModel> getAcompanhamentos() {
		return acompanhamentos;
	}

	public void setAcompanhamentos(Collection<AcompanhamentoModel> acompanhamentos) {
		this.acompanhamentos = acompanhamentos;
	}

	public int getIdChamado() {
		return idChamado;
	}

	public void setIdChamado(int idChamado) {
		this.idChamado = idChamado;
	}

	public String getDsChamado() {
		return dsChamado;
	}

	public void setDsChamado(String dsChamado) {
		this.dsChamado = dsChamado;
	}

	public UsuarioModel getUsuarioSolicitante() {
		return usuarioSolicitante;
	}

	public void setUsuarioSolicitante(UsuarioModel usuarioSolicitante) {
		this.usuarioSolicitante = usuarioSolicitante;
	}

	public SetorModel getSetorDestino() {
		return setorDestino;
	}

	public void setSetorDestino(SetorModel setorDestino) {
		this.setorDestino = setorDestino;
	}

	public StChamadoType getStChamado() {
		return stChamado;
	}

	public void setStChamado(StChamadoType stChamado) {
		this.stChamado = stChamado;
	}

}
