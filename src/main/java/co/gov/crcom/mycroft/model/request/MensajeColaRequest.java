package co.gov.crcom.mycroft.model.request;

import java.io.Serializable;
import java.util.List;

public class MensajeColaRequest implements Serializable {
	
	private static final long serialVersionUID = 1L;

	private String type;

	private String query;

	private String uuid;

	private String file;

	private List<MensajeColaTemaRequest> temas;

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getQuery() {
		return query;
	}

	public void setQuery(String query) {
		this.query = query;
	}

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public String getFile() {
		return file;
	}

	public void setFile(String file) {
		this.file = file;
	}

	public List<MensajeColaTemaRequest> getTemas() {
		return temas;
	}

	public void setTemas(List<MensajeColaTemaRequest> temas) {
		this.temas = temas;
	}

}
