package br.com.fiap.postech.techchallenge.phillippimentafood.producao.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.producao.application.service.OrdemProducaoService;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.OrdemProducao;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.PaginaResposta;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.domain.model.Status;
import br.com.fiap.postech.techchallenge.phillippimentafood.producao.presentation.dto.OrdemProducaoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDate;
import java.util.UUID;

@Tag(name = "Ordens de produção", description = "Operações relacionadas a ordens de produção")
@RestController
@RequestMapping
public class OrdemProducaoRestController {

    private final OrdemProducaoService ordemProducaoService;

    public OrdemProducaoRestController(OrdemProducaoService ordemProducaoService) {
        this.ordemProducaoService = ordemProducaoService;
    }

    @Operation(summary = "Permite consultar uma lista paginada de ordens de produção filtradas por status e data de criação.", description = "Retorna uma lista paginada de ordens de produção, se encontradas, incluindo as informações de id_externo, pedido_id, status, data_criacao e data_atualizacao.")
    @ApiResponse(responseCode = "200", description = "Lista paginada de ordens de produção retornada com sucesso, caso existam ordens correspondentes.")
    @GetMapping
    public ResponseEntity<PaginaResposta<OrdemProducaoResponse>> buscarPorStatusPaginado(@RequestParam Status status,
                                                                                         @Parameter(description = "Data de criação no formato yyyy-MM-dd.",
                                                                                                 schema = @Schema(type = "string", format = "date"))
                                                                                         @RequestParam(name = "data-criacao") LocalDate dataCriacao) {
        PaginaResposta<OrdemProducao> producaoPaginaResposta = ordemProducaoService.buscarPorStatusDataCriacaoPaginado(status, dataCriacao, 0);
        return ResponseEntity.status(HttpStatus.OK).body(OrdemProducaoResponse.from(producaoPaginaResposta));
    }

    @PatchMapping
    public ResponseEntity<OrdemProducaoResponse> atualizarStatusProducao(
            UUID idExternoOrdemProducao
    ) {
        return null;
    }
}
