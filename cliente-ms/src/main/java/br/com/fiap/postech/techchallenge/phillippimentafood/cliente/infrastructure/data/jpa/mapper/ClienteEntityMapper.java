package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.mapper;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Email;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class ClienteEntityMapper {

    public Cliente toDomain(ClienteEntity entity) {
        if (entity == null) return null;
        Cliente model = new Cliente(new CPF(entity.getCpf()), entity.getNome(), new Email(entity.getEmail()));
        model.setId(entity.getId());
        model.setIdExterno(entity.getIdExterno());
        model.setAnonimo(entity.getAnonimo());
        model.setDataCriacao(entity.getDataCriacao());
        model.setDataAtualizacao(entity.getDataAtualizacao());
        return model;
    }

    public List<Cliente> toDomain(List<ClienteEntity> entityList) {
        if (entityList == null) return List.of();
        return entityList.stream().map(this::toDomain).toList();
    }

    public ClienteEntity fromDomain(Cliente model) {
        if (model == null) return null;
        ClienteEntity entity = new ClienteEntity();
        entity.setId(model.getId());
        entity.setIdExterno(model.getIdExterno());
        entity.setCpf(model.getCpf() == null ? null : model.getCpf().getNumero());
        entity.setNome(model.getNome());
        entity.setEmail(model.getEmail() == null ? null : model.getEmail().getEndereco());
        entity.setAnonimo(model.getAnonimo());
        return entity;
    }

    public List<ClienteEntity> fromDomain(List<Cliente> modelList) {
        if (modelList == null) return List.of();
        return modelList.stream().map(this::fromDomain).toList();
    }
}
