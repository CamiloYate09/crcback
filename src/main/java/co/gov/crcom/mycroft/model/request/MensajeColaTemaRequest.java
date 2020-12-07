package co.gov.crcom.mycroft.model.request;

import java.io.Serializable;
import java.util.List;

public class MensajeColaTemaRequest implements Serializable {

	private static final long serialVersionUID = 1L;

	private String tema;

	private List<String> subtemas;

	public String getTema() {
		return tema;
	}

	public void setTema(String tema) {
		this.tema = tema;
	}

	public List<String> getSubtemas() {
		return subtemas;
	}

	public void setSubtemas(List<String> subtemas) {
		this.subtemas = subtemas;
	}

}
