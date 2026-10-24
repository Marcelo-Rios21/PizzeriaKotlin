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

    // Mostrar informacion registrada
    println("\n=== CLIENTE REGISTRADO ===")
    println("Nombre: $nombre")
    println("Direccion: $direccion")
    println("Telefono: $telefono")

    // Pizza
    val nombrePizza: String = "Pepperoni"
    val precioUnitario: Double = 12000.0
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

    var totalPedido: Double = 0.0
    var estadoPedido: String = "Pendiente"

    // Calculo del pedido
    val subtotalPedido: Double = precioUnitario * cantidadPizzas

    val descuento: Double = if (cantidadPizzas > 3) {
        subtotalPedido * 0.10
    } else {
        0.0
    }

    totalPedido = subtotalPedido - descuento

    // Mostrar calculo
    println("\n=== CALCULO DEL PEDIDO ===")
    println("Pizza: $nombrePizza")
    println("Precio unitario: $precioUnitario CLP")
    println("Cantidad: $cantidadPizzas")
    println("Subtotal: $subtotalPedido CLP")
    println("Descuento: $descuento CLP")
    println("Total: $totalPedido CLP")

    // Verificacion de stock
    val resultadoStock: String = if (ingredientesDisponibles >= cantidadPizzas) {
        ingredientesDisponibles -= cantidadPizzas
        estadoPedido = "Preparacion"
        "Pedido confirmado. Ingredientes suficientes."
    } else {
        estadoPedido = "Rechazado"
        "Ingredientes insuficientes. El pedido no puede ser completado."
    }

    println("\n=== VERIFICACION DE STOCK ===")
    println(resultadoStock)
    println("Ingredientes restantes: $ingredientesDisponibles")

    // Asignacion de repartidor
    val repartidorAsignado: String = if (estadoPedido == "Preparacion") {
        "Repartidor 1"
    } else {
        "Sin asignar"
    }

    // Resumen final del pedido
    println("\n=== RESUMEN DEL PEDIDO ===")
    println("ID del pedido: $idPedido")
    println("Cliente: $nombre")
    println("Direccion de entrega: $direccion")
    println("Telefono: $telefono")
    println("Pizza: $nombrePizza")
    println("Precio unitario: $precioUnitario CLP")
    println("Cantidad de pizzas: $cantidadPizzas")
    println("Total despues del descuento: $totalPedido CLP")
    println("Estado: $estadoPedido")
    println("Repartidor asignado: $repartidorAsignado")
}
