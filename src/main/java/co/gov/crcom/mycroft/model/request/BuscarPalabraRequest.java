package co.gov.crcom.mycroft.model.request;

import java.io.Serializable;

public class BuscarPalabraRequest implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private String palabra;

	public String getPalabra() {
		return palabra;
	}

	public void setPalabra(String palabra) {
		this.palabra = palabra;
	}

}
