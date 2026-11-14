# PizzeriaKotlin - Aplicacion Android S4

Proyecto academico de pizzeria desarrollado en Kotlin.

La semana 4 adapta el proyecto de consola de S1-S3
a una aplicacion Android con Jetpack Compose.

## Historial

- `main`: proyecto de consola de S1-S3.
- `s4-android`: adaptacion a Android.

## Requisitos

- Android Studio.
- Android SDK API 37.
- SDK minimo API 24.
- JDK 21 para ejecutar Gradle.
- Emulador Android, probado con Pixel 4 API 37.

## Ejecucion

1. Abrir PizzeriaKotlin en Android Studio.
2. Sincronizar Gradle.
3. Seleccionar la configuracion `app`.
4. Seleccionar el emulador.
5. Ejecutar mediante Run.

## Interfaz Android

La pantalla principal utiliza `MainActivity.kt`
y Jetpack Compose.

Muestra el mensaje:

"Bienvenidos a la mejor pizzería de la ciudad"

## Clases migradas

Se incorporaron las clases de S3:

- Usuario
- Cliente
- Producto
- Pedido
- Repartidor
- Promocionable

Las clases conservan las propiedades y los metodos
implementados durante las entregas anteriores.

## Verificaciones

- Compilacion Android correcta.
- Ejecucion correcta en emulador Pixel 4 API 37.
- Cuatro pruebas unitarias aprobadas:
  - Calculo sin descuentos.
  - Descuento por cantidad.
  - Promocion de diciembre de 2026.
  - Combinacion de pedidos y cambio de estado.

Las pruebas unitarias no sustituyen las pruebas
en dispositivos Android con API 24 y 25.

## Alcance

La interfaz actual presenta el mensaje de bienvenida.

La logica del modelo esta integrada y probada,
pero no existe todavia una interfaz para gestionar
pedidos de forma interactiva.

No se utiliza base de datos ni persistencia.
