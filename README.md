# API de Descontos - Exemplo Spring Boot com Testes

## Sobre o Projeto

Este projeto tem por finalidade demonstrar testes automatizados com Spring Boot. A aplicação é uma API REST simples que calcula descontos de produtos e gerencia um catálogo.

### Regra de Negócio

- Produtos com preço **até R$ 50,00**: 0% de desconto
- Produtos com preço **de R$ 50,01 a R$ 100,00**: 10% de desconto
- Produtos com preço **acima de R$ 100,00**: 20% de desconto

### Funcionalidades

- Criar produto
- Listar produtos
- Buscar produto por ID
- Atualizar produto
- Deletar produto
- Calcular desconto e preço final

## Tecnologias

- Java 17
- Spring Boot 3.2.0
- Spring Data JPA
- Banco H2 (em memória)
- JUnit 5
- MockMvc
- Mockito

## Estrutura do Projeto

src/

├── main/

│ └── java/com/alexsander/descontos/

│ ├── controller/ ← API REST

│ ├── service/ ← Regras de negócio

│ ├── repository/ ← Banco de dados

│ └── model/ ← Entidades

└── test/

└── java/com/alexsander/descontos/

├── controller/ ← Testes da API

├── service/ ← Testes das regras

└── repository/ ← Testes do banco

## Como Rodar

### Pré-requisitos

- JDK 17 ou superior
- Maven 3.8 ou superior
- IntelliJ IDEA / Eclipse / VS Code

### Passos

1. Clone o repositório:

git clone https://github.com/alexsander/spring-boot-test-exemplo.git
cd spring-boot-test-exemplo
Rode a aplicação:

bash
mvn spring-boot:run
Acesse no navegador:


http://localhost:8080/h2-console   ← Banco de dados
http://localhost:8080/produtos     ← API

