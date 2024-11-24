package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Status;

import java.util.List;
import java.util.UUID;

public record NovoPedidoRequest(
        UUID cliente,
        List<ItemPedidoRequest> itensPedido
) {
    public static Pedido toModel(NovoPedidoRequest novoPedidoRequest) {
        return new Pedido(novoPedidoRequest.cliente(), Status.PENDENTE, ItemPedidoRequest.toDomain(novoPedidoRequest.itensPedido()));
    }
}
