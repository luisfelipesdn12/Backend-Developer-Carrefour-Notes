# Criando aplicações web com Spring Web MVC

> **H1** Curso | **H2** Módulo | **H3** Aula | **H4** Tópicos | **H5** Sub-tópicos | **H6** Etc.

## Entendendo o Spring Web MVC

### Entendendo o Spring Web MVC:

- Um framework que permite a utilização dum padrão de projeto chamado **Dependency Injection**
- Delega todo gerenciamento de dependências

#### Spring Bean:

- Objetos que estão sendo gerenciador pela **inversão de controle** do Spring.

#### Spring Boot:

- Inicializa e facilita a configuração de um projeto Spring.

#### MVC:

- Um padrão de arquitetura que é baseado em:
  - **Model**: back-end, tratamento de dados.
  - **View**: front-end, interfaces.
  - **Controller**: camada que estabelece comunicação entre a **View** e o **Model**, recebe requisições.

---

## Aplicações REST com Spring Web MVC - Básico

### Entendendo a internet e seus recursos:

- Requests e Responses por meio do protocolo HTTP

---

#### Protocolo HTTP:

- **Hypertext Transfer Protocol**

---

#### HTTP Requests:

##### Parâmetros de um request:

- As requisições têm **três parâmetros** principais:
  - O **método**: GET, POST, DELETE, PUT etc.
  - O **caminho**: /hello, /home, /, /contact etc.
  - A **versão**: HTTP -> 1.1, 2.0 etc.

---

#### HTTP Response:

##### Parâmetros de um response:

- De semelhante modo um response também tem **três parâmetros** principais:
  - A **versão**: a mesma usada no request.
  - O **status code**: representa como a requisição se comportou. 200 OK, 404 Not Found Error, etc.
  - O **response body**: de fato o conteúdo que foi requisitado.

----

#### REST:

- **Representational State Transfer**
- Estilo de arquitetura no protocolo HTTP
- Define um conjunto de restrições e propriedades
- Utiliza verbos, códigos, cabeçalhos e URIs para compor APIs web.

---

#### Métodos HTTP:

- Permite a padronização dos requests:

| **Método** | **Descrição**                                         |
| ---------- | ----------------------------------------------------- |
| **GET**    | Solicitar uma representação de um recurso específico. |
| **POST**   | Submeter um novo recurso.                             |
| **PUT**    | Substitui/Altera algum recurso.                       |
| **DELETE** | Remove um recurso específico.                         |
| **PATCH**  | Aplica modificações parciais em um recurso.           |
| **OUTROS** | TRACE, HEAD, OPTIONS, CONNECT                         |

---

#### Status code:

| **Status Code** | **Descrição**         |
| --------------- | --------------------- |
| **200**         | OK                    |
| **201**         | Created               |
| **204**         | No Content            |
| **304**         | Not Modified          |
| **400**         | Bad Request           |
| **401**         | Unauthorized          |
| **403**         | Forbidden             |
| **404**         | Not Found             |
| **500**         | Internal Server Error |
| **502**         | Service Unvailable    |

---

#### Recurso:

- Coleção de modelos que serão expostos aos clientes.

##### HTTP Request:

```http
GET /cities
```
##### Response Body:

```json
[
    {"id":1,"name":"São Paulo","state":"SP"},
    {"id":2,"name":"São Carlos","state":"SC"},
    {"id":3,"name":"São João","state":"Fogueira do meu coração"},
]
```

---

#### Payload:

- Conteúdos das requisições e respostas:
- Inclui o body(UserResponse), UserRequest, UserEntity

---

#### JSON e XML:

- Formas de armazenar dados

##### Exemplo JSON:

```json
{
    "id" : 123,
    "name" : "Luis"
}
```

##### Exemplo XML:

```xml
<character>
    <id>123</id>
    <name>Luis</name>
</character>
```

---

#### Cabeçalhos:

- Inclui informações úteis para identificação e restrições de acesso, preferências no formato do response, cache etc.

---

#### Praticas:

- Os **caminhos** **não devem** ser **verbos**: /getAllCars, /createAllCars etc.

- E sim **substantivos**: /cars, carsCreation etc.

- Uso do plural:  /cars, /carsCreation; em vez de: /car, carCreation.

- Não alterar um recurso usando um GET.

- Usar sub-recursos para relacionamento entre Recursos:

    ```http
    GET /books/33/pages
    ```

- Todo sub-recurso é um recurso em potencial:

    ```http
    GET /states/10/cities
    ```

    ```http
    GET cities?region=north
    ```
  
- Ordem nos parâmetros querty: paginação, filtros e ordenação
  
  ```http
  GET /regions?page=0&size=1&sort=name
  ```
  
- Não quebrar o contrato da API. Usar versionamento:

    - Via header: `GET /cities -H "accept: app/api-v1.0+json"`
    - Via caminho: `GET /v1/cities`
    - Via parâmetro: `GET /cities?api_version=1.0.0`

- Usar status code corretamente.
- Entender o domínio, como representar e documentar.

---

### Trabalhando com o Status Code da aplicação:

- Usamos a funcionalidade de @ResponseStatus(HttpStatus), ResponseEntity<> etc para criar métodos na API que editam, deletam e criam novos Jedi.

#### Jedi Resouces:

```java
package com.luisfelipe.springwebdio.rest;

import java.util.List;

import javax.validation.Valid;

import com.luisfelipe.springwebdio.model.Jedi;
import com.luisfelipe.springwebdio.repositories.JediRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class JediResource {

    @Autowired
    private JediRepository repository;
    
    @GetMapping("/api/jedi")
    public List<Jedi> getAllJedi() {
        return repository.getAllJedi();
    }

    @GetMapping("/api/jedi/{id}")
    public ResponseEntity<List<Jedi>> getJedi(@PathVariable("id") long id) {
        List<Jedi> JediFromRepository =  repository
                                        .getJediById(id);
        if (! JediFromRepository.isEmpty()) {
            return ResponseEntity.ok(JediFromRepository);
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @PostMapping("/api/jedi")
    @ResponseStatus(HttpStatus.CREATED)
    public Jedi createJedi(@Valid @RequestBody final Jedi jedi) {
        return repository.addAndReturn(jedi);
    }

    @PutMapping("/api/jedi/{id}")
    public ResponseEntity<List<Jedi>> editJedi(@PathVariable long id, @Valid @RequestBody final Jedi editedJedi) {
        List<Jedi> jediToEdit = repository.getJediById(id);
        if (! jediToEdit.isEmpty()) {
            jediToEdit.stream()
                    .forEach(j -> {
                        j.setName(editedJedi.getName());
                        j.setLastName(editedJedi.getLastName());
                    });
            return ResponseEntity.ok(jediToEdit);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/api/jedi/{id}")
    public ResponseEntity deleteJedi(@PathVariable long id) {
        List<Jedi> jediToBeDeleted = repository
                                .getJediById(id);

        if (! jediToBeDeleted.isEmpty()) {
            jediToBeDeleted.stream()
                        .forEach(j -> repository
                                    .deleteJedi(j));
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}
```

---

#### Jedi Repository:

```java
package com.luisfelipe.springwebdio.repositories;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.luisfelipe.springwebdio.model.Jedi;

import org.springframework.stereotype.Repository;

@Repository
public class JediRepository {
    private final List<Jedi> jedi;

    private List<Long> idsList = new ArrayList<>();

    public JediRepository() {
		jedi = new ArrayList<>();
        jedi.add(new Jedi("Anaquin", "Skywalker"));
	}

    public List<Jedi> getAllJedi() {
        return this.jedi;
    }
    public void add(final Jedi jedi_) {
        this.jedi.add(jedi_);
    }

    public Jedi addAndReturn(final Jedi jedi_) {
        this.jedi.add(jedi_);
        return(jedi_);
    }

    public List<Jedi> getJediById(long id) {
        return jedi.stream()
                .filter(item -> item.getId() == id)
                .collect(Collectors.toList());
    }

    public void deleteJedi(Jedi jediToBeDeleted) {
        jedi.remove(jediToBeDeleted);
    }

    /** Generic Getters and Setters */

    public List<Long> getIdsList() {
        return idsList;
    }

    public void setIdsList(List<Long> idsList) {
        this.idsList = idsList;
    }
}
```

---

## Aplicações REST com Spring Web MVC - Intermediário

### Arquitetura REST com JAX-RS:

- A necessidade de trocar informações entre aplicações motivou diferentes abordagens para "integração de dados".
- JAX-RS é uma especificação que permite criar RESTful webservices

---

#### Extraindo valore:

- **@PathVariable** - especifica que o valor do parâmetro será especificado na URI. (site/user/{id})
- **@RequestParam** - extrai o valor do parâmetro da URI. (site/?idade=10&ud=RS)
- **@RequestBody** - recebe os valores no corpo da requisição.

#### Response:

- **ResponseEntity** - permite gerenciar informações do Response, como o status code.

---

## Aprenda sobre hateoas e filters em recursos com REST

### Veja como hateoas é aplicado em projetos:

- Uma API HATEOAS provêm informações que permitem navergar entre seus endpoints de forma dinâmica incluindo links junto às respostas.
- Com isso deixamos a responsabilidade de acessos e de ações para o backend da aplicação.

### Conheça os métodos de filters:

#### Filter:

##### Init:

- init(FilterConfig filterConfig);
- Chamado pelo contêiner da web para indicar para um filtro que ele está sendo colocado em serviço.

##### Do Filter:

- doFilter() -  filtra todas as chamadas e retornos da aplicação.

##### Destroy:

- destroy() - serve pra limpar memória e cache dos filters.

---

## Como Jackson e Binder são utilizados

### Aplicando Jackson e Binder em um projetos:

#### Jackson:

- Jackson é responsável por efetuar a Serialização e Desserialização de nossos objetos.
- ObjectMapper.
- @JsonProperty("id")
- @JsonFormat(pattern="yyyy-MM-dd'T'HH:mm:ss")
- @JsonIgnore
- @JsonGetter
- @JsonSetter

#### Binder:

- Custom Data Binder é fazer um mapper de uma propriedade que o mapper não reconhece por padrão ex um enum.

## Integrações REST entre Aplicações usando Spring Web

### Conheça a biblioteca cliente nativo:

```java
import java.net.http.*;
```

---

### RestTemplate: uma biblioteca dentro do pacote Spring Web:

- GetForObject(URL, ClassRetorno, ?uriVariable)
- GetForEntity(URL, ClassRetorno, ?uriVariable)
- Exchange(URL, MetofoHttp, CLassRetorno, ClassRetorno, ? uriVariable)
- PostForLocation(URL, DATA, ? uriVariable)
- PostForObject(URL, DATA, ClassRetorno, ? uriVariable)
- PostForEntity(URL, DATA, ClassRetorno, ? uriVariable)
- Exchange(URL, MetodoHttp, DATA, ClassRetorno, ? uriVariable)
- put(URI,DATA)
- put(URI,DATA, ? uriVariable)
- delete(URI)
- delete(URI, MetodoHttp, ? uriVariable)

---

### Descubra por que a FeingClient é uma das bibliotecas mais usadas:

```java
@SpringBootApplication
@EnableFeingClients
public class RestfullApplication {
    [...]
}
```

```java
@FeingClient(url = "${telegram.api}${telegram.token", name = "telegram")
public inteface TelegramFeing() {
    [...]
}
```

- @GetMapping("/getUpdates")
- @PostMapping("/sendMessage")
- @PutMapping
- @DeleteMapping
- @RequestMappig

#### Anotações:

- @RequestBody
- @PathVariable
- @RequestParam
- @RequestHeader

----

## Teste de aplicações Spring Web

### Aprenda a criar um profile de teste e a trabalhar com MockMVC:

```java
@Component
@Profile("Test")
public class MinhaCOnfigConfig
    
@ActiveProfiles("dev")
```

Outra opção:
Application.properties

---

# Certificado \P/

###### https://certificates.digitalinnovation.one/8AF29A80