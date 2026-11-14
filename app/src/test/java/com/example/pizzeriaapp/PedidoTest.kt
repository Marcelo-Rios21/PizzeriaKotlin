package com.example.pizzeriaapp

import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class PedidoTest {

    private fun crearCliente() = Cliente(
        "Ana",
        "Calle 1",
        "56911111111",
        "ana@ejemplo.cl"
    )

    private fun crearPizza() = Producto(
        "Pepperoni",
        12000.0,
        mapOf("Masa" to 1, "Pepperoni" to 1)
    )

    @Test
    fun calculaPedidoSinDescuento() {
        val pedido = Pedido(
            1,
            crearCliente(),
            listOf(crearPizza()),
            fecha = LocalDate.of(2026, 11, 14)
        )

        assertEquals(12000.0, pedido.subtotal, 0.001)
        assertEquals(0.0, pedido.descuento, 0.001)
        assertFalse(pedido.promocionActiva)
        assertEquals(14280.0, pedido.calcularTotal(), 0.001)
    }

    @Test
    fun aplicaDescuentoPorCantidad() {
        val pedido = Pedido(
            2,
            crearCliente(),
            List(4) { crearPizza() },
            fecha = LocalDate.of(2026, 11, 14)
        )

        assertEquals(48000.0, pedido.subtotal, 0.001)
        assertEquals(4800.0, pedido.descuento, 0.001)
        assertEquals(51408.0, pedido.calcularTotal(), 0.001)
    }

    @Test
    fun aplicaPromocionDeDiciembre() {
        val pedido = Pedido(
            3,
            crearCliente(),
            List(4) { crearPizza() },
            fecha = LocalDate.of(2026, 12, 10)
        )

        assertTrue(pedido.promocionActiva)
        assertEquals(2160.0, pedido.descuentoPromocional, 0.001)
        assertEquals(48837.6, pedido.calcularTotal(), 0.001)
    }

    @Test
    fun combinaPedidosYActualizaEstado() {
        val cliente = crearCliente()
        val fecha = LocalDate.of(2026, 11, 14)

        val primero = Pedido(
            4,
            cliente,
            List(2) { crearPizza() },
            fecha = fecha
        )

        val segundo = Pedido(
            5,
            cliente,
            List(2) { crearPizza() },
            fecha = fecha
        )

        val combinado = primero + segundo

        assertEquals(4, combinado.productos.size)
        assertEquals(51408.0, combinado.calcularTotal(), 0.001)

        val repartidor = Repartidor("Luis", "56922222222")
        repartidor.entregarPedido(combinado)

        assertEquals("En camino", combinado.estado)
    }
}
