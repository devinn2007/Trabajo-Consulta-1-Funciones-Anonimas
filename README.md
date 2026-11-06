# Trabajo-Consulta-1 Funciones Anónimas

*** Parte 1: Explorar: la programación funcional en el mundo real ***

Caso 1: Apache Spark

Problema que resuelve: Spark necesita procesar terabytes de datos distribuidos simultáneamente en clústeres de cientos de computadoras. El problema de usar lenguajes tradicionales aquí es que compartir variables que cambian de estado entre diferentes servidores genera errores de concurrencia y bloqueos.

Lenguaje: Scala.

Ventaja del enfoque funcional: Al utilizar programación funcional pura, Spark distribuye fragmentos de datos inmutables llamados RDDs. Al no haber efectos secundarios, los servidores procesan los datos a máxima velocidad en paralelo sin preocuparse de alterar la información de los demás nodos.

Fuente consultada: Documentación oficial de Apache Spark.
Enlace: https://spark.apache.org/docs/latest/rdd-programming-guide.html

Fragmento de código Scala:

``` scala
// Código extraído de la documentación oficial de Spark
val lines = sc.textFile("data.txt")
val lineLengths = lines.map(s => s.length)
val totalLength = lineLengths.reduce((a, b) => a + b)
```
