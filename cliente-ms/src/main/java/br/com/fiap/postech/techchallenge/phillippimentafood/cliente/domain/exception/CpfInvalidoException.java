package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

public class CpfInvalidoException extends DomainException {

    public CpfInvalidoException() {
        super("CPF inválido");
    }
}
