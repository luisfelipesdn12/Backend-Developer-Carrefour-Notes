# Aprenda a aplicar testes com Java

> **H1** Curso | **H2** Módulo | **H3** Aulas | **H4** Tópicos | **H5** Sub-tópicos | **H6** Etc. |

## Testes Unitários com JUnit 4

### Conheça o framework para testar e organizar códigos:

- Framework para escrever e executar testes repetidamente.
- A cada novo build o projeto é retestado.

```java
package com.luisfelipesdn12.primes.service_tests;

import static org.junit.Assert.assertEquals;

import com.luisfelipesdn12.primes.service.PrimeService;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class PrimeServiceTests {
    @Test
    public void testIsPrime() {
        PrimeService service = new PrimeService();

        boolean twoIsPrime = service.isPrime(2);
        assertEquals(true, twoIsPrime);

        boolean tenIsPrime = service.isPrime(10);
        assertEquals(false, tenIsPrime);
    }
}
```

---

#### Asserts:

- Indica se um determinado resultado coincide com o esperado;
- Suporta todos os tipos primitivos
- Import static
- Valor esperado seguido pelo valor a ser testado;

---

#### Rules:

- Componente que intercepta chamada e permite que o manipulemos
- Interface TestRule

---

#### Testando Exceções:

- Exceções esperadas;
- Exceções esperadas Rule;
- Try/Catch idiom;

---

## Explorando Mocks e Asserts

### Trabalhando com Mocks:

#### Mais utilizados:

- Mockito
- Easy Mock
- Power Mockito

#### O que são Mocks:

- Objetos mock, objeto simulados ou simplesmente Mock
- Objetos que simulam comportamentos de objetos reais que forma controlada
- Tem limitações e passa no teste erroneamente caso não tratado corretamente.

---

### O que é e como usar Asserts:

- Serve para comparar se um valor coincide com um valor esperado

---

### Hamcrest e Matcher:

#### Hamcrest:

- Serve para fazer testes unitários
- Em vez de usar vários Assertions, usamos apenas o AssertThat com os parâmetros adequados

#### AssertJ:

- Permite escrever asserts de mais fácil compreensão

```java
assertThat(x).isEqualTo(y);
```

---

## TDD e Testes Unitários com JUnit 5

### O que é TDD e seus benefícios:

#### O que é TDD:

- **TDD** - Test Drive Development (Desenvolvimento Guiado por Testes)

1. Escreva um teste
2. O veja falhar
3. Implemente o código
4. Rode o teste
5. Refatore

#### Benefícios:

- Escrever os testes antes de codificar permite um código muito mais limpo e sem bugs.

---

### Novidades do JUnit 5:

- Suporte a novos recursos do Java 8

---

# Certificado \P/

###### https://certificates.digitalinnovation.one/7F3DC58C



