package br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation;

import br.com.fiap.postech.techchallenge.phillippimentafood.produto.application.service.ProdutoService;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Categoria;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.domain.model.Produto;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto.AtualizarProdutoRequest;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto.CadastrarProdutoRequest;
import br.com.fiap.postech.techchallenge.phillippimentafood.produto.presentation.dto.ProdutoResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.UUID;

@Tag(name = "Produtos Controller", description = "Operações relacionadas a produtos")
@RestController
@RequestMapping("/produtos")
public class ProdutoRestController {

    private final ProdutoService produtoService;

    public ProdutoRestController(ProdutoService produtoService) {
        this.produtoService = produtoService;
    }

    @Operation(summary = "Permite o cadastro de um novo produto.", description = "Retorna o produto cadastrado com os seguintes detalhes: idExterno, categoria, nome, descricao, preco, data_criacao e data_atualizacao. A data_atualizacao será retornada como null quando não houver preenchimento.")
    @ApiResponse(responseCode = "201", description = "Produto cadastrado com sucesso.")
    @PostMapping
    public ResponseEntity<ProdutoResponse> criarProduto(@Valid @RequestBody CadastrarProdutoRequest request) {
        Produto produto = CadastrarProdutoRequest.toModel(request);
        ProdutoResponse produtoResponse = ProdutoResponse
                .fromModel(this.produtoService.cadastrarProduto(produto));
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoResponse);
    }

    @Operation(summary = "Permite a atualização dos dados de um produto existente.", description = "Retorna os detalhes do produto atualizado, incluindo: idExterno, categoria, nome, descricao, preco, data_criacao e data_atualizacao.")
    @ApiResponse(responseCode = "200", description = "Produto atualizado com sucesso.")
    @PutMapping("/{idExterno}")
    public ResponseEntity<ProdutoResponse> atualizarProduto(@PathVariable(name = "idExterno") UUID idExterno,
                                                            @Valid @RequestBody AtualizarProdutoRequest request) {
        Produto produtoAtualizado = AtualizarProdutoRequest.toModel(request);
        ProdutoResponse produtoResponse = ProdutoResponse
                .fromModel(this.produtoService.atualizarProduto(idExterno, produtoAtualizado));
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
    }

    @Operation(summary = "Permite localizar um produto específico utilizando o UUID fornecido.",
            description = "Retorna o produto, se encontrado, com os seguintes detalhes: UUID, Categoria, Nome, " +
                    "Descrição, Preço e Data de Criação. A Data de Atualização será retornada como null " +
                    "quando não houver preenchimento.")
    @ApiResponse(responseCode = "200", description = "Produto localizado com sucesso.")
    @GetMapping("/{idExterno}")
    public ResponseEntity<ProdutoResponse> obterProdutoPorIdExterno(@PathVariable UUID idExterno) {
        ProdutoResponse produtoResponse = ProdutoResponse
                .fromModel(this.produtoService.obterProdutoPorIdExterno(idExterno));
        return ResponseEntity.status(HttpStatus.OK).body(produtoResponse);
    }

    @Operation(summary = "Permite listar produtos com a opção de pesquisa por categoria.")
    @ApiResponse(responseCode = "200", description = "Listagem de produtos encontrados")
    @GetMapping
    public ResponseEntity<List<ProdutoResponse>> listarProdutosPorCategoria(@RequestParam Categoria categoria) {
        List<Produto> produtoList = this.produtoService.listarProdutosPorCategoria(categoria);
        return ResponseEntity.status(HttpStatus.OK).body(ProdutoResponse.fromModel(produtoList));
    }

    @Operation(summary = "Permite remover um produto")
    @ApiResponse(responseCode = "204", description = "Produto deletado com sucesso")
    @DeleteMapping("/{idExterno}")
    public ResponseEntity<Void> excluirProdutoPorIdExterno(@PathVariable UUID idExterno) {
        produtoService.excluirProdutoPorIdExterno(idExterno);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }
}
