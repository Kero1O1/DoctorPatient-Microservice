package Pazienti.Repository;

import Pazienti.Entity.PazienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PazienteRepository extends JpaRepository<PazienteEntity, Long> {
    List<PazienteEntity> findByMedicoId(Long medicoId);

}
