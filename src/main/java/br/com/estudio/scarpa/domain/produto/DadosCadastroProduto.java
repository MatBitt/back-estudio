package br.com.estudio.scarpa.domain.produto;

import java.math.BigDecimal;

public record DadosCadastroProduto(
        String titulo,
        String descricao,
        String imagem,
        BigDecimal preco) {
}
