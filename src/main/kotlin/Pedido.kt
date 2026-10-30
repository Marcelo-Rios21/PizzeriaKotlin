package org.example

class Pedido(
    val id: Int,
    val cliente: Cliente,
    val productos: List<Producto>
) {
    var estado: String = "Pendiente"
        private set

    fun calcularTotal(): Double {
        return productos.sumOf { it.precio }
    }

    fun cambiarEstado(nuevoEstado: String) {
        estado = nuevoEstado
    }
}
