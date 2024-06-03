package it.epicode.Bwspring.entities;
//


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clienti")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Cliente extends Base{

    private Long ragioneSociale;
    private Long partivaIva;
    private String email;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private Long telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContato;
    private Long telefonoContatto;
//    private ? logoAziendale;
}
