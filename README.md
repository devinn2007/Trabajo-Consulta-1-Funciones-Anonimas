# Trabajo-Consulta-1 Funciones Anónimas

## Parte 1: Explorar: la programación funcional en el mundo real

**Caso 1: Apache Spark**

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

**¿Qué es una función anónima y de dónde viene el nombre lambda?**

Una función anónima es un bloque de código que toma parámetros, realiza una operación y devuelve un resultado, pero que no tiene un nombre o identificador formal asociado en el código. El nombre lambda proviene del Cálculo Lambda, un sistema matemático formal creado por el matemático Alonzo Church en la década de 1930 para estudiar la computabilidad y definir las funciones de manera pura, mucho antes de que existieran las computadoras modernas.

``` scala
val sumar = (x: Int, y: Int) => x + y
```

**¿En qué se diferencia de una función nombrada?**

La principal diferencia es la estructura y el propósito. Una función nombrada se define con una palabra clave específica y un identificador para ser reutilizada en múltiples partes del programa. Una función anónima se define directamente en el lugar donde se necesita, ideal para comportamientos de un solo uso.

``` scala
// Función nombrada tradicional
def esParNombrada(numero: Int): Boolean = {
  numero % 2 == 0
}

// La misma lógica como función anónima
val esParAnonima = (numero: Int) => numero % 2 == 0
```

**¿Qué significa que las funciones sean ciudadanos de primera clase? ¿Por qué eso permite pasar una lambda a map o filter?**

Que una función sea un ciudadano de primera clase significa que el lenguaje de programación la trata exactamente igual que a un dato común, como un texto o un número. Puedes guardarla en una variable, devolverla como resultado de otra función y, lo más importante, pasarla como parámetro.

Esto es lo que hace que map o filter funcionen. Estas operaciones son funciones de orden superior preparadas para recibir otra función como argumento. Al pasarle una lambda a filter, le estamos enviando literalmente el comportamiento como si fuera un simple dato, para que filter lo ejecute internamente por cada elemento de la lista.

``` scala
val numeros = List(1, 2, 3, 4)
val decenas = numeros.map(x => x * 10)
```

**¿Qué es un closure?**

Un closure es una función anónima que atrapa o captura variables que fueron definidas fuera de su propio cuerpo, permitiendo que la función siga utilizando esos valores externos incluso si el contexto original donde se crearon ya terminó. La lambda encapsula tanto el código a ejecutar como el entorno en el que vive.

``` scala
val iva = 0.15 

// Esta lambda es un closure porque hace referencia a la variable externa iva
val calcularPrecioFinal = (precioBase: Double) => precioBase + (precioBase * iva)

println(calcularPrecioFinal(100.0))
```

## Parte 3: Aplicar: ¿en qué problemas sirven las funciones anónimas?

### 3.1 Catálogo de tipos de problemas

**Lista de objetos base utilizada para todos los ejemplos**

Esta misma colección de diez productos tecnológicos se definió en las soluciones de Scala, Java y Python para mantener la consistencia de los datos y hacer comparaciones exactas.

``` sacala
case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

val productos = List(
  Producto("Laptop X", "Laptops", 1200.0, 5),
  Producto("Laptop Y", "Laptops", 850.0, 15),
  Producto("iPhone 14", "Smartphones", 999.0, 8),
  Producto("Galaxy S23", "Smartphones", 900.0, 12),
  Producto("Pixel 7", "Smartphones", 600.0, 0),
  Producto("Audífonos Sony", "Accesorios", 250.0, 30),
  Producto("Cargador USB-C", "Accesorios", 25.0, 100),
  Producto("Monitor LG", "Periféricos", 300.0, 7),
  Producto("Teclado Mecánico", "Periféricos", 120.0, 20),
  Producto("Mouse Inalámbrico", "Periféricos", 50.0, 50)
)
```


**1. Selección**

Situación real: Una tienda en línea necesita detectar y bloquear los productos que se han quedado sin stock en su base de datos para evitar compras fallidas por parte de los clientes.

Scala: 

``` sacala
val sinStock = productos.filter(p => p.stock == 0)
```

Java:

``` java
List<Producto> sinStock = productos.stream()
    .filter(p -> p.getStock() == 0)
    .collect(Collectors.toList());
```

Python

``` python
sin_stock = list(filter(lambda p: p.stock == 0, productos))
```

Salida de las tres versiones: Una lista con un solo elemento correspondiente al teléfono Pixel 7.


**2. Transformación**

Situación real: Por época navideña la tienda decide aplicar un descuento del diez por ciento a todos sus artículos. Se requiere extraer únicamente los valores numéricos actualizados para sincronizarlos con la pasarela de pagos.

Scala: 

``` sacala
val preciosDescuento = productos.map(p => p.precio * 0.90)
```

Java:

``` java
List<Double> preciosDescuento = productos.stream()
    .map(p -> p.getPrecio() * 0.90)
    .collect(Collectors.toList());
```

Python

``` python
precios_descuento = list(map(lambda p: p.precio * 0.90, productos))
```

Salida de las tres versiones: Un arreglo numérico con los valores 1080.0, 765.0, 899.1, 810.0, 540.0, 225.0, 22.5, 270.0, 108.0 y 45.0.


**3. Extremos**

Situación real: El departamento de marketing solicita encontrar el artículo más costoso de todo el catálogo para promocionarlo en una campaña publicitaria enfocada en un público exclusivo.

Scala: 

``` sacala
val masCaro = productos.maxBy(p => p.precio)
```

Java:

``` java
Producto masCaro = productos.stream()
    .max(Comparator.comparing(Producto::getPrecio))
    .orElse(null);
```

Python

``` python
mas_caro = max(productos, key=lambda p: p.precio)
```

Salida de las tres versiones: El objeto completo correspondiente a la Laptop X.


**4. Acumulación**

Situación real: El equipo contable necesita calcular la inversión de capital total que la empresa tiene inmovilizada en su bodega. Para lograrlo se debe multiplicar el precio por la cantidad en bodega de cada artículo y luego sumar todos esos subtotales en una sola cifra final.

Scala: 

``` sacala
val valorTotal = productos.foldLeft(0.0)((acumulador, p) => acumulador + p.precio * p.stock)
```

Java:

``` java
Double valorTotal = productos.stream()
    .map(p -> p.getPrecio() * p.getStock())
    .reduce(0.0, (acumulador, subtotal) -> acumulador + subtotal);
```

Python

``` python
from functools import reduce
valor_total = reduce(lambda acc, p: acc + p.precio * p.stock, productos, 0.0)
```

Salida de las tres versiones: El valor numérico total 54542.0 calculado de forma idéntica en los tres lenguajes.

## Parte 4: Ampliar: cuatro operaciones nuevas

Las siguientes cuatro operaciones de Scala no fueron abordadas en las clases teóricas, pero resultan fundamentales para resolver problemas de agrupamiento y depuración sobre listas de datos.

### 4.2 Fichas de operaciones

**1. Operación groupBy**

Qué hace: Agrupa los elementos de la lista creando un diccionario donde las claves son el criterio evaluado y los valores son listas de los elementos que cumplen ese criterio.

Función anónima: Recibe una lambda que toma un elemento de la lista y devuelve un valor particular que servirá como clave de agrupación.

Ejemplo en Scala:

``` scala
val porCategoria = productos.groupBy(p => p.categoria)
```

Problema que resuelve: Agrupar todos los productos del inventario bajo su respectiva categoría para generar subreportes departamentales. Resuelve un problema de transformación y estructuración de datos.


**2. Operación sortBy**

Qué hace: Ordena la lista original basándose en una propiedad o atributo específico proporcionado mediante una regla interna.

Función anónima: Recibe una lambda que toma el elemento de la colección y devuelve la propiedad numérica o de texto por la que se ordenará de forma ascendente.

Ejemplo en Scala:

``` scala
val ordenados = productos.sortBy(p => p.precio)
```

Problema que resuelve: Mostrar a un usuario de la tienda en línea un catálogo de productos ordenados del precio más barato al más caro. Resuelve un problema clásico de ordenamiento.


**3. Operación partition**

Qué hace: Divide la lista original en exactamente dos listas independientes agrupadas en una estructura doble, basándose estrictamente en una condición lógica. La primera lista contiene los elementos que cumplen la condición y la segunda los que no.

Función anónima: Recibe una lambda conocida como predicado, la cual evalúa un elemento de la lista y devuelve obligatoriamente un valor booleano verdadero o falso.

Ejemplo en Scala:

``` scala
val particion = productos.partition(p => p.precio >= 600.0)
```

Problema que resuelve: Separar el inventario en productos de gama premium y gama estándar mediante un solo recorrido de la lista para calcular impuestos diferenciados. Resuelve un problema de selección múltiple.


**4. Operación distinctBy**

Qué hace: Extrae elementos únicos de una lista eliminando duplicados basándose únicamente en el campo especificado y reteniendo el primer elemento que encuentra.

Función anónima: Recibe una lambda que toma el elemento de la colección y devuelve el atributo específico que se evaluará para buscar colisiones o duplicados.

Ejemplo en Scala:

``` scala
val representativos = productos.distinctBy(p => p.categoria)
```

Problema que resuelve: Extraer un solo producto de muestra por cada categoría disponible en la tienda para tomar fotografías de portada para el sitio web. Resuelve un problema de depuración.


### 4.3 Combinar operaciones

Pregunta sobre la lista de objetos: ¿Cómo separo exclusivamente los accesorios y periféricos en dos inventarios distintos según si cuestan cincuenta dólares o más, asegurándome de que dentro de cada sublista los artículos aparezcan ordenados de menor a mayor precio?

Cadena de operaciones:

``` scala
val agrupados = productos
  .filter(p => p.categoria == "Accesorios" || p.categoria == "Periféricos")
  .sortBy(p => p.precio)
  .partition(p => p.precio >= 50.0)
```

Explicación de cada paso:

1. filter: Esta operación vista en clase se ejecuta primero para aislar del total de los diez productos únicamente aquellos que pertenecen al grupo de accesorios o periféricos, descartando todo lo demás.

2. sortBy: Esta operación nueva toma esa sublista recién filtrada y la organiza en orden ascendente según el campo del precio numérico.

3. partition: Esta operación nueva recibe la lista ya ordenada y la corta en dos listas distintas agrupadas conjuntamente. La primera contiene los que cuestan cincuenta o más y la segunda los menores a cincuenta. Al aplicarse al final, ambas sublistas resultantes heredan intacto el orden establecido en el paso anterior.


## Parte 5: Comparar y concluir

### 5.1 Scala, Java y Python frente a frente

| Criterio | Scala | Java | Python |
| :--- | :--- | :--- | :--- |
| **Sintaxis de la lambda** | `p => p.precio * 0.90` | `p -> p.getPrecio() * 0.90` | `lambda p: p.precio * 0.90` |
| **Cómo se declara el tipo** | Inferencia automática fuerte o mediante declaración explícita opcional `(p: Producto) =>`. | Usa interfaces funcionales de tipado estricto obligatorias. | Tipado dinámico nativo por lo que no requiere ni admite declarar el tipo en la lambda. |
| **Operaciones con listas disponibles** | Nativas y directas sobre la colección mediante métodos integrados. | Requiere transiciones abriendo un flujo con stream y recolectando al final. | Uso de funciones de orden superior del sistema envolviendo la lista. |
| **Restricción del lenguaje** | Orientado fuertemente a la inmutabilidad sin restricciones severas de bloque. | Las variables externas capturadas deben ser constantes o efectivamente finales. | Las lambdas están limitadas a una sola expresión en línea sin permitir bloques de código complejos. |
| **Legibilidad** | Excelente y muy limpia por el encadenamiento directo de izquierda a derecha. | Verbosa debido al código de andamiaje extra requerido por la interfaz de flujos. | Muy matemática pero el anidamiento de funciones altera el orden natural de lectura. |

**¿En qué lenguaje fue más natural resolver los problemas?**
Scala resultó ser el más natural. Al tener las funciones de orden superior integradas directamente como métodos de la propia colección, el flujo de pensamiento del programador se plasma en el código de izquierda a derecha de forma ininterrumpida y lógica.

**¿En qué tipo de problema encontraste más diferencias entre lenguajes y por qué?**
En los problemas de Transformación y Selección. La diferencia principal es el andamiaje requerido. En Scala el llamado es directo e inmediato sobre la lista. En Java existe una obligación técnica de abrir un flujo de datos asíncrono y luego cerrarlo para volver a formar una lista clásica, lo cual incrementa significativamente la cantidad de código requerido para una operación analítica simple. En Python la diferencia radica en que se usan funciones externas que envuelven la operación, obligando a leer la instrucción desde adentro hacia afuera.

### 5.2 Conclusión

Las funciones anónimas son la pieza fundamental en la programación declarativa sobre colecciones de datos, ya que nos permiten enfocarnos en el objetivo final del problema en lugar de programar los pasos mecánicos manuales para recorrer los elementos. Al delegar la lógica de iteración y los condicionales internos a las operaciones de orden superior, nuestro código se vuelve sustancialmente menos propenso a errores humanos de asignación de variables o desbordamiento de índices.

Esta capacidad de abstracción y seguridad matemática es la razón principal por la cual herramientas empresariales como Apache Spark basan su motor de procesamiento de grandes volúmenes de datos en estas funciones. Tal como se analizó en la primera parte de este documento, Spark toma la función anónima inyectada por el desarrollador y se encarga por detrás de enviarla a cientos de servidores para ejecutarse en paralelo de forma segura, garantizando que el usuario solucione problemas masivos escribiendo exactamente el mismo nivel de código limpio que usaría para procesar una lista pequeña en su propia computadora.
