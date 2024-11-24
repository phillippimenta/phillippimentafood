package br.com.fiap.postech.techchallenge.phillippimentafood.producao.infrastructure.config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    @Bean
    public Queue filaProducaoSolicitacao() {
        return new Queue("producao_solicitacao", true);
    }
}
