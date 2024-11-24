package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public class Pedido {

    private Long id;
    private UUID idExterno;
    private final UUID cliente;
    private final Status status;
    private final List<ItemPedido> itens;
    private BigDecimal total;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Pedido(UUID cliente, Status status, List<ItemPedido> itens) {
        this.idExterno = UUID.randomUUID();
        this.cliente = cliente;
        this.status = status;
        this.itens = itens;
        this.calcularTotal();
    }

    public void calcularTotal() {
        this.total = itens.stream()
                .map(item -> item.getPreco().multiply(new BigDecimal(item.getQuantidade())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    public void adicionarItem(ItemPedido item) {
        this.itens.add(item);
        this.calcularTotal();
    }

    public void removeItem(ItemPedido item) {
        this.itens.remove(item);
        this.calcularTotal();
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

    public UUID getCliente() {
        return cliente;
    }

    public Status getStatus() {
        return status;
    }

    public List<ItemPedido> getItens() {
        return itens;
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
