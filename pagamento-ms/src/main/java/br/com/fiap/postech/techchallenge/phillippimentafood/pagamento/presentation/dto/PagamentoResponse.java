package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.MetodoPagamento;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Status;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public record PagamentoResponse(
        @JsonProperty("id_externo")
        UUID idExterno,
        @JsonProperty("valor")
        BigDecimal valor,
        @JsonProperty("pedido_id_externo")
        UUID pedido,
        @JsonProperty("status")
        Status status,
        @JsonProperty("metodo_pagamento")
        MetodoPagamento metodoPagamento,
        @JsonProperty("data_criacao")
        LocalDateTime dataCriacao
) {
    public static PagamentoResponse fromModel(Pagamento pagamento) {
        return new PagamentoResponse(
                pagamento.getIdExterno(),
                pagamento.getValor(),
                pagamento.getPedidoIdExterno(),
                pagamento.getStatus(),
                pagamento.getMetodoPagamento(),
                pagamento.getDataCriacao()
        );
    }
}
