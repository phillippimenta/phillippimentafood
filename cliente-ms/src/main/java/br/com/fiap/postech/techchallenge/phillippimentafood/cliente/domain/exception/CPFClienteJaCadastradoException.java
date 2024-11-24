package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.exception;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;

public class CPFClienteJaCadastradoException extends DomainException {

    public CPFClienteJaCadastradoException(CPF cpf) {
        super(String.format("Cliente com CPF %s já cadastrado.", cpf.getNumeroFormatado()));
    }
}
