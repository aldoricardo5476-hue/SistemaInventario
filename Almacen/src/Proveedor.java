import java.util.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Proveedor {
    private int idProveedor;
    private String nombre;
    private String contacto;
    private String direccion;
    private List<Pedido> pedidosRealizados;
    private float costoPromedio;
    
    public Proveedor(int idProveedor, String nombre, String contacto, String direccion) {
        this.idProveedor = idProveedor;
        this.nombre = nombre;
        this.contacto = contacto;
        this.direccion = direccion;
        this.pedidosRealizados = new ArrayList<>();
        this.costoPromedio = 0;
    }
    
    // Realizar pedido al proveedor
    public void realizarPedido(int idProducto, String nombreProducto, int cantidad, float precioUnitario) {
        float costoPedido = cantidad * precioUnitario;
        Pedido pedido = new Pedido(idProducto, nombreProducto, cantidad, precioUnitario, costoPedido);
        pedidosRealizados.add(pedido);
        
        System.out.println("\n✓ Pedido realizado al proveedor: " + nombre);
        System.out.println("  Producto: " + nombreProducto);
        System.out.println("  Cantidad: " + cantidad + " unidades");
        System.out.println("  Costo total: $" + String.format("%.2f", costoPedido));
        System.out.println("  Fecha: " + LocalDateTime.now().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
    
    // Listar productos disponibles del proveedor
    public void listaProductosDisponibles() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    PRODUCTOS DISPONIBLES - PROVEEDOR   ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        System.out.println("Proveedor: " + nombre);
        System.out.println("Contacto: " + contacto);
        System.out.println("Dirección: " + direccion);
        System.out.println("\nProductos disponibles para surtido:");
        System.out.println("1 - Labial Rojo - $12.00 c/u");
        System.out.println("2 - Máscara de Pestañas - $18.00 c/u");
        System.out.println("3 - Base Maquillaje - $22.00 c/u");
        System.out.println("4 - Sombra de Ojos - $15.00 c/u");
        System.out.println("5 - Crema Hidratante - $26.00 c/u\n");
    }
    
    // Actualizar datos de contacto
    public void actualizarDatosContacto(String nuevoContacto, String nuevaDireccion) {
        this.contacto = nuevoContacto;
        this.direccion = nuevaDireccion;
        System.out.println("✓ Datos de contacto actualizados para el proveedor " + nombre);
    }
    
    // Generar reporte de pedidos
    public void generarReportePedidos() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       REPORTE DE PEDIDOS               ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        System.out.println("Proveedor: " + nombre + "\n");
        
        if (pedidosRealizados.isEmpty()) {
            System.out.println("No hay pedidos registrados");
            return;
        }
        
        System.out.println(String.format("%-20s | %-10s | %-8s | %-12s | %-15s", 
            "Producto", "Cantidad", "P. Unit.", "Costo Total", "Fecha"));
        System.out.println("─".repeat(75));
        
        double totalGastado = 0;
        for (Pedido p : pedidosRealizados) {
            System.out.println(p.toString());
            totalGastado += p.getCostoPedido();
        }
        
        System.out.println("─".repeat(75));
        System.out.println(String.format("TOTAL INVERTIDO EN SURTIDO: $%.2f\n", totalGastado));
    }
    
    // Calcular costo promedio de compra
    public void calcularCostoPromedio() {
        if (pedidosRealizados.isEmpty()) {
            costoPromedio = 0;
            return;
        }
        
        double totalCosto = 0;
        int totalItems = 0;
        
        for (Pedido p : pedidosRealizados) {
            totalCosto += p.getCostoPedido();
            totalItems += p.getCantidad();
        }
        
        costoPromedio = (float) (totalCosto / totalItems);
    }
    
    // Getters
    public int getIdProveedor() { return idProveedor; }
    public String getNombre() { return nombre; }
    public String getContacto() { return contacto; }
    public String getDireccion() { return direccion; }
    public List<Pedido> getPedidosRealizados() { return pedidosRealizados; }
    public float getCostoPromedio() { return costoPromedio; }
}

// Clase para registrar pedidos
class Pedido {
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private float precioUnitario;
    private float costoPedido;
    private LocalDateTime fecha;
    
    public Pedido(int idProducto, String nombreProducto, int cantidad, float precioUnitario, float costoPedido) {
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precioUnitario = precioUnitario;
        this.costoPedido = costoPedido;
        this.fecha = LocalDateTime.now();
    }
    
    public int getIdProducto() { return idProducto; }
    public String getNombreProducto() { return nombreProducto; }
    public int getCantidad() { return cantidad; }
    public float getPrecioUnitario() { return precioUnitario; }
    public float getCostoPedido() { return costoPedido; }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%-20s | %-10d | $%-7.2f | $%-14.2f | %s", 
            nombreProducto.substring(0, Math.min(20, nombreProducto.length())), 
            cantidad, 
            precioUnitario, 
            costoPedido,
            fecha.format(formato));
    }
}
