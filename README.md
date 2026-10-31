# PizzeriaKotlin

Aplicacion de consola desarrollada en Kotlin para gestionar pedidos diarios de una pizzeria.

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

- Registro de varios pedidos con nombre, direccion, telefono y correo del cliente.
- Validacion de telefono, correo y entradas con valores predeterminados.
- Calculo de subtotal, descuento, impuestos y total por pedido.
- Aplicacion de un descuento del 10% para pedidos de mas de 3 pizzas.
- Verificacion de ingredientes disponibles mediante when.
- Actualizacion del inventario cuando el pedido es confirmado.
- Asignacion del repartidor mas cercano mediante posiciones simuladas.
- Presentacion del resumen final del pedido.
- Reporte diario y clasificacion de pedidos por estado.
- Registro de notas opcionales y notificaciones simuladas.
- Manejo de errores para precios e inventarios invalidos.

## Datos de ejemplo

- Pizza: Pepperoni.
- Precio neto unitario: 12000 CLP.
- Ingredientes disponibles inicialmente: 10 unidades de Masa y 10 de Pepperoni.
- Consumo de inventario: 1 unidad de Masa y 1 de Pepperoni por pizza.
- Impuesto de ejemplo: 19% aplicado despues del descuento.

## Alcance

El programa procesa varios pedidos por ejecucion.

Los identificadores de pedidos son consecutivos desde 1 en cada ejecucion.
El inventario se reinicia al iniciar una nueva ejecucion.
No se utiliza base de datos ni almacenamiento persistente.
