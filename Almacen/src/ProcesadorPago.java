import java.util.Scanner;

public class ProcesadorPago {
    private Scanner scanner;
    
    public ProcesadorPago() {
        this.scanner = new Scanner(System.in);
    }
    
    public boolean procesarPago(double monto, String metodoPago) {
        System.out.println("\n=== PROCESANDO PAGO ===");
        System.out.println("Monto a pagar: $" + String.format("%.2f", monto));
        System.out.println("Método: " + metodoPago);
        
        switch(metodoPago.toLowerCase()) {
            case "1":
            case "tarjeta":
                return procesarTarjeta(monto);
            case "2":
            case "paypal":
                return procesarPayPal(monto);
            case "3":
            case "transferencia":
                return procesarTransferencia(monto);
            default:
                System.out.println("✗ Método de pago no válido");
                return false;
        }
    }
    
    private boolean procesarTarjeta(double monto) {
        System.out.print("Ingrese número de tarjeta (16 dígitos): ");
        String tarjeta = scanner.nextLine();
        
        if (tarjeta.length() != 16 || !tarjeta.matches("\\d+")) {
            System.out.println("✗ Número de tarjeta inválido");
            return false;
        }
        
        System.out.print("Ingrese CVV (3 dígitos): ");
        String cvv = scanner.nextLine();
        
        if (cvv.length() != 3 || !cvv.matches("\\d+")) {
            System.out.println("✗ CVV inválido");
            return false;
        }
        
        System.out.print("Ingrese fecha de vencimiento (MM/YY): ");
        String fecha = scanner.nextLine();
        
        if (!fecha.matches("\\d{2}/\\d{2}")) {
            System.out.println("✗ Fecha de vencimiento inválida");
            return false;
        }
        
        System.out.println("✓ Pago con tarjeta procesado exitosamente");
        return true;
    }
    
    private boolean procesarPayPal(double monto) {
        System.out.print("Ingrese correo de PayPal: ");
        String email = scanner.nextLine();
        
        if (!email.contains("@")) {
            System.out.println("✗ Correo inválido");
            return false;
        }
        
        System.out.print("Ingrese contraseña de PayPal: ");
        String password = scanner.nextLine();
        
        if (password.length() < 6) {
            System.out.println("✗ Contraseña inválida");
            return false;
        }
        
        System.out.println("✓ Pago con PayPal procesado exitosamente");
        return true;
    }
    
    private boolean procesarTransferencia(double monto) {
        System.out.println("Cuenta bancaria: XXXXX1234");
        System.out.println("Banco: Banco Principal");
        System.out.println("Referencia: COMPRA-" + System.currentTimeMillis());
        System.out.println("Ingrese código de confirmación (6 dígitos): ");
        String codigo = scanner.nextLine();
        
        if (codigo.length() != 6 || !codigo.matches("\\d+")) {
            System.out.println("✗ Código de confirmación inválido");
            return false;
        }
        
        System.out.println("✓ Pago por transferencia procesado exitosamente");
        return true;
    }
}
