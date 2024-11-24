package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

import java.util.UUID;

public interface ObterProdutoPorIdExternoPort {

    Produto obterProdutoPorIdExterno(UUID uuid);
}
