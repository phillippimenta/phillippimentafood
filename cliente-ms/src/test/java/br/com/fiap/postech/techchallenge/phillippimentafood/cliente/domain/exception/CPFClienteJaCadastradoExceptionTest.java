package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class CPFClienteJaCadastradoExceptionTest {

    @Test
    void testCpfClienteJaCadastradoException() {
        CPF cpf = new CPF("274.038.960-41"); // Supondo que o CPF tenha esse formato
        CPFClienteJaCadastradoException exception = new CPFClienteJaCadastradoException(cpf);
        String expectedMessage = String.format("Cliente com CPF %s já cadastrado.", cpf.getNumeroFormatado());
        assertEquals(expectedMessage, exception.getMessage(), "A mensagem da exceção deve conter o CPF formatado.");
    }
}
