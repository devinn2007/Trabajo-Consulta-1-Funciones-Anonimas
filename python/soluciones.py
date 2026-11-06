from functools import reduce

# Clase base para definir nuestros productos
class Producto:
    def __init__(self, nombre, categoria, precio, stock):
        self.nombre = nombre
        self.categoria = categoria
        self.precio = precio
        self.stock = stock

    # Permite imprimir directamente el nombre del producto en consola
    def __repr__(self):
        return self.nombre

# Lista base unificada para todos los ejercicios
productos = [
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
]



# EJERCICIO 1: SELECCIÓN (Problema: Detectar productos sin stock)
# filter recibe la lambda y la lista. Usamos list() para convertir el resultado en un arreglo
sin_stock = list(filter(lambda p: p.stock == 0, productos))
print(f"1. Selección: {sin_stock}")


# EJERCICIO 2: TRANSFORMACIÓN (Problema: Aplicar 10% de descuento)
# map transforma cada objeto usando la regla matemática de la lambda
precios_descuento = list(map(lambda p: p.precio * 0.90, productos))
print(f"2. Transformación: {precios_descuento}")


# EJERCICIO 3: EXTREMOS (Problema: Buscar el producto más caro)
# Pasamos la lambda al argumento 'key' para indicar cómo medir qué producto es mayor
mas_caro = max(productos, key=lambda p: p.precio)
print(f"3. Extremos: {mas_caro}")


# EJERCICIO 4: ACUMULACIÓN (Problema: Calcular el capital total)
# reduce recibe la lambda de sumatoria, la lista, y un 0.0 como valor inicial (semilla)
valor_total = reduce(lambda acc, p: acc + p.precio * p.stock, productos, 0.0)
print(f"4. Acumulación: {valor_total}")