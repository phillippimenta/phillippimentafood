package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.outbound.ProdutoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service.ProdutoService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BeanConfig {

    @Bean
    public ProdutoService getProdutoService(ProdutoRepositoryPort produtoRepositoryPort) {
        return new ProdutoService(produtoRepositoryPort);
    }
}
