package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.entity;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
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

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "produtos")
public class ProdutoEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "produtos_id_seq")
    @SequenceGenerator(name = "produtos_id_seq", sequenceName = "produtos_id_seq", allocationSize = 1)
    private Long id;
    @Column(name = "id_externo")
    private UUID idExterno;
    @Enumerated(EnumType.STRING)
    private Categoria categoria;
    private String nome;
    private String descricao;
    private BigDecimal preco;
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

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
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
