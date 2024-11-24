package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CpfInvalidoExceptionTest {

    @Test
    void testCpfInvalidoException() {
        CpfInvalidoException exception = new CpfInvalidoException();
        String expectedMessage = "CPF inválido";
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção deve ser 'CPF inválido'.");
    }
}
