public class Producto {
    int idProducto;
    String nombre;
    String categoria;
    int cantidad;
    float precio;

    public Producto(int idProducto, String nombre, String categoria, int cantidad, float precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.categoria = categoria;
        this.cantidad = cantidad;
        this.precio = precio;
    }
    
    // Getters
    public int getIdProducto() {
        return idProducto;
    }
    
    public String getNombre() {
        return nombre;
    }
    
    public String getCategoria() {
        return categoria;
    }
    
    public int getCantidad() {
        return cantidad;
    }
    
    public float getPrecio() {
        return precio;
    }
    
    // Setters
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    
    void agregarProducto(int cantidad) {
        this.cantidad += cantidad;
        System.out.println("Se han agregado " + cantidad + " unidades del producto " + nombre);
    }
    void actualizarCantidad(int nuevaCantidad) {
        this.cantidad = nuevaCantidad;
        System.out.println("La cantidad del producto " + nombre + " ha sido actualizada a " + nuevaCantidad);
    }
    void consultarProducto() {
        System.out.println("Producto ID: " + idProducto + ", Nombre: " + nombre + ", Categoria: " + categoria + ", Cantidad: " + cantidad + ", Precio: " + precio);
    }
    void calcularValorTotal() {
        float valorTotal = cantidad * precio;
        System.out.println("El valor total del producto " + nombre + " es: " + valorTotal);
    }
    
}
