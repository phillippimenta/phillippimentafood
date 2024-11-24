package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cliente {

    private Long id;
    private UUID idExterno;
    private final CPF cpf;
    private final String nome;
    private final Email email;
    private Boolean anonimo;
    private LocalDateTime dataCriacao;
    private LocalDateTime dataAtualizacao;

    public Cliente(CPF cpf, String nome, Email email) {
        this.idExterno = UUID.randomUUID();
        this.cpf = cpf;
        this.nome = nome;
        this.email = email;
        this.dataCriacao = LocalDateTime.now();
        this.anonimo = false;
    }

    public static Cliente criarClienteAnonimo() {
        Cliente cliente = new Cliente(null, null, null);
        cliente.setAnonimo(true);
        return cliente;
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

    public CPF getCpf() {
        return cpf;
    }

    public String getNome() {
        return nome;
    }

    public Email getEmail() {
        return email;
    }

    public Boolean getAnonimo() {
        return anonimo;
    }

    public void setAnonimo(Boolean anonimo) {
        this.anonimo = anonimo;
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
