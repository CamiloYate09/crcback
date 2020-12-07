package co.gov.crcom.mycroft.service;

import static co.gov.crcom.mycroft.util.ErrorMessageUtil.ERR_BUSCAR_006;
import static co.gov.crcom.mycroft.util.InfoMessageUtil.MSG_BUSCAR_002;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import co.gov.crcom.mycroft.model.entity.Tema;
import co.gov.crcom.mycroft.model.response.ListTemasSubtemasResponse;
import co.gov.crcom.mycroft.repository.TemaRepository;

@Service
public class ConsultarServiceImpl implements ConsultarService {

	@Autowired
	private TemaRepository mTemaRepository;

	/**
	 * @see ConsultarService#obtenerTemasYSubTemas()
	 */
	@Override
	public ResponseEntity<ListTemasSubtemasResponse> obtenerTemasYSubTemas() {

		ListTemasSubtemasResponse response = new ListTemasSubtemasResponse();
		try {

			// Obtenemos los temas de la base de datos.
			List<Tema> temas = mTemaRepository.findAll();

			// Construimos respuesta.
			response.setTemas(temas);
			response.setEstado(MSG_BUSCAR_002);
			response.setConsultaExitosa(Boolean.TRUE);

			// Retornamos respuesta.
			return ResponseEntity.status(HttpStatus.OK).body(response);

		} catch (Exception e) {
			response.setEstado(ERR_BUSCAR_006);
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(response);
		}
	}

}
