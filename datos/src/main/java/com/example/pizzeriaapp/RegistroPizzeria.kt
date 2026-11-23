package com.example.pizzeriaapp

data class ResumenPizzeria(
    val totalClientes: Int,
    val totalPedidos: Int,
    val ultimoCliente: String?
)

class RegistroPizzeria {

    private val clientes = mutableListOf<Cliente>()
    private val productos = mutableListOf<Producto>()
    private val pedidos = mutableListOf<Pedido>()

    fun registrarCliente(cliente: Cliente) {
        clientes.add(cliente)
    }

    fun registrarProducto(producto: Producto) {
        productos.add(producto)
    }

    fun registrarPedido(): Pedido {
        val cliente = clientes.lastOrNull()
            ?: throw IllegalStateException("Primero registra un cliente.")

        val producto = productos.lastOrNull()
            ?: throw IllegalStateException("Primero registra un producto.")

        val pedido = Pedido(
            id = pedidos.size + 1,
            cliente = cliente,
            productos = listOf(producto)
        )

        pedidos.add(pedido)
        return pedido
    }

    fun resumen(): ResumenPizzeria {
        return ResumenPizzeria(
            totalClientes = clientes.size,
            totalPedidos = pedidos.size,
            ultimoCliente = clientes.lastOrNull()?.nombre
        )
    }
}