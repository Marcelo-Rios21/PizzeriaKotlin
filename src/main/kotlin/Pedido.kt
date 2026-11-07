package org.example

import java.time.LocalDate

class Pedido(
    val id: Int,
    val cliente: Cliente,
    val productos: List<Producto>,
    val notas: String? = null,
    val fecha: LocalDate = LocalDate.now()
) {
    var estado: String = "Pendiente"
        private set

    val subtotal: Double = productos.sumOf { it.precio }

    val descuento: Double = if (productos.size > 3) {
        subtotal * 0.10
    } else {
        0.0
    }

    // Promocion de ejemplo: diciembre de 2026
    private val periodoPromocional =
        LocalDate.of(2026, 12, 1)..LocalDate.of(2026, 12, 31)

    val promocionActiva: Boolean = fecha in periodoPromocional

    val descuentoPromocional: Double = if (promocionActiva) {
        (subtotal - descuento) * 0.05
    } else {
        0.0
    }

    // Tasa de ejemplo: 19% despues de ambos descuentos
    val impuestos: Double =
        (subtotal - descuento - descuentoPromocional) * 0.19

    fun calcularTotal(): Double {
        return subtotal - descuento - descuentoPromocional + impuestos
    }

    fun cambiarEstado(nuevoEstado: String) {
        estado = nuevoEstado
    }
}
