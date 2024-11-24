package br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;

import java.time.LocalDate;

public interface BuscarPorStatusDataCriacaoPaginadoPort {

    PaginaResposta<OrdemProducao> buscarPorStatusDataCriacaoPaginado(Status status, LocalDate dataCriacao, int numeroPagina);
}
