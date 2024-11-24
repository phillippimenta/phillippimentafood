package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.outbound.OrdemProducaoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.entity.OrdemProducaoEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.mapper.OrdemProducaoEntityMapper;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.Optional;
import java.util.UUID;

@Repository
public class OrdemOrdemProducaoRepository implements OrdemProducaoRepositoryPort {

    private final OrdemProducaoJpaRepository repository;

    private final OrdemProducaoEntityMapper mapper;

    public OrdemOrdemProducaoRepository(OrdemProducaoJpaRepository repository,
                                        OrdemProducaoEntityMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public OrdemProducao salvar(OrdemProducao ordemProducao) {
        OrdemProducaoEntity ordemProducaoEntity = mapper.toEntity(ordemProducao);
        return mapper.toDomain(repository.save(ordemProducaoEntity));
    }

    @Override
    public Optional<OrdemProducao> obterPorPedidoId(UUID pedidoId) {
        OrdemProducaoEntity ordemProducaoEntity = repository.findByPedidoId(pedidoId);
        if (ordemProducaoEntity == null) return Optional.empty();
        return Optional.of(mapper.toDomain(ordemProducaoEntity));
    }

    @Override
    public PaginaResposta<OrdemProducao> buscarPorStatusDataCriacaoPaginado(Status status, LocalDate dataCriacao, int numeroPagina, int tamanhoPagina) {
        Pageable pageable = PageRequest.of(numeroPagina, tamanhoPagina);
        Page<OrdemProducaoEntity> producaoPaginada = repository.findByStatusAndDataCriacao(status, dataCriacao, pageable);
        return new PaginaResposta<>(
                mapper.toDomain(producaoPaginada.getContent()),
                producaoPaginada.getNumber(),
                producaoPaginada.getSize(),
                producaoPaginada.getTotalElements(),
                producaoPaginada.getTotalPages()
        );
    }
}
