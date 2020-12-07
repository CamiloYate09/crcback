package co.gov.crcom.mycroft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.crcom.mycroft.model.response.ListTemasSubtemasResponse;
import co.gov.crcom.mycroft.service.ConsultarService;
import co.gov.crcom.mycroft.util.ConfigUtil;

@RestController
@RequestMapping("/api/consultar")
public class ConsultarController {

	@Autowired
	private ConsultarService mConsultarService;

	@PreAuthorize(ConfigUtil.SS_ROL_CONSULTA)
	@GetMapping("/temas")
	public ResponseEntity<ListTemasSubtemasResponse> autenticarUsuario() {
		return mConsultarService.obtenerTemasYSubTemas();
	}

}
