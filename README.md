# PizzeriaKotlin

Aplicacion de consola desarrollada en Kotlin para gestionar un pedido basico de una pizzeria.

## Requisitos

- IntelliJ IDEA
- JDK 24
- Kotlin/JVM
- Gradle Wrapper incluido en el proyecto

## Ejecucion

1. Abrir la carpeta PizzeriaKotlin en IntelliJ IDEA.
2. Esperar a que Gradle sincronice el proyecto.
3. Abrir src/main/kotlin/Main.kt.
4. Ejecutar la funcion main().
5. Ingresar los datos solicitados por consola.

Para verificar la compilacion desde PowerShell, ejecutar:

    .\gradlew.bat build --console=plain

## Funcionalidades

- Registro del nombre, direccion y telefono del cliente.
- Validacion de entradas y asignacion de valores predeterminados.
- Calculo del subtotal segun precio y cantidad de pizzas.
- Aplicacion de un descuento del 10% para pedidos de mas de 3 pizzas.
- Verificacion de ingredientes disponibles.
- Actualizacion del inventario cuando el pedido es confirmado.
- Asignacion basica de repartidor.
- Presentacion del resumen final del pedido.

## Datos de ejemplo

- Pizza: Pepperoni.
- Precio unitario: 12000 CLP.
- Ingredientes disponibles inicialmente: 10 unidades.
- Consumo de inventario: 1 unidad por pizza.

## Alcance

El programa procesa un pedido por ejecucion.

El identificador del pedido es un valor fijo de ejemplo.
El inventario se reinicia al iniciar una nueva ejecucion.
No se utiliza base de datos ni almacenamiento persistente.

