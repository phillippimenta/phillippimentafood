package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.outbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;

public interface PagamentoRepositoryPort {

    Pagamento salvar(Pagamento pagamento);
}
