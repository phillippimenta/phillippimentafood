package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Status;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "pedidos")
public class PedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itens_pedido_id_seq")
    @SequenceGenerator(name = "itens_pedido_id_seq", sequenceName = "itens_pedido_id_seq", allocationSize = 1)
    private Long id;
    private UUID idExterno;
    @Column(name = "cliente_uuid")
    private UUID cliente;
    @Enumerated(EnumType.STRING)
    private Status status;
    @OneToMany(cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "pedido")
    private List<ItemPedidoEntity> itens = new ArrayList<>();
    private BigDecimal total;
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

    public void setIdExterno(UUID uuid) {
        this.idExterno = uuid;
    }

    public UUID getCliente() {
        return cliente;
    }

    public void setCliente(UUID cliente) {
        this.cliente = cliente;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public List<ItemPedidoEntity> getItens() {
        return itens;
    }

    public void setItens(List<ItemPedidoEntity> itens) {
        this.itens = itens;
    }

    public BigDecimal getTotal() {
        return total;
    }

    public void setTotal(BigDecimal total) {
        this.total = total;
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
