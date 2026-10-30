package org.example

class Pedido(
    val id: Int,
    val cliente: Cliente,
    val productos: List<Producto>
) {
    var estado: String = "Pendiente"
        private set

    val subtotal: Double = productos.sumOf { it.precio }

    val descuento: Double = if (productos.size > 3) {
        subtotal * 0.10
    } else {
        0.0
    }

    // Tasa de ejemplo: 19% sobre el monto neto con descuento
    val impuestos: Double = (subtotal - descuento) * 0.19

    fun calcularTotal(): Double {
        return subtotal - descuento + impuestos
    }

    fun cambiarEstado(nuevoEstado: String) {
        estado = nuevoEstado
    }
}
