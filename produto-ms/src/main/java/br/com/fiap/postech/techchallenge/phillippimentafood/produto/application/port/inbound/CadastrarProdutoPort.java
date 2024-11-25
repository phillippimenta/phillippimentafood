package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

public interface CadastrarProdutoPort {

    Produto cadastrarProduto(Produto produto);
}
