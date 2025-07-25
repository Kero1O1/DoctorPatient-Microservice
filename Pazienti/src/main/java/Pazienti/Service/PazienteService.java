package Pazienti.Service;

import Pazienti.Entity.PazienteEntity;
import Pazienti.Repository.PazienteRepository;
import Pazienti.DTO.MedicoDTO;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Optional;

@Service
public class PazienteService {

    private final PazienteRepository repository;
    private final RestTemplate restTemplate;

    public PazienteService(PazienteRepository repository, RestTemplate restTemplate) {
        this.repository = repository;
        this.restTemplate = restTemplate;
    }

    public PazienteEntity save(PazienteEntity paziente) {
        return repository.save(paziente);
    }

    public Optional<PazienteEntity> getById(Long id) {
        return repository.findById(id);
    }

    public MedicoDTO getMedicoByPazienteId(Long pazienteId) {
        PazienteEntity paziente = repository.findById(pazienteId).orElseThrow();
        String url = "http://localhost:8081/api/v1/medici/" + paziente.getMedicoId();
        return restTemplate.getForObject(url, MedicoDTO.class);
    }

    public List<PazienteEntity> getByMedicoId(Long medicoId) {
        return repository.findByMedicoId(medicoId);
    }

}
