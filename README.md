# Ecomwise Feedback

Ecomwise Feedback é um serviço inteligente projetado para coletar, analisar e gerar insights a partir dos feedbacks dos clientes, focado em e-commerce. Utilizando Inteligência Artificial (IA), o sistema oferece análise de sentimentos, classificação de feedbacks, sugestões automatizadas e detecção de tendências, permitindo que os e-commerces melhorem a experiência do cliente e tomem decisões mais informadas.

## Funcionalidades

### 1. **Análise de Sentimentos**
   - Determina se o feedback é **positivo**, **negativo** ou **neutro**. Isso ajuda os comerciantes a entender rapidamente como os clientes estão se sentindo em relação aos produtos ou serviços.

### 2. **Classificação de Feedbacks**
   - Classifica os feedbacks em **categorias específicas**, como qualidade do produto, tempo de entrega, atendimento, etc. Essa categorização permite que os e-commerces se concentrem em áreas específicas para melhorar a experiência do cliente.

### 3. **Sugestões Automatizadas**
   - Gera **recomendações automáticas** baseadas nos feedbacks. A IA sugere possíveis ações a serem tomadas para resolver problemas recorrentes identificados nos feedbacks dos clientes.

### 4. **Detecção de Tendências**
   - Identifica padrões e **tendências de feedbacks** ao longo do tempo. Isso inclui identificar se há um aumento significativo de feedbacks negativos em relação a um produto específico ou a qualquer outra área de serviço.

## Tecnologias Utilizadas

- **Java 17**
- **Spring Boot** - Framework para criação da API REST.
- **Spring Data JPA** - Para persistência de dados.
- **PostgreSQL** - Banco de dados relacional.
- **Apache Kafka** (ou outra ferramenta de mensageria) - Para processamento assíncrono de feedbacks e análise em tempo real.
- **TensorFlow / PyTorch** - Para implementar e treinar modelos de IA para análise de sentimentos e classificação.
- **Docker** - Para criação de contêineres e deploy do serviço.
- **JUnit & Mockito** - Para testes automatizados de integração e unitários.

## Estrutura do Projeto

O projeto está dividido nas seguintes camadas:

1. **API Layer** - Contém os controladores REST responsáveis por receber as requisições HTTP dos clientes.
2. **Service Layer** - Contém a lógica de negócios, onde os feedbacks são processados e analisados.
3. **Repository Layer** - Interface de persistência, onde os feedbacks e análises são armazenados no banco de dados.
4. **AI Layer** - Camada responsável pela comunicação com modelos de IA (análise de sentimentos, classificação de feedbacks, etc).
5. **DTO Layer** - Objetos de transferência de dados que são utilizados entre as camadas para garantir a separação de responsabilidades.

## Como Rodar o Projeto

### Pré-requisitos

- JDK 17
- Docker (se quiser rodar o banco de dados localmente com containers)
- PostgreSQL (ou conexão com o banco já configurado)
- IDE de sua preferência (como IntelliJ IDEA ou VS Code)

### Passos

1. Clone o repositório:

    ```bash
    git clone https://github.com/EcomwiseX/ecomwise-feedback.git
    cd ecomwise-feedback
    ```

2. Crie um banco de dados PostgreSQL chamado `ecomwise_feedback`.

3. Configure o `application.properties` com suas credenciais do banco de dados:

    ```properties
    spring.datasource.url=jdbc:postgresql://localhost:5432/ecomwise_feedback
    spring.datasource.username=seu_usuario
    spring.datasource.password=sua_senha
    ```

4. Se você quiser rodar o banco de dados usando Docker, use o comando:

    ```bash
    docker run --name ecomwise-postgres -e POSTGRES_USER=seu_usuario -e POSTGRES_PASSWORD=sua_senha -e POSTGRES_DB=ecomwise_feedback -p 5432:5432 -d postgres
    ```

5. Compile e execute o projeto:

    - Se estiver usando o Maven:

      ```bash
      ./mvnw spring-boot:run
      ```

    - Ou se estiver usando Gradle:

      ```bash
      ./gradlew bootRun
      ```

6. A aplicação estará rodando em `http://localhost:8080`.
