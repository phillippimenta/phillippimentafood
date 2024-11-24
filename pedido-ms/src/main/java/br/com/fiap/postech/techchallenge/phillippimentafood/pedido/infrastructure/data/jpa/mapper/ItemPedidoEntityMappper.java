package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.mapper;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.ItemPedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity.ItemPedidoEntity;

import java.util.List;

public class ItemPedidoEntityMappper {

    public ItemPedido toDomain(ItemPedidoEntity entity) {
        if (entity == null) return null;
        ItemPedido model = new ItemPedido(entity.getNome(), entity.getDescricao(), entity.getPreco(), entity.getQuantidade());
        model.setId(entity.getId());
        return model;
    }

    public List<ItemPedido> toDomain(List<ItemPedidoEntity> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream().map(this::toDomain).toList();
    }

    public ItemPedidoEntity fromDomain(ItemPedido model) {
        if (model == null) return null;
        ItemPedidoEntity entity = new ItemPedidoEntity();
        entity.setId(model.getId());
        entity.setNome(model.getNome());
        entity.setDescricao(model.getDescricao());
        entity.setPreco(model.getPreco());
        entity.setQuantidade(model.getQuantidade());
        return entity;
    }

    public List<ItemPedidoEntity> fromDomain(List<ItemPedido> modelList) {
        if (modelList == null) return List.of();
        return modelList.stream().map(this::fromDomain).toList();
    }
}
