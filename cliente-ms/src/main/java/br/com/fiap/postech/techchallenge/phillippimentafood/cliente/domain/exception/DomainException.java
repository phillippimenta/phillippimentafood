package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

public class DomainException extends RuntimeException {

    public DomainException(String mensagem) {
        super(mensagem);
    }
}
