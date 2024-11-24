package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.persistence.database.entity;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ordens_producao")
public class OrdemProducaoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ordens_producao_id_seq")
    @SequenceGenerator(name = "ordens_producao_id_seq", sequenceName = "ordens_producao_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "id_externo")
    private UUID idExterno;
    @Column(name = "pedido_id")
    private UUID pedidoId;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao", insertable = false)
    private LocalDateTime dataAtualizacao;

    @PrePersist
    public void prePersist() {
        this.dataCriacao = LocalDateTime.now();
    }

    @PreUpdate
    public void preUpdate() {
        this.dataAtualizacao = LocalDateTime.now();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UUID getIdExterno() {
        return idExterno;
    }

    public void setIdExterno(UUID idExterno) {
        this.idExterno = idExterno;
    }

    public UUID getPedidoId() {
        return pedidoId;
    }

    public void setPedidoId(UUID pedidoId) {
        this.pedidoId = pedidoId;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
