package br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model;

import java.util.List;

public class PaginaResposta<T> {

    private List<T> conteudo;
    private int numeroPagina;
    private int tamanhoPagina;
    private long totalElementos;
    private int totalPaginas;

    public PaginaResposta(List<T> conteudo, int numeroPagina, int tamanhoPagina, long totalElementos, int totalPaginas) {
        this.conteudo = conteudo;
        this.numeroPagina = numeroPagina;
        this.tamanhoPagina = tamanhoPagina;
        this.totalElementos = totalElementos;
        this.totalPaginas = totalPaginas;
    }

    // Getters e setters
    public List<T> getConteudo() {
        return conteudo;
    }

    public void setConteudo(List<T> conteudo) {
        this.conteudo = conteudo;
    }

    public int getNumeroPagina() {
        return numeroPagina;
    }

    public void setNumeroPagina(int numeroPagina) {
        this.numeroPagina = numeroPagina;
    }

    public int getTamanhoPagina() {
        return tamanhoPagina;
    }

    public void setTamanhoPagina(int tamanhoPagina) {
        this.tamanhoPagina = tamanhoPagina;
    }

    public long getTotalElementos() {
        return totalElementos;
    }

    public void setTotalElementos(long totalElementos) {
        this.totalElementos = totalElementos;
    }

    public int getTotalPaginas() {
        return totalPaginas;
    }

    public void setTotalPaginas(int totalPaginas) {
        this.totalPaginas = totalPaginas;
    }
}
