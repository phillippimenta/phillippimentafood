package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.infrastructure.data.jpa.entity;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.MetodoPagamento;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Status;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "pagamentos")
public class PagamentoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "pagamentos_id_seq")
    @SequenceGenerator(name = "pagamentos_id_seq", sequenceName = "pagamentos_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "id_externo")
    private UUID idExterno;
    @Column(name = "pedido_id_externo")
    private UUID pedidoIdExterno;
    private BigDecimal valor;
    @Enumerated(EnumType.STRING)
    private Status status;
    @Enumerated(EnumType.STRING)
    @Column(name = "metodo_pagamento")
    private MetodoPagamento metodoPagamento;
    @Column(name = "qr_code_url")
    private String qrCodeUrl;
    @Column(name = "data_criacao", updatable = false)
    private LocalDateTime dataCriacao;
    @Column(name = "data_atualizacao", insertable = false)
    private LocalDateTime dataAtualizacao;

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

    public UUID getPedidoIdExterno() {
        return pedidoIdExterno;
    }

    public void setPedidoIdExterno(UUID pedidoIdExterno) {
        this.pedidoIdExterno = pedidoIdExterno;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public void setValor(BigDecimal valor) {
        this.valor = valor;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public void setMetodoPagamento(MetodoPagamento metodoPagamento) {
        this.metodoPagamento = metodoPagamento;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
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
