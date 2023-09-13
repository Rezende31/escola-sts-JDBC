package com.projectJDBC.escola.exception;

public class TurmaExistenteException extends RuntimeException {

	public TurmaExistenteException() {
        super();
    }

    public TurmaExistenteException(String message) {
        super(message);
    }

    public TurmaExistenteException(String message, Throwable cause) {
        super(message, cause);
    }
}
