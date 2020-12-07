package co.gov.crcom.mycroft.service;

import org.springframework.http.ResponseEntity;

import co.gov.crcom.mycroft.model.request.LoginRequest;
import co.gov.crcom.mycroft.model.response.LoginResponse;

/**
 * Servicio para controlar lo correspondiente al login de la aplicación
 * 
 * @author Iván Díaz
 *
 */
public interface LoginService {
	
	/**
	 * Función encargada de verificar los datos de autenticación de un usuario.
	 * 
	 * @param request obkjeto que trae los datos para autenticar
	 * @return 
	 */
	public ResponseEntity<LoginResponse> autenticarUsuario(LoginRequest request);

}
