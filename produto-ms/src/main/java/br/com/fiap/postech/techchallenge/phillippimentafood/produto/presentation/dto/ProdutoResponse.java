package br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public record ProdutoResponse(
        @JsonProperty("id_externo")
        UUID idExterno,
        @JsonProperty("categoria")
        Categoria categoria,
        @JsonProperty("nome")
        String nome,
        @JsonProperty("descricao")
        String descricao,
        @JsonProperty("preco")
        BigDecimal preco,
        @JsonProperty("data_criacao")
        LocalDateTime dataCriacao,
        @JsonProperty("data_atualizacao")
        LocalDateTime dataAtualizacao
) {
    public static ProdutoResponse fromModel(Produto model) {
        return new ProdutoResponse(
                model.getIdExterno(),
                model.getCategoria(),
                model.getNome(),
                model.getDescricao(),
                model.getPreco(),
                model.getDataCriacao(),
                model.getDataAtualizacao()
        );
    }

    public static List<ProdutoResponse> fromModel(List<Produto> produtoList) {
        return produtoList.stream().map(ProdutoResponse::fromModel).collect(Collectors.toList());
    }
}
