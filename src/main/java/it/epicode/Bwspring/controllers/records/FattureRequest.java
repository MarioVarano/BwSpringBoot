package it.epicode.Bwspring.controllers.records;

import it.epicode.Bwspring.entities.Cliente;
import it.epicode.Bwspring.entities.StatoFattura;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FattureRequest(
@NotNull(message = "La data deve esserci")
LocalDate data,
@NotNull(message = "Importo necessario")
double importo,
@NotNull(message = "Numero fattura necessario")
Long numero,
@NotNull(message = "Stato fattura obbligatorio")
StatoFattura stato,
@NotNull(message = "Cliente necessario per emettere fattura")
Cliente cliente) {
}
