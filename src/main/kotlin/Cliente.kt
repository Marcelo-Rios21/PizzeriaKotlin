package org.example

class Cliente(
    nombre: String,
    val direccion: String,
    telefono: String,
    val correo: String
) : Usuario(nombre, telefono) {

    val telefono: String
        get() = contacto

    override fun mostrarContacto(): String {
        return "$nombre - Telefono: $telefono - Correo: $correo"
    }
}
