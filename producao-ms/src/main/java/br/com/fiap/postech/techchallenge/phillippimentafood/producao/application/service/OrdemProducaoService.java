package br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound.AtualizarStatusOrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound.BuscarPorStatusDataCriacaoPaginadoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound.CriarOrdemProducaoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.outbound.OrdemProducaoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;

import java.time.LocalDate;
import java.util.UUID;

public class OrdemProducaoService implements AtualizarStatusOrdemProducao,
        BuscarPorStatusDataCriacaoPaginadoPort, CriarOrdemProducaoPort {

    private final OrdemProducaoRepositoryPort ordemProducaoRepositoryPort;

    public OrdemProducaoService(OrdemProducaoRepositoryPort ordemProducaoRepositoryPort) {
        this.ordemProducaoRepositoryPort = ordemProducaoRepositoryPort;
    }

    @Override
    public OrdemProducao atualizarStatusProducao(UUID pedidoId, Status novoStatus) {
        return null;
    }

    @Override
    public PaginaResposta<OrdemProducao> buscarPorStatusDataCriacaoPaginado(Status status, LocalDate dataCriacao, int numeroPagina) {
        return ordemProducaoRepositoryPort.buscarPorStatusDataCriacaoPaginado(status, dataCriacao, numeroPagina, 20);
    }

    @Override
    public OrdemProducao criarOrdemProducao(UUID pedidoIdExterno) {
        OrdemProducao ordemProducao = new OrdemProducao(pedidoIdExterno);
        return ordemProducaoRepositoryPort.salvar(ordemProducao);
    }
}
