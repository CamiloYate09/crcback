package co.gov.crcom.mycroft.service;

import java.util.List;

public interface ColaMensajeService {

	/**
	 * Función encargada de enviar un mensaje a una cola de bus de mensajes de
	 * Azure.
	 * 
	 * @param urlConexion endpoint de conexión, con su nombre, llave, etc.
	 * @param nombreCola  nombre de la cola en la que se pondrá el mensaje
	 * @param mensaje     mensaje que se dejará en la cola
	 */
	public void enviarMensaje(String urlConexion, String nombreCola, String mensaje);

	/**
	 * Función encargada de recibir los mensajes que estén activos en la cola de
	 * Azure.
	 * 
	 * @param urlConexion endpoint de conexión, con su nombre, llave, etc.
	 * @param nombreCola  nombre de la cola en la que se pondrá el mensaje
	 */
	public List<String> recibirMensaje(String urlConexion, String nombreCola);

}
