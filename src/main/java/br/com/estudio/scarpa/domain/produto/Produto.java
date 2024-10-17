package br.com.estudio.scarpa.domain.produto;

import java.math.BigDecimal;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "produtos")
@Data
@NoArgsConstructor
public class Produto {

    @Id()
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String titulo;
    private String descricao;
    private String imagem;
    private BigDecimal preco;

    public Produto(DadosCadastroProduto dados) {
        this.titulo = dados.titulo();
        this.descricao = dados.descricao();
        this.imagem = dados.imagem();
        this.preco = dados.preco();
    }
}
