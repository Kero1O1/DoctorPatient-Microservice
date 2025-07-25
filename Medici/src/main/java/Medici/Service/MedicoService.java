package Medici.Service;

import Medici.DTO.MediciPazientiDTO;
import Medici.Entity.MedicoEntity;
import Medici.Repository.MedicoRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class MedicoService {

    private final MedicoRepository repository;
    private final RestTemplate restTemplate;

    public MedicoService(MedicoRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public MedicoEntity createMedico(MedicoEntity medico) {
        return repository.save(medico);
    }

    public Optional<MedicoEntity> getMedicoById(Long id) {
        return repository.findById(id);
    }

    public List<MedicoEntity> getAllMedici() {
        return repository.findAll();
    }

    public void deleteMedico(Long id) {
        repository.deleteById(id);
    }

    public MediciPazientiDTO getMedicoWithPazienti(Long medicoId) {
        MedicoEntity medico = repository.findById(medicoId)
                .orElseThrow(() -> new RuntimeException("Medico not found"));

        String url = "http://localhost:8082/api/v1/pazienti/byMedico/" + medicoId;
        ResponseEntity<List> response = restTemplate.getForEntity(url, List.class);

        MediciPazientiDTO dto = new MediciPazientiDTO();
        dto.setMedico(medico);
        dto.setPazienti(response.getBody());

        return dto;
    }
}
