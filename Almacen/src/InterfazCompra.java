import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class InterfazCompra {
    private Carrito carrito;
    private ProcesadorPago procesador;
    private Scanner scanner;
    private Producto[] productosDisponibles;
    private int numeroCompra;
    private Inventario inventario;
    
    public InterfazCompra() {
        this.carrito = new Carrito();
        this.procesador = new ProcesadorPago();
        this.scanner = new Scanner(System.in);
        this.numeroCompra = 0;
        this.inventario = null;
        inicializarProductos();
    }
    
    public InterfazCompra(Inventario inventario) {
        this.carrito = new Carrito();
        this.procesador = new ProcesadorPago();
        this.scanner = new Scanner(System.in);
        this.numeroCompra = 0;
        this.inventario = inventario;
        inicializarProductosDelInventario();
    }
    
    private void inicializarProductos() {
        productosDisponibles = new Producto[] {
            new Producto(1, "Labial Rojo", "Labios", 50, 15.99f),
            new Producto(2, "Máscara de Pestañas", "Ojos", 30, 22.50f),
            new Producto(3, "Base Maquillaje", "Rostro", 25, 28.00f),
            new Producto(4, "Sombra de Ojos", "Ojos", 40, 18.50f),
            new Producto(5, "Crema Hidratante", "Cuidado", 35, 32.00f)
        };
    }
    
    private void inicializarProductosDelInventario() {
        if (inventario != null) {
            productosDisponibles = inventario.getProductosInventario()
                .toArray(new Producto[0]);
        }
    }
    
    public void iniciarCompra() {
        boolean compraCompleta = false;
        
        while (!compraCompleta) {
            limpiarPantalla();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║   SISTEMA DE COMPRAS - TIENDA COSMÉTICOS║");
            System.out.println("╚════════════════════════════════════════╝\n");
            
            // Paso 1: Ver productos
            if (!paso1_VerProductos()) continue;
            
            // Paso 2: Agregar al carrito
            if (!paso2_AgregarAlCarrito()) continue;
            
            // Paso 3: Revisar carrito
            if (!paso3_RevisarCarrito()) continue;
            
            // Paso 4: Procesar pago
            if (!paso4_ProcesarPago()) continue;
            
            // Paso 5: Confirmación
            paso5_Confirmacion();
            compraCompleta = true;
            
            System.out.print("\n¿Desea realizar otra compra? (s/n): ");
            if (!scanner.nextLine().equalsIgnoreCase("s")) {
                System.out.println("\n¡Gracias por usar nuestro sistema!");
                break;
            }
            carrito.vaciar();
        }
    }
    
    private boolean paso1_VerProductos() {
        System.out.println("┌─ PASO 1: VER PRODUCTOS DISPONIBLES ─┐");
        System.out.println("└─────────────────────────────────────┘\n");
        
        for (Producto p : productosDisponibles) {
            System.out.println("[" + p.getIdProducto() + "] " + p.getNombre() + 
                             " - $" + String.format("%.2f", p.getPrecio()) + 
                             " (Stock: " + p.getCantidad() + ")");
        }
        
        System.out.print("\nPresione ENTER para continuar al paso 2...");
        scanner.nextLine();
        return true;
    }
    
    private boolean paso2_AgregarAlCarrito() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE COMPRAS - TIENDA COSMÉTICOS║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("┌─ PASO 2: AGREGAR PRODUCTOS AL CARRITO ─┐");
        System.out.println("└──────────────────────────────────────┘\n");
        
        boolean agregarMas = true;
        while (agregarMas) {
            System.out.print("Ingrese ID del producto (0 para terminar): ");
            
            try {
                int idProducto = Integer.parseInt(scanner.nextLine());
                
                if (idProducto == 0) {
                    if (carrito.estaVacio()) {
                        System.out.println("✗ Debe agregar al menos un producto");
                        continue;
                    }
                    agregarMas = false;
                    break;
                }
                
                Producto producto = buscarProducto(idProducto);
                if (producto == null) {
                    System.out.println("✗ Producto no encontrado");
                    continue;
                }
                
                System.out.print("Cantidad: ");
                int cantidad = Integer.parseInt(scanner.nextLine());
                
                carrito.agregarProducto(producto, cantidad);
                
                System.out.print("¿Agregar otro producto? (s/n): ");
                if (!scanner.nextLine().equalsIgnoreCase("s")) {
                    agregarMas = false;
                }
            } catch (NumberFormatException e) {
                System.out.println("✗ Ingrese un número válido");
            }
        }
        
        return true;
    }
    
    private boolean paso3_RevisarCarrito() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE COMPRAS - TIENDA COSMÉTICOS║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("┌─ PASO 3: REVISAR CARRITO ─┐");
        System.out.println("└────────────────────────┘\n");
        
        carrito.mostrarCarrito();
        
        System.out.println("Opciones:");
        System.out.println("1 - Continuar con la compra");
        System.out.println("2 - Eliminar un producto");
        System.out.println("3 - Vaciar carrito (CANCELAR COMPRA)");
        System.out.print("\nSeleccione opción: ");
        
        String opcion = scanner.nextLine();
        
        switch(opcion) {
            case "1":
                return true;
            case "2":
                System.out.print("Ingrese ID del producto a eliminar: ");
                try {
                    int id = Integer.parseInt(scanner.nextLine());
                    carrito.eliminarProducto(id);
                    System.out.print("\nPresione ENTER para volver a revisar...");
                    scanner.nextLine();
                    return paso3_RevisarCarrito();
                } catch (NumberFormatException e) {
                    System.out.println("✗ ID inválido");
                    return paso3_RevisarCarrito();
                }
            case "3":
                carrito.vaciar();
                System.out.println("\n✗ Compra cancelada");
                System.out.print("Presione ENTER para volver al inicio...");
                scanner.nextLine();
                return false;
            default:
                System.out.println("✗ Opción no válida");
                return paso3_RevisarCarrito();
        }
    }
    
    private boolean paso4_ProcesarPago() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE COMPRAS - TIENDA COSMÉTICOS║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("┌─ PASO 4: PROCESAR PAGO ─┐");
        System.out.println("└─────────────────────────┘\n");
        
        double total = carrito.calcularTotal();
        System.out.println("Total a pagar: $" + String.format("%.2f", total));
        System.out.println("\nMétodos de pago disponibles:");
        System.out.println("1 - Tarjeta de crédito");
        System.out.println("2 - PayPal");
        System.out.println("3 - Transferencia bancaria");
        System.out.print("\nSeleccione método (1-3): ");
        
        String metodo = scanner.nextLine();
        
        if (!metodo.matches("[1-3]")) {
            System.out.println("✗ Opción no válida");
            System.out.print("Presione ENTER para intentar de nuevo...");
            scanner.nextLine();
            return paso4_ProcesarPago();
        }
        
        if (!procesador.procesarPago(total, metodo)) {
            System.out.println("\n✗ Error al procesar el pago. Intente de nuevo.");
            System.out.print("Presione ENTER para intentar de nuevo...");
            scanner.nextLine();
            return paso4_ProcesarPago();
        }
        
        // Actualizar stock - registrar en inventario si está disponible
        for (Carrito.ItemCarrito item : carrito.getItems()) {
            if (inventario != null) {
                // Registrar venta en el inventario del sistema
                inventario.registrarVenta(item.producto.getIdProducto(), item.cantidad);
            } else {
                // Si no hay inventario del sistema, actualizar localmente
                int nuevoStock = item.producto.getCantidad() - item.cantidad;
                item.producto.setCantidad(nuevoStock);
            }
        }
        
        return true;
    }
    
    private void paso5_Confirmacion() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║   SISTEMA DE COMPRAS - TIENDA COSMÉTICOS║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("┌─ PASO 5: CONFIRMACIÓN ─┐");
        System.out.println("└──────────────────────┘\n");
        
        numeroCompra++;
        LocalDateTime ahora = LocalDateTime.now();
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
        
        System.out.println("✓ COMPRA COMPLETADA EXITOSAMENTE");
        System.out.println("\n═══════════════════════════════════════");
        System.out.println("Número de pedido: #" + String.format("%06d", numeroCompra));
        System.out.println("Fecha y hora: " + ahora.format(formato));
        System.out.println("═══════════════════════════════════════\n");
        
        carrito.mostrarCarrito();
        
        System.out.println("═══════════════════════════════════════");
        System.out.println("✓ Gracias por su compra!");
        System.out.println("═══════════════════════════════════════\n");
    }
    
    private Producto buscarProducto(int id) {
        for (Producto p : productosDisponibles) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }
    
    private void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
}
