package it.epicode.Bwspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "province")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Provincia extends Base{
    private String sigla;

    private String provincia;
    private String regione;
    @OneToMany(mappedBy = "provincia")
    private List<Comune> comuni;

    public Provincia(String sigla, String provincia, String regione) {
        this.sigla = sigla;
        this.provincia = provincia;
        this.regione = regione;
    }
}
