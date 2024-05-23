# Exemplo de @Scheduled

Este é um projeto simples de uma API desenvolvida com Spring Boot, criada para testar a funcionalidade da anotação `@Scheduled` do Spring Boot. O projeto incrementa um valor em uma coluna do banco de dados H2 a cada meia-noite.

## Funcionalidades

- **Incremento Diário:** Um método agendado que executa a cada meia-noite, incrementando em +1 a coluna `number_guia` do banco de dados.
- **Banco de Dados em Memória:** Utilização do H2 Database para facilitar o desenvolvimento e testes.
- **Dependências Essenciais:** Inclui dependências como Spring Web, Spring Dev Tools, e H2 Database para fornecer a funcionalidade completa do projeto.

## Tecnologias Utilizadas

- **Spring Boot**
  - Spring Web
  - Spring Dev Tools
  - Spring Data JPA
- **H2 Database**

## Pré-requisitos

- Java 11 ou superior
- Maven

## Como Executar o Projeto

1. **Clone o repositório:**

    ```bash
   git clone https://github.com/seu-usuario/nome-do-repositorio.git
   cd nome-do-repositorio

2. Compile e execute o projeto:
    ```bash
   mvn spring-boot:run

3. Acesse no browser o banco de dados H2 Database copiando a URL abaixo:

     ```bash
    http://localhost:8080/h2-console


## Configuração do Banco de Dados
O banco de dados H2 está configurado em src/main/resources/application.properties:

  ```bash
      spring.datasource.url=jdbc:h2:mem:testdb
      spring.datasource.driverClassName=org.h2.Driver
      spring.datasource.username=sa
      spring.datasource.password=password
      spring.h2.console.enabled=true
      spring.jpa.database-platform=org.hibernate.dialect.H2Dialect      

````
## Exemplo de Funcionamento
A cada meia-noite, a aplicação executa um método agendado que incrementa a coluna number_guia da tabela guia no banco de dados H2.

  ```java
    @Service
    @AllArgsConstructor
    public class GuiaService {
    
        @Autowired
        private GuiaRepository guiaRepository;
    
        @Scheduled(cron = "0 0 0 * * *")
        public GuiaModel incrementGuia(){
            String maxNumberStr = guiaRepository.findMaxNumber();
    
            int maxNumber = (maxNumberStr != null) ? Integer.parseInt(maxNumberStr) : 0;
            int newNumber = maxNumber + 1;
            int numDigits = Math.max(4, String.valueOf(newNumber).length());
    
            String formattedNumber = String.format("%0" + numDigits + "d", newNumber);
    
            GuiaModel newGuia = new GuiaModel();
            newGuia.setNumber(formattedNumber);
            newGuia.setDate(LocalDateTime.now());
    
            return guiaRepository.save(newGuia);
        }
    }


