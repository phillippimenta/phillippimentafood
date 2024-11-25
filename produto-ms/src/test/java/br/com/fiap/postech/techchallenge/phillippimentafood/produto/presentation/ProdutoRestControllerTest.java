package br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service.ProdutoService;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto.AtualizarProdutoRequest;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto.CadastrarProdutoRequest;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

public class ProdutoRestControllerTest {

    private MockMvc mockMvc;
    @Mock
    private ProdutoService produtoService;
    private ObjectMapper objectMapper;
    private AutoCloseable openMocks;

    @BeforeEach
    void setUp() {
        openMocks = MockitoAnnotations.openMocks(this);
        ProdutoRestController mensagemController = new ProdutoRestController(produtoService);
        mockMvc = MockMvcBuilders.standaloneSetup(mensagemController)
                .addFilter((request, response, chain) -> {
                    response.setCharacterEncoding("UTF-8");
                    chain.doFilter(request, response);
                }, "/*")
                .build();
        this.objectMapper = new ObjectMapper();
    }

    @AfterEach
    void tearDown() throws Exception {
        openMocks.close();
    }

    @Test
    public void deveCriarProdutoComSucesso() throws Exception {
        CadastrarProdutoRequest request = new CadastrarProdutoRequest(
                Categoria.LANCHE,
                "Produto Teste",
                "Descrição do Produto",
                new BigDecimal("100.00")
        );
        when(produtoService.cadastrarProduto(any(Produto.class))).thenReturn(new Produto(
                Categoria.LANCHE, "Produto Teste", "Descrição do Produto", new BigDecimal("100.00")
        ));
        mockMvc.perform(post("/produtos")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id_externo").exists())
                .andExpect(jsonPath("$.categoria").value(Categoria.LANCHE.name()))
                .andExpect(jsonPath("$.nome").value("Produto Teste"));
    }

    @Test
    public void deveAtualizarProdutoComSucesso() throws Exception {
        AtualizarProdutoRequest request = new AtualizarProdutoRequest(
                "Produto Atualizado",
                "Descrição Atualizada",
                new BigDecimal("150.00")
        );
        when(produtoService.atualizarProduto(any(UUID.class), any(Produto.class))).thenReturn(new Produto(
                Categoria.LANCHE, "Produto Atualizado", "Descrição Atualizada", new BigDecimal("150.00")
        ));
        mockMvc.perform(put("/produtos/{idExterno}", UUID.randomUUID())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Atualizado"))
                .andExpect(jsonPath("$.preco").value(150.00));
    }

    @Test
    public void deveBuscarProdutoPorIdExternoComSucesso() throws Exception {
        when(produtoService.obterProdutoPorIdExterno(any(UUID.class))).thenReturn(new Produto(
                Categoria.LANCHE, "Produto Teste", "Descrição do Produto", new BigDecimal("100.00")
        ));
        mockMvc.perform(get("/produtos/{idExterno}", UUID.randomUUID()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nome").value("Produto Teste"))
                .andExpect(jsonPath("$.preco").value(100.00));
    }

    @Test
    public void deveListarProdutosPorCategoriaComSucesso() throws Exception {
        List<Produto> produtos = Arrays.asList(new Produto(
                Categoria.LANCHE, "Produto 1", "Descrição 1", new BigDecimal("100.00")
        ), new Produto(
                Categoria.LANCHE, "Produto 2", "Descrição 2", new BigDecimal("150.00")
        ));
        when(produtoService.listarProdutosPorCategoria(Categoria.LANCHE)).thenReturn(produtos);
        mockMvc.perform(get("/produtos")
                        .param("categoria", Categoria.LANCHE.name()))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].nome").value("Produto 1"))
                .andExpect(jsonPath("$[1].preco").value(150.00));
    }

    @Test
    public void deveExcluirProdutoComSucesso() throws Exception {
        UUID idExterno = UUID.randomUUID();
        doNothing().when(produtoService).excluirProdutoPorIdExterno(idExterno);
        mockMvc.perform(delete("/produtos/{idExterno}", idExterno))
                .andExpect(status().isNoContent());
    }
}
