package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.data.jpa.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Long>, JpaSpecificationExecutor<ClienteEntity> {

    @Query("FROM ClienteEntity WHERE id = :id")
    ClienteEntity obterPorId(Long id);

    @Query("FROM ClienteEntity WHERE cpf = :cpf")
    ClienteEntity obterPorCpf(String cpf);
}
