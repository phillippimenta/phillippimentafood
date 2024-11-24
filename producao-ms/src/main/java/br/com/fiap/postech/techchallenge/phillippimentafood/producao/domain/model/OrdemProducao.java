package br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class OrdemProducao {

    private Long id;
    private UUID idExterno;
    private final UUID pedidoIdExterno;
    private Status status;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public OrdemProducao(UUID pedidoIdExterno) {
        this.idExterno = UUID.randomUUID();
        this.pedidoIdExterno = pedidoIdExterno;
        this.status = Status.RECEBIDO;
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

    public UUID getPedidoIdExterno() {
        return pedidoIdExterno;
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
