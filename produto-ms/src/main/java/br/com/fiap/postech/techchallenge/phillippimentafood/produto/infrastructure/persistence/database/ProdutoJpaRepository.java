package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.entity.ProdutoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ProdutoJpaRepository extends JpaRepository<ProdutoEntity, Long> {

    List<ProdutoEntity> findByCategoria(Categoria categoria);

    ProdutoEntity findByNomeIgnoreCase(String nome);

    ProdutoEntity findByIdExterno(UUID idExterno);
}
