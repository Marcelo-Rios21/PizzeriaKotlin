# PizzeriaKotlin

Aplicación Android para la gestión básica de clientes, productos y pedidos de una pizzería.

Desarrollada en Kotlin con Jetpack Compose y una arquitectura modular mediante Gradle.

## Tecnologías

- Kotlin
- Android SDK
- Jetpack Compose
- Material Design 3
- Android ViewModel
- LiveData
- Kotlin Coroutines
- Gradle

## Arquitectura

El proyecto está organizado en tres módulos independientes:

### app

Módulo principal de la aplicación Android.

Contiene:

- MainActivity
- PantallaResumen
- ResumenViewModel
- Interfaz de usuario con Jetpack Compose

### datos

Módulo responsable de los modelos y la lógica de negocio.

Contiene:

- Usuario
- Cliente
- Producto
- Pedido
- Repartidor
- Promocionable
- RegistroPizzeria

Incluye operaciones de registro, manejo de listas y cálculo de pedidos.

### utilidades

Módulo que contiene funciones generales reutilizables.

Actualmente incluye la validación de precios utilizada por el modelo de productos.

### Dependencias entre módulos

La aplicación utiliza la siguiente relación:

- app depende de datos.
- datos depende de utilidades.

Los módulos están declarados en settings.gradle.kts y sus dependencias se configuran mediante Gradle.

## Funcionalidades

### Gestión de clientes

Permite registrar clientes con:

- Nombre
- Dirección
- Teléfono
- Correo electrónico

### Gestión de productos

Permite registrar productos con nombre y precio.

La aplicación valida que el precio sea un valor numérico válido y no negativo.

### Gestión de pedidos

Permite registrar pedidos utilizando el último cliente y producto registrados.

La lógica de pedidos incluye:

- Cálculo de subtotales
- Descuentos por cantidad
- Promociones
- Cálculo de impuestos
- Total del pedido
- Cambio de estado
- Combinación de pedidos compatibles

### Resumen dinámico

La pantalla principal muestra:

- Número total de clientes registrados
- Número total de pedidos realizados
- Nombre del último cliente registrado

El resumen se actualiza mediante ViewModel y LiveData cuando cambian los registros.

### Formularios

Los formularios incluyen validaciones y mensajes de confirmación.

Después de registrar correctamente un cliente o producto, los campos se limpian automáticamente.

Si ocurre un error de validación, los campos conservan su contenido para permitir correcciones.

## Requisitos

- Android Studio
- JDK 21 para Gradle
- Android SDK API 37
- SDK mínimo API 24
- Emulador Android o dispositivo físico compatible

## Ejecución

1. Abrir PizzeriaKotlin en Android Studio.
2. Verificar la configuración del Android SDK.
3. Sincronizar el proyecto con Gradle.
4. Seleccionar la configuración app.
5. Seleccionar un emulador o dispositivo físico.
6. Ejecutar la aplicación.

La aplicación fue probada en un emulador Medium Phone con Android API 37.

## Pruebas

El proyecto incluye pruebas unitarias e instrumentales.

Las pruebas unitarias verifican la lógica de pedidos, descuentos, promociones, registros y actualización del resumen.

Las pruebas instrumentales verifican el funcionamiento de componentes del modelo en el entorno Android.

Para ejecutar las pruebas unitarias desde la terminal del proyecto:

    .\gradlew.bat :datos:testDebugUnitTest :app:testDebugUnitTest

Para compilar la aplicación:

    .\gradlew.bat :app:assembleDebug

Para ejecutar las pruebas instrumentales con un dispositivo conectado:

    .\gradlew.bat :app:connectedDebugAndroidTest

## Evidencias

La carpeta evidencias contiene capturas de la estructura modular y del funcionamiento de la aplicación.

Archivos:

- Estructura_modular.png
- Resumen_inicial.png
- Resumen_dinamico.png

## Almacenamiento

Los registros se mantienen en memoria durante la ejecución de la aplicación.

No se utiliza una base de datos ni se implementa persistencia entre reinicios del proceso.

## Alcance

La aplicación proporciona una interfaz básica para demostrar la integración entre los módulos, la lógica Kotlin y Jetpack Compose.

No implementa un sistema CRUD completo ni funcionalidades avanzadas de administración.