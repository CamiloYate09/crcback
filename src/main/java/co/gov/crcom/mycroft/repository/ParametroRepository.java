package co.gov.crcom.mycroft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.gov.crcom.mycroft.model.entity.Parametro;

public interface ParametroRepository extends JpaRepository<Parametro, String>, JpaSpecificationExecutor<Parametro> {

	/**
	 * Buscar un parámetro segun su identificador.
	 * 
	 * @param idParametro
	 * @return
	 */
	public Parametro findFirstByIdParametro(String idParametro);

}
