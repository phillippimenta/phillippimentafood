package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.inbound.CriarSolicitacaoPagamentoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.outbound.PagamentoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Pagamento;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.domain.model.Status;
import com.mercadopago.MercadoPagoConfig;
import com.mercadopago.client.payment.PaymentClient;

public class SolicitacaoPagamentoService implements CriarSolicitacaoPagamentoPort {

    private final PagamentoRepositoryPort pagamentoRepositoryPort;

    public SolicitacaoPagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort) {
        this.pagamentoRepositoryPort = pagamentoRepositoryPort;
    }

    @Override
    public Pagamento criarSolicitacaoPagamento(Pagamento pagamento) {
        pagamento.setStatus(Status.PENDENTE);
        MercadoPagoConfig.setAccessToken("SEU_ACCESS_TOKEN");
        PaymentClient paymentClient = new PaymentClient();
        return this.pagamentoRepositoryPort.salvar(pagamento);
    }
}
