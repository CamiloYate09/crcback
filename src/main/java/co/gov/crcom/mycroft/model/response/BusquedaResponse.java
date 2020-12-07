package co.gov.crcom.mycroft.model.response;

public class BusquedaResponse {

	private String uuid;

	private MessageResponse estado;

	private Boolean busquedaExitosa;

	public String getUuid() {
		return uuid;
	}

	public void setUuid(String uuid) {
		this.uuid = uuid;
	}

	public MessageResponse getEstado() {
		return estado;
	}

	public void setEstado(MessageResponse estado) {
		this.estado = estado;
	}

	public Boolean getBusquedaExitosa() {
		return busquedaExitosa;
	}

	public void setBusquedaExitosa(Boolean busquedaExitosa) {
		this.busquedaExitosa = busquedaExitosa;
	}

}
