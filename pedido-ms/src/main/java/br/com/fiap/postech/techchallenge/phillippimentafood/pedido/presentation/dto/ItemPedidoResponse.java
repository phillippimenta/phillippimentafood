package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

public record ItemPedidoResponse(
        Integer quantidade,
        String descricao,
        BigDecimal valor
) {

    public static ItemPedidoResponse fromModel(ItemPedido itemPedido) {
        return new ItemPedidoResponse(itemPedido.getQuantidade(), itemPedido.getDescricao(), itemPedido.getPreco());
    }

    public static List<ItemPedidoResponse> fromModel(List<ItemPedido> itemPedidoList) {
        return itemPedidoList.stream().map(ItemPedidoResponse::fromModel).toList();
    }
}
