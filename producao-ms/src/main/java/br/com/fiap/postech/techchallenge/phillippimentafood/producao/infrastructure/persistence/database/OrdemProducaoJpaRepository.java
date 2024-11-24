package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.entity.OrdemProducaoEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.UUID;

@Repository
public interface OrdemProducaoJpaRepository extends JpaRepository<OrdemProducaoEntity, Long> {

    OrdemProducaoEntity findByPedidoId(UUID pedidoId);

    @Query("SELECT op FROM OrdemProducaoEntity op WHERE op.status = :status AND DATE(op.dataCriacao) = :dataCriacao")
    Page<OrdemProducaoEntity> findByStatusAndDataCriacao(
            @Param("status") Status status,
            @Param("dataCriacao") LocalDate dataCriacao,
            Pageable pageable);
}
