package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.config;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.models.OpenAPI;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@OpenAPIDefinition(
        info = @Info(
                title = "Microsserviço de Clientes",
                version = "1.0",
                description = "API Desenvolvida como Parte do Trabalho de Conclusão dos Tech Challenges da Pós-Graduação em Arquitetura de Software da FIAP e Alura"
        )
)
public class SpringDocConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI();
    }
}