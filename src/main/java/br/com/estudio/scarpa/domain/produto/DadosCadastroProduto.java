package br.com.estudio.scarpa.domain.produto;

import java.math.BigDecimal;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public record DadosCadastroProduto(
        @NotBlank String titulo,
        String descricao,
        @NotBlank String imagem,
        @NotNull BigDecimal preco,
        String categoria) {
}
