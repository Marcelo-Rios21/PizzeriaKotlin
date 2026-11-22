package com.example.pizzeriaapp.utilidades

fun validarPrecioNoNegativo(precio: Double) {
    require(precio.isFinite() && precio >= 0.0) {
        "El precio del producto debe ser valido y no negativo."
    }
}