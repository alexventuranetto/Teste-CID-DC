package com.alexsander.descontos.repository;

import com.alexsander.descontos.model.Produto;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

@DataJpaTest
class ProdutoRepositoryTest {

	@Autowired
	private ProdutoRepository repository;

	
	// ===== Essa parte foi inserida para limpar os dados inseridos no import.sql
	@BeforeEach
	void setUp() {
		repository.deleteAll(); // Isso limpa os 49 produtos do import.sql antes do teste rodar
	}
	// ==========================================================================  
	
	@Test
	void deveSalvarProduto() {
		// 1. ARRANGE - Preparar os dados
		Produto produto = new Produto("Notebook", 2500.0);

		// 2. ACT - Executar a ação
		Produto salvo = repository.save(produto);

		// 3. ASSERT - Verificar o resultado
		assertThat(salvo.getId()).isNotNull();
		assertThat(salvo.getNome()).isEqualTo("Notebook");
		assertThat(salvo.getPreco()).isEqualTo(2500.0);
	}

	@Test
	void deveBuscarProdutoPorId() {
		// 1. ARRANGE - Salva um produto primeiro
		Produto produto = new Produto("Celular", 1500.0);
		Produto salvo = repository.save(produto);

		// 2. ACT - Buscar o produto
		Produto encontrado = repository.findById(salvo.getId()).orElse(null);

		// 3. ASSERT - Verificar o resultado
		assertThat(encontrado).isNotNull();
		assertThat(encontrado.getNome()).isEqualTo("Celular");
	}


	@Test 
	void deveListarTodosOsProdutos() {
		// ARRANGE
		repository.save(new Produto("Produto A", 10.0));
		repository.save(new Produto("Produto B", 20.0));
		repository.save(new Produto("Produto C", 30.0));

		// ACT
		List<Produto> resultado = repository.findAll();

		// ASSERT
		assertThat(resultado).hasSize(3); // Passar, pois o tamanho será exatamente 3!
	  //assertThat(resultado).hasSize(52); // Se fosse usar o banco que inicia com 49 era só você inserir mais 3:
	}

	@Test
	void deveDeletarProduto() {
		// 1. ARRANGE - Salva um produto
		Produto produto = new Produto("Produto para deletar", 100.0);
		Produto salvo = repository.save(produto);

		// 2. ACT - Deleta o produto
		repository.deleteById(salvo.getId());

		// 3. ASSERT - Verificar se foi deletado
		boolean existe = repository.existsById(salvo.getId());
		assertThat(existe).isFalse();
	}
}