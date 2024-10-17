package br.com.estudio.scarpa.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.estudio.scarpa.domain.produto.DadosCadastroProduto;
import br.com.estudio.scarpa.domain.produto.Produto;
import br.com.estudio.scarpa.domain.produto.ProdutoRepository;
import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityNotFoundException;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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
    public Produto cadastrarProduto(@Valid DadosCadastroProduto dados) {
        var produto = new Produto(dados);
        repository.save(produto);
        return produto;
    }

    public Page<Produto> listar(Pageable paginacao) {
        return repository.findAll(paginacao);
    }

    @Transactional
    public Produto atualizar(Produto dados) {
        var produto = repository.findById(dados.getId()).orElseThrow(() -> new EntityNotFoundException());

        produto.setDescricao(dados.getDescricao());
        produto.setImagem(dados.getImagem());
        produto.setPreco(dados.getPreco());
        produto.setTitulo(dados.getTitulo());

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
