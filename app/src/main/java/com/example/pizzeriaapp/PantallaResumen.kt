package com.example.pizzeriaapp

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.runtime.livedata.observeAsState

@Composable
fun PantallaResumen(
    viewModel: ResumenViewModel,
    totalEjemplo: Int,
    modifier: Modifier = Modifier
) {
    val resumen by viewModel.resumen.observeAsState(
        ResumenPizzeria(0, 0, null)
    )

    val mensaje by viewModel.mensaje.observeAsState()

    var nombreCliente by rememberSaveable { mutableStateOf("") }
    var direccion by rememberSaveable { mutableStateOf("") }
    var telefono by rememberSaveable { mutableStateOf("") }
    var correo by rememberSaveable { mutableStateOf("") }

    var nombreProducto by rememberSaveable { mutableStateOf("") }
    var precioProducto by rememberSaveable { mutableStateOf("") }

    Column(
        modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        Greeting("Android")

        Text(
            text = "Resumen de registros",
            style = MaterialTheme.typography.titleLarge
        )

        Text("Clientes registrados: ${resumen.totalClientes}")
        Text("Pedidos realizados: ${resumen.totalPedidos}")
        Text(
            "Último cliente registrado: " +
                (resumen.ultimoCliente ?: "Sin registros")
        )

        Text("Total de ejemplo: $totalEjemplo CLP")

        HorizontalDivider()

        Text(
            text = "Registrar cliente",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = nombreCliente,
            onValueChange = { nombreCliente = it },
            label = { Text("Nombre") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = direccion,
            onValueChange = { direccion = it },
            label = { Text("Dirección") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = telefono,
            onValueChange = { telefono = it },
            label = { Text("Teléfono") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = correo,
            onValueChange = { correo = it },
            label = { Text("Correo") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (viewModel.registrarCliente(
                    nombreCliente,
                    direccion,
                    telefono,
                    correo
                )) {
                    nombreCliente = ""
                    direccion = ""
                    telefono = ""
                    correo = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar cliente")
        }

        HorizontalDivider()

        Text(
            text = "Registrar producto",
            style = MaterialTheme.typography.titleMedium
        )

        OutlinedTextField(
            value = nombreProducto,
            onValueChange = { nombreProducto = it },
            label = { Text("Nombre del producto") },
            modifier = Modifier.fillMaxWidth()
        )

        OutlinedTextField(
            value = precioProducto,
            onValueChange = { precioProducto = it },
            label = { Text("Precio en CLP") },
            modifier = Modifier.fillMaxWidth()
        )

        Button(
            onClick = {
                if (viewModel.registrarProducto(
                    nombreProducto,
                    precioProducto
                )) {
                    nombreProducto = ""
                    precioProducto = ""
                }
            },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar producto")
        }

        HorizontalDivider()

        Text(
            text = "Registrar pedido",
            style = MaterialTheme.typography.titleMedium
        )

        Text("Se utilizarán el último cliente y producto registrados.")

        Button(
            onClick = { viewModel.registrarPedido() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Registrar pedido")
        }

        mensaje?.let {
            Text(text = it)
        }
    }
}