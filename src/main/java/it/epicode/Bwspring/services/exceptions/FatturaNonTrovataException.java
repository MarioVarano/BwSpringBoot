package it.epicode.Bwspring.services.exceptions;

public class FatturaNonTrovataException extends RuntimeException{
    public FatturaNonTrovataException(String message) {
        super(message);
    }
}
