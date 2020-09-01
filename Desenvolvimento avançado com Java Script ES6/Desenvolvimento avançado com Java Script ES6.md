# Desenvolvimento avançado com Java Script ES6
## Funções avançadas do ES6
### Aprenda o que é e como trabalhar com Arrow Functions:
#### Formas de declaração de fuções antes do ES6:
```javascript
// Funções tradicionais:
function log(to_log) {
    console.log(to_log);
}

log("KKKK");
```
```javascript
// Funções anônimas:
var log = function (to_log) {
    console.log(to_log);
}

log("KKKK");
```
---
#### Uso de uma arrow function:
```javascript
// Funções de flecha / Arrow functions:
// Modelo:
const arrow_func = ([parameters]) => ([returns]);
const arrow_func2 = ([parameters]) => {
    [function_body]
    return [returns];
}

// é possível usar sintaxes assim:
const sum = (...a) => {
    let sum = 0;
    for (let i of a) {
        sum+=i;
    }
    return sum;
};

sum(1,2,3,4,5,6);
```

### Enhanced Object Literals:
- É possível injetar métodos e variáveis num objeto, sem inserir sua chave. Nesse caso a chave vai ter o mesmo nome da função/parametro.

```javascript
const pi = 3; //KKKKKKKKKKKKK perdaum
const logPi = () => (console.log(pi));

// Caso queiramos um objeto contendo o valor de Pi e uma função que faz log desse valor. E que as chaves sejam exatamente iguais: pi, logPi.
// Antes:
const piObj = {
    pi : pi,
    logPi : logPi
}

// Com o ES6:
const piObjEC6 = {
    pi,
    logPi
}
```
---
## Aplicando conceitos REST, Spread Operator e Destructing
### Conheça Rest e Spread Operator:
#### Rest operator:
- Três pontos antes de um argument, retorna uma lista e métodos de Array:

```javascript
const sumPro = (...a) => {
    let sum = 0;
    a.forEach(i => sum += i);
    return sum;
}
```

### Como usar Destructuring em ReactJS:
```javascript
var obj = {
    name : "Luis",
    age : 15
}

var name = obj[0];
var age = obj[1];

/****/

const {name2, age2} = obj;
```
---
## Introdução a Generators
### Symbols e Iterators:
#### Symbols:
- Servem para criar identificadores únicos.
- É impossível criar dois symbols iguais:
```javascript
const id = Symbol("Meu id único");
const id2 = Symbol("Meu id único");

console.log(id === id2); //false
```

- Possui uma série de propriedades chamadas `well know symbols`

## Aplicando conceitos Promises e Fetch

```javascript

const seCep = new Promise((resolve, reject) => {
    fetch("https://viacep.com.br/ws/01001000/json/")
    .then(responseStream => responseStream.json())
    .then(jsonConvertion => resolve(jsonConvertion));
});

const casaCep = new Promise((resolve, reject) => {
    fetch("https://viacep.com.br/ws/02846000/json/")
    .then(responseStream => responseStream.json())
    .then(jsonConvertion => resolve(jsonConvertion));
});

Promise.race([seCep, casaCep])
        .then(c => console.log(c));
```

---
## Conceitos aplicados a qualidade de código e automação de testes em JS
