package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.outbound.ClienteRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.FiltroBuscarCliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.mapper.ClienteEntityMapper;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.specification.ClienteEntitySpecification;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class ClienteRepository implements ClienteRepositoryPort {

    private final ClienteJpaRepository clienteJpaRepository;

    private final ClienteEntityMapper clienteEntityMapper;

    public ClienteRepository(
            ClienteJpaRepository clienteJpaRepository,
            ClienteEntityMapper clienteEntityMapper
    ) {
        this.clienteJpaRepository = clienteJpaRepository;
        this.clienteEntityMapper = clienteEntityMapper;
    }

    @Override
    public PaginaResposta<Cliente> buscarClienteFiltroPaginado(FiltroBuscarCliente filtro, int numeroPagina, int tamanhoPagina) {
        Specification<ClienteEntity> specification = Specification.where(null);
        if (filtro.nome() != null && !filtro.nome().isBlank()) {
            specification = specification.and(ClienteEntitySpecification.comNome(filtro.nome()));
        }
        if (filtro.cpf() != null && !filtro.cpf().isBlank()) {
            specification = specification.and(ClienteEntitySpecification.comCpf(filtro.cpf()));
        }
        if (filtro.email() != null && !filtro.email().isBlank()) {
            specification = specification.and(ClienteEntitySpecification.comEmail(filtro.email()));
        }
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Page<ClienteEntity> resultadoPaginado = this.clienteJpaRepository.findAll(specification, pageable);
        return new PaginaResposta<>(
                this.clienteEntityMapper.toDomain(resultadoPaginado.getContent()),
                resultadoPaginado.getNumber(),
                resultadoPaginado.getSize(),
                resultadoPaginado.getTotalElements(),
                resultadoPaginado.getTotalPages()
        );
    }

    @Override
    public Cliente obterPorId(Long id) {
        ClienteEntity clienteEntity = this.clienteJpaRepository.obterPorId(id);
        return this.clienteEntityMapper.toDomain(clienteEntity);
    }

    @Override
    public Cliente obterPorCPF(CPF cpf) {
        ClienteEntity clienteEntity = this.clienteJpaRepository.obterPorCpf(cpf.getNumero());
        return this.clienteEntityMapper.toDomain(clienteEntity);
    }

    @Override
    public Cliente salvar(Cliente cliente) {
        ClienteEntity clienteEntity = this.clienteJpaRepository.save(this.clienteEntityMapper.fromDomain(cliente));
        return this.clienteEntityMapper.toDomain(clienteEntity);
    }
}
