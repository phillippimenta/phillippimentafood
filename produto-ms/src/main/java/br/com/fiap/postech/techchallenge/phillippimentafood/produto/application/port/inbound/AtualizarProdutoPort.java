package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

import java.util.UUID;

public interface AtualizarProdutoPort {

    Produto atualizarProduto(UUID idExterno, Produto produtoAtualizado);
}
