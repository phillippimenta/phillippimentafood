package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.mapper;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.entity.OrdemProducaoEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class OrdemProducaoEntityMapper {

    public OrdemProducao toDomain(OrdemProducaoEntity entity) {
        OrdemProducao model = new OrdemProducao(entity.getPedidoId());
        model.setId(entity.getId());
        model.setIdExterno(entity.getIdExterno());
        model.setStatus(entity.getStatus());
        model.setDataCriacao(entity.getDataCriacao());
        model.setDataAtualizacao(entity.getDataAtualizacao());
        return model;
    }

    public List<OrdemProducao> toDomain(List<OrdemProducaoEntity> entityList) {
        return entityList.stream().map(this::toDomain).toList();
    }

    public OrdemProducaoEntity toEntity(OrdemProducao domain) {
        OrdemProducaoEntity entity = new OrdemProducaoEntity();
        entity.setId(domain.getId());
        entity.setIdExterno(domain.getIdExterno());
        entity.setPedidoId(domain.getPedidoIdExterno());
        entity.setStatus(domain.getStatus());
        entity.setDataCriacao(domain.getDataCriacao());
        entity.setDataAtualizacao(domain.getDataAtualizacao());
        return entity;
    }
}
