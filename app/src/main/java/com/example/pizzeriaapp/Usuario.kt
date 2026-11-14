package com.example.pizzeriaapp

open class Usuario(
    val nombre: String,
    val contacto: String
) {
    open fun mostrarContacto(): String {
        return "$nombre - $contacto"
    }
}
