package co.gov.crcom.mycroft.model.request;

import java.io.Serializable;

public class BuscarArchivoRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String nombreArchivo;
	private String base64Archivo;
	private Boolean sobrescribir;

	public String getNombreArchivo() {
		return nombreArchivo;
	}

	public void setNombreArchivo(String nombreArchivo) {
		this.nombreArchivo = nombreArchivo;
	}

	public String getBase64Archivo() {
		return base64Archivo;
	}

	public void setBase64Archivo(String base64Archivo) {
		this.base64Archivo = base64Archivo;
	}

	public Boolean getSobrescribir() {
		return sobrescribir;
	}

	public void setSobrescribir(Boolean sobrescribir) {
		this.sobrescribir = sobrescribir;
	}

}
