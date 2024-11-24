package br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.application.service.SolicitacaoPagamentoService;
import br.com.fiap.postech.techchallenge.phillippimentafood.pagamento.presentation.dto.PagamentoResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Pagamentos", description = "Operações relacionadas a pagamentos")
@RestController
@RequestMapping
public class PagamentoRestController {

    private final SolicitacaoPagamentoService pagamentoService;

    public PagamentoRestController(SolicitacaoPagamentoService pagamentoService) {
        this.pagamentoService = pagamentoService;
    }

    @PostMapping
    public ResponseEntity<PagamentoResponse> gerarQrCode(@PathVariable Long id) {
        return null;
    }
}
