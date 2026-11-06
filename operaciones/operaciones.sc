case class Producto(nombre: String, categoria: String, precio: Double, stock: Int)

// Lista base unificada
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

// OPERACIÓN NUEVA 1: groupBy (Problema: Agrupar por categoría)
// Crea un diccionario (Map) usando el campo "categoria" como clave
val porCategoria = productos.groupBy(p => p.categoria)
println(s"groupBy:\n$porCategoria\n")


// OPERACIÓN NUEVA 2: sortBy (Problema: Ordenar por precio)
// Toma el campo precio y ordena toda la lista de menor a mayor
val ordenados = productos.sortBy(p => p.precio)
println(s"sortBy:\n$ordenados\n")


// OPERACIÓN NUEVA 3: partition (Problema: Premium vs Estándar)
// Corta la lista en dos partes: los que valen 600 o más, y los que no
val particion = productos.partition(p => p.precio >= 600.0)
println(s"partition:\nPremium: ${particion._1}\nEstándar: ${particion._2}\n")


// OPERACIÓN NUEVA 4: distinctBy (Problema: Un representante por grupo)
// Elimina elementos duplicados basándose en su categoría (retiene el primero)
val representativos = productos.distinctBy(p => p.categoria)
println(s"distinctBy:\n$representativos\n")


// COMBINACIÓN DE OPERACIONES
// 1. Filtramos solo Accesorios y Periféricos
// 2. Ordenamos esos resultados por precio
// 3. Partimos la lista final en dos (caros >= 50 y baratos < 50)
val agrupados = productos
  .filter(p => p.categoria == "Accesorios" || p.categoria == "Periféricos")
  .sortBy(p => p.precio)
  .partition(p => p.precio >= 50.0)

println(s"Accesorios Caros Ordenados: ${agrupados._1}")
println(s"Accesorios Baratos Ordenados: ${agrupados._2}")

