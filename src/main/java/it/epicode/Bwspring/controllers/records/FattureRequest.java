package it.epicode.Bwspring.controllers.records;

import it.epicode.Bwspring.entities.StatoFattura;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record FattureRequest(
@NotBlank(message = "La data deve esserci")
LocalDate data,
@NotBlank(message = "Importo necessario")
Double importo,
@NotNull(message = "Numero fattura necessario")
Long numero,
@NotBlank(message = "Codice cliente necessario")
Long codiceCliente,
@NotBlank(message = "Codice fornitore necessario")
Long codiceFornitore,
@NotBlank(message = "Stato fattura obbligatorio")
StatoFattura stato) {
}
