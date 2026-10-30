package org.example

class Repartidor(
    nombre: String,
    contacto: String
) : Usuario(nombre, contacto) {

    override fun mostrarContacto(): String {
        return "Repartidor: $nombre - $contacto"
    }

    fun entregarPedido(pedido: Pedido) {
        pedido.cambiarEstado("En camino")
    }
}
