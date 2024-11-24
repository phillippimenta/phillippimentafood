package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.outbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;

import java.util.List;
import java.util.UUID;

public interface ProdutoRepositoryPort {

    List<Produto> pesquisarPorCategoria(Categoria categoria);

    Produto pesquisarPorUuid(UUID uuid);

    Produto pesquisarPorNome(String nome);

    Produto salvar(Produto produto);

    void deletarPorId(Long id);
}
