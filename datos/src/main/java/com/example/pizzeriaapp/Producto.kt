package com.example.pizzeriaapp

import com.example.pizzeriaapp.utilidades.validarPrecioNoNegativo

@Promocionable
class Producto(
    val nombre: String,
    val precio: Double,
    val ingredientes: Map<String, Int>,
    val categoria: String = "pizza"
) {
    init {
        validarPrecioNoNegativo(precio)
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Producto) return false

        return nombre == other.nombre && categoria == other.categoria
    }

    override fun hashCode(): Int {
        var resultado = nombre.hashCode()
        resultado = 31 * resultado + categoria.hashCode()
        return resultado
    }

    fun detallarProducto(): String {
        val detalleIngredientes = ingredientes.entries.joinToString(", ") {
            "${it.key}: ${it.value}"
        }

        return "$nombre - $precio CLP - Ingredientes: $detalleIngredientes"
    }
}
