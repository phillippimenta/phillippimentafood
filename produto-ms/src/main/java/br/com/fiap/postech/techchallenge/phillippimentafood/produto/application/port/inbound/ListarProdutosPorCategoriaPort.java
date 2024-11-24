package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;

import java.util.List;

public interface ListarProdutosPorCategoriaPort {

    List<Produto> listarProdutosPorCategoria(Categoria categoria);
}
