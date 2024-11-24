package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.exception;

import java.util.UUID;

public class PedidoNaoEncontradoException extends DomainException {

    public PedidoNaoEncontradoException(UUID uuid) {
        super(String.format("Pedido '%s' não encontrado.", uuid));
    }
}
