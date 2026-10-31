package org.example

import kotlin.math.abs

fun main() {
    // Producto disponible
    val producto = try {
        Producto(
            "Pepperoni",
            12000.0,
            mapOf("Masa" to 1, "Pepperoni" to 1)
        )
    } catch (e: IllegalArgumentException) {
        println("Error al registrar producto: ${e.message}")
        return
    }

    // Inventario compartido por todos los pedidos del dia
    val inventario = mutableMapOf(
        "Masa" to 10,
        "Pepperoni" to 10
    )

    // Posiciones simuladas para calcular cercania
    val repartidoresDisponibles = listOf(
        Repartidor("Repartidor 1", "No informado", 2),
        Repartidor("Repartidor 2", "No informado", 5),
        Repartidor("Repartidor 3", "No informado", 8)
    )

    print("Ingrese cantidad de pedidos del dia: ")
    val cantidadIngresada = readlnOrNull()?.toIntOrNull()

    val cantidadPedidos = if (
        cantidadIngresada != null && cantidadIngresada > 0
    ) {
        cantidadIngresada
    } else {
        println("Cantidad no valida. Se registrara 1 pedido.")
        1
    }

    // Almacenar los pedidos del dia en un arreglo
    val pedidosDelDia: Array<Pedido> = Array(cantidadPedidos) { indice ->
        registrarPedido(
            indice + 1,
            producto,
            inventario,
            repartidoresDisponibles
        )
    }

    // Reporte general mediante for
    println("\n=== PEDIDOS DEL DIA ===")

    for (pedido in pedidosDelDia) {
        println(
            "Pedido ${pedido.id} | " +
            "Cliente: ${pedido.cliente.nombre} | " +
            "Pizzas: ${pedido.productos.size} | " +
            "Total: ${pedido.calcularTotal()} CLP | " +
            "Estado: ${pedido.estado}"
        )
    }

    // Agrupar segun el estado de preparacion
    val completados = pedidosDelDia.filter {
        it.estado == "Listo" || it.estado == "En camino"
    }

    val pendientes = pedidosDelDia.filter {
        it.estado == "En preparación"
    }

    val rechazados = pedidosDelDia.filter {
        it.estado == "Rechazado"
    }

    println("\n=== CLASIFICACION DE PEDIDOS ===")

    println("Preparacion completada: ${completados.size}")
    for (pedido in completados) {
        println("- Pedido ${pedido.id}: ${pedido.cliente.nombre} - ${pedido.estado}")
    }

    println("Pendientes de preparacion: ${pendientes.size}")
    for (pedido in pendientes) {
        println("- Pedido ${pedido.id}: ${pedido.cliente.nombre} - ${pedido.estado}")
    }

    println("Rechazados: ${rechazados.size}")
    for (pedido in rechazados) {
        println("- Pedido ${pedido.id}: ${pedido.cliente.nombre} - ${pedido.estado}")
    }
}

fun registrarPedido(
    idPedido: Int,
    producto: Producto,
    inventario: MutableMap<String, Int>,
    repartidoresDisponibles: List<Repartidor>
): Pedido {
    println("\n=== REGISTRO DEL PEDIDO $idPedido ===")

    // Cliente
    print("Ingrese nombre del cliente: ")
    val nombreIngresado = readlnOrNull()?.trim()
    val nombre = if (nombreIngresado.isNullOrBlank()) {
        "No informado"
    } else {
        nombreIngresado
    }

    print("Ingrese direccion de entrega: ")
    val direccionIngresada = readlnOrNull()?.trim()
    val direccion = if (direccionIngresada.isNullOrBlank()) {
        "No informada"
    } else {
        direccionIngresada
    }

    print("Ingrese telefono del cliente: ")
    val telefonoIngresado = readlnOrNull()?.trim()

    val telefono = if (
        telefonoIngresado != null &&
        telefonoIngresado.matches(Regex("\\+?[0-9]{8,15}"))
    ) {
        telefonoIngresado
    } else {
        println("Telefono no valido. Se utilizara un valor predeterminado.")
        "(000) 000-0000"
    }

    print("Ingrese correo electronico del cliente: ")
    val correoIngresado = readlnOrNull()?.trim()

    val correo = if (
        correoIngresado != null &&
        correoIngresado.matches(
            Regex("[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,}")
        )
    ) {
        correoIngresado
    } else {
        println("Correo no valido. Se utilizara un valor predeterminado.")
        "correo@invalido.com"
    }

    val cliente = Cliente(nombre, direccion, telefono, correo)

    println("\n=== CLIENTE REGISTRADO ===")
    val usuario: Usuario = cliente
    println("Contacto: ${usuario.mostrarContacto()}")
    println("Direccion: ${cliente.direccion}")

    // Cantidad de pizzas
    print("Ingrese cantidad de pizzas: ")
    val pizzasIngresadas = readlnOrNull()?.toIntOrNull()

    val cantidadPizzas = if (
        pizzasIngresadas != null && pizzasIngresadas > 0
    ) {
        pizzasIngresadas
    } else {
        println("Cantidad no valida. Se utilizara 1 pizza.")
        1
    }

    // Notas opcionales
    print("Ingrese notas del pedido (opcional): ")
    val notas = readlnOrNull()?.trim()?.takeIf { it.isNotEmpty() }

    val pedido = Pedido(
        idPedido,
        cliente,
        List(cantidadPizzas) { producto },
        notas
    )

    // Calculos centralizados en Pedido
    println("\n=== CALCULO DEL PEDIDO ===")
    println("Detalle del producto: ${producto.detallarProducto()}")
    println("Cantidad: $cantidadPizzas")
    println("Subtotal: ${pedido.subtotal} CLP")
    println("Descuento: ${pedido.descuento} CLP")
    println("Impuestos: ${pedido.impuestos} CLP")
    println("Total: ${pedido.calcularTotal()} CLP")

    // Inventario compartido
    println("\n=== VERIFICACION DE STOCK ===")

    val stockSuficiente = try {
        actualizarInventario(
            producto.ingredientes,
            cantidadPizzas,
            inventario
        )
    } catch (e: IllegalArgumentException) {
        println("Error de inventario: ${e.message}")
        false
    }

    var repartidorAsignado: Repartidor? = null

    if (stockSuficiente) {
        pedido.cambiarEstado("En preparación")
        println("Pedido confirmado. Ingredientes suficientes.")

        // Ubicacion numerica simulada: 0 a 10
        print("Ingrese posicion de entrega (0 a 10): ")
        val posicionIngresada = readlnOrNull()?.toIntOrNull()

        val posicionEntrega = if (
            posicionIngresada != null && posicionIngresada in 0..10
        ) {
            posicionIngresada
        } else {
            println("Posicion no valida. Se utilizara la posicion 5.")
            5
        }

        // Buscar el repartidor con menor distancia
        val distanciaMinima = repartidoresDisponibles.minOfOrNull {
            abs(it.posicion - posicionEntrega)
        }

        repartidorAsignado = repartidoresDisponibles.find {
            distanciaMinima != null &&
                abs(it.posicion - posicionEntrega) == distanciaMinima
        }

        println("Seleccione estado del pedido:")
        println("1. En preparación")
        println("2. Listo")
        println("3. En camino")
        print("Opcion: ")

        val opcionesPermitidas: Set<String> = setOf("1", "2", "3")
        val opcionIngresada = readlnOrNull()?.trim()

        val opcionEstado = opcionIngresada?.takeIf {
            it in opcionesPermitidas
        } ?: "1"

        when (opcionEstado) {
            "2" -> pedido.cambiarEstado("Listo")

            "3" -> {
                if (repartidorAsignado != null) {
                    repartidorAsignado.entregarPedido(pedido)
                } else {
                    pedido.cambiarEstado("Listo")
                }
            }

            else -> pedido.cambiarEstado("En preparación")
        }
    } else {
        pedido.cambiarEstado("Rechazado")
        println("Ingredientes insuficientes. El pedido no puede ser completado.")
    }

    println("Inventario restante:")

    for ((ingrediente, cantidad) in inventario) {
        println("$ingrediente: $cantidad")
    }

    mostrarResumenPedido(pedido, repartidorAsignado)

    // Simular notificacion al correo valido
    cliente.correo.takeIf { it != "correo@invalido.com" }?.let { correoValido ->
        println(
            "Notificacion simulada a $correoValido: " +
            "pedido ${pedido.id} - ${pedido.estado}"
        )
    }

    return pedido
}

fun mostrarResumenPedido(
    pedido: Pedido,
    repartidor: Repartidor?
) {
    val producto = pedido.productos.first()

    println("\n=== RESUMEN DEL PEDIDO ===")
    println("ID del pedido: ${pedido.id}")
    println("Cliente: ${pedido.cliente.nombre}")
    println("Direccion de entrega: ${pedido.cliente.direccion}")
    println("Telefono: ${pedido.cliente.telefono}")
    println("Correo: ${pedido.cliente.correo}")
    println("Notas: ${pedido.notas ?: "Sin notas"}")
    println("Pizza: ${producto.nombre}")
    println("Precio unitario: ${producto.precio} CLP")
    println("Cantidad de pizzas: ${pedido.productos.size}")
    println("Subtotal: ${pedido.subtotal} CLP")
    println("Descuento: ${pedido.descuento} CLP")
    println("Impuestos: ${pedido.impuestos} CLP")
    println("Total final: ${pedido.calcularTotal()} CLP")
    println("Estado: ${pedido.estado}")
    println("Repartidor asignado: ${repartidor?.nombre ?: "Sin asignar"}")
}

fun actualizarInventario(
    ingredientesPorPizza: Map<String, Int>,
    cantidadPizzas: Int,
    inventario: MutableMap<String, Int>
): Boolean {
    require(inventario.values.all { it >= 0 }) {
        "El inventario no puede contener cantidades negativas."
    }

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
