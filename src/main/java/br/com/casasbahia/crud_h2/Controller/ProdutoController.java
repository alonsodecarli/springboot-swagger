package br.com.casasbahia.crud_h2.Controller;

import br.com.casasbahia.crud_h2.dto.ProdutoRequest;
import br.com.casasbahia.crud_h2.dto.ProdutoResponse;
import br.com.casasbahia.crud_h2.mapper.ProdutoMapper;
import br.com.casasbahia.crud_h2.model.Produto;
import br.com.casasbahia.crud_h2.service.ProdutoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.headers.Header;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@Tag(name = "Produtos", description = "Operações do cadastro de produtos")
@RestController
@RequestMapping("/api/produtos")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService produtoService;
    private final ProdutoMapper produtoMapper;

    @Operation(summary = "Cadastrar um produto")
    @ApiResponse(responseCode = "201", description = "Produto cadastrado", useReturnTypeSchema = true,
            headers = @Header(name = "Location", description = "URL do produto cadastrado",
                    schema = @Schema(type = "string", format = "uri")))
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @PostMapping
    public ResponseEntity<ProdutoResponse> criar(@Valid @RequestBody ProdutoRequest request) {
        Produto salvo = produtoService.criar(produtoMapper.toEntity(request));
        URI location = ServletUriComponentsBuilder.fromCurrentRequestUri()
                .path("/{id}")
                .buildAndExpand(salvo.getId())
                .toUri();
        return ResponseEntity.created(location).body(produtoMapper.toResponse(salvo));
    }

    @Operation(summary = "Listar os produtos")
    @ApiResponse(responseCode = "200", description = "Lista retornada", useReturnTypeSchema = true)
    @GetMapping
    public List<ProdutoResponse> listar() {
        return produtoService.listar().stream()
                .map(produtoMapper::toResponse)
                .toList();
    }

    @Operation(summary = "Buscar um produto pelo ID")
    @ApiResponse(responseCode = "200", description = "Produto encontrado", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "ID ou requisição inválida", content = @Content)
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @GetMapping("/{id}")
    public ProdutoResponse buscarPorId(
            @Parameter(description = "ID do produto", example = "1") @PathVariable("id") Long id) {
        return produtoMapper.toResponse(produtoService.buscarPorId(id));
    }

    @Operation(summary = "Atualizar um produto")
    @ApiResponse(responseCode = "200", description = "Produto atualizado", useReturnTypeSchema = true)
    @ApiResponse(responseCode = "400", description = "Requisição inválida", content = @Content)
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @PutMapping("/{id}")
    public ProdutoResponse atualizar(
            @Parameter(description = "ID do produto", example = "1") @PathVariable("id") Long id,
            @Valid @RequestBody ProdutoRequest request) {
        Produto produto = produtoMapper.toEntity(request);
        produto.setId(id);
        return produtoMapper.toResponse(produtoService.atualizar(produto));
    }

    @Operation(summary = "Excluir um produto")
    @ApiResponse(responseCode = "204", description = "Produto excluído", content = @Content)
    @ApiResponse(responseCode = "400", description = "ID ou requisição inválida", content = @Content)
    @ApiResponse(responseCode = "404", description = "Produto não encontrado", content = @Content)
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(
            @Parameter(description = "ID do produto", example = "1") @PathVariable("id") Long id) {
        produtoService.deletar(id);
        return ResponseEntity.noContent().build();
    }
}