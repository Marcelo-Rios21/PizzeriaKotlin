package org.example

import java.time.LocalDate

@Promocionable
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

    val promocionActiva: Boolean =
        fecha in periodoPromocional &&
        productos.any {
            it.javaClass.isAnnotationPresent(Promocionable::class.java)
        }

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

    operator fun component1(): Cliente = cliente

    operator fun component2(): List<Producto> = productos

    operator fun component3(): Double = calcularTotal()

    fun cambiarEstado(nuevoEstado: String) {
        estado = nuevoEstado
    }

    operator fun plus(otro: Pedido): Pedido {
        require(cliente == otro.cliente) {
            "Solo se pueden combinar pedidos del mismo cliente."
        }

        require(fecha == otro.fecha) {
            "Solo se pueden combinar pedidos de la misma fecha."
        }

        return Pedido(
            id = id,
            cliente = cliente,
            productos = productos + otro.productos,
            notas = listOfNotNull(notas, otro.notas)
                .joinToString(" | ")
                .takeIf { it.isNotEmpty() },
            fecha = fecha
        )
    }
}
