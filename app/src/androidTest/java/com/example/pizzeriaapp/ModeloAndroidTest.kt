package com.example.pizzeriaapp

import androidx.test.ext.junit.runners.AndroidJUnit4
import java.time.LocalDate
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class ModeloAndroidTest {

    @Test
    fun verificaPedidoEnAndroid() {
        val cliente = Cliente(
            "Ana",
            "Calle 1",
            "56911111111",
            "ana@ejemplo.cl"
        )

        val pizza = Producto(
            "Pepperoni",
            12000.0,
            mapOf("Masa" to 1, "Pepperoni" to 1)
        )

        val pedido = Pedido(
            id = 1,
            cliente = cliente,
            productos = List(4) { pizza },
            fecha = LocalDate.of(2026, 12, 10)
        )

        assertTrue(pedido.promocionActiva)
        assertEquals(4800.0, pedido.descuento, 0.001)
        assertEquals(2160.0, pedido.descuentoPromocional, 0.001)
        assertEquals(48837.6, pedido.calcularTotal(), 0.001)

        val repartidor = Repartidor(
            "Luis",
            "56922222222"
        )

        repartidor.entregarPedido(pedido)

        assertEquals("En camino", pedido.estado)
    }
}
