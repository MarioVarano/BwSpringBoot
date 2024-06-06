package it.epicode.Bwspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="utenti")
@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor

public class Utenti extends Base{
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "utente_id")
    private List<Ruoli> ruoli;

    //trovate modo per upload immagine pd
    private String avatar;


}
