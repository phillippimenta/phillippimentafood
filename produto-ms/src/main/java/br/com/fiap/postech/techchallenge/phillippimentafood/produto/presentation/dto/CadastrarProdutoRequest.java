package br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

public record CadastrarProdutoRequest(
        @Schema(description = "Tipo do produto")
        @NotNull(message = "Tipo do produto deve ser informado.")
        @JsonProperty("categoria")
        Categoria categoria,
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
    public static Produto toModel(CadastrarProdutoRequest request) {
        return new Produto(
                request.categoria(),
                request.nome(),
                request.descricao(),
                request.preco()
        );
    }
}
