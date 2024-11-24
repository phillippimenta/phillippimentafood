package br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.outbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

public interface OrdemProducaoRepositoryPort {

    OrdemProducao salvar(OrdemProducao ordemProducao);

    Optional<OrdemProducao> obterPorPedidoId(UUID pedidoId);

    PaginaResposta<OrdemProducao> buscarPorStatusDataCriacaoPaginado(Status status, LocalDate dataCriacao, int numeroPagina, int tamanhoPagina);
}
