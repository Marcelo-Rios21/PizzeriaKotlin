package org.example

class Cliente(
    nombre: String,
    val direccion: String,
    telefono: String,
    val correo: String
) : Usuario(nombre, telefono) {

    val telefono: String
        get() = contacto

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (other !is Cliente) return false

        return nombre == other.nombre && correo == other.correo
    }

    override fun hashCode(): Int {
        var resultado = nombre.hashCode()
        resultado = 31 * resultado + correo.hashCode()
        return resultado
    }

    operator fun component1(): String = nombre

    operator fun component2(): String = correo

    operator fun component3(): String = telefono

    override fun mostrarContacto(): String {
        return "$nombre - Telefono: $telefono - Correo: $correo"
    }
}
