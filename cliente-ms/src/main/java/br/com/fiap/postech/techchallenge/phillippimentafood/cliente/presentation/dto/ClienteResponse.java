package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;
import java.util.UUID;

public record ClienteResponse(
        @JsonProperty("id_externo")
        UUID idExterno,
        @JsonProperty("cpf")
        String cpf,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("email")
        String email
) {
    public static ClienteResponse from(Cliente cliente) {
        String numeroCPF = cliente.getCpf() == null ? null : cliente.getCpf().getNumero();
        String enderecoEmail = cliente.getEmail() == null ? null : cliente.getEmail().getEndereco();
        return new ClienteResponse(cliente.getIdExterno(), numeroCPF, cliente.getNome(), enderecoEmail);
    }

    public static List<ClienteResponse> from(List<Cliente> clienteList) {
        return clienteList.stream().map(ClienteResponse::from).toList();
    }

    public static PaginaResposta<ClienteResponse> from(PaginaResposta<Cliente> paginaResposta) {
        return new PaginaResposta<>(
                paginaResposta.getConteudo().stream().map(ClienteResponse::from).toList(),
                paginaResposta.getNumeroPagina(),
                paginaResposta.getTamanhoPagina(),
                paginaResposta.getTotalElementos(),
                paginaResposta.getTotalPaginas()
        );
    }
}
