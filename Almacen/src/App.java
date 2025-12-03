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

