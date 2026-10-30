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
        mapOf("Masa" to 1, "Pepperoni" to 1)
    )

    val inventario = mutableMapOf(
        "Masa" to 10,
        "Pepperoni" to 10
    )

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
    val subtotalPedido: Double = pedido.subtotal
    val descuento: Double = pedido.descuento
    val impuestos: Double = pedido.impuestos
    val totalPedido: Double = pedido.calcularTotal()

    // Mostrar calculo
    println("\n=== CALCULO DEL PEDIDO ===")
    println("Pizza: ${producto.nombre}")
    println("Precio unitario: ${producto.precio} CLP")
    println("Cantidad: $cantidadPizzas")
    println("Subtotal: $subtotalPedido CLP")
    println("Descuento: $descuento CLP")
    println("Impuestos: $impuestos CLP")
    println("Total: $totalPedido CLP")

    // Verificacion de stock
    println("\n=== VERIFICACION DE STOCK ===")

    val stockSuficiente = actualizarInventario(
        producto.ingredientes,
        cantidadPizzas,
        inventario
    )

    val resultadoStock: String = if (stockSuficiente) {
        pedido.cambiarEstado("Preparacion")
        "Pedido confirmado. Ingredientes suficientes."
    } else {
        pedido.cambiarEstado("Rechazado")
        "Ingredientes insuficientes. El pedido no puede ser completado."
    }

    println(resultadoStock)
    println("Inventario restante:")

    for ((ingrediente, cantidad) in inventario) {
        println("$ingrediente: $cantidad")
    }

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
    println("Subtotal: $subtotalPedido CLP")
    println("Descuento: $descuento CLP")
    println("Impuestos: $impuestos CLP")
    println("Total final: $totalPedido CLP")
    println("Estado: ${pedido.estado}")
    println("Repartidor asignado: $repartidorAsignado")
}

fun actualizarInventario(
    ingredientesPorPizza: Map<String, Int>,
    cantidadPizzas: Int,
    inventario: MutableMap<String, Int>
): Boolean {
    // Calcular ingredientes necesarios para el lote
    val necesarios = ingredientesPorPizza.mapValues { (_, unidades) ->
        unidades * cantidadPizzas
    }

    // Identificar ingredientes insuficientes
    val faltantes = necesarios.filter { (nombre, cantidad) ->
        (inventario[nombre] ?: 0) < cantidad
    }

    return when {
        faltantes.isNotEmpty() -> {
            println("Ingredientes insuficientes:")

            for ((nombre, cantidad) in faltantes) {
                val disponibles = inventario[nombre] ?: 0
                val diferencia = cantidad - disponibles

                println(
                    "- $nombre: necesarios $cantidad, " +
                    "disponibles $disponibles, faltan $diferencia"
                )
            }

            println("Pasos para reabastecer:")
            println("1. Revisar las cantidades faltantes.")
            println("2. Reponer los ingredientes en el inventario.")
            println("3. Volver a realizar el pedido.")

            false
        }

        else -> {
            for ((nombre, cantidad) in necesarios) {
                inventario[nombre] = (inventario[nombre] ?: 0) - cantidad
            }

            true
        }
    }
}
