# ListifyShop API

O **ListifyShop** é uma aplicação backend para gerenciar listas de compras e compartilhamento de listas, integrando funcionalidades como autenticação, gerenciamento de usuários e produtos. O projeto foi desenvolvido com **Spring Boot**, utilizando **MapStruct** para mapeamento de objetos e **JWT** para autenticação segura.

## Funcionalidades

* **Autenticação de Usuário**: Implementação de login, registro e autenticação via JWT.
* **Gestão de Usuários**: Permite criação, atualização, consulta e deleção de usuários.
* **Gerenciamento de Listas de Compras**: Criação e manipulação de listas de compras, incluindo a possibilidade de adicionar e remover produtos.
* **Compartilhamento de Listas**: Funcionalidade para compartilhar listas de compras com outros usuários.
* **Controle de Acesso**: Restrição de acesso com base em papéis (Admin, User), utilizando **Spring Security**.

## Tecnologias Utilizadas

* **Spring Boot**: Framework principal para a construção do backend.
* **MapStruct**: Biblioteca para mapeamento eficiente de objetos.
* **JWT (JSON Web Tokens)**: Utilizado para autenticação e autorização de usuários.
* **Spring Data JPA**: Para interação com o banco de dados usando o modelo de entidades JPA.
* **Spring Security**: Implementação de segurança e controle de acesso com base em papéis.
* **H2 Database**: Banco de dados em memória para facilitar o desenvolvimento e testes (pode ser substituído por outro banco como PostgreSQL ou MySQL).

## Como Rodar o Projeto

### Pré-requisitos

* Java 21 ou superior
* Maven ou Gradle

### Passos para Execução

1. Clone o repositório:

   ```bash
   git clone https://github.com/Willians-S-S/ListifyShop.git
   ```

2. Navegue até o diretório do projeto:

   ```bash
   cd ListifyShop
   ```

3. Compile e inicie o projeto:

   ```bash
   mvn spring-boot:run
   ```

4. A API estará disponível em `http://localhost:8080`.

## Endpoints

### 1. **Usuários**

* **POST /auth/sign**: Criação de um novo usuário.
* **GET /user/{id}**: Obter detalhes do usuário.
* **PUT /user/{id}**: Atualizar informações do usuário.
* **DELETE /user/{id}**: Deletar um usuário.

### 2. **Listas de Compras**

* **GET /shopping-lists/all**: Listar todas as listas de compras (requer permissão de administrador).
* **POST /shopping-lists/{userId}**: Criar uma nova lista de compras.
* **PUT /shopping-lists**: Atualizar uma lista existente.
* **DELETE /shopping-lists**: Deletar uma lista.

### 3. **Produtos em Listas**

* **POST /products-in-list**: Adicionar um produto à lista de compras.
* **GET /products-in-list/{productsInListId}**: Consultar um produto específico em uma lista.
* **PUT /products-in-list/{productsInListId}**: Atualizar um produto.
* **DELETE /products-in-list/{productsInListId}**: Remover um produto.

### 4. **Autenticação**

* **POST /auth/login**: Realizar o login e obter um token JWT.

### 5. **Imagens**

* **POST /image/{userId}**: Enviar imagem para o usuário.
* **GET /image/{userId}/{image}**: Obter a imagem do usuário.
* **DELETE /image/{userId}/{image}**: Deletar imagem do usuário.

## Estrutura de Diretórios

```
src/
├── main/
│   ├── java/
│   │   ├── com/
│   │   │   ├── willians/
│   │   │   │   ├── ListifyShop/
│   │   │   │   │   ├── controller/      # Controladores REST
│   │   │   │   │   ├── dto/             # Objetos de Transferência de Dados
│   │   │   │   │   ├── entety/          # Entidades JPA
│   │   │   │   │   ├── exception/       # Classes de exceção personalizadas
│   │   │   │   │   ├── repository/      # Repositórios JPA
│   │   │   │   │   ├── security/        # Configurações de segurança (JWT, roles)
│   │   │   │   │   ├── service/         # Serviços de lógica de negócios
│   │   │   │   │   └── utils/            # Utilitários auxiliares
└── resources/
    ├── application.properties  # Configurações do Spring Boot
```
