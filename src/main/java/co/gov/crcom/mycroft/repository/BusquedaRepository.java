package co.gov.crcom.mycroft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.gov.crcom.mycroft.model.entity.Busqueda;


public interface BusquedaRepository extends JpaRepository<Busqueda, String>, JpaSpecificationExecutor<Busqueda> {

}
