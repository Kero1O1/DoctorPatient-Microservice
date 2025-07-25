package Medici.DTO;

import Medici.Entity.MedicoEntity;
import java.util.List;


public class MediciPazientiDTO {
    private MedicoEntity medico;
    private List<Object> pazienti; // You can replace Object with a real PazienteDTO if needed

    public MedicoEntity getMedico() {
        return medico;
    }

    public void setMedico(MedicoEntity medico) {
        this.medico = medico;
    }

    public List<Object> getPazienti() {
        return pazienti;
    }

    public void setPazienti(List<Object> pazienti) {
        this.pazienti = pazienti;
    }
}
