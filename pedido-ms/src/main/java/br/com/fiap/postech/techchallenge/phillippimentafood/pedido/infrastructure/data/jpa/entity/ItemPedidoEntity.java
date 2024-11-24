package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.data.jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;

import java.math.BigDecimal;

@Entity
@Table(name = "itens_pedido")
public class ItemPedidoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "itens_pedido_id_seq")
    @SequenceGenerator(name = "itens_pedido_id_seq", sequenceName = "itens_pedido_id_seq", allocationSize = 1)
    private Long id;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private Integer quantidade;
    @ManyToOne
    @JoinColumn(name = "pedido_id")
    private PedidoEntity pedido;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public BigDecimal getPreco() {
        return preco;
    }

    public void setPreco(BigDecimal preco) {
        this.preco = preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public PedidoEntity getPedido() {
        return pedido;
    }

    public void setPedido(PedidoEntity pedido) {
        this.pedido = pedido;
    }
}
