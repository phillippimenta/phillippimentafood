package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.ItemPedido;

import java.math.BigDecimal;
import java.util.List;

public record ItemPedidoRequest(
        String nome,
        String descricao,
        BigDecimal preco,
        Integer quantidade
) {

    public static ItemPedido toDomain(ItemPedidoRequest itemPedido) {
        return new ItemPedido(itemPedido.nome(), itemPedido.descricao(), itemPedido.preco(), itemPedido.quantidade());
    }

    public static List<ItemPedido> toDomain(List<ItemPedidoRequest> itemPedidoList) {
        return itemPedidoList.stream().map(ItemPedidoRequest::toDomain).toList();
    }
}
