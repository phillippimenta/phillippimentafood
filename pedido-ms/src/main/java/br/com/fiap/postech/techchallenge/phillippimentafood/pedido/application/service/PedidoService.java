package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.inbound.ListarPedidosPorDataCriacoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.inbound.CriarPedidoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.outbound.PedidoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public class PedidoService implements CriarPedidoPort, ListarPedidosPorDataCriacoPort {

    private final PedidoRepositoryPort pedidoRepositoryPort;

    public PedidoService(PedidoRepositoryPort pedidoRepositoryPort) {
        this.pedidoRepositoryPort = pedidoRepositoryPort;
    }

    @Override
    public Pedido criarPedido(Pedido pedido) {
        return this.pedidoRepositoryPort.salvar(pedido);
    }

    @Override
    public List<Pedido> listarPedidosPorDataCriacao(LocalDate dataCriacao) {
        return this.pedidoRepositoryPort.listarPorDataCriacao(dataCriacao);
    }
}
