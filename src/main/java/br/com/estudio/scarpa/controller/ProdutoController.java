package br.com.estudio.scarpa.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estudio.scarpa.domain.produto.DadosCadastroProduto;
import br.com.estudio.scarpa.domain.produto.Produto;
import br.com.estudio.scarpa.service.ProdutoService;

@RestController
@RequestMapping("api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrar(@RequestBody DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = service.cadastrarProduto(dados);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<Page<Produto>> listar(@PageableDefault(size = 10, sort = {"titulo"}) Pageable paginacao) {
        var page = service.listar(paginacao);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Produto> atualizar(@RequestBody Produto dados) {
        var produto = service.atualizar(dados);
        return ResponseEntity.ok(produto);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity<Produto> excluir(@PathVariable Long id) {
        service.excluir(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Produto> detalhar(@PathVariable Long id) {
        var produto = service.detalhar(id);
        return ResponseEntity.ok(produto);
    }

}
