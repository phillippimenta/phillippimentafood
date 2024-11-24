package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.exception;

import java.util.List;

public class DomainException extends RuntimeException {

    public DomainException(String mensagem) {
        super(mensagem);
    }

    public DomainException(List<String> mensagens) {
        super(String.join(", ", mensagens));
    }
}
