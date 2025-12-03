public class Devolucion {
    int idDevolucion;    
    String tipo;
    String descripcion;
    String estado;
    String fechaDevolucion;

    public Devolucion(int idDevolucion, String tipo, String descripcion, String estado, String fechaDevolucion) {
        this.idDevolucion = idDevolucion;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaDevolucion = fechaDevolucion;
    }
    void registrarDevolucion() {
        System.out.println("Devolución registrada con ID: " + idDevolucion);
    }
    void procesarDevolucion() {
        System.out.println("Devolución procesada con ID: " + idDevolucion);
    }
    void consultarDevolucion() {
        System.out.println("Consultando Devolución con ID: " + idDevolucion);
    }
    void actualizarinventario() {
        System.out.println("Inventario actualizado por Devolución con ID: " + idDevolucion);
    }
    
}
