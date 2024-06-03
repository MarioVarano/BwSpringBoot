package it.epicode.Bwspring.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "Indirizzi")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Indirizzi extends Base{

    private String via;
    private Integer civico;
    private String localita;
    private Long cap;
    private String comune;

}
