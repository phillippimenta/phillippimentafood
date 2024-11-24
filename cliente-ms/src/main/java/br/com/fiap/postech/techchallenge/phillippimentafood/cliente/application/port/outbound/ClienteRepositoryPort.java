package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.outbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.FiltroBuscarCliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;

public interface ClienteRepositoryPort {

    PaginaResposta<Cliente> buscarClienteFiltroPaginado(FiltroBuscarCliente filtro, int numeroPagina, int tamanhoPagina);

    Cliente obterPorId(Long id);

    Cliente obterPorCPF(CPF cpf);

    Cliente salvar(Cliente cliente);
}
