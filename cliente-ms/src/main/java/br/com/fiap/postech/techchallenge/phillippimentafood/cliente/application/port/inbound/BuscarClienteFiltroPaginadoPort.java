package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.FiltroBuscarCliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;

public interface BuscarClienteFiltroPaginadoPort {

    PaginaResposta<Cliente> buscarClienteFiltroPaginado(FiltroBuscarCliente filtro, int numeroPagina, int tamanhoPagina);
}
