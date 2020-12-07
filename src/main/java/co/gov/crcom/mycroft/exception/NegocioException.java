package co.gov.crcom.mycroft.exception;

import co.gov.crcom.mycroft.model.response.MessageResponse;

public class NegocioException extends Throwable {

	private static final long serialVersionUID = 1L;

	private MessageResponse mensaje;

	/**
	 * @param message mensaje de error
	 */
	public NegocioException(String message) {
		super(message);
		mensaje = new MessageResponse(message);
	}
	
	/**
	 * @param message mensaje de error
	 */
	public NegocioException(MessageResponse message) {
		super(message.getMessage());
		mensaje = message;
	}

	/**
	 * @param message
	 * @param cause
	 */
	public NegocioException(String message, Exception cause) {
		super(message, cause);
	}

	/**
	 * @param cause
	 */
	public NegocioException(Exception cause) {
		super(cause);
	}

	/**
	 * Permite adicionar un error
	 *
	 * @param error
	 */
	public void addError(MessageResponse error) {
		mensaje = error;
	}

	/**
	 * Indica si tiene errores
	 *
	 * @return
	 */
	public boolean hasErrors() {
		return mensaje != null;
	}

	public String getErrorMessage() {
		return super.getMessage();
	}

	public MessageResponse getMensaje() {
		return mensaje;
	}
	
}
