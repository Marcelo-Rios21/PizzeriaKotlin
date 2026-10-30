package org.example

open class Usuario(
    val nombre: String,
    val contacto: String
) {
    open fun mostrarContacto(): String {
        return "$nombre - $contacto"
    }
}
