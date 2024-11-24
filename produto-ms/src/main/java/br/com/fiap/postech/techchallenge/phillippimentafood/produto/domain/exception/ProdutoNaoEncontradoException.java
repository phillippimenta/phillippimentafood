package br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception;

import java.util.UUID;

public class ProdutoNaoEncontradoException extends DomainException {

    public ProdutoNaoEncontradoException(UUID uuid) {
        super(String.format("Produto '%s' não encontrado.", uuid));
    }
}
