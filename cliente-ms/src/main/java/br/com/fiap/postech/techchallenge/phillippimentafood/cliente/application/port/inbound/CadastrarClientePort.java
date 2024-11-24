package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;

public interface CadastrarClientePort {

    Cliente cadastrarCliente(Cliente cliente);
}
