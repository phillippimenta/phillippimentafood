package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Status;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public record PedidoResponse(
        UUID uuid,
        UUID cliente,
        Status status,
        List<ItemPedidoResponse> itensPedido,
        BigDecimal valorTotal,
        LocalDateTime dataCriacao,
        LocalDateTime dataAtualizacao
) {
    public static PedidoResponse fromModel(Pedido pedido) {
        return new PedidoResponse(pedido.getIdExterno(), pedido.getCliente(), pedido.getStatus(), ItemPedidoResponse.fromModel(pedido.getItens()), pedido.getTotal(), pedido.getDataCriacao(), pedido.getDataAtualizacao());
    }

    public static List<PedidoResponse> fromModel(List<Pedido> pedidoList) {
        return pedidoList.stream().map(PedidoResponse::fromModel).toList();
    }
}
