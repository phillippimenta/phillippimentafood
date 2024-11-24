package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.CPF;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Email;
import jakarta.validation.constraints.NotBlank;

public record CadastrarClienteRequest(
        @NotBlank(message = "CPF deve ser informado.")
        String cpf,
        @NotBlank(message = "Nome deve ser informado.")
        String nome,
        @NotBlank(message = "E-mail deve ser informado.")
        String email
) {
    public static Cliente toModel(CadastrarClienteRequest request) {
        if (request == null) {
            throw new IllegalArgumentException("Request não pode ser nulo");
        }
        Cliente cliente = new Cliente(new CPF(request.cpf()), request.nome(), new Email(request.email()));
        return cliente;
    }
}
