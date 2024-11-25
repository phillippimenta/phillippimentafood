package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.outbound.ProdutoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.entity.ProdutoEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.mapper.ProdutoEntityMapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public class ProdutoRepository implements ProdutoRepositoryPort {

    private final ProdutoJpaRepository produtoJpaRepository;

    private final ProdutoEntityMapper produtoEntityMapper;

    public ProdutoRepository(ProdutoJpaRepository produtoJpaRepository,
                             ProdutoEntityMapper produtoEntityMapper) {
        this.produtoJpaRepository = produtoJpaRepository;
        this.produtoEntityMapper = produtoEntityMapper;
    }

    @Override
    public List<Produto> pesquisarPorCategoria(Categoria categoria) {
        List<ProdutoEntity> produtoEntityList = this.produtoJpaRepository.findByCategoria(categoria);
        return this.produtoEntityMapper.toDomain(produtoEntityList);
    }

    @Override
    public Produto pesquisarPorUuid(UUID uuid) {
        ProdutoEntity produtoEntity = this.produtoJpaRepository.findByIdExterno(uuid);
        return this.produtoEntityMapper.toDomain(produtoEntity);
    }

    @Override
    public Produto pesquisarPorNome(String nome) {
        ProdutoEntity produtoEntity = this.produtoJpaRepository.findByNomeIgnoreCase(nome);
        return this.produtoEntityMapper.toDomain(produtoEntity);
    }

    @Override
    public Produto salvar(Produto produto) {
        ProdutoEntity entity = this.produtoEntityMapper.fromDomain(produto);
        return this.produtoEntityMapper.toDomain(this.produtoJpaRepository.save(entity));
    }

    @Override
    public void deletarPorId(Long id) {
        this.produtoJpaRepository.deleteById(id);
    }
}
