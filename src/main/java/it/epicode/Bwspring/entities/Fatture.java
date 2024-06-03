package it.epicode.Bwspring.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@EqualsAndHashCode(callSuper = true)
@Entity
@Table(name = "fatture")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Fatture extends Base{
    private LocalDate data;
    private Double importo;
    private Long numero;
    @OneToOne(cascade = CascadeType.PERSIST, fetch = FetchType.EAGER)
    @JoinColumn(name="statoFattura_id")
    private StatoFattura stato;
}
