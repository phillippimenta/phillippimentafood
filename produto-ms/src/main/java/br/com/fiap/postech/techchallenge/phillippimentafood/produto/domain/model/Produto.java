package br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Produto {

    private Long id;
    private UUID idExterno;
    private final Categoria categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Produto(Categoria categoria, String nome, String descricao, BigDecimal preco) {
        this.idExterno = UUID.randomUUID();
        this.categoria = categoria;
        this.nome = nome;
        this.descricao = descricao;
        this.preco = preco;
    }

    public void atualizar(String novoNome, String novaDescricao, BigDecimal novoPreco) {
        if (novoNome != null && !novoNome.isBlank()) {
            this.nome = novoNome;
        }
        if (novaDescricao != null && !novaDescricao.isBlank()) {
            this.descricao = novaDescricao;
        }
        if (novoPreco != null && novoPreco.compareTo(BigDecimal.ZERO) > 0) {
            this.preco = novoPreco;
        }
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

    public Categoria getCategoria() {
        return categoria;
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
