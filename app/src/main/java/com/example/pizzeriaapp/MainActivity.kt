package com.example.pizzeriaapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.pizzeriaapp.ui.theme.PizzeriaAppTheme
import java.time.LocalDate

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val cliente = Cliente(
            "Cliente de ejemplo",
            "Direccion de ejemplo",
            "56911111111",
            "cliente@example.com"
        )

        val pizza = Producto(
            "Pepperoni",
            12000.0,
            mapOf("Masa" to 1, "Pepperoni" to 1)
        )

        val pedido = Pedido(
            id = 1,
            cliente = cliente,
            productos = listOf(pizza),
            fecha = LocalDate.of(2026, 11, 14)
        )

        setContent {
            PizzeriaAppTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(modifier = Modifier.padding(innerPadding)) {
                        Greeting(name = "Android")
                        Text(
                            text = "Total de ejemplo: ${pedido.calcularTotal().toInt()} CLP"
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Bienvenidos a la mejor pizzería de la ciudad",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PizzeriaAppTheme {
        Greeting("Android")
    }
}