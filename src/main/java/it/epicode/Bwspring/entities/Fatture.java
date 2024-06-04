package it.epicode.Bwspring.entities;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fatture")
@Data
@Builder(setterPrefix = "with")
@AllArgsConstructor
@NoArgsConstructor
public class Fatture extends Base{
    private LocalDate data;
    private double importo;
    private Long numero;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="statoFattura_id")
    private StatoFattura stato;
}
