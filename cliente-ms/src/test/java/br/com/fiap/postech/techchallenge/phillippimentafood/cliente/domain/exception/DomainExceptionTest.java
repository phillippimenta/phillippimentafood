package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

class DomainExceptionTest {

    @Test
    void testDomainExceptionWithSingleMessage() {
        String expectedMessage = "Erro no domínio";
        DomainException exception = new DomainException(expectedMessage);
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção deve ser a mesma que foi passada no construtor.");
    }

    @Test
    void testDomainExceptionWithMultipleMessages() {
        List<String> messages = Arrays.asList("Erro 1", "Erro 2", "Erro 3");
        String expectedMessage = String.join(", ", messages);
        DomainException exception = new DomainException(messages);
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção deve ser a junção das mensagens passadas.");
    }
}
