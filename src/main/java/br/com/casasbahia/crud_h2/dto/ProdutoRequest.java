package br.com.casasbahia.crud_h2.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PositiveOrZero;

import java.math.BigDecimal;

@Schema(description = "Dados para criar ou atualizar um produto")
public record ProdutoRequest(
        @NotBlank @Schema(example = "Teclado USB") String nome,
        @Schema(example = "84716052") String ncm,
        @Schema(example = "Teclados") String descricaoNcm,
        @NotNull @PositiveOrZero @Schema(example = "89.90") BigDecimal preco,
        @NotNull @PositiveOrZero @Schema(example = "12") Integer quantidade
) {}