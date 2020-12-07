package co.gov.crcom.mycroft.model.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "parametro")
public class Parametro implements Serializable {

	private static final long serialVersionUID = 1L;
	
	 // Constantes
	public static final String PAR_EXPIRACION_JWT = "PAR-001";
	
	public static final String PAR_CONEXION_BUS_MENSAJES_AZURE = "PAR-002";
	
	public static final String PAR_NOMBRE_COLA_BUSCAR = "PAR-003";
	
	public static final String PAR_RUTA_DIRECTORIO_BUSQUEDA = "PAR-004";
	
	public static final String PAR_ENDPOINT_SOAP_LDAP = "PAR-005";


	//Definición de datos.
	@Id
	@Basic(optional = false)
	@Column(name = "ID_PARAMETRO")
	private String idParametro;

	@Basic(optional = false)
	@Column(name = "NOMBRE")
	private String nombre;

	@Column(name = "DESCRIPCION")
	private String descripcion;

	@Basic(optional = false)
	@Column(name = "VALOR")
	private String valor;

	@Basic(optional = false)
	@Column(name = "ESTADO")
	private String estado;

	@Basic(optional = false)
	@Column(name = "FECHA_CREACION")
	@Temporal(TemporalType.TIMESTAMP)
	private Date fechaCreacion;

	@Basic(optional = false)
	@Column(name = "TIPO_PARAMETRO")
	private String tipoParametro;

	public Parametro() {
	}

	public Parametro(String idParametro) {
		this.idParametro = idParametro;
	}

	public Parametro(String idParametro, String nombre, String valor, String estado, Date fechaCreacion,
			String tipoParametro) {
		this.idParametro = idParametro;
		this.nombre = nombre;
		this.valor = valor;
		this.estado = estado;
		this.fechaCreacion = fechaCreacion;
		this.tipoParametro = tipoParametro;
	}

	public String getIdParametro() {
		return idParametro;
	}

	public void setIdParametro(String idParametro) {
		this.idParametro = idParametro;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getDescripcion() {
		return descripcion;
	}

	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	public String getValor() {
		return valor;
	}

	public void setValor(String valor) {
		this.valor = valor;
	}

	public String getEstado() {
		return estado;
	}

	public void setEstado(String estado) {
		this.estado = estado;
	}

	public Date getFechaCreacion() {
		return fechaCreacion;
	}

	public void setFechaCreacion(Date fechaCreacion) {
		this.fechaCreacion = fechaCreacion;
	}

	public String getTipoParametro() {
		return tipoParametro;
	}

	public void setTipoParametro(String tipoParametro) {
		this.tipoParametro = tipoParametro;
	}

	@Override
	public int hashCode() {
		int hash = 0;
		hash += (idParametro != null ? idParametro.hashCode() : 0);
		return hash;
	}

	@Override
	public boolean equals(Object object) {
		if (!(object instanceof Parametro)) {
			return false;
		}
		Parametro other = (Parametro) object;
		if ((this.idParametro == null && other.idParametro != null)
				|| (this.idParametro != null && !this.idParametro.equals(other.idParametro))) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return "co.gov.crcom.mycroft.model.entity.Parametro[ idParametro=" + idParametro + " ]";
	}

}
