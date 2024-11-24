package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.outbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PedidoRepositoryPort {

    List<Pedido> listarPorDataCriacao(LocalDate dataCriacao);

    Optional<Pedido> obterPorId(Long id);

    Optional<Pedido> obterPorUuid(UUID uuid);

    Pedido salvar(Pedido pedido);
}
