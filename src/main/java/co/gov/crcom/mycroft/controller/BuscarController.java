package co.gov.crcom.mycroft.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import co.gov.crcom.mycroft.model.request.BuscarArchivoRequest;
import co.gov.crcom.mycroft.model.request.BuscarPalabraRequest;
import co.gov.crcom.mycroft.model.request.BuscarTemaRequest;
import co.gov.crcom.mycroft.model.response.BusquedaResponse;
import co.gov.crcom.mycroft.service.BuscarService;
import co.gov.crcom.mycroft.util.ConfigUtil;

@RestController
@RequestMapping("/api/buscar")
public class BuscarController {
	
	@Autowired
	private BuscarService mBuscarService;

	@PreAuthorize(ConfigUtil.SS_ROL_CONSULTA)
	@PostMapping("/archivo")
	public ResponseEntity<BusquedaResponse> buscarPorArchivo(@RequestBody BuscarArchivoRequest peticion) {
		return mBuscarService.buscarPorArchivo(peticion);
	}
	
	@PreAuthorize(ConfigUtil.SS_ROL_CONSULTA)
	@PostMapping("/palabra")
	public ResponseEntity<BusquedaResponse> buscarPorPalabra(@RequestBody BuscarPalabraRequest peticion) {
		return mBuscarService.buscarPorPalabra(peticion);
	}
	
	@PreAuthorize(ConfigUtil.SS_ROL_CONSULTA)
	@PostMapping("/tema")
	public ResponseEntity<BusquedaResponse> buscarPorTema(@RequestBody BuscarTemaRequest peticion) {
		return mBuscarService.buscarPorTema(peticion);
	}

}
