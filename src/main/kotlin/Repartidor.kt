package org.example

class Repartidor(
    nombre: String,
    contacto: String,
    val posicion: Int = 0
) : Usuario(nombre, contacto) {

    override fun mostrarContacto(): String {
        return "Repartidor: $nombre - $contacto"
    }

    fun entregarPedido(pedido: Pedido) {
        pedido.cambiarEstado("En camino")
    }
}
