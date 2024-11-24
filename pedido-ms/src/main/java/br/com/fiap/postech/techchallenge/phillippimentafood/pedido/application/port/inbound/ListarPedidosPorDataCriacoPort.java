package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;

import java.time.LocalDate;
import java.util.List;

public interface ListarPedidosPorDataCriacoPort {

    List<Pedido> listarPedidosPorDataCriacao(LocalDate dataCriacao);
}
