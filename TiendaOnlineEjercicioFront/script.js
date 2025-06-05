document.addEventListener("DOMContentLoaded", () => {
    const clienteForm = document.getElementById("clienteForm");
    if (clienteForm) {
        clienteForm.addEventListener("submit", (event) => {
            event.preventDefault();
            crearCliente();
        });
    }

    loadClientes();
    loadProductos();
});

// Simula autenticación simple
// function login() {
//     const username = document.getElementById("username").value;
//     const password = document.getElementById("password").value;
//     if (username && password) {
//         document.getElementById("loginPage").style.display = "none";
//         document.getElementById("homePage").style.display = "block";
//     } else {
//         alert("Ingrese usuario y contraseña");
//     }
// }

// Simula autenticación simple con fetch
function login() {
    const email = document.getElementById("username").value;
    const password = document.getElementById("password").value;

    if (!email || !password) {
        alert("Ingrese usuario y contraseña");
        return;
    }

    fetch('http://localhost:8085/cliente/login', {
            method: 'POST',
            headers: { 'Content-Type': 'application/json' },
            body: JSON.stringify({ email, password })
        })
        .then(response => {
            if (!response.ok) throw new Error("Usuario o clave inválidos");
            return response.json();
        })
        .then(cliente => {
            console.log("Login exitoso:", cliente);
            document.getElementById("loginPage").style.display = "none";
            document.getElementById("homePage").style.display = "block";
        })
        .catch(error => {
            alert(error.message);
        });
}


function showSection(sectionId) {
    const sections = document.querySelectorAll(".section");
    sections.forEach((sec) => (sec.style.display = "none"));
    document.getElementById(sectionId).style.display = "block";
}

// Crear cliente desde formulario
function crearCliente() {
    const nombre = document.getElementById("nombre").value;
    const apellido = document.getElementById("apellido").value;
    const email = document.getElementById("email").value;
    const fechaRegistro = document.getElementById("fechaRegistro").value;
    const password = document.getElementById("password").value;
    const direccion = document.getElementById("direccion").value;
    const telefono = document.getElementById("telefono").value;

    const cliente = {
        nombre,
        apellido,
        email,
        fechaRegistro,
        password,
        direccion,
        telefono,
    };

    fetch("http://localhost:8085/cliente", {
            method: "POST",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(cliente),
        })
        .then((res) => {
            if (!res.ok) throw new Error("Error al crear cliente");
            return res.json();
        })
        .then(() => {
            loadClientes();
            document.getElementById("clienteForm").reset();
            alert("Cliente creado exitosamente.");
        })
        .catch((err) => alert(err.message));
}

// Cargar clientes en tabla
function loadClientes() {
    fetch("http://localhost:8085/cliente")
        .then((res) => res.json())
        .then((clientes) => {
            const table = document.getElementById("clientesTable");
            table.innerHTML = `
        <tr>
          <th>ID</th><th>Nombre</th><th>Apellido</th><th>Email</th><th>Teléfono</th>
        </tr>`;
            clientes.forEach((c) => {
                table.innerHTML += `
          <tr>            
            <td>${c.idCliente}</td>
            <td>${c.nombre}</td>
            <td>${c.apellido}</td>
            <td>${c.email}</td>
            <td>${c.telefono}</td>
            <td><input type="radio" name="clienteSeleccionado" 
                value='${JSON.stringify(c)}' 
                onchange="seleccionarCliente(this.value)">
            </td>
          </tr>`;
            });
        });
}

// Productos y Facturación (lógica básica, puedes expandir)
function loadProductos() {
    fetch("http://localhost:8085/producto")
        .then((res) => res.json())
        .then((productos) => {
            const container = document.getElementById("productosContainer");
            container.innerHTML = "";
            productos.forEach((p) => {
                const card = document.createElement("div");
                card.className = "card";
                card.innerHTML = `
          <h4>${p.nombre}</h4>
          <p>${p.descripcion}</p>
          <p>Precio: $${p.precio}</p>
          <button onclick="seleccionarProducto(${p.idProducto}, '${p.nombre}', ${p.precio})">Seleccionar</button>`;
                container.appendChild(card);
            });
        });
}


let clienteSeleccionado = null;

function seleccionarCliente(clienteJSON) {
    clienteSeleccionado = JSON.parse(clienteJSON);
    console.log("Cliente seleccionado:", clienteSeleccionado);
}


let carrito = [];

function seleccionarProducto(id, nombre, precio) {
    const existente = carrito.find((item) => item.id === id);
    if (existente) {
        existente.cantidad += 1;
    } else {
        carrito.push({ id, nombre, precio, cantidad: 1 });
    }
    mostrarResumen();
}

function mostrarResumen() {
    const resumen = document.getElementById("resumenSeleccion");

    // Mostrar los datos del cliente seleccionado
    resumen.innerHTML = `
        <p><strong>Cliente seleccionado:</strong> ${clienteSeleccionado.nombre} ${clienteSeleccionado.apellido}</p>
        <p><strong>Email:</strong> ${clienteSeleccionado.email}</p>
        <p><strong>Teléfono:</strong> ${clienteSeleccionado.telefono}</p>`;

    // Mostrar los productos seleccionados
    resumen.innerHTML += "<h4>Resumen de compra:</h4>";
    carrito.forEach((item) => {
        resumen.innerHTML += `<p>${item.nombre} x ${item.cantidad} - $${item.precio * item.cantidad}</p>`;
    });
}


function confirmarFactura() {

    if (!selectedClienteId) {
        alert("Debe seleccionar un cliente.");
        return;
    }

    const total = carrito.reduce((sum, p) => sum + p.precio * p.cantidad, 0);

    // Supongamos que ya tenés seleccionado el cliente
    // y que tenés un array carrito con los productos seleccionados

    const factura = {
        idCliente: clienteSeleccionado.idCliente, // desde la variable global
        fecha: new Date().toISOString(), // o podés poner una fija "2024-06-03T00:00:00"
        total: total,
        detalles: carrito.map(item => ({
            id: { idProducto: item.idProducto },
            cantidad: item.cantidad,
            precioUnitario: item.precioUnitario
        }))
    };

    // fetch("http://localhost:8085/factura/completa", {
    //         method: "POST",
    //         headers: { "Content-Type": "application/json" },
    //         body: JSON.stringify(factura),
    //     })
    //     .then((res) => {
    //         if (!res.ok) throw new Error("Error al guardar factura");
    //         return res.json();
    //     })
    //     .then(() => {
    //         alert("Factura registrada con éxito.");
    //         carrito = [];
    //         mostrarResumen();
    //     })
    //     .catch((err) => alert(err.message));
}