-- ============================================
-- POPULAÇÃO INICIAL DO BANCO DE DADOS H2
-- Sistema de Descontos - Exemplo Didático
-- ============================================

-- Limpa a tabela antes de inserir (evita duplicatas)
-- DELETE FROM produtos;

-- ============================================
-- PRODUTOS COM PREÇOS BAIXOS (SEM DESCONTO)
-- Regra: preço <= 50,00 → 0% de desconto
-- ============================================

INSERT INTO produtos (nome, preco) VALUES ('Caderno', 15.90);
INSERT INTO produtos (nome, preco) VALUES ('Caneta', 2.50);
INSERT INTO produtos (nome, preco) VALUES ('Lápis', 1.80);
INSERT INTO produtos (nome, preco) VALUES ('Borracha', 3.50);
INSERT INTO produtos (nome, preco) VALUES ('Régua', 5.00);
INSERT INTO produtos (nome, preco) VALUES ('Apontador', 4.20);
INSERT INTO produtos (nome, preco) VALUES ('Corretivo', 6.75);
INSERT INTO produtos (nome, preco) VALUES ('Cola', 8.30);
INSERT INTO produtos (nome, preco) VALUES ('Tesoura', 12.40);
INSERT INTO produtos (nome, preco) VALUES ('Grampeador', 25.00);
INSERT INTO produtos (nome, preco) VALUES ('Fita Adesiva', 7.80);
INSERT INTO produtos (nome, preco) VALUES ('Post-it', 9.90);
INSERT INTO produtos (nome, preco) VALUES ('Clips', 4.60);
INSERT INTO produtos (nome, preco) VALUES ('Grampos', 3.20);
INSERT INTO produtos (nome, preco) VALUES ('Elástico', 2.90);

-- ============================================
-- PRODUTOS COM PREÇO MÉDIO (10% DE DESCONTO)
-- Regra: 50,01 <= preço <= 100,00 → 10% de desconto
-- ============================================

INSERT INTO produtos (nome, preco) VALUES ('Mochila', 89.90);
INSERT INTO produtos (nome, preco) VALUES ('Estojo', 55.00);
INSERT INTO produtos (nome, preco) VALUES ('Calculadora', 75.50);
INSERT INTO produtos (nome, preco) VALUES ('Dicionário', 62.30);
INSERT INTO produtos (nome, preco) VALUES ('Livro Técnico', 95.00);
INSERT INTO produtos (nome, preco) VALUES ('Tablet', 99.99);
INSERT INTO produtos (nome, preco) VALUES ('Mouse', 65.00);
INSERT INTO produtos (nome, preco) VALUES ('Teclado', 85.00);
INSERT INTO produtos (nome, preco) VALUES ('Headset', 78.50);
INSERT INTO produtos (nome, preco) VALUES ('Carregador', 55.90);
INSERT INTO produtos (nome, preco) VALUES ('Cabo USB', 52.00);
INSERT INTO produtos (nome, preco) VALUES ('Adaptador', 58.70);
INSERT INTO produtos (nome, preco) VALUES ('Fone de Ouvido', 70.00);
INSERT INTO produtos (nome, preco) VALUES ('Webcam', 92.40);
INSERT INTO produtos (nome, preco) VALUES ('Microfone', 68.80);

-- ============================================
-- PRODUTOS COM PREÇO ALTO (20% DE DESCONTO)
-- Regra: preço > 100,00 → 20% de desconto
-- ============================================

INSERT INTO produtos (nome, preco) VALUES ('Notebook', 3500.00);
INSERT INTO produtos (nome, preco) VALUES ('Smartphone', 2500.00);
INSERT INTO produtos (nome, preco) VALUES ('Monitor', 1200.00);
INSERT INTO produtos (nome, preco) VALUES ('Impressora', 850.00);
INSERT INTO produtos (nome, preco) VALUES ('Cadeira Gamer', 1500.00);
INSERT INTO produtos (nome, preco) VALUES ('Mesa Digitalizadora', 650.00);
INSERT INTO produtos (nome, preco) VALUES ('Drone', 1800.00);
INSERT INTO produtos (nome, preco) VALUES ('Câmera', 2100.00);
INSERT INTO produtos (nome, preco) VALUES ('Projetor', 2800.00);
INSERT INTO produtos (nome, preco) VALUES ('Ar Condicionado', 2200.00);
INSERT INTO produtos (nome, preco) VALUES ('Geladeira', 3800.00);
INSERT INTO produtos (nome, preco) VALUES ('Fogão', 1900.00);
INSERT INTO produtos (nome, preco) VALUES ('Máquina de Lavar', 2600.00);
INSERT INTO produtos (nome, preco) VALUES ('TV 50', 3200.00);
INSERT INTO produtos (nome, preco) VALUES ('Sofá', 2400.00);

-- ============================================
-- PRODUTOS ESPECIAIS PARA TESTES
-- Valores exatos nas fronteiras das regras
-- ============================================

-- Fronteira exata: 50,00 (sem desconto - limite superior)
INSERT INTO produtos (nome, preco) VALUES ('Produto Fronteira 50', 50.00);

-- Fronteira exata: 50,01 (10% desconto - limite inferior)
INSERT INTO produtos (nome, preco) VALUES ('Produto Fronteira 50.01', 50.01);

-- Fronteira exata: 100,00 (10% desconto - limite superior)
INSERT INTO produtos (nome, preco) VALUES ('Produto Fronteira 100', 100.00);

-- Fronteira exata: 100,01 (20% desconto - limite inferior)
INSERT INTO produtos (nome, preco) VALUES ('Produto Fronteira 100.01', 100.01);

-- ============================================
-- CONSULTAS PARA VERIFICAR OS DADOS
-- (descomente para testar)
-- ============================================

-- SELECT * FROM produtos ORDER BY id;
-- SELECT COUNT(*) FROM produtos;

-- ============================================
-- RESULTADO ESPERADO:
-- Total de produtos: 49
-- 
-- 14 produtos com preço até 50,00
-- 15 produtos com preço entre 50,01 e 100,00
-- 15 produtos com preço acima de 100,00
-- 5 produtos para testes de fronteira
-- ============================================