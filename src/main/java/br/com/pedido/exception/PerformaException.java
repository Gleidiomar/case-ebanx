package br.com.pedido.exception;


import lombok.Getter;


@Getter
public class PerformaException extends RuntimeException {

    private final String[] args;

    public PerformaException(String message, String... args) {
        super(message);
        this.args = args;
    }

    public PerformaException(String message, Throwable cause, String... args) {
        super(message, cause);
        this.args = args;
    }
}