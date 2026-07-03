package com.alexsander.descontos.service;

import com.alexsander.descontos.model.Produto;
import com.alexsander.descontos.repository.ProdutoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProdutoService {

    @Autowired
    private ProdutoRepository repository;

    /**
     * Salva um produto no banco de dados
     */
    public Produto salvar(Produto produto) {
        return repository.save(produto);
    }

    /**
     * Calcula o desconto baseado no preço do produto
     * Regra:
     * - Preço até R$ 50,00: sem desconto (0%)
     * - Preço de R$ 50,01 a R$ 100,00: 10% de desconto
     * - Preço acima de R$ 100,00: 20% de desconto
     */
    public Double calcularDesconto(Double preco) {
        if (preco > 100) {
            return preco * 0.20;  // 20% de desconto
        } else if (preco > 50) {
            return preco * 0.10;  // 10% de desconto
        } else {
            return 0.0;           // Sem desconto
        }
    }

    /**
     * Calcula o preço final do produto após aplicar o desconto
     */
    public Double calcularPrecoFinal(Double preco) {
        Double desconto = calcularDesconto(preco);
        return preco - desconto;
    }

    /**
     * Lista todos os produtos cadastrados
     */
    public Iterable<Produto> listarTodos() {
        return repository.findAll();
    }

    /**
     * Busca um produto por ID
     */
    public Produto buscarPorId(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado!"));
    }

    /**
     * Deleta um produto por ID
     */
    public void deletar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Produto não encontrado!");
        }
        repository.deleteById(id);
    }
}