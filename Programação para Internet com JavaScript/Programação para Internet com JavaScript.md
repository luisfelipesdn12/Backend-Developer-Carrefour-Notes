# Programação para Internet com JavaScript

> **H1** Curso | **H2** Módulo | **H3** Aula | **H4** Tópicos | **H5** Sub-tópicos | **H6** Etc. |

## Introdução ao JavaScript

### Introdução ao JavaScript:

- Linguagem de script interpretada e multiplataforma.
- Client Side - É executado do lado do cliente (user)
- Tem capacidade de interagir com elementos de uma página WEB.
- Muito usado no desenvolvimento de páginas e também de aplicativos mobile híbridos.

#### DOM:

- Document Object Model
- Java Script pode alterar todos os elementos do HTML.
- Pode alterar todos os atributos e estilos de CSS de uma página.

---

```html
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>My Page</title>
        <script src="js/main.js"></script>
    </head>
    <body>
        <h3 id="number">0</h3>
        <button type="button" onclick="button()">Add</button>
        <button type="button" onclick="redirectToGo()">Go to go</button>
    </body>
</html>
```

```javascript
var number = 1;

function button() {
    document.getElementById("number")
            .innerHTML = number;
    number++;
}

function redirectToGo() {
    window.open("https://golang.org");
}
```

---

# Certificado \P/

### https://certificates.digitalinnovation.one/5F849F73