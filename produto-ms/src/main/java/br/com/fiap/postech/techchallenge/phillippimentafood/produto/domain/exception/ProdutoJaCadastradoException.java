package br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

public class ProdutoJaCadastradoException extends DomainException {

    public ProdutoJaCadastradoException(Produto produto) {
        super(String.format("Produto %s já cadastrado.", produto.getNome()));
    }
}
