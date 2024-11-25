package br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.application.service.PedidoService;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.domain.model.Pedido;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto.NovoPedidoRequest;
import br.com.fiap.postech.techchallenge.phillippimentafood.pedido.presentation.dto.PedidoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.List;

@Tag(name = "Pedidos", description = "Operações relacionadas a pedidos")
@RestController
@RequestMapping
public class PedidoRestController {

    private final PedidoService pedidoService;

    public PedidoRestController(PedidoService pedidoService) {
        this.pedidoService = pedidoService;
    }

    @Operation(summary = "Permite realizar a pesquisa de pedidos com base na data de criação.", description = "Retorna uma lista de pedidos encontrados, contendo as seguintes informações: UUID, cliente, status, itens do pedido, valor total, data de criação e data de atualização.")
    @ApiResponse(responseCode = "200", description = "Lista de pedidos encontrados.")
    @GetMapping
    public ResponseEntity<List<PedidoResponse>> listarPedidosPorDataCriaco(
            @Parameter(description = "Data de criação no formato yyyy-MM-dd.",
                    schema = @Schema(type = "string", format = "date"))
            @RequestParam(required = true)
            @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dataCriacao
    ) {
        List<PedidoResponse> pedidoResponse = PedidoResponse.fromModel(this.pedidoService.listarPedidosPorDataCriacao(dataCriacao));
        return ResponseEntity.status(HttpStatus.OK).body(pedidoResponse);
    }

    @Operation(summary = "Permite o cadastro de um novo pedido.", description = "Retorna o pedido salvo contendo as seguintes informações: UUID, cliente, status, itens do pedido, valor total, data de criação e data de atualização.")
    @ApiResponse(responseCode = "201", description = "Pedido criado com sucesso.")
    @PostMapping
    public ResponseEntity<PedidoResponse> cadastrarCliente(@Valid @RequestBody NovoPedidoRequest request) {
        Pedido pedido = NovoPedidoRequest.toModel(request);
        PedidoResponse pedidoResponse = PedidoResponse.fromModel(this.pedidoService.criarPedido(pedido));
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoResponse);
    }
}
