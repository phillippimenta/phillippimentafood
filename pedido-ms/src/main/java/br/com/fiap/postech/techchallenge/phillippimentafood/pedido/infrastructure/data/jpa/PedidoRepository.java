package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.outbound.PedidoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity.PedidoEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.mapper.PedidoEntityMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public class PedidoRepository implements PedidoRepositoryPort {

    private final PedidoJpaRepository pedidoJpaRepository;
    private final PedidoEntityMapper pedidoEntityMapper = new PedidoEntityMapper();

    public PedidoRepository(PedidoJpaRepository pedidoJpaRepository) {
        this.pedidoJpaRepository = pedidoJpaRepository;
    }

    @Override
    public List<Pedido> listarPorDataCriacao(LocalDate dataCriacao) {
        List<PedidoEntity> pedidos = this.pedidoJpaRepository.findByDataCriacao(dataCriacao);
        return this.pedidoEntityMapper.toDomain(pedidos);
    }

    @Override
    public Optional<Pedido> obterPorId(Long id) {
        Optional<PedidoEntity> pedidoEntity = this.pedidoJpaRepository.findById(id);
        return pedidoEntity.map(this.pedidoEntityMapper::toDomain);
    }

    @Override
    public Optional<Pedido> obterPorUuid(UUID uuid) {
        Optional<PedidoEntity> pedidoEntity = this.pedidoJpaRepository.findByUuid(uuid);
        return pedidoEntity.map(this.pedidoEntityMapper::toDomain);
    }

    @Override
    public Pedido salvar(Pedido pedido) {
        PedidoEntity pedidoEntity = this.pedidoEntityMapper.fromDomain(pedido);
        this.pedidoJpaRepository.save(pedidoEntity);
        return this.pedidoEntityMapper.toDomain(pedidoEntity);
    }
}
