# Supermercado Backend

## 🇧🇷 Descrição

Este projeto é um sistema de gerenciamento de supermercado desenvolvido em Java. Ele permite o cadastro de clientes, produtos e a simulação de um carrinho de compras com funcionalidades completas de adição, remoção e listagem de itens. Utiliza Maven para gerenciamento de dependências, JPA com Hibernate para persistência de dados e banco de dados em memória (HSQLDB).

## 🇺🇸 Description

This project is a supermarket management system developed in Java. It allows the registration of customers and products, and simulates a shopping cart with full functionalities for adding, removing, and listing items. Uses Maven for dependency management, JPA with Hibernate for data persistence, and an in-memory database (HSQLDB).

---

## 🛠 Tecnologias / Technologies

- Java
- Maven
- JPA (Jakarta Persistence API)
- Hibernate ORM
- HSQLDB (in-memory database)
- Lombok (para simplificação de código)
- IntelliJ IDEA

---

## 🚀 Funcionalidades / Features

- Cadastro de produtos
- Cadastro de clientes
- Adição de produtos ao carrinho
- Remoção da última adição do carrinho (histórico com Stack)
- Consulta de itens no carrinho com totalizadores de preços
- Persistência em banco de dados em memória (HSQLDB)
- Código modularizado com DAO, Service e Model

---

## 📂 Estrutura de Pacotes / Package Structure

```bash
src/main/java
 ├── dao
 │    ├── GenericDAO.java
 │    ├── ClienteDAO.java
 │    ├── ProdutoDAO.java
 │    └── ItemCarrinhoDAO.java
 ├── model
 │    ├── Cliente.java
 │    ├── Produto.java
 │    └── ItemCarrinho.java
 ├── service
 │    ├── CarrinhoService.java
 │    └── SupermercadoService.java
 └── Main.java
resources
 └── META-INF/persistence.xml
```
## ⚙️ Como executar / How to run

1. Clone o repositório:
 ```bash
 git clone https://github.com/seu-usuario/seu-repositorio.git
```
2. Abra o projeto no IntelliJ IDEA.
3. Execute a classe Main.java para iniciar o sistema.

## 📌 Observação adicional
Este projeto não depende de conexão com bancos externos. Toda a persistência é feita durante a execução na memória.

## 🧑‍💻 Autor / Author
Ithalo Teodósio

## Projeto acadêmico e profissional de backend Java.
