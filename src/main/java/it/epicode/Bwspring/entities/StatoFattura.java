package it.epicode.Bwspring.entities;


import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="stato_fatture")
@EqualsAndHashCode(callSuper = true)
public class StatoFattura extends Base{
    private String stato;
}
