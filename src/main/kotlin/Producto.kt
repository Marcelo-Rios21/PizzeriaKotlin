package org.example

class Producto(
    val nombre: String,
    val precio: Double,
    val ingredientes: Map<String, Int>
) {
    init {
        require(precio.isFinite() && precio >= 0.0) {
            "El precio del producto debe ser valido y no negativo."
        }
    }

    fun detallarProducto(): String {
        val detalleIngredientes = ingredientes.entries.joinToString(", ") {
            "${it.key}: ${it.value}"
        }

        return "$nombre - $precio CLP - Ingredientes: $detalleIngredientes"
    }
}
