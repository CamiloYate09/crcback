package co.gov.crcom.mycroft.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.FileOutputStream;
import java.io.OutputStream;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Base64;
import java.util.Date;
import java.util.UUID;

import co.gov.crcom.mycroft.exception.NegocioException;
import co.gov.crcom.mycroft.model.entity.Busqueda;
import co.gov.crcom.mycroft.model.entity.Parametro;
import co.gov.crcom.mycroft.model.request.BuscarArchivoRequest;
import co.gov.crcom.mycroft.model.request.BuscarPalabraRequest;
import co.gov.crcom.mycroft.model.request.BuscarTemaRequest;
import co.gov.crcom.mycroft.model.request.MensajeColaRequest;
import co.gov.crcom.mycroft.model.response.BusquedaResponse;
import co.gov.crcom.mycroft.repository.BusquedaRepository;
import co.gov.crcom.mycroft.repository.ParametroRepository;

import static co.gov.crcom.mycroft.util.ErrorMessageUtil.*;
import static co.gov.crcom.mycroft.util.InfoMessageUtil.*;
import static co.gov.crcom.mycroft.model.entity.Parametro.*;

@Service
public class BuscarServiceImpl implements BuscarService {

	// Servicios:
	@Autowired
	private ColaMensajeService mColaMensajeService;

	@Autowired
	private ParametroRepository mParametroRepository;

	@Autowired
	private BusquedaRepository mBusquedaRepository;

	/**
	 * @see BuscarService#buscarPorArchivo(BuscarArchivoRequest)
	 */
	@Override
	public ResponseEntity<BusquedaResponse> buscarPorArchivo(BuscarArchivoRequest peticion) {

		BusquedaResponse response = new BusquedaResponse();
		try {
			// Validamos que venga una petición válida.
			if (peticion == null) {
				response.setEstado(ERR_BUSCAR_001);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			if (peticion.getNombreArchivo() == null) {
				response.setEstado(ERR_BUSCAR_002);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			if (peticion.getBase64Archivo() == null) {
				response.setEstado(ERR_BUSCAR_003);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			// Ejecutamos el método genérico que realiza la búsqueda.
			return ejecutarBusqueda(peticion, null, null);

		} catch (Exception e) {
			response.setEstado(ERR_BUSCAR_004);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * @see BuscarService#buscarPorPalabra(BuscarPalabraRequest)
	 */
	@Override
	public ResponseEntity<BusquedaResponse> buscarPorPalabra(BuscarPalabraRequest peticion) {

		BusquedaResponse response = new BusquedaResponse();
		try {
			// Validamos que venga una petición válida.
			if (peticion == null) {
				response.setEstado(ERR_BUSCAR_001);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			if (peticion.getPalabra() == null) {
				response.setEstado(ERR_BUSCAR_007);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			// Ejecutamos el método genérico que realiza la búsqueda.
			return ejecutarBusqueda(null, peticion, null);

		} catch (Exception e) {
			response.setEstado(ERR_BUSCAR_004);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * @see BuscarService#buscarPorTema(BuscarTemaRequest)
	 */
	@Override
	public ResponseEntity<BusquedaResponse> buscarPorTema(BuscarTemaRequest peticion) {
		BusquedaResponse response = new BusquedaResponse();
		try {
			// Validamos que venga una petición válida.
			if (peticion == null) {
				response.setEstado(ERR_BUSCAR_001);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}
			if (peticion.getTemas() == null || peticion.getTemas().isEmpty()) {
				response.setEstado(ERR_BUSCAR_008);
				return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
			}

			// Ejecutamos el método genérico que realiza la búsqueda.
			return ejecutarBusqueda(null, null, peticion);

		} catch (Exception e) {
			response.setEstado(ERR_BUSCAR_004);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * Método genérico para ejecutar una búsqueda en los sistemas de Informese.
	 * 
	 * @param pArchivo petición de tipo archivo.
	 * @param pPalabra petición de tipo palabra.
	 * @param pTema    petición de tipo temas/subtemas.
	 * @return
	 */
	private ResponseEntity<BusquedaResponse> ejecutarBusqueda(BuscarArchivoRequest pArchivo,
			BuscarPalabraRequest pPalabra, BuscarTemaRequest pTema) {

		Busqueda nuevaBusqueda = new Busqueda();
		BusquedaResponse response = new BusquedaResponse();
		MensajeColaRequest objMensaje = new MensajeColaRequest();

		try {

			// 1. Creamos la entidad busqueda para almacenar en base de datos.
			nuevaBusqueda.setUsuario("test");
			nuevaBusqueda.setUuid(UUID.randomUUID().toString());
			nuevaBusqueda.setFecha(new Date());
			nuevaBusqueda.setEstado(Busqueda.ESTADO_INICIANDO);
			nuevaBusqueda.setTipo(Busqueda.TIPO_ARCHIVO);
			this.mBusquedaRepository.save(nuevaBusqueda);

			// 2. Si la petición es por archivo:
			if (pArchivo != null) {
				nuevaBusqueda.setArchivo(pArchivo.getNombreArchivo());

				// 2.1. Enviamos archivo al directorio especificado:
				Parametro parRutaBusqueda = mParametroRepository.findFirstByIdParametro(PAR_RUTA_DIRECTORIO_BUSQUEDA);
				Path rutaArchivo = Paths.get(parRutaBusqueda.getValor(), pArchivo.getNombreArchivo());
				byte[] dataArchivo = Base64.getDecoder().decode(pArchivo.getBase64Archivo());
				try (OutputStream stream = new FileOutputStream(rutaArchivo.toString())) {
					stream.write(dataArchivo);
				}

				// 2.2. Asignamos el archivo al mensaje que se enviará a la cola
				objMensaje.setFile(rutaArchivo.toString());
			}

			// 3. Si la petición es por palabra:
			if (pPalabra != null) {

				// 3.1. Asignamos la palabra al mensaje que se enviará a la cola
				objMensaje.setQuery(pPalabra.getPalabra());
			}

			// 4. Si la petición es por temas y subtemas:
			if (pTema != null) {

				// 4.1. Asignamos la palabra al mensaje que se enviará a la cola
				objMensaje.setTemas(pTema.getTemas());
			}

			// 5. Construimos el mensaje y lo enviamos a la cola
			objMensaje.setUuid(nuevaBusqueda.getUuid());
			objMensaje.setType("search");

			// 5.1. Enviamos el mensaje a la cola.
			ObjectMapper mapperObj = new ObjectMapper();
			String mensaje = mapperObj.writeValueAsString(objMensaje);
			this.enviarBusqueda(mensaje);

			// 6. Actualizamos el estado de busqueda
			nuevaBusqueda.setEstado(Busqueda.ESTADO_PROCESANDO);
			this.mBusquedaRepository.save(nuevaBusqueda);

			// 7. Retornamos salida
			response.setEstado(MSG_BUSCAR_001);
			response.setUuid(nuevaBusqueda.getUuid());
			response.setBusquedaExitosa(Boolean.TRUE);
			return ResponseEntity.status(HttpStatus.ACCEPTED).body(response);

		} catch (NegocioException e) {
			nuevaBusqueda.setEstado(Busqueda.ESTADO_FALLIDA);
			this.mBusquedaRepository.save(nuevaBusqueda);
			response.setEstado(e.getMensaje());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		} catch (Exception e) {
			if (nuevaBusqueda.getUuid() != null) {
				nuevaBusqueda.setEstado(Busqueda.ESTADO_FALLIDA);
				this.mBusquedaRepository.save(nuevaBusqueda);
			}
			response.setEstado(ERR_BUSCAR_004);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

	/**
	 * Enviar un mensaje a la cola con una busqueda especifica.
	 * 
	 * @param busqueda
	 * @throws NegocioException
	 */
	private void enviarBusqueda(String busqueda) throws NegocioException {

		// Obtenemos los parámetros requeridos para enviar el mensaje a la cola.
		Parametro parUrlConexion = mParametroRepository.findFirstByIdParametro(PAR_CONEXION_BUS_MENSAJES_AZURE);
		Parametro parNombreCola = mParametroRepository.findFirstByIdParametro(PAR_NOMBRE_COLA_BUSCAR);

		if (parUrlConexion != null && parNombreCola != null) {

			// Parámetros de conexión con la cola.
			String urlConexion = parUrlConexion.getValor();
			String nombreCola = parNombreCola.getValor();

			// Enviamos el mensaje a la cola.
			mColaMensajeService.enviarMensaje(urlConexion, nombreCola, busqueda);

		} else {
			// Si no encontró los parámetros de la petición.
			throw new NegocioException(ERR_BUSCAR_005);
		}

	}

}
