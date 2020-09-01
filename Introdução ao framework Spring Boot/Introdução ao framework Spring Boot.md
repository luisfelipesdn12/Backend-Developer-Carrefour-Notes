# Introdução ao framework Spring Boot

> **H1** - Curso; **H2** - Módulo; **H3** - Aula;

---

## O que é o Spring Boot?

### O que é e quais problemas resolvem o Spring Boot:

---

#### Problemas do Spring:

- Configurações de beans em arquivos xml.
- Setup manual do Banco de Dados.
- Muito tempo gasto em configuração.
- Perda de foco em valor.

#### Surgimento do Spring Boot:

- Criado pela Spring Source em 2012.
- Facilitar o setup de projetos Spring.
- Sem necessidade de criar arquivos de configuração.
- Foco na produtividade.
- Maior tempo no desenvolvimento de valor.

#### Problemas que resolve:

- Produtividade.
- Starters.
- Execução simplificada.

- Configurações.
- Tempo maior em valor.

---

#### Passos realizados:

- No site **https://start.spring.io/**, foi feito o download de um projeto Maven.
- Depois, na pasta src/java/main, foi criada uma pasta chamada "controller".
- Nessa pasta foi criado um arquivo "ControllerHello.java" onde foi inserido o seguinte código:

```java
package com.dio.luisfelipesdn.mysbwebapp.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerHello {
    
    @GetMapping("/")
    public String helloMessage() {
        return "Hello, Dio e Luis kkkk";
    }
}
```

- Depois, com o terminal na pasta raiz do projeto, foi inserido o comando:

```cmd
mvn spring-boot:run
```

- O projeto foi inicializado na porta 8080 a mensagem apareceu.

---

### Auto Configuration:

#### Problemas da configuração manual:

- Múltiplos Arquivos XML;
- Trabalho que podia ser facilmente automatizado;

#### Auto configuration traz:

- **Starters:** dependências simplificadas.
- **Identificação** e **configuração** automática da **dependência**.
- **Projeto simplificado** para foco no valor.

---

### FatJar/UberJar:

#### Antes do SpringBoot:

- **Spring tradicional:** war precisa de servidor de aplicação.
- **Dependência** de um container web ou servidor de aplicação.
- **Complexidade** para configurações;
- **Atualizações frequentes** junto com versão do projeto;
- **Gerenciamento manual** de configurações.

#### FatJar/UberJar:

- Artefato pronto para execução.
- Container web embutido na geração e execução (Tomcat).
- Deploy embarcado com outros containers são opcionais.
- Dependências principais do projeto embarcadas.
- Execução em um único java -jar.
- Pode-se gerar um war tradicional também.

---

#### Passos realizados:

- Empacotar todo código com as dependências num único arquivo .jar

```cmd
mvn clean pakage
```

- Para fazer em .war, mudamos o pom.xml:

```xml
...
	<pakaging>war</pakaging>
...
```

---

## Trabalhando com Profiles e Configurações

### Importância dos Profiles:

- **Múltiplos ambientes** existem num mesmo projeto.
  - Desenvolvimento local
  - Testes do desenvolvimento
  - Staging
  - Produção de fato
- **Existem**:
  - Banco de dados para cada ambiente
  - Execução de testes unitários em ambiente local
  - Suites de testes completas em ambiente de teste.
  - Simulação do ambiente real
  - Deploy simplificado em produção
- Isso dificulta configurar todos os ambientes.

#### Spring Boot Profiles:

- Configurações próprias para cada ambiente.
- Ambientes com sua configuração: dev, local, production etc

---

### Configurações com Properties:

```java
@Configuration
@ConfigurationProperties("spring.datasource")
@SupressWarnings("unused")
public class DBConfiguration {
    private String driverClassName;
    private String url;
    private String username;
    private String password;


    @Profile("dev")
    @Bean
    public String devDataBaseConnection() {
        System.out.println("DB Connection for DEV - H2");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB connection for DEV - H2";
    }

    @Profile("prod")
    @Bean
    public String prodDataBaseConnection() {
        System.out.println("DB Connection to RDS_PROD - Hight Performance");
        System.out.println(driverClassName);
        System.out.println(url);
        return "DB Connection to RDS_PROD - Hight Performance";
    }
}
```

---

#### Passos feitos:

- Criamos mais dois arquivos .properties

  - application-dev.properties

    ```properties
    #DEV environment
    
    app.message=This is the propertity file for the ${spring.application.name} to DEV environment
    
    spring.datasource.driver-class-name=org.h3.Driver
    spring.datasource.url=jdbc:h2:men:db;BD_CLOSE_DELAY=-1
    spring.datasource.username=sa
    spring.datasource.password=sa
    ```

  - application-prod.properties

    ```properties
    #PROD environment
    
    app.message=This is the propertity file for the ${spring.application.name} to PROD environment
    
    spring.datasource.driver-class-name=com.mysql.cj.idbc.Driver
    spring.datasource.url=<MYSQL_URL>
    spring.datasource.username=<USERNAME>
    spring.datasource.password=<PASSWORD>
    ```

- Criamos também uma classe que basicamente mostra qual dos dois profiles a aplicação estava rodando, com base na propriedade app.message.

- Definimos na application.properties, qual perfil rodar:

  ```properties
  spring.profiles.active=pro
  
  spring.application.name=Spring Boot Config Project
  
  ```

- Rodamos o projeto.

---

### Configurações com YAML e command line:

#### Troca o formato de .properties --> .yml

```properties
basic.value: true
basic.message: Dynamic Message PROPERTIES
basic.number: 100
```

```yaml
basic:
	value: true
	message: Dynamic Message YAML
	number: 100
```

#### Troca o formato para command line

- Sobrescreve as propriedades padrão

```properties
server.port=8081
spring.application.name=SampleApp
```

```cmd
mvn spring-boot:run -Dserver.port=8085
```

- Nesse caso, como a linha de comando tem uma prioridade maior nas configurações, o programa é executado na porta 8085.

---

### Configurações com variáveis de ambiente:

- Pose ser injetada através da notação @Value no projeto.

- Ou definir uma variável de ambiente padrão.

  ```java
  @Value("${VARIÁVEL_DEFINIDA:VALOR_DEFAULT}")
  private String varDeAmbientePreDefinida;
  ```

---

# Certificado \P/

###### https://certificates.digitalinnovation.one/332D87AB