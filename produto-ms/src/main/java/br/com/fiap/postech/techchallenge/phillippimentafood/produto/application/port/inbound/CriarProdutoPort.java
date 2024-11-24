package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

public interface CriarProdutoPort {

    Produto criarProduto(Produto produto);
}
