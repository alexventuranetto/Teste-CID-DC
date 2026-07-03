package com.alexsander.descontos.controller;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import com.alexsander.descontos.exception.GlobalExceptionHandler;
import com.alexsander.descontos.model.Produto;
import com.alexsander.descontos.service.ProdutoService;

import tools.jackson.databind.ObjectMapper;

@WebMvcTest({ ProdutoController.class, GlobalExceptionHandler.class })
class ProdutoControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockitoBean
	private ProdutoService service;

	@Autowired
	private ObjectMapper objectMapper;

	// ==================== TESTES DO GET /produtos ====================

	@Test
	void deveListarTodosOsProdutos() throws Exception {
		// ARRANGE
		Produto p1 = new Produto(1L, "Produto A", 10.0);
		Produto p2 = new Produto(2L, "Produto B", 20.0);
		when(service.listarTodos()).thenReturn(Arrays.asList(p1, p2));

		// ACT & ASSERT
		mockMvc.perform(get("/produtos")).andExpect(status().isOk()).andExpect(jsonPath("$[0].id").value(1))
				.andExpect(jsonPath("$[0].nome").value("Produto A")).andExpect(jsonPath("$[1].id").value(2))
				.andExpect(jsonPath("$[1].nome").value("Produto B"));
	}

	// ==================== TESTES DO GET /produtos/{id} ====================

	@Test
	void deveBuscarProdutoPorIdExistente() throws Exception {
		// ARRANGE
		Produto produto = new Produto(1L, "Mouse", 80.0);
		when(service.buscarPorId(1L)).thenReturn(produto);

		// ACT & ASSERT
		mockMvc.perform(get("/produtos/1")).andExpect(status().isOk()).andExpect(jsonPath("$.id").value(1))
				.andExpect(jsonPath("$.nome").value("Mouse")).andExpect(jsonPath("$.preco").value(80.0));
	}

//	@Test
//	void deveRetornarErroAoBuscarProdutoInexistente() throws Exception {
//		// ARRANGE
//		when(service.buscarPorId(999L)).thenThrow(new RuntimeException("Produto não encontrado!"));
//
//		// ACT & ASSERT
//		Exception exception = assertThrows(Exception.class, () -> {
//			mockMvc.perform(get("/produtos/999")).andExpect(status().isInternalServerError());
//		});
//
//		// Opcional: Verificar se a mensagem dentro da exceção está correta
//		assertTrue(exception.getCause().getMessage().contains("Produto não encontrado!"));
//	}

	@Test // Comente este teste abaixo e descomente o teste acima para ver erro no teste
	void deveRetornarErroAoBuscarProdutoInexistente() throws Exception {
	    // ARRANGE
	    when(service.buscarPorId(999L))
	            .thenThrow(new RuntimeException("Produto não encontrado!"));

	    // ACT & ASSERT
	    mockMvc.perform(get("/produtos/999"))
	            .andExpect(status().isInternalServerError()); // Valida o HTTP 500 gerado pelo Handler
	}
	
	
	// ==================== TESTES DO POST /produtos ====================

	@Test
	void deveCriarProdutoComSucesso() throws Exception {
		// ARRANGE
		Produto produto = new Produto("Teclado", 150.0);
		Produto salvo = new Produto(1L, "Teclado", 150.0);

		when(service.salvar(any(Produto.class))).thenReturn(salvo);

		// ACT & ASSERT
		mockMvc.perform(post("/produtos").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(produto))).andExpect(status().isCreated())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.nome").value("Teclado"))
				.andExpect(jsonPath("$.preco").value(150.0));
	}

	// ==================== TESTES DO PUT /produtos/{id} ====================

	@Test
	void deveAtualizarProdutoComSucesso() throws Exception {
		// ARRANGE
		Produto produto = new Produto("Monitor", 800.0);
		Produto atualizado = new Produto(1L, "Monitor", 700.0);

		when(service.salvar(any(Produto.class))).thenReturn(atualizado);

		// ACT & ASSERT
		mockMvc.perform(put("/produtos/1").contentType(MediaType.APPLICATION_JSON)
				.content(objectMapper.writeValueAsString(produto))).andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(1)).andExpect(jsonPath("$.preco").value(700.0));
	}

	// ==================== TESTES DO DELETE /produtos/{id} ====================

	@Test
	void deveDeletarProdutoComSucesso() throws Exception {
		// ARRANGE
		doNothing().when(service).deletar(1L);

		// ACT & ASSERT
		mockMvc.perform(delete("/produtos/1")).andExpect(status().isNoContent());
	}

//    @Test
//    void deveRetornarErroAoDeletarProdutoInexistente() throws Exception {
//        // ARRANGE
//        doThrow(new RuntimeException("Produto não encontrado!"))
//                .when(service).deletar(999L);
//
//        // ACT & ASSERT
//        Exception exception = assertThrows(Exception.class, () -> {
//            mockMvc.perform(delete("/produtos/999"))
//            .andExpect(status().isInternalServerError());
//        });
//
//        assertTrue(exception.getCause().getMessage().contains("Produto não encontrado!"));
//    }

	@Test  // Comente este teste abaixo e descomente o teste acima para ver erro no teste
	void deveRetornarErroAoDeletarProdutoInexistente() throws Exception {
	    // ARRANGE
	    doThrow(new RuntimeException("Produto não encontrado!"))
	            .when(service).deletar(999L);

	    // ACT & ASSERT
	    mockMvc.perform(delete("/produtos/999"))
	            .andExpect(status().isInternalServerError()); // Valida o HTTP 500 gerado pelo Handler
	}
	// ==================== TESTES DO GET /produtos/calcular ====================

	@Test
	void deveCalcularPrecoFinalParaPreco80() throws Exception {
		// ARRANGE
		when(service.calcularDesconto(80.0)).thenReturn(8.0);
		when(service.calcularPrecoFinal(80.0)).thenReturn(72.0);

		// ACT & ASSERT
		mockMvc.perform(get("/produtos/calcular").param("preco", "80")).andExpect(status().isOk())
				.andExpect(jsonPath("$.precoOriginal").value(80.0)).andExpect(jsonPath("$.desconto").value(8.0))
				.andExpect(jsonPath("$.precoFinal").value(72.0));
	}

	@Test
	void deveCalcularPrecoFinalParaPreco150() throws Exception {
		// ARRANGE
		when(service.calcularDesconto(150.0)).thenReturn(30.0);
		when(service.calcularPrecoFinal(150.0)).thenReturn(120.0);

		// ACT & ASSERT
		mockMvc.perform(get("/produtos/calcular").param("preco", "150")).andExpect(status().isOk())
				.andExpect(jsonPath("$.precoOriginal").value(150.0)).andExpect(jsonPath("$.desconto").value(30.0))
				.andExpect(jsonPath("$.precoFinal").value(120.0));
	}

	@Test
	void deveCalcularPrecoFinalParaPreco50() throws Exception {
		// ARRANGE
		when(service.calcularDesconto(50.0)).thenReturn(0.0);
		when(service.calcularPrecoFinal(50.0)).thenReturn(50.0);

		// ACT & ASSERT
		mockMvc.perform(get("/produtos/calcular").param("preco", "50")).andExpect(status().isOk())
				.andExpect(jsonPath("$.precoOriginal").value(50.0)).andExpect(jsonPath("$.desconto").value(0.0))
				.andExpect(jsonPath("$.precoFinal").value(50.0));
	}
}