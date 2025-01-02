package it.unisa.uniclass.common.exceptions;

public class NotFoundUserException extends Exception {

    public NotFoundUserException() {
        super("Utente non trovato");
    }

    public NotFoundUserException(String message) {
        super(message);
    }
}
