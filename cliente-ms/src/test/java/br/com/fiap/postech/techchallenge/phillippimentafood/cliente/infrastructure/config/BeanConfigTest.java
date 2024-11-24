package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.port.outbound.ClienteRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.service.ClienteService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE)
class BeanConfigTest {

    @Autowired
    private ApplicationContext applicationContext;

    @Test
    void deveRegistrarBeanClienteService() {
        // Arrange
        ClienteRepositoryPort mockClienteRepositoryPort = mock(ClienteRepositoryPort.class);
        // Act
        ClienteService clienteService = applicationContext.getBean(ClienteService.class);
        // Assert
        assertThat(clienteService).isNotNull();
        assertThat(clienteService).isInstanceOf(ClienteService.class);
    }
}
