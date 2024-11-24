package br.com.fiap.postech.techchallenge.phillippimentafood.cliente.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.application.service.ClienteService;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.Cliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.FiltroBuscarCliente;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.presentation.dto.CadastrarClienteRequest;
import br.com.fiap.postech.techchallenge.phillippimentafood.cliente.presentation.dto.ClienteResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Clientes", description = "Operações relacionadas a clientes")
@RestController
@RequestMapping
public class ClienteRestController {

    private final ClienteService clienteService;

    public ClienteRestController(ClienteService clienteService) {
        this.clienteService = clienteService;
    }

    @Operation(summary = "Permite obter um cliente a partir do CPF fornecido.", description = "Retorna o cliente, se encontrado, com as informações de UUID, CPF, nome e e-mail.")
    @ApiResponse(responseCode = "200", description = "Cliente localizado com sucesso.")
    @GetMapping("{cpf}")
    public ResponseEntity<ClienteResponse> obterClientePorCPF(@PathVariable String cpf) {
        ClienteResponse clienteResponse = ClienteResponse
                .from(this.clienteService.obterClientePorCPF(cpf));
        return ResponseEntity.status(HttpStatus.OK).body(clienteResponse);
    }

    @Operation(summary = "Permite realizar o cadastro de um novo cliente.", description = "Retorna o cliente salvo com as informações de UUID, CPF, nome e e-mail.")
    @ApiResponse(responseCode = "201", description = "Cliente cadastrado com sucesso.")
    @PostMapping
    public ResponseEntity<ClienteResponse> cadastrarCliente(@Valid @RequestBody CadastrarClienteRequest request) {
        Cliente cliente = CadastrarClienteRequest.toModel(request);
        ClienteResponse clienteResponse = ClienteResponse
                .from(this.clienteService.cadastrarCliente(cliente));
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteResponse);
    }

    @Operation(summary = "Permite consultar uma lista paginada de clientes com filtros opcionais por cpf, nome ou e-mail.",
            description = "Retorna uma lista paginada de clientes, se encontrados, incluindo as informações de id_externo, cpf, nome, email, numeroPagina, tamanhoPagina, totalElementos e totalPaginas.")
    @ApiResponse(responseCode = "200", description = "Lista paginada de ordens de produção retornada com sucesso, caso existam ordens correspondentes.")
    @GetMapping
    public ResponseEntity<PaginaResposta<ClienteResponse>> buscarClienteFiltroPaginado(
            @RequestParam(required = false) String cpf,
            @RequestParam(required = false) String nome,
            @RequestParam(required = false) String email,
            @RequestParam(defaultValue = "0") int numeroPagina,
            @RequestParam(defaultValue = "20") int tamanhoPagina
    ) {
        FiltroBuscarCliente filtro = new FiltroBuscarCliente(cpf, nome, email);
        PaginaResposta<Cliente> paginaResposta = this.clienteService.buscarClienteFiltroPaginado(filtro, numeroPagina, tamanhoPagina);
        return ResponseEntity.status(HttpStatus.OK).body(ClienteResponse.from(paginaResposta));
    }
}
