package co.gov.crcom.mycroft.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import co.gov.crcom.mycroft.model.entity.Tema;

public interface TemaRepository extends JpaRepository<Tema, Integer>, JpaSpecificationExecutor<Tema> {

}
