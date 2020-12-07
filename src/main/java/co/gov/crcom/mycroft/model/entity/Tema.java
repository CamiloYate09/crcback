package co.gov.crcom.mycroft.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlTransient;

import com.fasterxml.jackson.annotation.JsonManagedReference;

@Entity
@Table(name = "tema")
public class Tema implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Basic(optional = false)
	@Column(name = "ID_TEMA")
	private Integer idTema;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@OneToMany(cascade = CascadeType.ALL, mappedBy = "idTema")
	@JsonManagedReference
	private List<Subtema> subtemaList;

	public Tema() {
	}

	public Tema(Integer idTema) {
		this.idTema = idTema;
	}

	public Integer getIdTema() {
		return idTema;
	}

	public void setIdTema(Integer idTema) {
		this.idTema = idTema;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	@XmlTransient
	public List<Subtema> getSubtemaList() {
		return subtemaList;
	}

	public void setSubtemaList(List<Subtema> subtemaList) {
		this.subtemaList = subtemaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idTema != null ? idTema.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Tema)) {
			return false;
		}
		Tema other = (Tema) object;
		if ((this.idTema == null && other.idTema != null)
				|| (this.idTema != null && !this.idTema.equals(other.idTema))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "co.gov.crcom.mycroft.model.entity.Tema[ idTema=" + idTema + " ]";
	}

}
