# FinCore 💰

API REST para controle financeiro pessoal, desenvolvida com Java e Spring Boot.

O projeto permite gerenciar receitas e despesas, organizar transações por categorias e consultar resumos financeiros.

## Tecnologias

- Java 25
- Spring Boot 4.1.2
- Maven
- PostgreSQL 17
- Docker
- Spring Data JPA

## Funcionalidades

- Cadastro, consulta, atualização e exclusão de transações.
- Validação de dados e regras de negócio.
- Organização de receitas e despesas por categorias.
- Cálculo de saldo financeiro.
- Resumo financeiro por período.

## Executando o projeto

1. Clone o repositório:
   ```bash
   git clone https://github.com/Rafaeelbe/FinCore.git
   ```

2. Configure a variável de ambiente `DB_PASSWORD`.

3. Inicie o banco de dados:
   ```bash
   docker compose up -d
   ```

4. Execute a aplicação pela classe `FincoreApplication`.

A API estará disponível em `http://localhost:8080`.

---

Desenvolvido por [Rafael](https://github.com/Rafaeelbe).
