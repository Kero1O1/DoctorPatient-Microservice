package Medici.Repository;

import Medici.Entity.MedicoEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MedicoRepository extends JpaRepository<MedicoEntity, Long> {
}
