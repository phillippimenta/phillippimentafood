package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;

public interface CriarSolicitacaoPagamentoPort {

    Pagamento criarSolicitacaoPagamento(Pagamento pagamento);
}
