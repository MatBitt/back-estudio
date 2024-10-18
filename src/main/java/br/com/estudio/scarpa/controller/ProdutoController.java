package br.com.estudio.scarpa.controller;

import lombok.RequiredArgsConstructor;

import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import br.com.estudio.scarpa.domain.produto.DadosAtualiazacaoProduto;
import br.com.estudio.scarpa.domain.produto.DadosCadastroProduto;
import br.com.estudio.scarpa.domain.produto.Produto;
import br.com.estudio.scarpa.service.ProdutoService;
import jakarta.validation.Valid;

@RestController
@RequestMapping("api/produto")
@RequiredArgsConstructor
public class ProdutoController {

    private final ProdutoService service;

    @PostMapping
    @Transactional
    public ResponseEntity<Produto> cadastrar(@RequestBody @Valid DadosCadastroProduto dados, UriComponentsBuilder uriBuilder) {
        var produto = service.cadastrarProduto(dados);
        var uri = uriBuilder.path("/produto/{id}").buildAndExpand(produto.getId()).toUri();
        return ResponseEntity.created(uri).body(produto);
    }

    @GetMapping
    public ResponseEntity<Iterable<Produto>> listar() {
        var produtos = service.listar();
        return ResponseEntity.ok(produtos);
    }

    @PutMapping
    @Transactional
    public ResponseEntity<Produto> atualizar(@RequestBody DadosAtualiazacaoProduto dados) {
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
