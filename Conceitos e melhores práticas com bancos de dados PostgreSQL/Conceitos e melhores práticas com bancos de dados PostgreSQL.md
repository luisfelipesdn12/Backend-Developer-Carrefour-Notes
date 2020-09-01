# Conceitos e melhores práticas com bancos de dados PostgreSQL

> **H1** Curso | **H2** Módulo | **H3** Aula | **H4** Tópicos | **H5** Sub-tópicos | **H6** Etc. |

## Introdução ao banco de dados PostgreSQL

### Fundamentos de banco de dados:

#### O que são dados?

- Valores brutos, fatos brutos, observações documentada, são recolhidos e armazenados sem nenhum tipo de tratamento.

---

#### O que são informações?

- Estruturação de dados, organização de dados. Conjunto de dados relacionados entre si que geram valor.

---

#### Modelo relacional:

- Se baseia no princípio que todos os dados serão armazenados em tabelas, com linhas e colunas.
- **Linhas:** tuplas.
- **Colunas:** atributos.

---

#### Tabelas:

- Conjuntos de dados dispostos em colunas e linhas referentes a um objetivo comum. As colunas são os campos da tabela e as linhas são os valores em si.

##### O que pode se tornar uma tabela?

- **Coisas tangíveis** que podem ter atributos podem ser uma tabela. (carro, produto, animal)
- **Funções** podem ser uma tabela (perfis de usuário, status de compra)
- **Eventos ou ocorrências** (Produtos de um pedido, histórico de dados, log de erros)

##### Colunas importantes:

- **Chave Primárias / Primary Key / PK**
  - Conjunto de um ou mais campos que nunca se repetem. Identidade da tabela. São utilizados como índice de referências na criação de relacionamentos entre tabelas.
- **Chave Estrangeira / Foreign Key / FK**
  - Valor de referência a uma PK de outra ou da mesma tabela para criar um relacionamento.

---

#### Sistema de gerenciamento de banco de dados - SGBD:

- Conjunto de programas ou softwares usados para gerenciar o banco de dados.

---

#### Introdução ao PostgreSQL:

- SGBD objeto relacional.
- Desenvolvido em 1986 no Departamento de Ciência da Computação na Universidade da Califórnia em Berkeley.
- Open Source

##### Arquitetura multiprocessos:

- Existem vários processos que ocorrem na máquina pelo PostgreSQL simultaneamente para diversas funcionalidade

##### Modelo cliente/servidor:

###### Cliente:

- Interfaces Gráficas
- Terminal
- Aplicação

###### Servidor:

- O próprio Banco de dados.

##### Principais características:

- Open Source
- Point in time recovery
- Linguagem procedural com suporte a várias linguagens de programação
- Views, functions, procedures, triggers
- Consultas complexas e Commom table expressions (CTE)
- Suporte a dados geográficos (PostGIS)
- Controle de concorrências multi-versão

---

## Objetos e tipos de dados do PostgreSQL

### O que é o arquivo postgresql.conf (e outros arquivos de configuração):

#### postgresql.conf:

- Arquivo onde estão definidas e armazenadas todas as configurações do servidor PostgreSQL.
- Alguns parâmetros só poderão ser ser alterador com uma reinicialização do banco de dados.
- A view pg_settings, acessada por dentro do banco de dados, guarda todas as configurações atuais.

##### Acesso às informações:

- Ao acessar a view pg_settings, é possível visualizar todas as configurações atuais:

  ```sql
  SELECT name, setting
  FROM pg_settings;
  ```

- Ou é possível usar o comando:

  ```sql
  SHOW [parâmetro];
  ```

##### Localização:

- Por padrão, encontra-se dentro do diretório PGDATA definido no momento da inicialização do cluster de BD.

##### Parâmetros das configurações de conexão:

- **LISTEN_ADDRESSES**

  - Endereço(s) TCP/IP das interfaces que o servidor PostgreSQL var escutar/liberar conexões.
  - O valor `*` nessa configuração libera o acesso à qualquer dispositivo, pode ser usado para poupar tempo em testes, mas é uma péssima prática em produção.
- **PORT**

  - A porta TCP que o servidor vai ouvir. O padrão é 5432.
- **MAX_CONNECTIONS**

  - Número máximo de conexões simultâneas no servidor PostgreSQL.
- **SUPERUSER_RESERVED_CONNECTIONS**
  - Número de conexões reservadas para a conexão do banco de dados por super-usuários mesmo se o banco de dados já estiver lotado de conexões.

##### Parâmetros das configurações de autenticação:

- **AUTHENTICATION_TIMEPUT**
  - Tempo máximo em segundos para o cliente conseguir uma conexão com o servidor.
- **PASSWORD_ENCRYPTION**
  - Algoritmo que será usado para criptografar a senha do usuário.
- **SSL**
  - Habilita a conexão criptografada por SLL
  - Maior segurança

#### Parâmetros das configurações de memória:

- **SHARED_BUFFERS**
  - Tamanho da memória compartilhada do servidor para cache/buffer de tabelas, índices e demais relações.
- **WORK_MEM**
  - Tamanho da memória para operações de agrupamento e ordenação (ODER BY, SISTINCT, MERGE JOINS).
- **MAINTENANCE_WORK_MEM**
  - Tamanho da memória para operações como VACUUM, INDEX, ALTER, TABLE.

---

#### pg_hba.conf:

- Arquivo responsável pelo controle de autenticação dos usuários no servidor PostgreSQL.

---

#### pg_ident.conf:

- Arquivo responsável por mapear os usuários do sistema operacional com os usuários do banco de dados. 

---

#### Comandos administrativos:

##### Windows:

- Serviços > postgresql > click com botão direito > opções.

---

#### Binários do PostgreSQL:

- createdb
- createuser
- dropdb
- dropuser
- initbd
- pg_ctl
- pg_basebackup
- pg_dum / pg_dumball
- pg_restore
- psql
- reindexdb
- vacuumdb

---

#### Arquitetura/Hierarquia:

##### Cluster:

Coleção de banco de dados que compartilham as mesmas configurações (arquivos de configuração) do PostgreSQL e do sistemas operacional (porta, listen_address etc.).

##### Banco de dados (db, database):

- Conjunto de schemas com seus objetos/relações (tabelas, funções, views etc.)

##### Schemas:

Conjunto de objetos/relações (tabelas, funções, views etc.)

---

### Conheça a ferramenta PGAdmin:

#### Quando houver problemas em se conectar no banco de dados:

1. Liberar acesso ao cluster em postgresql.conf
2. Liberar acesso ao cluster para o usuário do banco de dados em pg_hdb.conf
3. Criar/editar usuários

---

### Como administrar usuários no banco de dados:

#### Conceitos users/roles/groups:

- Roles (papéis/funções), users e grupo de usuários são "contas", perfis de atuação em um banco de dados, que possuem permissões em comum ou específicas.
- Nas versões anteriores do PostgrSQL 8.1, usuários e roles tinham comportamentos diferentes.
- Atualmente users e roles são aliadas
- É possível que roles pertençam a ouras roles. 

#### Associação entre roles:

- Quando uma role assume as permissões de outra role. Necessária a opção **INHERT**.

- **IN ROLE** - a role que está sendo criada passa a pertencer a role informada.

- **ROLE** - a role informada passa a pertencer a role que está sendo criada.

- Após a criação da role:

  ```postgresql
  GRANT [role a ser concedida] TO [role a assumir as permições]
  ```

  ```postgresql
  REVOKE [role que foi concedida] FROM [role que assumiu as permições]
  ```

- Excluir role **DROP ROLE nome_da_role**

#### Administrando acessos:

##### GRANT:

- São privilégios de acesso aos objetos do banco de dados.

###### Privilégios:

- --**tabela**
- --coluna
- --sequence
- --**database**
- --domain
- --foreign data wrapper
- --foreign server
- --**function**
- --language
- --large object
- --**schema**
- --tablespace
- --type

---

### Objetos e comandos do banco de dados:

#### Databases, Schemas e Objetos:

- **Database** - É o banco de dados. Grupo de schemas e seus objetos, como tabelas, types, views, funções, entre outros.
- **Schemas** - É um grupo de objetos, como tabelas, types, views, funções entre outros. É possível relacionar objetos entre diversos schemas.
- **Objetos** - São as tabelas, views, funções, types, sequences, entre outros.

##### Database:

- **CREATE DATABASE [name]** 
- **ALTER DATABASE [nome] RENAME TO [new_name]**
- **DROP DATABASE [nome]**

##### SCHEMA:

- Comandos semelhantes.

- **Melhores práticas** - IF EXISTS - DROP SCHEMAS IF EXISTS

---

#### Tabelas, Colunas e Tipos de dados:

- **Tabela** - Conjunto de dados dispostos em colunas e linhas referentes a um objetivo comum.
- **Colunas** -  Consideradas como "campos da tabela", como atributos da tabela.
- **Linhas** - Chamadas também de tuplas, e é onde estão contidas os valores, os dados.

##### Exemplo:

```exemplo
TABELA = automovel
COLUNA 1 = tipo(carro, moto, aviao, helicoptero)
COLUNA 2 = ano_fabricacao(2010, 2011, 2012)

TABELA = produto
COLUNA 1 = cod_serial
COLUNA 2 = preco
```

---

#### Chave primária:

- Conjunto de um ou mais campos que nunca se repetem na tabela.
- Garantem a integridade de um dado único.
- Podem ser usada para relacionar uma tabela em outra.
- Não pode haver duas ocorrência.
- Não pode ser um atributo opcional.
- Não deve se alterar.

#### Chave estrangeira:

- Referencia de chaves primárias de outras tabelas ou da mesma tabela.

#### Tipos de dados (que trabalharemos):

- **Numeric**
- **Character**
- **Date/Time**
- **Boolean**

---

#### DML e DDL:

- **DML** - Data Manipulation Language - Linguagem de manipulação de dados.
  - INSERT, UPDATE, DELETE, SELECT.
  - Alguna autores considerem o SELECT, outros não.

- **DDL** -  Data Definition Language - Linguagem de definição de dados.
  - CREATE, ALTER, DROP

---

## Fundamento da Structed Query Language (SQL)

### Conheça o DML e Truncate:

#### Idempotência:

- A capacidade de um comando em ser executado mais de uma vez sem erros ou alterações.
- Exemplo: "CREATE TABLE table_name" **não** é um comando idempotente, pois se executarmos mais de uma vez, retornará um erro dizendo que a tabla já existe.
- Um exemplo de comando idempotente é: "CREATE TABLE IF NOT EXISTS table_name".

#### DML:

##### SELECT:

```sql
-- Base:
SELECT [campos] FROM [tabela] WHERE [condições];

-- Exemplos:
SELECT numero, nome FROM banco;
SELECT numero, nome FROM banco WHERE ativo IS TRUE;
SELECT nome FROM cliente WHERE email LIKE '%gmail.com';

-- Não é uma boa prática:
SELECT numero FROM agencia
	WHERE banco_numero IN (
        SELECT numero FROM banco WHERE nome ILIKE '%Bradesco%'
    );
```

###### Condicão:

- Primeira condição sempre WHERE. Demais condições, AND ou OR.
- **Operadores**
  - =
  - "> / >="
  - < / <=
  - <> / !=
  - LIKE
  - ILIKE
  - IN

---

##### INSERT:

```sql
-- Modelo:
INSERT [campos da tabela] VALUES [valores];
INSERT [campos da tabela] SELECT [valores];

-- Exemplos:
INSERT INTO agencia (banco_numero, numero, nome) VALUES (341,1,'Centro da cidade'); --> não idempotente
INSERT INTO agencia (banco_numero, numero, nome) VALUES (341,1,'Centro da cidade') ON CONFLICT (banco_numero, numero) DO NOTHING; --> idempotente
```

---

##### UPDATE:

```sql
-- SEMPRE COLOCAR WHERE!!!!!!
-- Modelo:
UPDATE [tabela] SET campo1 = novo_valor WHERE [condição];
```

---

##### DELETE:

```sql
-- SEMPRE COLOCAR WHERE!!!!!!
-- Modelo:
DELETE FROM [tabela] SET campo1 = novo_valor WHERE [condição];
```

---

##### TRUNCATE:

- Esvazia a tabela

---

### Funções agregadas em PostgreSQL:

> **Referencia: https://www.postgresql.org/docs/11/functions-aggregate.html**

#### Funções que serão abordadas:

- **AVG**
- **COUNT**
- **MAX**/**MIN**
- **SUM**

---

#### AVG:

- Retorna a média aritmética dos valores:

```sql
-- Modelo:
SELECT AVG([column_name]) FROM [table_name];
SELECT AVG([column_name]) FROM [table_name] WHERE [condition];

-- Exemplos:
SELECT AVG(preco) FROM produtos;
SELECT AVG(preco) FROM produtos WHERE em_estoque IS TRUE;
```

---

#### COUNT:

- Retorna a quantidade de valores:

```sql
-- Modelo:
SELECT COUNT([column_name]) FROM [table_name];
SELECT COUNT([column_name]) FROM [table_name] WHERE [condition];

-- Exemplos:
SELECT COUNT(preco) FROM produtos;
SELECT COUNT(preco) FROM produtos WHERE em_estoque != TRUE;

SELECT COUNT(preco), cor
	FROM produtos
	WHERE em_estoque = TRUE
	GROUP BY cor;
	--> Retorna a quantidade de preços para cada cor
```

---

#### MIN E MAX:

```sql
-- AUTOESPLICATIVO ??
```

---

#### SUM:

```sql
-- LEIA ACIMA :) 
```

---

### Trabalhando com JOINs:

#### Tipos:

- JOIN ou INNER JOIN
- LEFT JOIN
- RIGHT JOIN
- FULL JOIN
- CROSS JOIN

---

#### JOIN ou INNER JOIN:

- A intersecção das tabelas.
- O conjunto de colunas que relacionam uma tabela e outra.
- Quando o campo tenha alguma relação, traz o campo e o valor, caso não tenha, não traz o campo.

```sql
SELECT tabela_1.campos, tabela_2.campos
	FROM tabela_1
	JOIN tabela_2
		ON tabela_2.campo = tabela_1.campo
```

---

#### LEFT JOIN ou LEFT OUTER JOIN:

- Retorna todos os campos da tabela à esquerda, mesmo se não tiverem relação.
- Quando o campo tenha alguma relação, traz o campo e o valor, caso não tenha, traz o campo e nulo.

```sql
SELECT tabela_1.campos, tabela_2.campos
	FROM tabela_1
	LEFT JOIN tabela_2
		ON tabela_2.campo = tabela_1.campo
```

---

#### RIGHT JOIN ou RIGHT OUTER JOIN:

- Retorna todos os campos da tabela à direita, mesmo se não tiverem relação.
- Quando o campo tenha alguma relação, traz o campo e o valor, caso não tenha, traz o campo e nulo.

```sql
SELECT tabela_1.campos, tabela_2.campos
	FROM tabela_1
	RIGHT JOIN tabela_2
		ON tabela_2.campo = tabela_1.campo
```

---

#### FULL JOIN:

- Uma espécie de fusão entre left e right join.

---

#### CROSS JOIN:

- Todos os campos da tabela se relacionam formando uma matriz.

---

### Otimizando o código com CTE:

#### Common Table Expression:

- Forma auxiliar de organizar blocos de códigos, para consultas muito grandes, gerando tabelas temporárias e criando relacionamentos entre elas.

---

## Comandos avançados da SQL

### Como as views auxiliam no acesso ao banco de dados:

- Views são camadas para as tabelas
- Aceitam comandos de SELECT, **INSERT, UPDATE e DELETE**.

#### IDEMPOTÊNCIA:

```sql
-- Exemplo Idempotente:

CREATE OR REPLACE VIEW vw_banco AS (
	SELECT numero, nome, ativo
    FROM banco
);

SELECT numero, nome, ativo
FROM VWNACAS;
```

#### Views temporárias:

- Só existem na sua seção pessoal e é excluída no logoff.

```sql
-- Exemplo temporário:
CREATE OR REPLACE TEMPORARY VIEW vw_banco AS (
	SELECT numero, nome, ativo
    FROM banco
);

SELECT numero, nome, ativo
FROM VWNACAS;
```

---

### Conheça um dos principais conceitos de banco de dados: transações:

- Conceito de múltiplas etapas/códigos reunidos em apenas 1 transação, onde o resultado precisa ser tudo ou nada.

```sql
BEGIN;
[operações];
SAVEPOINT [nome do savepoint];
[operações];
ROLLBACK TO [nome do savepoint];
[operações];
COMMIT;
```

---

### Conheça as funções que podem ser criadas pelo desenvolvedor:

#### Tipos de funções:

- Query Language Functions - em SQL;
- Procedural Language Functions - outras linguagens;
- Internal Functions - PostgreSQL;
- C Language Functions;

#### Idempotência nas funções:

```sql
CREATE OR REPLACE FUNCTION [nome da função]
```

- Caso for dar um replace:
  - A nova função precisa ter o mesmo nome da anterior
  - Precisa ter o mesmo tipo de retorno
  - Precisa ter o masmo número de parâmetros

---

# Certificado \P/

### https://certificates.digitalinnovation.one/F62203D2