import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Comparator;

// Clase base para definir la estructura de nuestros productos
class Producto {
    String nombre;
    String categoria;
    Double precio;
    Integer stock;

    public Producto(String nombre, String categoria, Double precio, Integer stock) {
        this.nombre = nombre;
        this.categoria = categoria;
        this.precio = precio;
        this.stock = stock;
    }

    public Double getPrecio() { return precio; }
    public Integer getStock() { return stock; }
    
    @Override
    public String toString() {
        return nombre;
    }
}

public class soluciones {
    public static void main(String[] args) {
        // Lista base unificada para todos los ejercicios
        List<Producto> productos = Arrays.asList(
            new Producto("Laptop X", "Laptops", 1200.0, 5),
            new Producto("Laptop Y", "Laptops", 850.0, 15),
            new Producto("iPhone 14", "Smartphones", 999.0, 8),
            new Producto("Galaxy S23", "Smartphones", 900.0, 12),
            new Producto("Pixel 7", "Smartphones", 600.0, 0),
            new Producto("Audifonos Sony", "Accesorios", 250.0, 30),
            new Producto("Cargador USB-C", "Accesorios", 25.0, 100),
            new Producto("Monitor LG", "Periféricos", 300.0, 7),
            new Producto("Teclado Mecánico", "Periféricos", 120.0, 20),
            new Producto("Mouse Inalámbrico", "Periféricos", 50.0, 50)
        );



        // EJERCICIO 1: SELECCIÓN (Problema: Detectar productos sin stock)
        // La función anónima (lambda) p -> p.getStock() == 0 evalúa si el stock es cero
        List<Producto> sinStock = productos.stream()
            .filter(p -> p.getStock() == 0)
            .collect(Collectors.toList());
        System.out.println("1. Seleccion: " + sinStock);



        // EJERCICIO 2: TRANSFORMACIÓN (Problema: Aplicar 10% de descuento)
        // La lambda p -> p.getPrecio() * 0.90 toma el objeto y devuelve un número nuevo
        List<Double> preciosDescuento = productos.stream()
            .map(p -> p.getPrecio() * 0.90)
            .collect(Collectors.toList());
        System.out.println("2. Transformacion: " + preciosDescuento);



        // EJERCICIO 3: EXTREMOS (Problema: Buscar el producto más caro)
        // Usamos una referencia a método (equivalente a lambda) para comparar por precio
        Producto masCaro = productos.stream()
            .max(Comparator.comparing(Producto::getPrecio))
            .orElse(null);
        System.out.println("3. Extremos: " + masCaro);



        // EJERCICIO 4: ACUMULACIÓN (Problema: Calcular el capital total)
        // Primero transformamos el producto en su subtotal (precio * stock)
        // Luego la lambda (acumulador, subtotal) -> acumulador + subtotal suma todos los valores
        Double valorTotal = productos.stream()
            .map(p -> p.getPrecio() * p.getStock())
            .reduce(0.0, (acumulador, subtotal) -> acumulador + subtotal);
        System.out.println("4. Acumulacion: " + valorTotal);
    }
}
