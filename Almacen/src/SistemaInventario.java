import java.util.Scanner;

public class SistemaInventario {
    private int id;    
    private String nombre;
    private String descripcion;
    private Inventario inventario;
    private Proveedor proveedor;
    private Scanner scanner;
    
    public SistemaInventario(int id, String nombre, String descripcion) {
        this.id = id;
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.inventario = new Inventario();
        this.proveedor = new Proveedor(1, "Distribuidora Cosméticos Premium", 
                                       "contacto@cosmeticos.com", 
                                       "Calle Principal 123");
        this.scanner = new Scanner(System.in);
        inicializarInventario();
    }
    
    private void inicializarInventario() {
        // Agregar productos iniciales al inventario
        inventario.agregarProducto(new Producto(1, "Labial Rojo", "Labios", 50, 15.99f));
        inventario.agregarProducto(new Producto(2, "Máscara de Pestañas", "Ojos", 30, 22.50f));
        inventario.agregarProducto(new Producto(3, "Base Maquillaje", "Rostro", 25, 28.00f));
        inventario.agregarProducto(new Producto(4, "Sombra de Ojos", "Ojos", 40, 18.50f));
        inventario.agregarProducto(new Producto(5, "Crema Hidratante", "Cuidado", 35, 32.00f));
    }
    
    public void iniciarSistema() {
        System.out.println("\n✓ Sistema de Inventario Iniciado: " + nombre);
        System.out.println("Descripción: " + descripcion);
    }
    
    public void mostrarMenuPrincipal() {
        boolean salir = false;
        
        while (!salir) {
            limpiarPantalla();
            System.out.println("╔════════════════════════════════════════╗");
            System.out.println("║  SISTEMA DE GESTIÓN DE INVENTARIO      ║");
            System.out.println("╚════════════════════════════════════════╝\n");
            
            System.out.println("MENÚ PRINCIPAL");
            System.out.println("1 - Verificar inventario actual");
            System.out.println("2 - Registrar venta");
            System.out.println("3 - Registrar surtido (reabastecimiento)");
            System.out.println("4 - Ajuste de inventario físico");
            System.out.println("5 - Notificación de reabastecimiento");
            System.out.println("6 - Realizar pedido a proveedor");
            System.out.println("7 - Ver reporte de movimientos");
            System.out.println("8 - Ver reporte de ventas");
            System.out.println("9 - Ver reporte de pedidos");
            System.out.println("0 - Salir\n");
            
            System.out.print("Seleccione opción: ");
            String opcion = scanner.nextLine();
            
            switch(opcion) {
                case "1":
                    inventario.verificarInventario();
                    break;
                case "2":
                    registrarVenta();
                    break;
                case "3":
                    registrarSurtido();
                    break;
                case "4":
                    ajusteInventarioFisico();
                    break;
                case "5":
                    inventario.notificarReabastecimiento();
                    break;
                case "6":
                    realizarPedidoProveedor();
                    break;
                case "7":
                    inventario.generarReporteMovimientos();
                    break;
                case "8":
                    inventario.generarReporteVentas();
                    break;
                case "9":
                    proveedor.generarReportePedidos();
                    break;
                case "0":
                    salir = true;
                    cerrarSistema();
                    break;
                default:
                    System.out.println("✗ Opción no válida");
            }
            
            if (!salir) {
                System.out.print("\nPresione ENTER para continuar...");
                scanner.nextLine();
            }
        }
    }
    
    private void registrarVenta() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║        REGISTRAR VENTA                 ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        inventario.verificarInventario();
        
        System.out.print("Ingrese ID del producto a vender: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese cantidad vendida: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            if (inventario.registrarVenta(id, cantidad)) {
                System.out.println("✓ Venta registrada exitosamente");
            } else {
                System.out.println("✗ Error al registrar la venta");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Ingrese valores válidos");
        }
    }
    
    private void registrarSurtido() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║     REGISTRAR SURTIDO (REABASTECIMIENTO)║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        proveedor.listaProductosDisponibles();
        
        System.out.print("Ingrese ID del producto a surtir: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese cantidad a surtir: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            if (inventario.registrarSurtido(id, cantidad)) {
                System.out.println("\n✓ Surtido registrado y stock actualizado");
            } else {
                System.out.println("\n✗ Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Ingrese valores válidos");
        }
    }
    
    private void ajusteInventarioFisico() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║    AJUSTE DE INVENTARIO FÍSICO         ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        System.out.println("⚠️  VERIFICACIÓN DE INVENTARIO FÍSICO\n");
        System.out.println("Ingrese los datos de verificación física:\n");
        
        inventario.verificarInventario();
        
        System.out.print("Ingrese ID del producto a ajustar: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese cantidad real encontrada en almacén: ");
            int cantidadReal = Integer.parseInt(scanner.nextLine());
            
            if (inventario.registrarAjuste(id, cantidadReal)) {
                System.out.println("\n✓ Ajuste de inventario completado");
            } else {
                System.out.println("\n✗ Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Ingrese valores válidos");
        }
    }
    
    private void realizarPedidoProveedor() {
        limpiarPantalla();
        System.out.println("╔════════════════════════════════════════╗");
        System.out.println("║      REALIZAR PEDIDO A PROVEEDOR       ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        proveedor.listaProductosDisponibles();
        
        System.out.print("Ingrese ID del producto a pedir: ");
        try {
            int id = Integer.parseInt(scanner.nextLine());
            System.out.print("Ingrese cantidad a solicitar: ");
            int cantidad = Integer.parseInt(scanner.nextLine());
            
            // Obtener producto para nombre y precio
            Producto producto = null;
            for (Producto p : inventario.getProductosInventario()) {
                if (p.getIdProducto() == id) {
                    producto = p;
                    break;
                }
            }
            
            if (producto != null) {
                float precioCompra = producto.getPrecio() * 0.8f; // 20% de descuento mayorista
                proveedor.realizarPedido(id, producto.getNombre(), cantidad, precioCompra);
            } else {
                System.out.println("✗ Producto no encontrado");
            }
        } catch (NumberFormatException e) {
            System.out.println("✗ Ingrese valores válidos");
        }
    }
    
    public void generarReporte() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║     REPORTE GENERAL DE INVENTARIO      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        inventario.verificarInventario();
        inventario.generarReporteMovimientos();
        inventario.generarReporteVentas();
        proveedor.generarReportePedidos();
    }
    
    public void cerrarSistema() {
        System.out.println("\n✓ Cerrando Sistema de Inventario");
        System.out.println("¡Hasta luego!\n");
        System.exit(0);
    }
    
    private void limpiarPantalla() {
        for (int i = 0; i < 50; i++) {
            System.out.println();
        }
    }
    
    // Getters
    public Inventario getInventario() { return inventario; }
    public Proveedor getProveedor() { return proveedor; }
}

    
