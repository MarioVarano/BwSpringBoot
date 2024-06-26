package it.epicode.Bwspring.entities;
//


import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "clienti")
@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor

public class Cliente extends Base{

    private Long ragioneSociale;
    private Long partitaIva;
    private String email;
    private LocalDate dataUltimoContatto;
    private Double fatturatoAnnuale;
    private String pec;
    private Long telefono;
    private String emailContatto;
    private String nomeContatto;
    private String cognomeContatto;
    private Long telefonoContatto;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="indirizzo_legale_id")
    private Indirizzi indirizzoLegale;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="indirizzo_operativa_id")
    private Indirizzi indirizzoOperativa;


//costruttore ne caso cliente abbia solo un indirizzo che li assegna uguali nel caso
    public Cliente(Long ragioneSociale, Long partitaIva, String email, LocalDate dataUltimoContatto, Double fatturatoAnnuale, String pec, Long telefono, String emailContatto, String nomeContatto, String cognomeContatto, Long telefonoContatto,  Indirizzi indirizzoOperativa) {
        this.ragioneSociale = ragioneSociale;
        this.partitaIva = partitaIva;
        this.email = email;
        this.dataUltimoContatto = dataUltimoContatto;
        this.fatturatoAnnuale = fatturatoAnnuale;
        this.pec = pec;
        this.telefono = telefono;
        this.emailContatto = emailContatto;
        this.nomeContatto = nomeContatto;
        this.cognomeContatto = cognomeContatto;
        this.telefonoContatto = telefonoContatto;
        this.indirizzoLegale = indirizzoOperativa;
        this.indirizzoOperativa = indirizzoOperativa;

    }
//    private ? logoAziendale;
}
