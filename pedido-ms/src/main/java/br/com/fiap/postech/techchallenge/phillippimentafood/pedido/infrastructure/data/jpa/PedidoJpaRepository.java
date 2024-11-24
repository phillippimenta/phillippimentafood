package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity.PedidoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface PedidoJpaRepository extends JpaRepository<PedidoEntity, Long> {

    Optional<PedidoEntity> findByUuid(UUID uuid);

    @Query("FROM PedidoEntity p WHERE FUNCTION('DATE', p.dataCriacao) = :dataCriacao")
    List<PedidoEntity> findByDataCriacao(LocalDate dataCriacao);
}
