# 🚀 Cadastro e Gerenciamento de Funcionários (REST API)

Uma API RESTful robusta desenvolvida em **Java 21** e **Spring Boot 3**, projetada para o gerenciamento eficiente de funcionários. O projeto aplica boas práticas de arquitetura em camadas, tratamento global de exceções, validação de dados, migrations automatizadas para banco de dados relacional e conteinerização completa com Docker.

---

## 🛠️ Tecnologias Utilizadas

* **Java 21** & **Spring Boot 3**
* **Spring Data JPA** & **Hibernate**
* **PostgreSQL** (Banco principal) & **H2 Database** (Perfil de testes)
* **Flyway** (Gerenciamento de migrations de banco)
* **Bean Validation** (Validação de DTOs)
* **OpenAPI 3 / Swagger** (Documentação interativa)
* **Docker & Docker Compose** (Ambiente de infraestrutura isolado)
* **Maven** (Gerenciamento de dependências)

---

## 📐 Arquitetura e Boas Práticas Implementadas

* **Camadas bem definidas:** Separação clara entre Controllers, DTOs (Records), Services, Repositories e Models.
* **OpenAPI Separation:** Interface dedicada (`FuncionarioControllerAPI`) para isolar as anotações da documentação Swagger da lógica do Controller.
* **Tratamento Global de Erros (`@RestControllerAdvice`):** Padronização das respostas de erro no formato REST com suporte a capturas de DTOs inválidos, dados duplicados, payloads malformados e exceções genéricas.
* **Sanitização de Dados:** Tratamento de dados sensíveis e máscaras (ex: CPF) salvando apenas valores limpos no banco de dados.
* **Profiles de Ambiente:** Isolação entre ambiente de produção/dev com PostgreSQL e ambiente de testes (`application-test.properties`) com H2 em memória.

---

## 📌 Endpoints da API

A documentação interativa completa (Swagger) pode ser acessada com a aplicação rodando em:  
👉 `http://localhost:8080/swagger-ui/index.html`

| Método | Endpoint | Descrição |
| :--- | :--- | :--- |
| `POST` | `/funcionarios` | Cadastra um novo funcionário |
| `GET` | `/funcionarios` | Lista todos os funcionários cadastrados |
| `GET` | `/funcionarios/{id}` | Busca um funcionário específico por ID |
| `PUT` | `/funcionarios/{id}` | Atualiza os dados completos de um funcionário |
| `PATCH` | `/funcionarios/{id}/corrigir-cpf` | Atualização parcial destinada à correção de CPF |
| `DELETE` | `/funcionarios/{id}` | Remove um funcionário pelo ID |

---

## 🐳 Como Executar o Projeto com Docker

### Pré-requisitos
* **Docker Desktop** e **Docker Compose** instalados e rodando.

### Passo a passo

1. **Clone o repositório:**
   ```bash
   git clone [https://github.com/seu-usuario/CadastroFuncionarios.git](https://github.com/seu-usuario/CadastroFuncionarios.git)
   cd CadastroFuncionarios
