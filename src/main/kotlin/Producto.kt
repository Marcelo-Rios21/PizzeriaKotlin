package org.example

class Producto(
    val nombre: String,
    val precio: Double,
    val ingredientes: Map<String, Int>
) {
    fun detallarProducto(): String {
        val detalleIngredientes = ingredientes.entries.joinToString(", ") {
            "${it.key}: ${it.value}"
        }

        return "$nombre - $precio CLP - Ingredientes: $detalleIngredientes"
    }
}
