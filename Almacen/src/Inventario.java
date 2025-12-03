import java.util.*;

public class Inventario {
    private List<Producto> productosInventario;
    private List<Movimiento> movimientos;
    private float valorTotalInventario;
    private final int STOCK_MINIMO = 10;
    private final int STOCK_OPTIMO = 30;
    
    public Inventario() {
        this.productosInventario = new ArrayList<>();
        this.movimientos = new ArrayList<>();
        this.valorTotalInventario = 0;
    }
    
    // Agregar producto al inventario
    public void agregarProducto(Producto producto) {
        productosInventario.add(producto);
        registrarMovimiento(producto.getIdProducto(), "INGRESO", producto.getCantidad(), producto.getNombre());
        actualizarValorTotal();
    }
    
    // Registrar venta (disminuir stock)
    public boolean registrarVenta(int idProducto, int cantidad) {
        Producto producto = buscarProducto(idProducto);
        if (producto == null) {
            return false;
        }
        
        if (producto.getCantidad() < cantidad) {
            System.out.println("✗ Stock insuficiente para: " + producto.getNombre());
            return false;
        }
        
        producto.setCantidad(producto.getCantidad() - cantidad);
        registrarMovimiento(idProducto, "VENTA", cantidad, producto.getNombre());
        actualizarValorTotal();
        
        // Verificar si llegó a stock mínimo
        verificarStockBajo(producto);
        
        return true;
    }
    
    // Registrar surtido (aumento de stock)
    public boolean registrarSurtido(int idProducto, int cantidad) {
        Producto producto = buscarProducto(idProducto);
        if (producto == null) {
            return false;
        }
        
        producto.setCantidad(producto.getCantidad() + cantidad);
        registrarMovimiento(idProducto, "SURTIDO", cantidad, producto.getNombre());
        actualizarValorTotal();
        System.out.println("✓ Surtido registrado para: " + producto.getNombre());
        
        return true;
    }
    
    // Registrar ajuste de inventario físico
    public boolean registrarAjuste(int idProducto, int cantidadReal) {
        Producto producto = buscarProducto(idProducto);
        if (producto == null) {
            return false;
        }
        
        int diferencia = cantidadReal - producto.getCantidad();
        String tipo = diferencia > 0 ? "AJUSTE_POSITIVO" : "AJUSTE_NEGATIVO";
        
        producto.setCantidad(cantidadReal);
        registrarMovimiento(idProducto, tipo, Math.abs(diferencia), producto.getNombre());
        actualizarValorTotal();
        
        if (diferencia > 0) {
            System.out.println("✓ Se agregaron " + diferencia + " unidades de " + producto.getNombre());
        } else if (diferencia < 0) {
            System.out.println("✓ Se removieron " + Math.abs(diferencia) + " unidades de " + producto.getNombre());
        }
        
        return true;
    }
    
    // Verificar inventario y generar reporte
    public void verificarInventario() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║        VERIFICACIÓN DE INVENTARIO      ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        if (productosInventario.isEmpty()) {
            System.out.println("No hay productos en el inventario");
            return;
        }
        
        System.out.println(String.format("%-3s | %-20s | %-8s | %-8s | %-10s | %-10s", 
            "ID", "Producto", "Stock", "Mínimo", "Óptimo", "Estado"));
        System.out.println("─".repeat(75));
        
        for (Producto p : productosInventario) {
            String estado = obtenerEstadoStock(p);
            System.out.println(String.format("%-3d | %-20s | %-8d | %-8d | %-10d | %s", 
                p.getIdProducto(), 
                p.getNombre().substring(0, Math.min(20, p.getNombre().length())), 
                p.getCantidad(), 
                STOCK_MINIMO, 
                STOCK_OPTIMO,
                estado));
        }
        
        System.out.println("─".repeat(75));
        System.out.println(String.format("Valor total del inventario: $%.2f\n", valorTotalInventario));
    }
    
    // Verificar stock bajo y generar alarma
    private void verificarStockBajo(Producto producto) {
        if (producto.getCantidad() <= STOCK_MINIMO) {
            generarAlarmaStockBajo(producto);
        }
    }
    
    // Generar alarma de stock bajo
    private void generarAlarmaStockBajo(Producto producto) {
        System.out.println("\n⚠️  ALERTA DE STOCK BAJO");
        System.out.println("Producto: " + producto.getNombre());
        System.out.println("Stock actual: " + producto.getCantidad());
        System.out.println("Reabastecimiento necesario\n");
    }
    
    // Obtener estado del stock
    private String obtenerEstadoStock(Producto producto) {
        if (producto.getCantidad() <= STOCK_MINIMO) {
            return "🔴 CRÍTICO";
        } else if (producto.getCantidad() <= STOCK_OPTIMO) {
            return "🟡 BAJO";
        } else {
            return "🟢 ADECUADO";
        }
    }
    
    // Registrar movimiento en historial
    private void registrarMovimiento(int idProducto, String tipo, int cantidad, String nombreProducto) {
        movimientos.add(new Movimiento(idProducto, tipo, cantidad, nombreProducto));
    }
    
    // Actualizar valor total del inventario
    private void actualizarValorTotal() {
        valorTotalInventario = (float) productosInventario.stream()
            .mapToDouble(p -> p.getCantidad() * p.getPrecio())
            .sum();
    }
    
    // Generar reporte de movimientos
    public void generarReporteMovimientos() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║       REPORTE DE MOVIMIENTOS          ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        if (movimientos.isEmpty()) {
            System.out.println("No hay movimientos registrados");
            return;
        }
        
        System.out.println(String.format("%-20s | %-20s | %-15s | %-8s | %-20s", 
            "Fecha/Hora", "Producto", "Tipo", "Cantidad", ""));
        System.out.println("─".repeat(90));
        
        for (Movimiento m : movimientos) {
            System.out.println(m.toString());
        }
        
        System.out.println("─".repeat(90));
    }
    
    // Generar reporte de ventas
    public void generarReporteVentas() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║         REPORTE DE VENTAS             ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        Map<Integer, Integer> ventasPorProducto = new HashMap<>();
        double totalVentas = 0;
        
        for (Movimiento m : movimientos) {
            if (m.getTipo().equals("VENTA")) {
                ventasPorProducto.put(m.getIdProducto(), 
                    ventasPorProducto.getOrDefault(m.getIdProducto(), 0) + m.getCantidad());
            }
        }
        
        if (ventasPorProducto.isEmpty()) {
            System.out.println("No hay ventas registradas");
            return;
        }
        
        System.out.println(String.format("%-20s | %-10s | %-15s | %-15s", 
            "Producto", "Cantidad", "Precio Unit.", "Total"));
        System.out.println("─".repeat(65));
        
        for (Map.Entry<Integer, Integer> entry : ventasPorProducto.entrySet()) {
            Producto p = buscarProducto(entry.getKey());
            if (p != null) {
                double total = entry.getValue() * p.getPrecio();
                totalVentas += total;
                System.out.println(String.format("%-20s | %-10d | $%-14.2f | $%.2f", 
                    p.getNombre().substring(0, Math.min(20, p.getNombre().length())), 
                    entry.getValue(), 
                    p.getPrecio(), 
                    total));
            }
        }
        
        System.out.println("─".repeat(65));
        System.out.println(String.format("TOTAL VENTAS: $%.2f\n", totalVentas));
    }
    
    // Notificar reabastecimiento necesario
    public void notificarReabastecimiento() {
        System.out.println("\n╔════════════════════════════════════════╗");
        System.out.println("║    NOTIFICACIÓN DE REABASTECIMIENTO    ║");
        System.out.println("╚════════════════════════════════════════╝\n");
        
        List<Producto> productosBajos = new ArrayList<>();
        
        for (Producto p : productosInventario) {
            if (p.getCantidad() <= STOCK_MINIMO) {
                productosBajos.add(p);
            }
        }
        
        if (productosBajos.isEmpty()) {
            System.out.println("✓ Todos los productos tienen stock adecuado");
        } else {
            System.out.println("Productos que requieren reabastecimiento:\n");
            for (Producto p : productosBajos) {
                int cantidadFaltante = STOCK_OPTIMO - p.getCantidad();
                System.out.println("  • " + p.getNombre() + 
                                 " - Stock actual: " + p.getCantidad() + 
                                 " - Sugerido: " + cantidadFaltante + " unidades");
            }
        }
        System.out.println();
    }
    
    // Búsqueda de producto
    private Producto buscarProducto(int id) {
        for (Producto p : productosInventario) {
            if (p.getIdProducto() == id) {
                return p;
            }
        }
        return null;
    }
    
    // Getters
    public List<Producto> getProductosInventario() {
        return productosInventario;
    }
    
    public float getValorTotalInventario() {
        return valorTotalInventario;
    }
    
    public List<Movimiento> getMovimientos() {
        return movimientos;
    }
    
    public int getStockMinimo() {
        return STOCK_MINIMO;
    }
    
    public int getStockOptimo() {
        return STOCK_OPTIMO;
    }
}
