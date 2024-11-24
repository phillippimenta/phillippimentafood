package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import java.util.UUID;

public interface ExcluirProdutoPorIdExternoPort {

    void excluirProdutoPorIdExterno(UUID idExterno);
}
