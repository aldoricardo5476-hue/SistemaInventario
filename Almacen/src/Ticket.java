public class Ticket {
    int idTicket;
    String tipo;
    String descripcion;
    String estado;
    String fechaCreacion;

    public Ticket(int idTicket, String tipo, String descripcion, String estado, String fechaCreacion) {
        this.idTicket = idTicket;
        this.tipo = tipo;
        this.descripcion = descripcion;
        this.estado = estado;
        this.fechaCreacion = fechaCreacion;
    }
    void crearTicket() {
        System.out.println("Ticket creado con ID: " + idTicket);
    }
    void cerrarTicket() {
        System.out.println("Ticket cerrado con ID: " + idTicket);
    }
    void consultarTicket() {
        System.out.println("Consultando Ticket con ID: " + idTicket);
    }
    void asignarPrioridad(String prioridad) {
        System.out.println("Prioridad " + prioridad + " asignada al Ticket con ID: " + idTicket);
    }

}
