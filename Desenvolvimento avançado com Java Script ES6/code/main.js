const sum = (...a) => {
    let sum = 0;
    for (let i of a) {
        sum+=i;
    }
    return sum;
};

const sumPro = (...a) => {
    let sum = 0;
    a.forEach(i => sum += i);
    return sum;
}

function ehDivPorDoisNoob(n) {
    if (n % 2 == 0) {
        return "eh";
    } else {
        return "nao";
    }
}

const ehDivPorDoisPro = n => (n % 2 == 0 ? "eh" : "nao");

/*
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
*/

const generatePrimes = () => {
    let primeNumbers = [];

    for (let n = 2; n < 100000; n++) {
        let nIsPrime = primeNumbers
                    .some((p) => (n % p == 0));
        if (nIsPrime) {
            console.log(n);
            primeNumbers.push(n);
        }
    }
};

console.time();
let primeNumbers = [];

for (let n = 2; n < 100000; n++) {
    let nIsPrime = ! primeNumbers
                .some((p) => (n % p == 0));
    if (nIsPrime) {
        primeNumbers.push(n);
    }
}

console.log(primeNumbers);
console.timeEnd();