package Pazienti.DTO;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MedicoDTO {
    private Long id;
    private String name;
    private String specialty;
}
