package br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model;

public enum Status {
    RECEBIDO,           // Pedido recebido pela produção
    EM_PREPARACAO,      // Pedido em preparação na cozinha
    PRONTO,             // Pedido pronto para retirada
    FINALIZADO          // Pedido retirado e concluído
}
