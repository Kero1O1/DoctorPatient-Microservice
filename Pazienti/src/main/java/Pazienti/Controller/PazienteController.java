package Pazienti.Controller;

import Pazienti.DTO.MedicoDTO;
import Pazienti.Entity.PazienteEntity;
import Pazienti.Service.PazienteService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/pazienti")
public class PazienteController {

    private final PazienteService service;

    public PazienteController(PazienteService service) {
        this.service = service;
    }

    @GetMapping("/test")
    public String test() {
        return "Pazienti Service is UP";
    }

    @PostMapping
    public ResponseEntity<PazienteEntity> create(@RequestBody PazienteEntity paziente) {
        return ResponseEntity.ok(service.save(paziente));
    }

    @GetMapping("/{id}")
    public ResponseEntity<PazienteEntity> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping("/{id}/medico")
    public ResponseEntity<MedicoDTO> getMedicoForPaziente(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMedicoByPazienteId(id));
    }

    @GetMapping("/byMedico/{medicoId}")
    public ResponseEntity<List<PazienteEntity>> getPazientiByMedicoId(@PathVariable Long medicoId) {
        return ResponseEntity.ok(service.getByMedicoId(medicoId));
    }

}
