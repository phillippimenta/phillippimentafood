package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;

public interface SalvarPedidoPort {

    Pedido salvarPedido(Pedido pedido);
}
