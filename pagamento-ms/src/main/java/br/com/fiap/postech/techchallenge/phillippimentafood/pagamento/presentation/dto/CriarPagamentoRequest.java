package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.MetodoPagamento;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;

import java.math.BigDecimal;
import java.util.UUID;

public record CriarPagamentoRequest(
        BigDecimal valor,
        UUID pedido,
        MetodoPagamento metodoPagamento
) {
    public static Pagamento toModel(CriarPagamentoRequest request) {
        return new Pagamento(request.pedido(), request.valor(), request.metodoPagamento());
    }
}
