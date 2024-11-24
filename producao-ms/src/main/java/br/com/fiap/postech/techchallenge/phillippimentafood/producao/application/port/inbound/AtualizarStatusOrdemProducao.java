package br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;

import java.util.UUID;

public interface AtualizarStatusOrdemProducao {

    OrdemProducao atualizarStatusProducao(UUID pedidoId, Status novoStatus);
}
