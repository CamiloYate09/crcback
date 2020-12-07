package co.gov.crcom.mycroft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.crcom.mycroft.model.request.LoginRequest;
import co.gov.crcom.mycroft.model.response.LoginResponse;
import co.gov.crcom.mycroft.service.LoginService;

@RestController
@RequestMapping("/api/public")
public class LoginController {

	@Autowired
	private LoginService mLoginService;
	
	@PostMapping("/login")
    public ResponseEntity<LoginResponse> autenticarUsuario(@RequestBody LoginRequest peticion) {
        return mLoginService.autenticarUsuario(peticion);
    }

}
