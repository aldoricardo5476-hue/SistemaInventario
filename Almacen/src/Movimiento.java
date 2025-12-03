import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Movimiento {
    private int idProducto;
    private String tipo;
    private int cantidad;
    private String nombreProducto;
    private LocalDateTime fecha;
    
    public Movimiento(int idProducto, String tipo, int cantidad, String nombreProducto) {
        this.idProducto = idProducto;
        this.tipo = tipo;
        this.cantidad = cantidad;
        this.nombreProducto = nombreProducto;
        this.fecha = LocalDateTime.now();
    }
    
    public int getIdProducto() { 
        return idProducto; 
    }
    
    public String getTipo() { 
        return tipo; 
    }
    
    public int getCantidad() { 
        return cantidad; 
    }
    
    public String getNombreProducto() {
        return nombreProducto;
    }
    
    public LocalDateTime getFecha() {
        return fecha;
    }
    
    public void registrarEntrada() {
        System.out.println("✓ Entrada registrada - Producto: " + nombreProducto + " - Cantidad: " + cantidad);
    }
    
    public void registrarSalida() {
        System.out.println("✓ Salida registrada - Producto: " + nombreProducto + " - Cantidad: " + cantidad);
    }
    
    public void generarHistorial() {
        System.out.println("Movimiento ID: " + idProducto + " - Tipo: " + tipo + 
                         " - Producto: " + nombreProducto + " - Cantidad: " + cantidad +
                         " - Fecha: " + fecha.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
    }
    
    @Override
    public String toString() {
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        return String.format("%-20s | %-20s | %-15s | %-8d | %s", 
            fecha.format(formato), 
            nombreProducto.substring(0, Math.min(20, nombreProducto.length())), 
            tipo, 
            cantidad,
            "");
    }
}
