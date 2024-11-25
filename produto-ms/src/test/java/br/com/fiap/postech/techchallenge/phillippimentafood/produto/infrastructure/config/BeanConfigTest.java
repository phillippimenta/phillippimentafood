package br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.config;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.outbound.ProdutoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service.ProdutoService;
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
        ProdutoRepositoryPort mockProdutoRepositoryPort = mock(ProdutoRepositoryPort.class);
        // Act
        ProdutoService produtoService = applicationContext.getBean(ProdutoService.class);
        // Assert
        assertThat(produtoService).isNotNull();
        assertThat(produtoService).isInstanceOf(ProdutoService.class);
    }
}
