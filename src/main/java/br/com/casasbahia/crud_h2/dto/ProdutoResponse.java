package br.com.casasbahia.crud_h2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import java.math.BigDecimal;

@Schema(description = "Produto retornado pela API")
public record ProdutoResponse(
        @Schema(description = "Identificador gerado pelo banco", example = "1") Long id,
        @Schema(example = "Teclado USB") String nome,
        @Schema(example = "84716052") String ncm,
        @Schema(example = "Teclados") String descricaoNcm,
        @Schema(example = "89.90") BigDecimal preco,
        @Schema(example = "12") Integer quantidade
) {}