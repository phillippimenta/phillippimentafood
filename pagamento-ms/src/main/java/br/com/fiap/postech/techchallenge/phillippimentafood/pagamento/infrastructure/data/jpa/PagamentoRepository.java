package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.infrastructure.data.jpa;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.outbound.PagamentoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;
import org.springframework.stereotype.Repository;

@Repository
public class PagamentoRepository implements PagamentoRepositoryPort {

    @Override
    public Pagamento salvar(Pagamento pagamento) {
        return null;
    }
}
