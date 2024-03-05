// Inicializar el plugin para ocultar nav en pantallas medianas y pequeñas
document.addEventListener('DOMContentLoaded', function () {
    let elem = document.querySelector('.sidenav');
    let instance = M.Sidenav.init(elem);
});

// CARGAR TABLA CLIENTES
// funcionalidad JS -> Call the table jQuery plugin
document.addEventListener("DOMContentLoaded", function () {
    const clientsTable = document.getElementById("clientsTable");
    cargarClientes(clientsTable);
});

async function cargarClientes(clientsTable) {
    const tableClients = document.querySelector("#clientsTable tbody");
    const request = await fetch('api/persons', {
        method: 'GET',
        headers: {
            'Accept': 'application/json',
            'Content-Type': 'application/json'
        },
    });
    const listPersons = await request.json();
    console.log(listPersons);

    // Crear una nueva fila para cada fila de datos
    // const rows = listPersons.map(row => {
    //     return `<tr>
    //             <td>${row.id}</td>
    //             <td>${row.name}</td>
    //             <td>${row.lastName}</td>
    //             <td>${row.phone}</td>
    //             <td>${row.birthday}</td>
    //             <td><button class="btn waves-effect waves-light" type="button" name="edit">Edit<i class="material-icons">edit</i></button>    <button class="btn waves-effect waves-light" type="button" name="delete">Delete<i class="material-icons">delete</i></button></td>
    //             </tr>`;
    // });
    // tableClients.outerHTML += rows;
}

// ACTIVAR CONTRASTE ALTO
// Seleccionar los dos inputs que están dentro de los elementos con la clase switch
const inputs = document.querySelectorAll(".switch input[type=checkbox]");

// let html = document.querySelector("html");
let elements = document.querySelectorAll("html *");

for (let i = 0; i < inputs.length; i++) {
    // Capturar el evento de tipo change de los dos inputs
    inputs[i].addEventListener("change", function () {
        /// Obtener el valor de la propiedad checked que puede ser true or false
        const isChecked = inputs[i].checked;

        if (isChecked) {
            // html.classList.add("high-contrast")
            for (var j = 0; j < elements.length; j++) {
                elements[j].classList.add("high-contrast");
            }
        } else {
            // Si el checkbox no está marcado, quitar la clase .high-contrast a cada elemento
            // html.classList.remove("high-contrast")
            for (var j = 0; j < elements.length; j++) {
                elements[j].classList.remove("high-contrast");
            }
        }
    });
}