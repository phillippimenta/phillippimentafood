package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.inbound.BuscarClienteFiltroPaginadoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.inbound.CadastrarClientePort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.inbound.ObterClientePorCPFPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.outbound.ClienteRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception.CPFClienteJaCadastradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception.DomainException;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.FiltroBuscarCliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;

import java.util.Objects;

public class ClienteService implements CadastrarClientePort, ObterClientePorCPFPort,
        BuscarClienteFiltroPaginadoPort {

    private final ClienteRepositoryPort clienteRepositoryPort;

    public ClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        this.clienteRepositoryPort = clienteRepositoryPort;
    }

    @Override
    public Cliente cadastrarCliente(Cliente cliente) {
        Cliente clientePesquisado = this.clienteRepositoryPort.obterPorCPF(cliente.getCpf());
        if (clientePesquisado != null) {
            throw new CPFClienteJaCadastradoException(cliente.getCpf());
        }
        cliente.setAnonimo(Boolean.FALSE);
        return this.clienteRepositoryPort.salvar(cliente);
    }

    @Override
    public Cliente obterClientePorCPF(String numero) {
        if (numero == null || Objects.equals(numero, "")) {
            throw new DomainException("O CPF deve ser fornecido para realizar a pesquisa.");
        }
        return this.clienteRepositoryPort.obterPorCPF(new CPF(numero));
    }

    @Override
    public PaginaResposta<Cliente> buscarClienteFiltroPaginado(FiltroBuscarCliente filtro, int numeroPagina, int tamanhoPagina) {
        return this.clienteRepositoryPort.buscarClienteFiltroPaginado(filtro, numeroPagina, tamanhoPagina);
    }
}
