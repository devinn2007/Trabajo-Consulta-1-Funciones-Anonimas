# Trabajo-Consulta-1 Funciones Anónimas

## Parte 1: Explorar: la programación funcional en el mundo real

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

Explicación: En este ejemplo existen dos funciones anónimas. La primera es s => s.length, que se pasa al método map para transformar cada línea de texto en un número. La segunda es (a, b) => a + b, que se pasa a reduce para sumar todas las longitudes. Ambas lambdas son enviadas por Spark a través de la red hacia los nodos trabajadores para ejecutarse de forma distribuida.

**Caso 2: Netflix: Arquitectura de Microservicios con RxJava**

Problema que resuelve: La aplicación de Netflix necesita manejar cientos de miles de peticiones de usuarios al mismo tiempo y coordinar llamadas a docenas de bases de datos distintas en tiempo real para armar tu página de inicio sin que la aplicación se congele.

Lenguaje: Java: utilizando la librería reactiva RxJava.

Ventaja del enfoque funcional: Adoptaron el paradigma funcional para evitar funciones anidadas dentro de otras funciones. Las funciones anónimas permiten definir cadenas de operaciones de forma declarativa, procesando flujos de datos asíncronos como si fueran simples listas.

Fuente consultada: Blog Técnico de Netflix (Netflix TechBlog) - "Reactive Programming in the Netflix API with RxJava".

Enlace: https://netflixtechblog.com/reactive-programming-in-the-netflix-api-with-rxjava-7811c3a1496a

Fragmento de código Java:

``` java
// Lógica conceptual basada en RxJava de Netflix
videoStream
    .filter(video -> video.getRating() >= 4.0)
    .map(video -> video.getMetadata())
    .subscribe(metadata -> display(metadata));
```

Explicación: Aquí vemos tres funciones anónimas. La función video -> video.getRating() >= 4.0 se pasa a filter para dejar pasar solo los videos con buena calificación. Luego, video -> video.getMetadata() extrae la información del video. Cada lambda es un bloque de comportamiento independiente inyectado en la tubería de datos asíncrona.

## Parte 2: Entender: ¿qué es una función anónima?

¿Qué es una función anónima y de dónde viene el nombre lambda?

Una función anónima es un bloque de código que toma parámetros, realiza una operación y devuelve un resultado, pero que no tiene un nombre o identificador formal asociado en el código. El nombre lambda proviene del Cálculo Lambda, un sistema matemático formal creado por el matemático Alonzo Church en la década de 1930 para estudiar la computabilidad y definir las funciones de manera pura, mucho antes de que existieran las computadoras modernas.

``` scala
val sumar = (x: Int, y: Int) => x + y
```

¿En qué se diferencia de una función nombrada?

La principal diferencia es la estructura y el propósito. Una función nombrada se define con una palabra clave específica y un identificador para ser reutilizada en múltiples partes del programa. Una función anónima se define directamente en el lugar donde se necesita, ideal para comportamientos de un solo uso.

``` scala
// Función nombrada tradicional
def esParNombrada(numero: Int): Boolean = {
  numero % 2 == 0
}

// La misma lógica como función anónima
val esParAnonima = (numero: Int) => numero % 2 == 0
```

¿Qué significa que las funciones sean ciudadanos de primera clase? ¿Por qué eso permite pasar una lambda a map o filter?

Que una función sea un ciudadano de primera clase significa que el lenguaje de programación la trata exactamente igual que a un dato común, como un texto o un número. Puedes guardarla en una variable, devolverla como resultado de otra función y, lo más importante, pasarla como parámetro.

Esto es lo que hace que map o filter funcionen. Estas operaciones son funciones de orden superior preparadas para recibir otra función como argumento. Al pasarle una lambda a filter, le estamos enviando literalmente el comportamiento como si fuera un simple dato, para que filter lo ejecute internamente por cada elemento de la lista.

``` scala
val numeros = List(1, 2, 3, 4)
val decenas = numeros.map(x => x * 10)
```

¿Qué es un closure?

Un closure es una función anónima que atrapa o captura variables que fueron definidas fuera de su propio cuerpo, permitiendo que la función siga utilizando esos valores externos incluso si el contexto original donde se crearon ya terminó. La lambda encapsula tanto el código a ejecutar como el entorno en el que vive.

``` scala
val iva = 0.15 

// Esta lambda es un closure porque hace referencia a la variable externa iva
val calcularPrecioFinal = (precioBase: Double) => precioBase + (precioBase * iva)

println(calcularPrecioFinal(100.0))
```
