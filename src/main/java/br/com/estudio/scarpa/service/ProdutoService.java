package br.com.estudio.scarpa.service;

import org.springframework.stereotype.Service;

import br.com.estudio.scarpa.domain.produto.DadosAtualiazacaoProduto;
import br.com.estudio.scarpa.domain.produto.DadosCadastroProduto;
import br.com.estudio.scarpa.domain.produto.Produto;
import br.com.estudio.scarpa.domain.produto.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProdutoService {
    private final ProdutoRepository repository;

    @PersistenceContext
    private EntityManager em;

    @Transactional
    public void save(Produto produto) {
        repository.save(produto);
    }

    @Transactional
    public Produto cadastrarProduto(DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        repository.save(produto);
        return produto;
    }

    public Iterable<Produto> listar() {
        return repository.findAll();
    }

    @Transactional
    public Produto atualizar(DadosAtualiazacaoProduto dados) {
        var produto = repository.findById(dados.id()).orElseThrow(() -> new EntityNotFoundException());

        produto.setDescricao(dados.descricao());
        produto.setImagem(dados.imagem());
        produto.setPreco(dados.preco());
        produto.setTitulo(dados.titulo());
        produto.setCategoria(dados.categoria().toLowerCase());

        repository.save(produto);

        return produto;
    }

    @Transactional
    public void excluir(Long id) {
        var produto = repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
        repository.delete(produto);
    }

    public Produto detalhar(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException());
    }
}
