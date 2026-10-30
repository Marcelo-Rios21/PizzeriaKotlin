package org.example

fun main() {
    // Cliente
    print("Ingrese nombre del cliente: ")
    val nombreIngresado = readlnOrNull()?.trim()
    val nombre: String = if (nombreIngresado.isNullOrBlank()) {
        "No informado"
    } else {
        nombreIngresado
    }

    print("Ingrese direccion de entrega: ")
    val direccionIngresada = readlnOrNull()?.trim()
    val direccion: String = if (direccionIngresada.isNullOrBlank()) {
        "No informada"
    } else {
        direccionIngresada
    }

    print("Ingrese telefono del cliente: ")
    val telefonoIngresado = readlnOrNull()?.trim()
    val telefono: String = if (
        telefonoIngresado != null &&
        telefonoIngresado.matches(Regex("\\+?[0-9]{8,15}"))
    ) {
        telefonoIngresado
    } else {
        println("Telefono no valido. Se utilizara un valor predeterminado.")
        "No informado"
    }

    val cliente = Cliente(nombre, direccion, telefono, "correo@invalido.com")

    // Mostrar informacion registrada
    println("\n=== CLIENTE REGISTRADO ===")
    println("Nombre: ${cliente.nombre}")
    println("Direccion: ${cliente.direccion}")
    println("Telefono: ${cliente.telefono}")

    // Pizza
    val producto = Producto(
        "Pepperoni",
        12000.0,
        mapOf("Pepperoni" to 1)
    )
    var ingredientesDisponibles: Int = 10

    // Pedido
    val idPedido: Int = 1

    print("Ingrese cantidad de pizzas: ")
    val cantidadIngresada = readlnOrNull()?.toIntOrNull()
    val cantidadPizzas: Int = if (
        cantidadIngresada != null && cantidadIngresada > 0
    ) {
        cantidadIngresada
    } else {
        println("Cantidad no valida. Se utilizara 1 pizza.")
        1
    }

    val pedido = Pedido(
        idPedido,
        cliente,
        List(cantidadPizzas) { producto }
    )

    // Calculo del pedido
    val subtotalPedido: Double = pedido.calcularTotal()

    val descuento: Double = if (cantidadPizzas > 3) {
        subtotalPedido * 0.10
    } else {
        0.0
    }

    val totalPedido: Double = subtotalPedido - descuento

    // Mostrar calculo
    println("\n=== CALCULO DEL PEDIDO ===")
    println("Pizza: ${producto.nombre}")
    println("Precio unitario: ${producto.precio} CLP")
    println("Cantidad: $cantidadPizzas")
    println("Subtotal: $subtotalPedido CLP")
    println("Descuento: $descuento CLP")
    println("Total: $totalPedido CLP")

    // Verificacion de stock
    val resultadoStock: String = if (ingredientesDisponibles >= cantidadPizzas) {
        ingredientesDisponibles -= cantidadPizzas
        pedido.cambiarEstado("Preparacion")
        "Pedido confirmado. Ingredientes suficientes."
    } else {
        pedido.cambiarEstado("Rechazado")
        "Ingredientes insuficientes. El pedido no puede ser completado."
    }

    println("\n=== VERIFICACION DE STOCK ===")
    println(resultadoStock)
    println("Ingredientes restantes: $ingredientesDisponibles")

    // Asignacion de repartidor
    val repartidor = Repartidor("Repartidor 1", "No informado")
    val repartidorAsignado: String = if (pedido.estado == "Preparacion") {
        repartidor.nombre
    } else {
        "Sin asignar"
    }

    // Resumen final del pedido
    println("\n=== RESUMEN DEL PEDIDO ===")
    println("ID del pedido: ${pedido.id}")
    println("Cliente: ${pedido.cliente.nombre}")
    println("Direccion de entrega: ${pedido.cliente.direccion}")
    println("Telefono: ${pedido.cliente.telefono}")
    println("Pizza: ${producto.nombre}")
    println("Precio unitario: ${producto.precio} CLP")
    println("Cantidad de pizzas: $cantidadPizzas")
    println("Total despues del descuento: $totalPedido CLP")
    println("Estado: ${pedido.estado}")
    println("Repartidor asignado: $repartidorAsignado")
}
