package co.gov.crcom.mycroft.service;

import org.springframework.http.ResponseEntity;

import co.gov.crcom.mycroft.model.response.ListTemasSubtemasResponse;

public interface ConsultarService {
	
	/**
	 * Función encargada de devolver todos los temas y subtemas parametrizados.
	 * @return
	 */
	public ResponseEntity<ListTemasSubtemasResponse> obtenerTemasYSubTemas();

}
