package Medici.Controller;

import Medici.Entity.MedicoEntity;
import Medici.DTO.MediciPazientiDTO;
import Medici.Service.MedicoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/medici")
public class MedicoController {

    private final MedicoService service;

    public MedicoController(MedicoService service) {
        this.service = service;
    }

    // POST
    @PostMapping
    public ResponseEntity<MedicoEntity> createMedico(@RequestBody MedicoEntity medico) {
        return ResponseEntity.ok(service.createMedico(medico));
    }

    // GET all
    @GetMapping
    public ResponseEntity<List<MedicoEntity>> getAllMedici() {
        return ResponseEntity.ok(service.getAllMedici());
    }

    // GET by ID
    @GetMapping("/{id}")
    public ResponseEntity<MedicoEntity> getMedicoById(@PathVariable Long id) {
        return service.getMedicoById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    // PUT (update entire medico)
    @PutMapping("/{id}")
    public ResponseEntity<MedicoEntity> updateMedico(@PathVariable Long id, @RequestBody MedicoEntity updated) {
        Optional<MedicoEntity> existing = service.getMedicoById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        MedicoEntity medico = existing.get();
        medico.setName(updated.getName());
        medico.setSpecialty(updated.getSpecialty());

        return ResponseEntity.ok(service.createMedico(medico));
    }

    // PATCH (update partial)
    @PatchMapping("/{id}")
    public ResponseEntity<MedicoEntity> patchMedico(@PathVariable Long id, @RequestBody MedicoEntity partial) {
        Optional<MedicoEntity> existing = service.getMedicoById(id);
        if (existing.isEmpty()) return ResponseEntity.notFound().build();

        MedicoEntity medico = existing.get();

        if (partial.getName() != null) {
            medico.setName(partial.getName());
        }
        if (partial.getSpecialty() != null) {
            medico.setSpecialty(partial.getSpecialty());
        }

        return ResponseEntity.ok(service.createMedico(medico));
    }

    // DELETE
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMedico(@PathVariable Long id) {
        service.deleteMedico(id);
        return ResponseEntity.noContent().build();
    }

    // GET medico + pazienti
    @GetMapping("/{id}/pazienti")
    public ResponseEntity<MediciPazientiDTO> getMedicoWithPazienti(@PathVariable Long id) {
        return ResponseEntity.ok(service.getMedicoWithPazienti(id));
    }
}
