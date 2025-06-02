
# Cadastro de Usuários - Spring Boot API

Este projeto é uma API REST desenvolvida com Java e Spring Boot para gerenciamento de usuários. Inclui funcionalidades de registro, autenticação com JWT, listagem, atualização e exclusão de usuários, além de documentação Swagger e testes automatizados com TDD, BDD e ATDD.


## Tecnologias utilizadas

- Java 17
- Spring Boot
- Spring Web
- Spring Security
- Spring Data JPA
- PostegreSQL
- JWT (JSON Web Token)
- Cucumber (BDD)
- JUnit 5 (TDD & ATDD)
- Swagger / OpenAPI 3


## Estrutura de pastas

```
src/
├── main/
│   ├── java/com/cadastro/usuario/
│   │   ├── controller/
│   │   ├── service/
│   │   ├── model/
│   │   ├── repository/
│   │   ├── config/
│   │   └── dtos/
│   └── resources/
│       └── application.yml
├── test/
│   └── java/com/cadastro/usuario/
│       ├── cucumber/
│       └── service/
```

## Como executar o projeto

### Pré-requisitos

- Java 17
- Maven

### Comandos

# Compilar o projeto
./mvnw clean install

# Rodar o projeto
./mvnw spring-boot:run
```


## Endpoints principais

| Método | Endpoint             | Descrição                        |
|--------|----------------------|----------------------------------|
| POST   | `/api/usuarios/registro` | Registro de novo usuário        |
| POST   | `/api/usuarios/login`    | Autenticação (retorna JWT)      |
| GET    | `/api/usuarios`          | Lista todos os usuários         |
| GET    | `/api/usuarios/{id}`     | Busca usuário por ID            |
| PUT    | `/api/usuarios/{id}`     | Atualiza usuário por ID         |
| DELETE | `/api/usuarios/{id}`     | Deleta usuário por ID           |


## Testes automatizados

- TDD com JUnit 5  
- BDD com Cucumber  

Execute os testes com:

bash
./mvnw test



## Documentação Swagger

Acesse a documentação interativa da API em:

```
http://localhost:8099/swagger-ui/index.html
```

Ou a especificação OpenAPI:

```
http://localhost:8099/v3/api-docs
```


## Segurança

- Autenticação com JWT (Bearer Token)  
- Validação de email, senha e confirmações via Bean Validation (`@Valid`)  
- Retorno de erros padronizados (`403`, `404`, `400`, etc.)  


## Autor(a)

**Izabella Barros Lopes**  



