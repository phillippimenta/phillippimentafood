package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.mapper;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.entity.ProdutoEntity;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ProdutoEntityMapper {

    public Produto toDomain(ProdutoEntity entity) {
        if (entity == null) return null;
        Produto model = new Produto(entity.getCategoria(), entity.getNome(), entity.getDescricao(), entity.getPreco());
        model.setId(entity.getId());
        model.setIdExterno(entity.getIdExterno());
        model.setDataCriacao(entity.getDataCriacao());
        model.setDataAtualizacao(entity.getDataAtualizacao());
        return model;
    }

    public List<Produto> toDomain(List<ProdutoEntity> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream().map(this::toDomain).collect(Collectors.toList());
    }

    public ProdutoEntity fromDomain(Produto model) {
        if (model == null) return null;
        ProdutoEntity entity = new ProdutoEntity();
        entity.setId(model.getId());
        entity.setIdExterno(model.getIdExterno());
        entity.setCategoria(model.getCategoria());
        entity.setNome(model.getNome());
        entity.setDescricao(model.getDescricao());
        entity.setPreco(model.getPreco());
        entity.setDataCriacao(model.getDataCriacao());
        entity.setDataAtualizacao(model.getDataAtualizacao());
        return entity;
    }

    public List<ProdutoEntity> fromDomain(List<Produto> modelList) {
        if (modelList == null) return List.of();
        return modelList.stream().map(this::fromDomain).collect(Collectors.toList());
    }
}
