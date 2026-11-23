package com.example.pizzeriaapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch

class ResumenViewModel : ViewModel() {

    private val registro = RegistroPizzeria()

    private val _resumen = MutableLiveData(registro.resumen())
    val resumen: LiveData<ResumenPizzeria> = _resumen

    private val _mensaje = MutableLiveData<String?>(null)
    val mensaje: LiveData<String?> = _mensaje

    fun registrarCliente(
        nombre: String,
        direccion: String,
        telefono: String,
        correo: String
    ): Boolean {
        if (listOf(nombre, direccion, telefono, correo).any { it.isBlank() }) {
            _mensaje.value = "Completa todos los datos del cliente."
            return false
        }

        registro.registrarCliente(
            Cliente(
                nombre.trim(),
                direccion.trim(),
                telefono.trim(),
                correo.trim()
            )
        )

        actualizarResumen("Cliente registrado.")
        return true
    }
    fun registrarProducto(nombre: String, precioTexto: String): Boolean {
        if (nombre.isBlank()) {
            _mensaje.value = "Ingresa el nombre del producto."
            return false
        }

        val precio = precioTexto.trim().replace(',', '.').toDoubleOrNull()

        if (precio == null) {
            _mensaje.value = "Ingresa un precio numerico valido."
            return false
        }

        return try {
            registro.registrarProducto(
                Producto(
                    nombre = nombre.trim(),
                    precio = precio,
                    ingredientes = emptyMap()
                )
            )

            actualizarResumen("Producto registrado.")
            true
        } catch (error: IllegalArgumentException) {
            _mensaje.value = error.message
            false
        }
    }
    fun registrarPedido() {
        viewModelScope.launch {
            try {
                registro.registrarPedido()
                actualizarResumen("Pedido registrado.")
            } catch (error: IllegalStateException) {
                _mensaje.value = error.message
            }
        }
    }

    private fun actualizarResumen(texto: String) {
        _resumen.value = registro.resumen()
        _mensaje.value = texto
    }
}