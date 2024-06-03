package it.epicode.Bwspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;


@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "comuni")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comune extends Base{
    private String codice;
    private String nome;
    @ManyToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    private Provincia provincia;

    public Comune(String codice, String nome) {
        this.codice = codice;
        this.nome = nome;

    }
}
