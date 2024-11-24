package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Pagamento {

    private Long id;
    private UUID idExterno;
    private final UUID pedidoIdExterno;
    private final BigDecimal valor;
    private Status status;
    private final MetodoPagamento metodoPagamento;
    private String qrCodeUrl;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Pagamento(UUID pedidoIdExterno, BigDecimal valor, MetodoPagamento metodoPagamento) {
        this.idExterno = UUID.randomUUID();
        this.pedidoIdExterno = pedidoIdExterno;
        this.valor = valor;
        this.metodoPagamento = metodoPagamento;
        this.dataCriacao = LocalDateTime.now();
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setIdExterno(UUID idExterno) {
        this.idExterno = idExterno;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public void setDataCriacao(LocalDateTime dataCriacao) {
        this.dataCriacao = dataCriacao;
    }

    public Long getId() {
        return id;
    }

    public UUID getIdExterno() {
        return idExterno;
    }

    public BigDecimal getValor() {
        return valor;
    }

    public UUID getPedidoIdExterno() {
        return pedidoIdExterno;
    }

    public Status getStatus() {
        return status;
    }

    public MetodoPagamento getMetodoPagamento() {
        return metodoPagamento;
    }

    public LocalDateTime getDataCriacao() {
        return dataCriacao;
    }

    public String getQrCodeUrl() {
        return qrCodeUrl;
    }

    public void setQrCodeUrl(String qrCodeUrl) {
        this.qrCodeUrl = qrCodeUrl;
    }

    public LocalDateTime getDataAtualizacao() {
        return dataAtualizacao;
    }

    public void setDataAtualizacao(LocalDateTime dataAtualizacao) {
        this.dataAtualizacao = dataAtualizacao;
    }
}
