package it.epicode.Bwspring.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="ruoli")
@Builder(setterPrefix = "with")
@EqualsAndHashCode(callSuper = true)
public class Ruoli extends Base{
    private String nome;

}
