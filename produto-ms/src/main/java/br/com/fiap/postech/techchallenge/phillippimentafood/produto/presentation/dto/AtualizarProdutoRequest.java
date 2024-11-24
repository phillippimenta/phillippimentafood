package br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record AtualizarProdutoRequest(
        @Schema(description = "Nome do produto")
        @NotBlank(message = "Nome do produto deve ser informado.")
        String nome,
        @Schema(description = "Descrição do produto")
        @NotBlank(message = "Descrição do produto deve ser informada.")
        String descricao,
        @Schema(description = "Preço do produto")
        @NotNull(message = "Preço do produto deve ser informado.")
        BigDecimal preco
) {
    public static Produto toModel(AtualizarProdutoRequest request) {
        return new Produto(
                null,
                request.nome(),
                request.descricao(),
                request.preco()
        );
    }
}
