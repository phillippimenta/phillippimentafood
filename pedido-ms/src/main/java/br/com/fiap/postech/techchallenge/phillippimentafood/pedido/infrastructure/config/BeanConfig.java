package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.port.outbound.PedidoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.service.PedidoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public PedidoService getClienteService(PedidoRepositoryPort pedidoRepositoryPort) {
        return new PedidoService(pedidoRepositoryPort);
    }
}
