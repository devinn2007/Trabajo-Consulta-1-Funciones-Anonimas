// Case class que define de forma inmutable nuestros productos
case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

// Lista base unificada para todos los ejercicios
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

// EJERCICIO 1: SELECCIÓN (Problema: Detectar productos sin stock)
// Retiene únicamente los elementos donde p.stock == 0 sea verdadero
val sinStock = productos.filter(p => p.stock == 0)
println(s"1. Selección: $sinStock")

// EJERCICIO 2: TRANSFORMACIÓN (Problema: Aplicar 10% de descuento)
// Extrae el precio de cada elemento y genera una lista nueva con los valores multiplicados
val preciosDescuento = productos.map(p => p.precio * 0.90)
println(s"2. Transformación: $preciosDescuento")

// EJERCICIO 3: EXTREMOS (Problema: Buscar el producto más caro)
// La lambda le indica a maxBy que el criterio de búsqueda es el precio numérico
val masCaro = productos.maxBy(p => p.precio)
println(s"3. Extremos: $masCaro")

// EJERCICIO 4: ACUMULACIÓN (Problema: Calcular el capital total)
// foldLeft usa un valor semilla (0.0). La lambda recibe el acumulado actual y el siguiente producto
val valorTotal = productos.foldLeft(0.0)((acc, p) => acc + p.precio * p.stock)
println(s"4. Acumulación: $valorTotal")

