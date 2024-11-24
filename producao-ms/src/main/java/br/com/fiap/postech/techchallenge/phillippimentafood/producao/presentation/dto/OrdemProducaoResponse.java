package br.com.fiap.postech.techchallenge.phillippimentafood.producao.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record OrdemProducaoResponse(
        @JsonProperty("id_externo")
        UUID idExterno,
        @JsonProperty("pedido_id")
        UUID pedidoId,
        @JsonProperty("status")
        Status status,
        @JsonProperty("data_criacao")
        LocalDateTime dataCriacao,
        @JsonProperty("data_atualizacao")
        LocalDateTime dataAtualizacao
) {
    public static OrdemProducaoResponse from(OrdemProducao model) {
        return new OrdemProducaoResponse( model.getIdExterno(), model.getPedidoIdExterno(), model.getStatus(),
                model.getDataCriacao(), model.getDataAtualizacao());
    }

    public static PaginaResposta<OrdemProducaoResponse> from(PaginaResposta<OrdemProducao> paginaResposta) {
        return new PaginaResposta<>(
                paginaResposta.getConteudo().stream().map(OrdemProducaoResponse::from).toList(),
                paginaResposta.getNumeroPagina(),
                paginaResposta.getTamanhoPagina(),
                paginaResposta.getTotalElementos(),
                paginaResposta.getTotalPaginas()
        );
    }

    public static List<OrdemProducaoResponse> from(List<OrdemProducao> models) {
        return models.stream().map(OrdemProducaoResponse::from).toList();
    }
}
