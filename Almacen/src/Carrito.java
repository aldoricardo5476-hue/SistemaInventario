import java.util.*;

public class Carrito {
    private List<ItemCarrito> items;
    
    public Carrito() {
        this.items = new ArrayList<>();
    }
    
    public void agregarProducto(Producto producto, int cantidad) {
        if (cantidad <= 0) {
            System.out.println("Error: La cantidad debe ser mayor a 0");
            return;
        }
        
        if (cantidad > producto.getCantidad()) {
            System.out.println("Error: Stock insuficiente. Disponible: " + producto.getCantidad());
            return;
        }
        
        // Verificar si el producto ya está en el carrito
        for (ItemCarrito item : items) {
            if (item.producto.getIdProducto() == producto.getIdProducto()) {
                item.cantidad += cantidad;
                System.out.println("✓ Cantidad actualizada para " + producto.getNombre());
                return;
            }
        }
        
        // Agregar nuevo item
        items.add(new ItemCarrito(producto, cantidad));
        System.out.println("✓ " + producto.getNombre() + " agregado al carrito");
    }
    
    public void eliminarProducto(int idProducto) {
        items.removeIf(item -> item.producto.getIdProducto() == idProducto);
        System.out.println("✓ Producto eliminado del carrito");
    }
    
    public double calcularTotal() {
        return items.stream().mapToDouble(ItemCarrito::getSubtotal).sum();
    }
    
    public List<ItemCarrito> getItems() {
        return items;
    }
    
    public void mostrarCarrito() {
        if (items.isEmpty()) {
            System.out.println("El carrito está vacío");
            return;
        }
        
        System.out.println("\n=== CARRITO DE COMPRAS ===");
        for (ItemCarrito item : items) {
            System.out.println(item.producto.getNombre() + " x" + item.cantidad + 
                             " = $" + String.format("%.2f", item.getSubtotal()));
        }
        System.out.println("------------------------");
        System.out.println("Total: $" + String.format("%.2f", calcularTotal()));
        System.out.println();
    }
    
    public void vaciar() {
        items.clear();
        System.out.println("✓ Carrito vaciado");
    }
    
    public boolean estaVacio() {
        return items.isEmpty();
    }
    
    // Clase interna para representar un item en el carrito
    public static class ItemCarrito {
        public Producto producto;
        public int cantidad;
        
        public ItemCarrito(Producto producto, int cantidad) {
            this.producto = producto;
            this.cantidad = cantidad;
        }
        
        public double getSubtotal() {
            return producto.getPrecio() * cantidad;
        }
    }
}
