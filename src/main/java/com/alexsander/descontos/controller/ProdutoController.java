package com.alexsander.descontos.controller;

import com.alexsander.descontos.model.Produto;
import com.alexsander.descontos.service.ProdutoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/produtos")
public class ProdutoController {

    @Autowired
    private ProdutoService service;

    /**
     * GET /produtos - Lista todos os produtos
     */
    @GetMapping
    public Iterable<Produto> listar() {
        return service.listarTodos();
    }

    /**
     * GET /produtos/{id} - Busca produto por ID
     */
    @GetMapping("/{id}")
    public Produto buscarPorId(@PathVariable Long id) {
        return service.buscarPorId(id);
    }

    /**
     * POST /produtos - Cria um novo produto
     */
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Produto criar(@RequestBody Produto produto) {
        return service.salvar(produto);
    }

    /**
     * PUT /produtos/{id} - Atualiza um produto existente
     */
    @PutMapping("/{id}")
    public Produto atualizar(@PathVariable Long id, @RequestBody Produto produto) {
        produto.setId(id);
        return service.salvar(produto);
    }

    /**
     * DELETE /produtos/{id} - Deleta um produto
     */
    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deletar(@PathVariable Long id) {
        service.deletar(id);
    }

    /**
     * GET /produtos/calcular?preco=80 - Calcula desconto e preço final
     */
    @GetMapping("/calcular")
    public Map<String, Double> calcular(@RequestParam Double preco) {
        Map<String, Double> resultado = new HashMap<>();
        resultado.put("precoOriginal", preco);
        resultado.put("desconto", service.calcularDesconto(preco));
        resultado.put("precoFinal", service.calcularPrecoFinal(preco));
        return resultado;
    }
}