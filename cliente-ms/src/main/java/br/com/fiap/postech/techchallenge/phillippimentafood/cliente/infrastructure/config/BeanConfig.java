package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.outbound.ClienteRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.service.ClienteService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ClienteService getClienteService(ClienteRepositoryPort clienteRepositoryPort) {
        return new ClienteService(clienteRepositoryPort);
    }
}
