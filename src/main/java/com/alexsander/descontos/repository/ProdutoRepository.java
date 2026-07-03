package com.alexsander.descontos.repository;

import com.alexsander.descontos.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long> {
	// O JpaRepository já fornece:
	// - save(produto)
	// - findAll()
	// - findById(id)
	// - deleteById(id)
	// - existsById(id)
}