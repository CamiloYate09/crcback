package co.gov.crcom.mycroft.model.entity;

import java.io.Serializable;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

@Entity
@Table(name = "subtema")
public class Subtema implements Serializable {

	private static final long serialVersionUID = 1L;
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_SUBTEMA")
	private Integer idSubtema;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@JoinColumn(name = "ID_TEMA", referencedColumnName = "ID_TEMA")
	@ManyToOne(optional = false)
	@JsonBackReference
	private Tema idTema;

	public Subtema() {
	}

	public Subtema(Integer idSubtema) {
		this.idSubtema = idSubtema;
	}

	public Integer getIdSubtema() {
		return idSubtema;
	}

	public void setIdSubtema(Integer idSubtema) {
		this.idSubtema = idSubtema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public Tema getIdTema() {
		return idTema;
	}

	public void setIdTema(Tema idTema) {
		this.idTema = idTema;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idSubtema != null ? idSubtema.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Subtema)) {
			return false;
		}
		Subtema other = (Subtema) object;
		if ((this.idSubtema == null && other.idSubtema != null)
				|| (this.idSubtema != null && !this.idSubtema.equals(other.idSubtema))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "co.gov.crcom.mycroft.model.entity.Subtema[ idSubtema=" + idSubtema + " ]";
	}

}
