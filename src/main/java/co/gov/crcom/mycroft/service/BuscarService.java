package co.gov.crcom.mycroft.service;

import org.springframework.http.ResponseEntity;

import co.gov.crcom.mycroft.model.request.BuscarArchivoRequest;
import co.gov.crcom.mycroft.model.request.BuscarPalabraRequest;
import co.gov.crcom.mycroft.model.request.BuscarTemaRequest;
import co.gov.crcom.mycroft.model.response.BusquedaResponse;

/**
 * Servicio para controlar lo correspondiente a las busquedas en el sistema CRC.
 * 
 * @author Iván Díaz
 *
 */
public interface BuscarService {

	/**
	 * Inicia el proceso de busqueda por archivo de forma asincrona. Deja el archivo
	 * en un directorio compartido y luego envia un mensaje a la cola de Azure.
	 * 
	 * @param peticion
	 * @return
	 */
	public ResponseEntity<BusquedaResponse> buscarPorArchivo(BuscarArchivoRequest peticion);

	/**
	 * Inicia el proceso de busqueda por palabra de forma asincrona. Envía un
	 * mensaje a la cola de Azure.
	 * 
	 * @param peticion
	 * @return
	 */
	public ResponseEntity<BusquedaResponse> buscarPorPalabra(BuscarPalabraRequest peticion);

	/**
	 * Inicia el proceso de busqueda por temas y subtemas de forma asincrona. Envía
	 * un mensaje a la cola de Azure.
	 * 
	 * @param peticion
	 * @return
	 */
	public ResponseEntity<BusquedaResponse> buscarPorTema(BuscarTemaRequest peticion);

}
