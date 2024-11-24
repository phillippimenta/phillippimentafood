package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model;

import java.math.BigDecimal;

public class ItemPedido {

    private Long id;
    private final String nome;
    private final String descricao;
    private final BigDecimal preco;
    private final Integer quantidade;

    public ItemPedido(String nome, String descricao, BigDecimal preco,  Integer quantidade) {
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
        this.quantidade = quantidade;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
