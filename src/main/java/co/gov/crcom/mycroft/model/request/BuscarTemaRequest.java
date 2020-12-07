package co.gov.crcom.mycroft.model.request;

import java.io.Serializable;
import java.util.List;

public class BuscarTemaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private List<MensajeColaTemaRequest> temas;

	public List<MensajeColaTemaRequest> getTemas() {
		return temas;
	}

	public void setTemas(List<MensajeColaTemaRequest> temas) {
		this.temas = temas;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

}
