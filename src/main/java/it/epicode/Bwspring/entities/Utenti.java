package it.epicode.Bwspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;
@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name="utenti")
@Data
@AllArgsConstructor
@NoArgsConstructor

public class Utenti extends Base{
    private String username;
    private String email;
    private String password;
    private String nome;
    private String cognome;
    @OneToMany(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name = "ruolo_id")
    private List<Ruoli> ruoli;
    //trovate modo per upload immagine pd
    //private String avatar;


}
