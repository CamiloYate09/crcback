package co.gov.crcom.mycroft.util;

import co.gov.crcom.mycroft.model.response.MessageResponse;

public class ErrorMessageUtil {
	
	
	public static final MessageResponse ERR_LOGIN_001 = new MessageResponse("Login - Usuario y/o contraseña incorrecta.");
	public static final MessageResponse ERR_LOGIN_002 = new MessageResponse("Login - No se encuentran los parámetros requeridos para el proceso.");
	
	public static final MessageResponse ERR_BUSCAR_001 = new MessageResponse("Búsqueda - Debe enviar una petición válida.");
	public static final MessageResponse ERR_BUSCAR_002 = new MessageResponse("Búsqueda - Debe enviar el nombre del archivo.");
	public static final MessageResponse ERR_BUSCAR_003 = new MessageResponse("Búsqueda - Debe enviar el archivo.");
	
	public static final MessageResponse ERR_BUSCAR_004 = new MessageResponse("Búsqueda - Error general al procesar la búsqueda por archivo.");
	public static final MessageResponse ERR_BUSCAR_005 = new MessageResponse("Búsqueda - No se encuentran los parámetros requeridos para el proceso.");
	
	public static final MessageResponse ERR_BUSCAR_006 = new MessageResponse("Temas - No se han podido obtener los temas de la aplicación.");
	
	public static final MessageResponse ERR_BUSCAR_007 = new MessageResponse("Búsqueda - Debe enviar un criterio de búsqueda válido.");
	
	public static final MessageResponse ERR_BUSCAR_008 = new MessageResponse("Búsqueda - Debe enviar temas/subtemas válidos.");

}
