package com.alexsander.descontos.service;

import com.alexsander.descontos.model.Produto;
import com.alexsander.descontos.repository.ProdutoRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;

import java.util.Arrays;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@SpringBootTest
class ProdutoServiceTest {

    @Autowired
    private ProdutoService service;

    @MockitoBean
    private ProdutoRepository repository;

    // ==================== TESTES DE DESCONTO ====================

    @Test
    void deveCalcularDescontoParaPrecoAte50() {
        // ARRANGE
        Double preco = 50.0;

        // ACT
        Double desconto = service.calcularDesconto(preco);

        // ASSERT
        assertThat(desconto).isEqualTo(0.0);
    }

    @Test
    void deveCalcularDescontoParaPrecoEntre50e100() {
        // ARRANGE
        Double preco = 80.0;

        // ACT
        Double desconto = service.calcularDesconto(preco);

        // ASSERT
        assertThat(desconto).isEqualTo(8.0); // 10% de 80
    }

    @Test
    void deveCalcularDescontoParaPrecoAcimaDe100() {
        // ARRANGE
        Double preco = 150.0;

        // ACT
        Double desconto = service.calcularDesconto(preco);

        // ASSERT
        assertThat(desconto).isEqualTo(30.0); // 20% de 150
    }

    @Test
    void deveCalcularPrecoFinalParaPrecoAte50() {
        // ARRANGE
        Double preco = 30.0;

        // ACT
        Double precoFinal = service.calcularPrecoFinal(preco);

        // ASSERT
        assertThat(precoFinal).isEqualTo(30.0);
    }

    @Test
    void deveCalcularPrecoFinalParaPrecoEntre50e100() {
        // ARRANGE
        Double preco = 80.0;

        // ACT
        Double precoFinal = service.calcularPrecoFinal(preco);

        // ASSERT
        assertThat(precoFinal).isEqualTo(72.0); // 80 - 8
    }

    @Test
    void deveCalcularPrecoFinalParaPrecoAcimaDe100() {
        // ARRANGE
        Double preco = 150.0;

        // ACT
        Double precoFinal = service.calcularPrecoFinal(preco);

        // ASSERT
        assertThat(precoFinal).isEqualTo(120.0); // 150 - 30
    }

    // ==================== TESTES DE SALVAR ====================

    @Test
    void deveSalvarProdutoComSucesso() {
        // ARRANGE
        Produto produto = new Produto("Monitor", 800.0);
        when(repository.save(produto)).thenReturn(produto);

        // ACT
        Produto salvo = service.salvar(produto);

        // ASSERT
        assertThat(salvo).isNotNull();
        assertThat(salvo.getNome()).isEqualTo("Monitor");
        verify(repository, times(1)).save(produto);
    }

    // ==================== TESTES DE LISTAR ====================

    @Test
    void deveListarTodosOsProdutos() {
        // ARRANGE
        Produto p1 = new Produto("Produto 1", 10.0);
        Produto p2 = new Produto("Produto 2", 20.0);
        when(repository.findAll()).thenReturn(Arrays.asList(p1, p2));

        // ACT
        Iterable<Produto> produtos = service.listarTodos();

        // ASSERT
        assertThat(produtos).hasSize(2);
        verify(repository, times(1)).findAll();
    }

    // ==================== TESTES DE BUSCAR POR ID ====================

    @Test
    void deveBuscarProdutoPorIdExistente() {
        // ARRANGE
        Produto produto = new Produto(1L, "Teclado", 150.0);
        when(repository.findById(1L)).thenReturn(Optional.of(produto));

        // ACT
        Produto encontrado = service.buscarPorId(1L);

        // ASSERT
        assertThat(encontrado).isNotNull();
        assertThat(encontrado.getNome()).isEqualTo("Teclado");
        verify(repository, times(1)).findById(1L);
    }

    @Test
    void deveLancarExcecaoAoBuscarProdutoInexistente() {
        // ARRANGE
        when(repository.findById(999L)).thenReturn(Optional.empty());

        // ACT & ASSERT
        assertThatThrownBy(() -> service.buscarPorId(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Produto não encontrado!");
    }

    // ==================== TESTES DE DELETAR ====================

    @Test
    void deveDeletarProdutoExistente() {
        // ARRANGE
        when(repository.existsById(1L)).thenReturn(true);
        doNothing().when(repository).deleteById(1L);

        // ACT
        service.deletar(1L);

        // ASSERT
        verify(repository, times(1)).deleteById(1L);
    }

    @Test
    void deveLancarExcecaoAoDeletarProdutoInexistente() {
        // ARRANGE
        when(repository.existsById(999L)).thenReturn(false);

        // ACT & ASSERT
        assertThatThrownBy(() -> service.deletar(999L))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("Produto não encontrado!");
    }
}