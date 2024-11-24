package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.port.outbound.PagamentoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.service.SolicitacaoPagamentoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public SolicitacaoPagamentoService pagamentoService(PagamentoRepositoryPort pagamentoRepositoryPort) {
        return new SolicitacaoPagamentoService(pagamentoRepositoryPort);
    }
}
