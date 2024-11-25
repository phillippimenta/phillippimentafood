package br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception.ProdutoJaCadastradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.exception.ProdutoNaoEncontradoException;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.ProdutoJpaRepository;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.ProdutoRepository;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.entity.ProdutoEntity;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.infrastructure.persistence.database.mapper.ProdutoEntityMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class ProdutoServiceTest {

    private ProdutoService produtoService;
    @Mock
    private ProdutoJpaRepository produtoJpaRepository;
    private ProdutoEntityMapper produtoEntityMapper;
    private AutoCloseable mock;

    @BeforeEach
    void setup() throws Exception {
        this.mock = MockitoAnnotations.openMocks(this);
        this.produtoEntityMapper = new ProdutoEntityMapper();
        ProdutoRepository produtoRepository = new ProdutoRepository(this.produtoJpaRepository, this.produtoEntityMapper);
        this.produtoService = new ProdutoService(produtoRepository);
    }

    @AfterEach
    void tearDown() throws Exception {
        this.mock.close();
    }

    @Test
    public void deveCadastrarProduto_QuandoDadosSaoValidos() {
        // Arrange
        String nome = "Bauru simples";
        String descricao = "Pão, hambúrguer, ovo, queijo, presunto, tomate, cebola e alface.";
        BigDecimal preco = new BigDecimal("12.90");
        Produto produto = new Produto(Categoria.LANCHE, nome, descricao, preco);
        when(this.produtoJpaRepository.findByNomeIgnoreCase(any(String.class))).thenReturn(null);
        ProdutoEntity produtoEntity = this.produtoEntityMapper.fromDomain(produto);
        produtoEntity.setId(1L);
        produtoEntity.setDataCriacao(LocalDateTime.now());
        when(this.produtoJpaRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntity);
        // Act
        Produto produtoCadastrado = this.produtoService.cadastrarProduto(produto);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByNomeIgnoreCase(any());
        verify(this.produtoJpaRepository, times(1)).save(any());
        assertThat(produtoCadastrado)
                .isInstanceOf(Produto.class)
                .isNotNull();
        assertThat(produtoCadastrado.getNome()).isEqualTo(nome);
        assertThat(produtoCadastrado.getDescricao()).isEqualTo(descricao);
        assertThat(produtoCadastrado.getPreco()).isEqualTo(preco);
    }

    @Test
    public void deveGerarExcecao_QuandoCadastrarProduto_NomeJaExiste() {
        // Arrange
        String nome = "Bauru simples";
        String descricao = "Pão, hambúrguer, ovo, queijo, presunto, tomate, cebola e alface.";
        BigDecimal preco = new BigDecimal("12.90");
        Produto produto = new Produto(Categoria.LANCHE, nome, descricao, preco);
        ProdutoEntity produtoEntity = this.produtoEntityMapper.fromDomain(produto);
        produtoEntity.setId(1L);
        produtoEntity.setDataCriacao(LocalDateTime.now());
        when(this.produtoJpaRepository.findByNomeIgnoreCase(any(String.class))).thenReturn(produtoEntity);
        String mensagem = String.format("Produto %s já cadastrado.", nome);
        // Act and Assert
        assertThatThrownBy(() -> this.produtoService.cadastrarProduto(produto))
                .isInstanceOf(ProdutoJaCadastradoException.class)
                .hasMessage(mensagem);
    }

    @Test
    public void deveExcluirProdutoPorIdExterno_QuandoProdutoExiste() {
        // Arrange
        UUID idExterno = UUID.randomUUID();
        Produto produto = new Produto(Categoria.LANCHE, "Bauru simples", "Pão, hambúrguer, queijo", BigDecimal.valueOf(12.90));
        produto.setId(1L); // ID gerado pelo banco
        produto.setIdExterno(idExterno); // UUID gerado externamente
        ProdutoEntity produtoEntity = this.produtoEntityMapper.fromDomain(produto);
        produtoEntity.setId(1L);
        produtoEntity.setDataCriacao(LocalDateTime.now());
        when(this.produtoJpaRepository.findByIdExterno(any(UUID.class))).thenReturn(produtoEntity);
        // Act
        this.produtoService.excluirProdutoPorIdExterno(idExterno);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByIdExterno(eq(idExterno));
        verify(this.produtoJpaRepository, times(1)).deleteById(eq(produtoEntity.getId()));
    }

    @Test
    public void deveListarProdutosPorCategoria_QuandoCategoriaValida() {
        // Arrange
        Categoria categoria = Categoria.LANCHE;  // A categoria que você deseja testar
        Produto produto1 = new Produto(Categoria.LANCHE, "Bauru simples", "Pão, hambúrguer, queijo", BigDecimal.valueOf(12.90));
        Produto produto2 = new Produto(Categoria.LANCHE, "Bauru duplo", "Pão, hambúrguer, queijo, presunto", BigDecimal.valueOf(15.90));
        ProdutoEntity produtoEntity1 = this.produtoEntityMapper.fromDomain(produto1);
        ProdutoEntity produtoEntity2 = this.produtoEntityMapper.fromDomain(produto2);
        List<ProdutoEntity> produtoEntities = Arrays.asList(produtoEntity1, produtoEntity2);
        when(this.produtoJpaRepository.findByCategoria(eq(categoria))).thenReturn(produtoEntities);
        // Act
        List<Produto> produtos = this.produtoService.listarProdutosPorCategoria(categoria);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByCategoria(eq(categoria));
        assertThat(produtos).isNotEmpty();
        assertThat(produtos.size()).isEqualTo(2);
        assertThat(produtos.get(0).getNome()).isEqualTo("Bauru simples");
        assertThat(produtos.get(1).getNome()).isEqualTo("Bauru duplo");
    }

    @Test
    public void deveRetornarListaVazia_QuandoNaoExistemProdutosParaCategoria() {
        // Arrange
        Categoria categoria = Categoria.LANCHE;
        when(this.produtoJpaRepository.findByCategoria(eq(categoria))).thenReturn(Collections.emptyList());
        // Act
        List<Produto> produtos = this.produtoService.listarProdutosPorCategoria(categoria);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByCategoria(eq(categoria));
        assertThat(produtos).isEmpty();
    }

    @Test
    public void deveObterProdutoPorIdExterno_QuandoProdutoExistir() {
        // Arrange
        UUID idExterno = UUID.randomUUID();
        Produto produto = new Produto(Categoria.LANCHE, "Bauru simples", "Pão, hambúrguer, queijo", BigDecimal.valueOf(12.90));
        produto.setId(1L);  // ID gerado pelo banco
        produto.setIdExterno(idExterno);  // UUID gerado externamente
        ProdutoEntity produtoEntity = this.produtoEntityMapper.fromDomain(produto);
        produtoEntity.setId(1L);
        produtoEntity.setDataCriacao(LocalDateTime.now());
        when(this.produtoJpaRepository.findByIdExterno(eq(idExterno))).thenReturn(produtoEntity);
        // Act
        Produto produtoObtido = this.produtoService.obterProdutoPorIdExterno(idExterno);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByIdExterno(eq(idExterno));
        assertThat(produtoObtido).isNotNull();
        assertThat(produtoObtido.getNome()).isEqualTo("Bauru simples");
        assertThat(produtoObtido.getIdExterno()).isEqualTo(idExterno);
    }

    @Test
    public void deveGerarExcecao_QuandoObterProdutoPorIdExterno_IdExternoNaoExistir() {
        // Arrange
        UUID idExterno = UUID.randomUUID();
        when(this.produtoJpaRepository.findByIdExterno(eq(idExterno))).thenReturn(null);
        // Act and Assert
        assertThatThrownBy(() -> this.produtoService.obterProdutoPorIdExterno(idExterno))
                .isInstanceOf(ProdutoNaoEncontradoException.class)
                .hasMessageContaining(idExterno.toString());
    }

    @Test
    public void deveAtualizarProduto_QuandoProdutoExistir() {
        // Arrange
        UUID idExterno = UUID.randomUUID();
        Produto produtoExistente = new Produto(Categoria.LANCHE, "Bauru simples", "Pão, hambúrguer, queijo", BigDecimal.valueOf(12.90));
        produtoExistente.setId(1L);
        produtoExistente.setIdExterno(idExterno);
        Produto produtoAtualizado = new Produto(Categoria.LANCHE, "Bauru especial", "Pão, hambúrguer, ovo, queijo", BigDecimal.valueOf(15.90));
        ProdutoEntity produtoEntityExistente = this.produtoEntityMapper.fromDomain(produtoExistente);
        produtoEntityExistente.setId(1L);
        produtoEntityExistente.setDataCriacao(LocalDateTime.now());
        ProdutoEntity produtoEntityAtualizado = this.produtoEntityMapper.fromDomain(produtoAtualizado);
        produtoEntityAtualizado.setId(1L);
        when(this.produtoJpaRepository.findByIdExterno(eq(idExterno))).thenReturn(produtoEntityExistente);
        when(this.produtoJpaRepository.save(any(ProdutoEntity.class))).thenReturn(produtoEntityAtualizado);
        // Act
        Produto produtoRetornado = this.produtoService.atualizarProduto(idExterno, produtoAtualizado);
        // Assert
        verify(this.produtoJpaRepository, times(1)).findByIdExterno(eq(idExterno));
        verify(this.produtoJpaRepository, times(1)).save(any(ProdutoEntity.class));
        assertThat(produtoRetornado).isNotNull();
        assertThat(produtoRetornado.getNome()).isEqualTo("Bauru especial");
        assertThat(produtoRetornado.getDescricao()).isEqualTo("Pão, hambúrguer, ovo, queijo");
        assertThat(produtoRetornado.getPreco()).isEqualTo(BigDecimal.valueOf(15.90));
    }

    @Test
    public void deveGerarExcecao_QuandoAtualizarProduto_IdExternoNaoExistir() {
        // Arrange
        UUID idExterno = UUID.randomUUID();
        Produto produtoAtualizado = new Produto(Categoria.LANCHE, "Bauru especial", "Pão, hambúrguer, ovo, queijo", BigDecimal.valueOf(15.90));
        when(this.produtoJpaRepository.findByIdExterno(eq(idExterno))).thenReturn(null);
        // Act and Assert
        assertThatThrownBy(() -> this.produtoService.atualizarProduto(idExterno, produtoAtualizado))
                .isInstanceOf(ProdutoNaoEncontradoException.class)
                .hasMessageContaining(idExterno.toString());
    }

}
