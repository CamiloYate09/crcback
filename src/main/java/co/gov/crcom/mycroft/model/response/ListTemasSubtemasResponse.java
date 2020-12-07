package co.gov.crcom.mycroft.model.response;

import java.io.Serializable;
import java.util.List;

import co.gov.crcom.mycroft.model.entity.Tema;

public class ListTemasSubtemasResponse implements Serializable {

	private static final long serialVersionUID = 1L;

	private MessageResponse estado;

	private boolean consultaExitosa;

	private List<Tema> temas;
	
	public ListTemasSubtemasResponse() {
		// TODO Auto-generated constructor stub
	}

	public MessageResponse getEstado() {
		return estado;
	}

	public void setEstado(MessageResponse estado) {
		this.estado = estado;
	}

	public boolean isConsultaExitosa() {
		return consultaExitosa;
	}

	public void setConsultaExitosa(boolean consultaExitosa) {
		this.consultaExitosa = consultaExitosa;
	}

	public List<Tema> getTemas() {
		return temas;
	}

	public void setTemas(List<Tema> temas) {
		this.temas = temas;
	}

}
