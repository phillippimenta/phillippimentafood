package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound.AtualizarProdutoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound.CriarProdutoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound.ExcluirProdutoPorIdExternoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound.ListarProdutosPorCategoriaPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.inbound.ObterProdutoPorIdExternoPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.port.outbound.ProdutoRepositoryPort;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception.ProdutoJaCadastradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception.ProdutoNaoEncontradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;

import java.util.List;
import java.util.UUID;

public class ProdutosPorCategoriaPorIdExternoService implements CriarProdutoPort, AtualizarProdutoPort,
        ListarProdutosPorCategoriaPort, ExcluirProdutoPorIdExternoPort, ObterProdutoPorIdExternoPort {

    private final ProdutoRepositoryPort produtoRepositoryPort;

    public ProdutosPorCategoriaPorIdExternoService(ProdutoRepositoryPort produtoRepositoryPort) {
        this.produtoRepositoryPort = produtoRepositoryPort;
    }

    @Override
    public Produto criarProduto(Produto produto) {
        Produto produtoPesquisado = this.produtoRepositoryPort.pesquisarPorNome(produto.getNome());
        if (produtoPesquisado != null) {
            throw new ProdutoJaCadastradoException(produtoPesquisado);
        }
        return this.produtoRepositoryPort.salvar(produto);
    }

    @Override
    public void excluirProdutoPorIdExterno(UUID idExterno) {
        Produto produtoEncontrado = this.obterProdutoPorIdExterno(idExterno);
        produtoRepositoryPort.deletarPorId(produtoEncontrado.getId());
    }

    @Override
    public List<Produto> listarProdutosPorCategoria(Categoria categoria) {
        return this.produtoRepositoryPort.pesquisarPorCategoria(categoria);
    }

    @Override
    public Produto obterProdutoPorIdExterno(UUID idExterno) {
        Produto produtoEncontrado = this.produtoRepositoryPort.pesquisarPorUuid(idExterno);
        if (produtoEncontrado == null) {
            throw new ProdutoNaoEncontradoException(idExterno);
        }
        return produtoEncontrado;
    }

    @Override
    public Produto atualizarProduto(UUID idExterno, Produto produtoAtualizado) {
        Produto produto = this.obterProdutoPorIdExterno(idExterno);
        produto.atualizar(produtoAtualizado.getNome(), produtoAtualizado.getDescricao(), produtoAtualizado.getPreco());
        return produtoRepositoryPort.salvar(produto);
    }
}
