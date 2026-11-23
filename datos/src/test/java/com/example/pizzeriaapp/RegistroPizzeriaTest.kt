package com.example.pizzeriaapp

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class RegistroPizzeriaTest {

    private fun cliente(nombre: String) = Cliente(
        nombre,
        "Direccion de prueba",
        "56911111111",
        "$nombre@ejemplo.cl"
    )

    private fun producto() = Producto(
        "Pepperoni",
        12000.0,
        mapOf("Pepperoni" to 1)
    )

    @Test
    fun comienzaSinRegistros() {
        val resumen = RegistroPizzeria().resumen()

        assertEquals(0, resumen.totalClientes)
        assertEquals(0, resumen.totalPedidos)
        assertNull(resumen.ultimoCliente)
    }

    @Test
    fun actualizaResumenConClientesYPedidos() {
        val registro = RegistroPizzeria()

        registro.registrarCliente(cliente("Ana"))
        registro.registrarCliente(cliente("Luis"))
        registro.registrarProducto(producto())
        registro.registrarPedido()

        val resumen = registro.resumen()

        assertEquals(2, resumen.totalClientes)
        assertEquals(1, resumen.totalPedidos)
        assertEquals("Luis", resumen.ultimoCliente)
    }

    @Test
    fun pedidoUtilizaUltimoClienteYProducto() {
        val registro = RegistroPizzeria()
        val ultimoCliente = cliente("Maria")
        val pizza = producto()

        registro.registrarCliente(cliente("Ana"))
        registro.registrarCliente(ultimoCliente)
        registro.registrarProducto(pizza)

        val pedido = registro.registrarPedido()

        assertEquals(ultimoCliente, pedido.cliente)
        assertEquals(pizza, pedido.productos.single())
        assertEquals(14280.0, pedido.calcularTotal(), 0.001)
    }

    @Test(expected = IllegalStateException::class)
    fun impidePedidosSinCliente() {
        val registro = RegistroPizzeria()
        registro.registrarProducto(producto())
        registro.registrarPedido()
    }

    @Test(expected = IllegalStateException::class)
    fun impidePedidosSinProducto() {
        val registro = RegistroPizzeria()
        registro.registrarCliente(cliente("Ana"))
        registro.registrarPedido()
    }
}