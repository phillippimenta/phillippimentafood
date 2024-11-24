package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.port.outbound.OrdemProducaoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.service.OrdemProducaoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public OrdemProducaoService producaoService(OrdemProducaoRepositoryPort ordemProducaoRepositoryPort) {
        return new OrdemProducaoService(ordemProducaoRepositoryPort);
    }
}
