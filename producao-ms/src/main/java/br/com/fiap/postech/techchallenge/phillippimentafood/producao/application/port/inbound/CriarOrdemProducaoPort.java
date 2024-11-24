package br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;

import java.util.UUID;

public interface CriarOrdemProducaoPort {

    OrdemProducao criarOrdemProducao(UUID pedidoIdExterno);
}
