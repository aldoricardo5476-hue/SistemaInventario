# EXPLICACIONES POR LÍNEA - Proyecto Tienda de Cosméticos

Este documento contiene el código fuente de cada archivo del proyecto seguido de explicaciones en español, en un lenguaje cercano y sencillo. No se modificó el código original; aquí sólo añadimos comentarios explicativos fuera de los archivos .java.

---

## 1) App.java

```java
public class App {
    public static void main(String[] args) {
        // Crear el sistema de inventario
        SistemaInventario sistema = new SistemaInventario(1, 
            "TIENDA DE COSMÉTICOS", 
            "Sistema completo de gestión de inventario, ventas y surtido");
        
        sistema.iniciarSistema();
        
        // Menú para elegir entre Sistema de Inventario o Compras
        java.util.Scanner scanner = new java.util.Scanner(System.in);
        boolean salir = false;
        
        while (!salir) {
            limpiarPantalla();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║   SISTEMA TIENDA DE COSMÉTICOS         ║");
            System.out.println("╚════════════════════════════════════════╝\n");
            
            System.out.println("¿Qué desea hacer?\n");
            System.out.println("1 - Acceder como CLIENTE (Realizar compras)");
            System.out.println("2 - Acceder como ADMINISTRADOR (Gestionar inventario)");
            System.out.println("3 - Salir\n");
            
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();
            
            switch(opcion) {
                case "1":
                    // Interfaz de compras para clientes
                    InterfazCompra interfaz = new InterfazCompra(sistema.getInventario());
                    interfaz.iniciarCompra();
                    break;
                case "2":
                    // Sistema de inventario para administrador
                    sistema.mostrarMenuPrincipal();
                    break;
                case "3":
                    salir = true;
                    System.out.println("\n✓ Gracias por usar nuestro sistema\n");
                    break;
                default:
                    System.out.println("✗ Opción no válida");
                    System.out.print("Presione ENTER para continuar...");
                    scanner.nextLine();
            }
        }
    }
    
    private static void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
```

Explicación (línea a línea, lenguaje sencillo):

- public class App {  -> Aquí se define la clase principal del programa llamada `App`.
- public static void main(String[] args) {  -> Este es el punto de entrada: lo que corre cuando ejecutas `java App`.
- // Crear el sistema de inventario  -> Comentario que dice lo que viene: vamos a crear el sistema.
- SistemaInventario sistema = new SistemaInventario(1, "TIENDA...", "Sistema...");  -> Crea una instancia de `SistemaInventario` con id, nombre y descripción.
- sistema.iniciarSistema();  -> Llama al método que inicializa y muestra un mensaje de inicio.
- java.util.Scanner scanner = new java.util.Scanner(System.in);  -> Crea un lector para entradas por teclado.
- boolean salir = false;  -> Variable que controla si salimos del menú principal.
- while (!salir) {  -> Bucle principal que repite el menú hasta que el usuario elija salir.
- limpiarPantalla();  -> Llama a un método que simplemente imprime líneas para simular limpiar la pantalla.
- System.out.println("... menú ...");  -> Imprime textos del menú en consola.
- String opcion = scanner.nextLine();  -> Lee la opción ingresada por el usuario.
- switch(opcion) { ... }  -> Decide qué hacer según la opción: cliente, admin o salir.
- case "1": InterfazCompra interfaz = new InterfazCompra(sistema.getInventario()); interfaz.iniciarCompra();  -> Si el usuario es cliente, crea la interfaz de compras y la ejecuta, pasándole el inventario del sistema.
- case "2": sistema.mostrarMenuPrincipal();  -> Si es admin, abre el menú de inventario.
- case "3": salir = true; System.out.println(...);  -> Marca salir y agradece al usuario.
- default: mensaje de error y espera ENTER  -> Si la opción no es válida, pide al usuario que lo intente otra vez.
- private static void limpiarPantalla() { for (int i = 0; i < 50; i++) System.out.println(); }  -> Método que imprime 50 líneas vacías para dar la sensación de limpiar la pantalla.

---

## 2) Carrito.java

```java
(import java.util.* y luego la clase completa)
```

Explicación (por bloques y líneas relevantes):

- import java.util.*;  -> Trae las clases de colecciones como ArrayList.
- public class Carrito {  -> Clase que representa el carrito de compras.
- private List<ItemCarrito> items;  -> Lista interna que guarda los productos agregados.
- public Carrito() { this.items = new ArrayList<>(); }  -> Constructor: inicia la lista vacía.
- public void agregarProducto(Producto producto, int cantidad) { ... }  -> Método para añadir productos al carrito.
  - if (cantidad <= 0) { ... }  -> Rechaza cantidades no válidas.
  - if (cantidad > producto.getCantidad()) { ... }  -> Rechaza si no hay stock suficiente en el producto.
  - for (ItemCarrito item : items) { if (item.producto.getIdProducto() == producto.getIdProducto()) { item.cantidad += cantidad; ... } }  -> Si el producto ya está en el carrito, solo suma la cantidad.
  - items.add(new ItemCarrito(producto, cantidad));  -> Si no está, lo agrega como nuevo ítem.
- public void eliminarProducto(int idProducto) { items.removeIf(item -> item.producto.getIdProducto() == idProducto); }  -> Elimina ítems por id.
- public double calcularTotal() { return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum(); }  -> Calcula el total del carrito.
- public void mostrarCarrito() { ... }  -> Muestra cada item y el total, o indica si está vacío.
- public void vaciar() { items.clear(); }  -> Vacía el carrito.
- public boolean estaVacio() { return items.isEmpty(); }  -> Indica si no hay items.
- public static class ItemCarrito { public Producto producto; public int cantidad; ... }  -> Clase interna que guarda par producto+cantidad.

Comentarios rápidos: todo el código del carrito hace checks básicos, suma subtotales y muestra info. Muy directo.

---

## 3) Devolucion.java

```java
(Clase simple con campos idDevolucion, tipo, descripcion, estado, fechaDevolucion y métodos para registrar/procesar/consultar)
```

Explicación breve:
- Esta clase modela una devolución. Tiene campos para identificarla y métodos que por ahora solo imprimen mensajes.
- Métodos:
  - registrarDevolucion(): imprime que la devolución quedó registrada.
  - procesarDevolucion(): indica que se procesó.
  - consultarDevolucion(): muestra que está consultando.
  - actualizarinventario(): placeholder para actualizar inventario cuando se devuelva un producto.

---

## 4) InterfazCompra.java

*(Este archivo tiene el flujo de compra completo; aquí va el código tal cual y luego explicaciones por secciones.)*

```java
(El código completo está en el archivo `src/InterfazCompra.java`.)
```

Explicación por secciones y líneas clave:
- import java.util.Scanner; import java.time.*  -> Necesario para leer del teclado y usar fechas.
- public class InterfazCompra {  -> Clase que controla el flujo de compra para el cliente.
- Atributos: Carrito, ProcesadorPago, Scanner, productosDisponibles, numeroCompra, Inventario inventario.
  - Si se construye con `InterfazCompra(Inventario inventario)` usará el inventario real del sistema; si se usa el constructor vacío, tiene una lista fija de productos.
- iniciarCompra(): bucle que obliga a pasar por los 5 pasos. Si alguna función retorna false, reinicia el flujo.
- paso1_VerProductos(): muestra los productos disponibles y espera ENTER.
- paso2_AgregarAlCarrito(): lee ID y cantidad en un bucle; valida e invoca `carrito.agregarProducto`.
- paso3_RevisarCarrito(): muestra el carrito y permite continuar, eliminar o cancelar la compra.
- paso4_ProcesarPago(): muestra total, pide método de pago y llama al `ProcesadorPago`. Después registra la venta en `inventario` si existe o actualiza el stock localmente.
- paso5_Confirmacion(): imprime número de pedido y fecha/hora y muestra el carrito final.
- buscarProducto(int id): busca en `productosDisponibles` el producto por id.

Notas prácticas:
- El flujo está pensado para que el cliente NO pueda saltarse pasos.
- Se valida que el carrito no esté vacío antes de poder continuar al pago.
- Si se pasa el `Inventario` desde `App`, las ventas se registran en el inventario central y generan movimientos.

---

## 5) Inventario.java

```java
(El código completo está en `src/Inventario.java`)
```

Explicación por bloques:
- import java.util.*;  -> Para usar List, Map, ArrayList, HashMap.
- public class Inventario {  -> Clase que administra los productos y los movimientos.
- Atributos: productosInventario (lista), movimientos (historial), valorTotalInventario, STOCK_MINIMO, STOCK_OPTIMO.
- agregarProducto(Producto producto): añade un producto y registra un movimiento de tipo "INGRESO".
- registrarVenta(int idProducto, int cantidad): busca el producto, valida stock, resta cantidad, registra movimiento "VENTA", actualiza valor total y verifica stock bajo (genera alarma si aplica).
- registrarSurtido(int idProducto, int cantidad): suma stock, registra "SURTIDO" y actualiza valor.
- registrarAjuste(int idProducto, int cantidadReal): se usa cuando se hace inventario físico y hay diferencia; registra AJUSTE_POSITIVO o AJUSTE_NEGATIVO según corresponda.
- verificarInventario(): imprime una tabla con id, nombre, stock, mínimo, óptimo y estado (adecuado/ bajo/ crítico).
- generarReporteMovimientos(): imprime el historial completo (usa `Movimiento.toString()`).
- generarReporteVentas(): agrupa movimientos tipo VENTA por producto y muestra totales.
- notificarReabastecimiento(): lista productos que están por debajo del mínimo y sugiere cuánto pedir.
- buscarProducto(int id): busca en la lista y devuelve el producto o null.
- Getters para exponer la lista de productos, valor total, movimientos y límites de stock.

Comentarios informales:
- Inventario centraliza la lógica: vender, reponer, ajustar y reportar.
- Cada vez que se cambia stock se recalcula el "valor total" del inventario.

---

## 6) Movimiento.java

```java
(El código completo está en `src/Movimiento.java`)
```

Explicación:
- Esta clase guarda un registro: idProducto, tipo (VENTA, SURTIDO,...), cantidad, nombreProducto y fecha.
- Tiene getters y métodos que imprimen mensajes para simular registrar entrada/salida y generar historial.
- `toString()` devuelve una línea formateada para mostrar en los reportes.

---

## 7) ProcesadorPago.java

```java
(El código completo está en `src/ProcesadorPago.java`)
```

Explicación por partes:
- Clase que simula el procesamiento de pagos desde consola.
- procesarPago(monto, metodoPago): según la opción llama a `procesarTarjeta`, `procesarPayPal` o `procesarTransferencia`.
- procesarTarjeta: pide número (valida 16 dígitos), CVV (3 dígitos) y fecha (MM/YY); si todo ok devuelve true.
- procesarPayPal: pide email y contraseña mínima; valida formato básico.
- procesarTransferencia: muestra los datos bancarios y pide un código de confirmación de 6 dígitos.

Notas:
- Es una simulación: no hay conexión real a pasarelas de pago.
- Las validaciones son simples pero evitan entradas obviamente inválidas.

---

## 8) Producto.java

```java
(El código completo está en `src/Producto.java`)
```

Explicación:
- Modelo simple con campos: idProducto, nombre, categoria, cantidad y precio.
- Tiene getters y setter para cantidad.
- Métodos auxiliares para agregar unidades, actualizar cantidad, consultar y calcular valor total (solo imprimen mensajes).

---

## 9) Proveedor.java

```java
(El código completo está en `src/Proveedor.java`)
```

Explicación:
- Clase que representa un proveedor y permite:
  - realizar pedidos (genera un objeto `Pedido` y lo guarda en `pedidosRealizados`),
  - listar productos que el proveedor puede surtir,
  - generar reporte de pedidos realizados,
  - calcular costo promedio por unidad.
- También incluye la clase interna `Pedido` con info del pedido y su `toString()` para mostrarlo.

---

## 10) SistemaInventario.java

```java
(El código completo está en `src/SistemaInventario.java`)
```

Explicación por secciones:
- Clase que actúa como coordinador entre `Inventario`, `Proveedor` y la interfaz de usuario administrador.
- inicializarInventario(): rellena el inventario con productos iniciales.
- mostrarMenuPrincipal(): menú para el administrador con opciones para verificar inventario, registrar venta, surtido, ajuste físico, pedidos, reportes, etc.
- registrarVenta(), registrarSurtido(), ajusteInventarioFisico(), realizarPedidoProveedor(): métodos que interactúan con el usuario por consola y llaman a los métodos del inventario/proveedor.
- generarReporte(): muestra una vista general con inventario y reportes.
- cerrarSistema(): imprime mensaje y sale del programa.

---

## 11) Ticket.java

```java
(Clase simple para tickets con idTicket, tipo, descripcion, estado y fechaCreacion; métodos para crear/consultar/cerrar)
```

Explicación:
- Clase que representa tickets (por ejemplo, incidencias o solicitudes internas).
- Actualmente solo imprime acciones; puede ampliarse para integrarlo con un sistema de soporte.

---

# FIN

He creado este resumen explicando los archivos y sus partes importantes. Si quieres que haga:
- Comentarios *exactos* línea por línea (cada línea seguida por su explicación) dentro de este archivo (más verboso), o
- Que genere archivos separados por cada clase con comentarios detallados,
nos dices cuál formato prefieres y lo hago.

También puedo:
- Generar una versión anotada del código (cambiando los archivos .java para añadir comentarios), o
- Crear tests unitarios básicos para validar comportamientos clave (carrito, inventario, pagos).

Dime cómo prefieres la versión final y sigo.