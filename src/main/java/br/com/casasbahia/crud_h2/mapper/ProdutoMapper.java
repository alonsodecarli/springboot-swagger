package br.com.casasbahia.crud_h2.mapper;

import br.com.casasbahia.crud_h2.dto.ProdutoRequest;
import br.com.casasbahia.crud_h2.dto.ProdutoResponse;
import br.com.casasbahia.crud_h2.model.Produto;
import org.springframework.stereotype.Component;

@Component
public class ProdutoMapper {

    public Produto toEntity(ProdutoRequest request) {
        return Produto.builder()
                .nome(request.nome())
                .ncm(request.ncm())
                .descricaoNcm(request.descricaoNcm())
                .preco(request.preco())
                .quantidade(request.quantidade())
                .build();
    }

    public ProdutoResponse toResponse(Produto produto) {
        return new ProdutoResponse(
                produto.getId(),
                produto.getNome(),
                produto.getNcm(),
                produto.getDescricaoNcm(),
                produto.getPreco(),
                produto.getQuantidade()
        );
    }
}