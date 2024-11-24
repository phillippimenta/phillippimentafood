package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.mapper;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.ItemPedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity.PedidoEntity;

import java.util.List;

public class PedidoEntityMapper {

    private final ItemPedidoEntityMappper itemPedidoEntityMappper = new ItemPedidoEntityMappper();

    public Pedido toDomain(PedidoEntity entity) {
        if (entity == null) return null;
        List<ItemPedido> itensPedido = entity.getItens().stream().map(itemPedidoEntityMappper::toDomain).toList();
        Pedido model = new Pedido(entity.getCliente(), entity.getStatus(), itensPedido);
        model.setId(entity.getId());
        model.setIdExterno(entity.getIdExterno());
        model.setDataCriacao(entity.getDataCriacao());
        model.setDataAtualizacao(entity.getDataAtualizacao());
        model.setTotal(entity.getTotal());
        return model;
    }

    public List<Pedido> toDomain(List<PedidoEntity> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream().map(this::toDomain).toList();
    }

    public PedidoEntity fromDomain(Pedido model) {
        if (model == null) return null;
        PedidoEntity entity = new PedidoEntity();
        entity.setId(model.getId());
        entity.setIdExterno(model.getIdExterno());
        entity.setStatus(model.getStatus());
        entity.setDataCriacao(model.getDataCriacao());
        entity.setDataAtualizacao(model.getDataAtualizacao());
        entity.setTotal(model.getTotal());
        entity.setCliente(model.getCliente());
        entity.setItens(itemPedidoEntityMappper.fromDomain(model.getItens()));
        entity.getItens().forEach(itemPedidoEntity -> itemPedidoEntity.setPedido(entity));
        return entity;
    }

    public List<PedidoEntity> fromDomain(List<Pedido> modelList) {
        if (modelList == null) return List.of();
        return modelList.stream().map(this::fromDomain).toList();
    }
}
