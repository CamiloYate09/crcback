package co.gov.crcom.mycroft.model.response;

import java.util.List;

public class LoginResponse {

	private String jwt;

	private MessageResponse estado;

	private List<String> roles;

	private Boolean loginExitoso;

	public String getJwt() {
		return jwt;
	}

	public void setJwt(String jwt) {
		this.jwt = jwt;
	}

	public MessageResponse getEstado() {
		return estado;
	}

	public void setEstado(MessageResponse estado) {
		this.estado = estado;
	}

	public List<String> getRoles() {
		return roles;
	}

	public void setRoles(List<String> roles) {
		this.roles = roles;
	}

	public Boolean getLoginExitoso() {
		return loginExitoso;
	}

	public void setLoginExitoso(Boolean loginExitoso) {
		this.loginExitoso = loginExitoso;
	}

}
