# TG-ECOIL

## Descrição
TG-ECOIL é uma aplicação Spring Boot para gerenciar dados de uma base de dados PostgreSQL. O projeto utiliza diversas tecnologias como Java, Spring Boot, Maven, MapStruct, Lombok, e Flyway.

## Tecnologias Utilizadas
- Java 21
- Spring Boot 3.3.5
- Maven
- PostgreSQL
- Flyway
- MapStruct
- Lombok

## Configuração do Projeto

### Pré-requisitos
- JDK 21
- Maven
- PostgreSQL

### Como Executar
1. Clone o repositório
2. Execute docker-compose up -d para subir o banco de dados
3. Execute o comando `mvn clean install` para instalar as dependências
4. Execute o comando `mvn spring-boot:run` para iniciar a aplicação
5. Acesse `http://localhost:8080` para acessar a aplicação

`mvn clean install`

`mvn spring-boot:run`

`mvn test`
