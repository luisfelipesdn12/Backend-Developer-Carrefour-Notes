# Desenvolvimento back-end com Node.js

## Criando Serviços Escaláveis com Hapi:

### O que é o Hapi:
- Um framework para construir aplicações e serviços.
- Similar ao Exprexx.js
- Software de código aberto
- Criado por Eran Hammer, Arquiteto de Plataforma Móvel no Walmart
- Permite que os devs se concentrem em escrever lógica de aplicativo reutilizável em vez de gastar tempo construindo infraestrutura.

### Funcionalidades:
- Authentication e Authorization
- Armazenamento em Cache
- Roteamento
- Validação
- Cookies
- Loggin
- Tratamento de erros
- Monitoramento de Processos

### Express:
- Um pouco menos opinativo que o Hapi, sendo menos abstraído do Node
- Parece uma app nativa em Node.js
- Usa middlewares para fornecer acesso ao pipeline.

### Hapi:
- Estrutura "padrão"
- Implementação mais abstrata do Node
- Usa plugins para estender funcionalidades

## Dev de apps seguras com JWT:

### O que é?

- J.W.T - JSON Web Token
- Padrão (RFC-7519)
- Os dados podem ser validados a qualquer momento pois o token é assinado digitalmente
- Seções: Header, Payload, Signature

#### Header:
- Objeto JSON quedefine informações sobre o tipo do TOKEN (typ)
- O algoritimo de criptografia usado em sua assinatura (alg)(normalmente HMAC SHA256 ou RSA)

##### Exemplo:
```json
{
    "alg" : "HS256",
    "typ" : "JWT"
}
```

---

#### Payload:
- Objeto JSON com as Claims (informações) da entidade tratada.
- **Tipos:** Reserved claims, Public claims e Private claims

##### Reserved claims:
- Atributos não obrigatórios (mas recomendados) que são usados na validação do token pelos protocolos de segurança das APIs.
    - sub: entidade à quem o token pertence
    - iss: emissor do token
    - exp: timestamp da expiração do token
    - iat: timestamp de criação do token
    - aud: destinatário do token

##### Public claims:
- Atributos usados pelas aplicações (ex. user autenticado)
    - name
    - roles
    - permissions

##### Private claims
- Atributos definidos especialmente para compartilhar informações entre aplicações:

###### Exemplo:
```json
{
    "sub" : "40028922",
    "name" : "John Doe",
    "admin" : true
}
```

- Por segurança, recomenda-se não armazenar informações confidenciais ou sensíveis no token.

---

##### Signature:
- A concatenação dos hashes gerados a partir do Header e Payload usando base64UrlEncode, com uma chave secreta ou certificado RSA.

- Garante a integridade do token, se ele foi modificado e se realmente foi gerado por você.

- Previne ataques do tipo man-in-the-middle: o invasor poderia interceptar a requisição e modificar seu conteúdo.

- Apenas quem está de posse da chave pode criar, alterar e validar o token.

---

##### Exemplo de Token JWT:

```JWT
Header.Payload.Signature
eHUgfuydjhbcfsyuGCSXDUDCGD.egyuJHgsdfutdfdtfuysvuysfus.husiiVYViyvcdtykHBkfuyfsikvi
```

---

### Como o token é usado?

- O user faz login em um serviço de autenticação e um token JWT é criado e retornado para o client.
- O token é enviado para as APIs atravéz do header Authorization de cada request HTTP, com a flag Bearer.

```HTTP response
Authorization: Bearer <token>
```

- Com o token, a API não precisa consultar as informações do usuário no banco de dados.

## Conceitos aplicados a qualidade de código e automação de testes:

### O que é qualidade?

- Não é fácil indicar se o código tem ou não qualidade;
- Cada time/projeto define o que é qualidade e quais serão as boas práticas para cada projeto/demanda;
- É importante que essa "fórmula" de qualidade seja definida logo no começo do projeto;
- Alguns conceitos precisam ser seguidos para evitar problemas futuros.

#### Legibilidade:

- Não entender o que determinado código está fazendo, muitas vezes nos faz criar algo que já existe ou gerar trabalho que pode se transformar em um problema no futuro.
- Term o menor numero de linhas possível, facilitando o entendimento e a leitura do que o código faz.
- Nomes de classes constantes e funções devem ter declarações expressivas, indicando o que elas fazem ou representam.
- Ser legível no sentido que não só a máquina entenda, mas que também qualquer pessoa entenda.
- Desde a estrutura até a nomeclatura deve haver organização.

#### Munutenbilidade:

- Envolve três principais características:
  - Estabilidade: a capacidade de manter um código estável, ligada a fatores desde a arquitetura ao código em si.
  - Flexibilidade: criar pensando em algo extensivo para novas funcionalidades.
  - Usabilidade: evitar duplicidade e garantir que o código entregue o mesmo resultado independente do contexto.

#### Princípios da Engenharia de Software:

- Guia para criar códigos legíveis, reutilizáveis e refatoráveis.
- Orientações que não precisam ser rigorosamente seguidas, mas que foram usadas em código durante muitos anos.
- Critérios para se avaliar a qualidade de código JavaScript que você e seu time produzem. 

#### Repositório util:

https://github.com/felipe-augusto/clean-code-javascript

---

### Ferramentas de Lint:

- O termo lint surgiu em programação da necessidade de implementar algum tipo de chacagem automática para previnir e/ou solucionar erros enquanto escrevemos códigos.
- Lint ou Linter é um programa que executa checagem de código estático em procura de erros programáticos e estilísticos.
- Depura o código em busca de possíveis bugs, verifica erros de sintaxe, complexidade ciclomática de código.

#### Exemplos de erros programáticos:

- Falta de `;` quando necessário;
- Variáveis declaradas e não usadas;
- Uso de variáveis antes da declaração;
- Execução de código depois de declarado retorno em funções;
- Uso de imports descontinuados ou não seguros;
- Loops infinitos;

#### Exemplos de erros de estilo:

- Espaçamento indevido;
- Falta de identação;
- Ultrapassagem de limites de caracteres por linha (pré-definido);
- Uso misto de tipos de aspas (`"minha string'`);

#### O que é o ESLint?

- É um projeto open source criado por Nicholas C. Xakas em 2013;
- Ferramentas "plugável" para linting em JS.
- Já vem com algumas regras padrão para análise de código.
- Novas regras podem ser adicionadas ou desativadas de acordo com a necessidade.

### Desenvolvimento guiado por testes:

- O TDD (Test Driven Development) é um processo de software que visa o feedback rápido e validação do comportamento da aplicação.
- Os testes se tornam consequência do processo, pois determina o comportamento esperado da implementação.
- É dividido em ciclos que são conhecidos como **Red**, **Green** e **Refactor**
- Sugestão de livro: https://leanpub.com/construindo-apis-testaveis-com-nodejs

#### Ciclos do TDD:

##### Red:
- O teste é criado antes da funcionalidade ser implementada;
- O teste deve quebrar, pois a implementação não existe
- Nesta fase também é verificado erros de sintaxe e semântica
  
##### Green:
- A funcionalidade é adicionada para que o teste passe;
- A lógica ainda não é necessária, apensas que atenda os requisitos do teste.
- Podem ser adicionados **TODO**s, **FIXME**s e dados estáticos, sendo o suficiente para o teste passar.

##### Refactor:
- A lógica necessária é adicionada;
- Com o testes validado nos ciclos anteriores, garantirá que a funcionalidade está sendo implementada corretamente.
- Devem ser removidos os dados estáticos e feito a implementação real para que os testes voltem a passar.

#### Pirâmide de testes:
- Conceito criado por Mike Cohn, escrito no livro Succeeding with Agile
- Propõe mais testes de baixo nível (testes de unidade), testes de integração e os teste que envolvem interfaces.

#### Tipos de testes:

##### Unidade (Unit Tests):
- São de baixo nível, com foco em pequenas partes do software.
- Tendem a ser mais rapidamente executadas comparados com outros testes, pois testam partes isoladas.
- O que define uma unidade é o comportamento e facilidade de ser isolada das suas dependências.

##### Integração (Integration Tests):
- Servem para verificar se a comunicação entre os componetes de um sistema está ocorrendo conforme o esperado.
- Diferente dos testes de unidade, deve ser testados o comportamento da integração entre as unidades.
- O teste pode ser de qualquer nível, seja a integração entre comandos, classes ou até mesmo serviços.

##### Integração de Contrato (Integration Contract Tests):
- Garante força devido ao crescimento das APIs e dos microserviços.
- Garante que os dados consumidos de serviços externos continuam compatíveis e funcionando. 
- Evita que a aplicação quebre caso a resposta da API mude.

---

## Implementando um servidor WebSocket com o Node.js

### O que é e como funciona o WebSocket:

#### Antes do WebSocket:

- Requisições HTTP Tradicionais:
  - Um cliente requisita dados e o servidor responde passando os dados solicitados.

- Requisições AJAX (XMLHttpRequest / fetch):
  - Possibilidade de fazer conexões entre um cliente e servidor de forma bidirecional.

- Pooling:
  - O cliente faz requisições em busca de novos dados regularmente.
  - Pode ser feito de duas maneiras: 
    - Short Pooling:
      - Requisições AJAX feitas em intervalos de tempo fixos
    - Long Pooling:
      - Mantém a conexão HTTP aberta até o servidor ter dados disponíveis para passar para o cliente.

#### Com o WebSocket:
- Uma aplicação TCP que escuta uma porta de um servidor que segue um protocolo específico.
- Estabelece uma conexão com o navegador e se comunica diretamente com ele.
- Define um canal de comunicação full-duplex através de um único socket através da Web.
- A conexão é estabelecida uma única vez e a comunicação entre o servidor e o navegador se torna contínua.
- Usado em apps que requerem atualizações regulares e rápidas a partir de um WebServer (jogos multiplayer, chat, etc).

### Aprenda a desenvolver WebSocket na prática:

#### Socket.IO
- Oferece uma API simples baseada em eventos
- Permite a comunicação entre o servidor e o cliente em tempo real
- Foi desenvolvido em JavaScript e funciona tanto no Front-end quanto no Back-end (Node.js)
- O mecanismo padrão é o WebSockets, com fallbacks em Flash e AJAX

# Certificado: \P/

###### https://certificates.digitalinnovation.one/FE734F8B

![Certificado](https://hermes.digitalinnovation.one/certificates/cover/FE734F8B.jpg)
