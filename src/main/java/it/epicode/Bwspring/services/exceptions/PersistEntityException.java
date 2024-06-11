package it.epicode.Bwspring.services.exceptions;


import it.epicode.Bwspring.services.dto.DtoBase;

/**
 * Eccezione nella persistenza di un dato.
 *
 */
public class PersistEntityException extends ServiceException {
	private static final long serialVersionUID = 1L;

	/**
	 * Dato per cui non è stato possibile il salvataggio.
	 */
	public final DtoBase invalidDto;

	public PersistEntityException(DtoBase invalidDto) {
		this.invalidDto = invalidDto;
	}
}
