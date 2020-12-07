package co.gov.crcom.mycroft.model.entity;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.JoinColumn;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "busqueda")
public class Busqueda implements Serializable {
	
	private static final long serialVersionUID = 1L;

	//Constantes
	public static final String ESTADO_INICIANDO = "INICIADA";
	public static final String ESTADO_PROCESANDO = "PROCESANDO";
	public static final String ESTADO_TERMINADA = "TERMINADA";
	public static final String ESTADO_FALLIDA = "FALLIDA";
	
	public static final String TIPO_ARCHIVO = "ARCHIVO";
	public static final String TIPO_PALABRA = "PALABRA";
	public static final String TIPO_TEMA = "TEMA";

	//Definicion:
	@Id
	@Basic(optional = false)
	@Column(name = "uuid")
	private String uuid;

	@Basic(optional = false)
	@Column(name = "usuario")
	private String usuario;

	@Basic(optional = false)
	@Column(name = "fecha")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fecha;

	@Basic(optional = false)
	@Column(name = "estado")
	private String estado;

	@Basic(optional = false)
	@Column(name = "tipo")
	private String tipo;

	@Column(name = "busqueda")
	private String busqueda;

	@Column(name = "archivo")
	private String archivo;

	@ManyToMany
	@JoinTable(name = "busqueda_tema", 
		joinColumns = {@JoinColumn(name = "uuid")}, 
		inverseJoinColumns = {@JoinColumn(name = "id_tema")}
	)
	private List<Tema> temaList;

	public Busqueda() {
	}

	public Busqueda(String uuid) {
		this.uuid = uuid;
	}

	public Busqueda(String uuid, String usuario, Date fecha, String estado, String tipo) {
		this.uuid = uuid;
		this.usuario = usuario;
		this.fecha = fecha;
		this.estado = estado;
		this.tipo = tipo;
	}

	public String getUsuario() {
		return usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public Date getFecha() {
		return fecha;
	}

	public void setFecha(Date fecha) {
		this.fecha = fecha;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getBusqueda() {
		return busqueda;
	}

	public void setBusqueda(String busqueda) {
		this.busqueda = busqueda;
	}

	public String getArchivo() {
		return archivo;
	}

	public void setArchivo(String archivo) {
		this.archivo = archivo;
	}

	@XmlTransient
	public List<Tema> getTemaList() {
		return temaList;
	}

	public void setTemaList(List<Tema> temaList) {
		this.temaList = temaList;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (uuid != null ? uuid.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Busqueda)) {
			return false;
		}
		Busqueda other = (Busqueda) object;
		if ((this.uuid == null && other.uuid != null) || (this.uuid != null && !this.uuid.equals(other.uuid))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "co.gov.crcom.mycroft.model.entity.Busqueda[ uuid=" + uuid + " ]";
	}

}
