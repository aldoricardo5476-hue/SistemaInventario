# Sistema de Gestión de Inventario - Tienda de Cosméticos

## 📋 Descripción General

Sistema integral de gestión de inventario para una tienda de cosméticos que incluye:
- ✅ Sistema de compras para clientes con flujo de 5 pasos
- ✅ Gestión de inventario con actualización automática
- ✅ Control de stock (mínimo y óptimo)
- ✅ Sistema de surtido y reabastecimiento
- ✅ Procesamiento de múltiples métodos de pago
- ✅ Historial de movimientos y reportes

## 🏗️ Estructura del Proyecto

```
Almacen/
├── src/                      # Código fuente Java
│   ├── App.java             # Punto de entrada principal
│   ├── InterfazCompra.java  # Sistema de compras cliente
│   ├── Carrito.java         # Gestión del carrito
│   ├── Producto.java        # Modelo de producto
│   ├── ProcesadorPago.java  # Procesamiento de pagos
│   ├── Inventario.java      # Sistema de inventario
│   ├── SistemaInventario.java # Sistema principal admin
│   ├── Proveedor.java       # Gestión de proveedores
│   ├── Movimiento.java      # Registro de movimientos
│   ├── Ticket.java          # Generación de tickets
│   └── Devolucion.java      # Gestión de devoluciones
├── bin/                      # Archivos compilados
├── lib/                      # Librerías externas
└── README.md                 # Este archivo
```

## 🚀 Cómo Ejecutar

### Compilar
```bash
cd Almacen
javac -d bin src/*.java
```

### Ejecutar
```bash
java -cp bin App
```

O usando el script proporcionado:
```bash
./run.sh
```

## 💻 Características Principales

### 1. **Sistema de Compras (Cliente)**
Flujo de 5 pasos obligatorios que el cliente DEBE completar:

**Paso 1: Ver Productos**
- Visualiza todos los productos disponibles
- Muestra precio y stock disponible

**Paso 2: Agregar al Carrito**
- Agregar múltiples productos
- Validación automática de stock
- Cantidad personalizable

**Paso 3: Revisar Carrito**
- Ver resumen de compra
- Opción de eliminar productos
- Cancelar compra si lo desea

**Paso 4: Procesar Pago**
- 3 métodos de pago disponibles:
  - 💳 Tarjeta de crédito
  - 📱 PayPal
  - 🏦 Transferencia bancaria
- Validación de datos de pago

**Paso 5: Confirmación**
- Número de pedido único
- Fecha y hora de compra
- Resumen de la compra

### 2. **Sistema de Inventario (Administrador)**

#### Funciones Principales:
- **Verificar Inventario**: Vista completa de stock actual
  - Productos en stock óptimo (🟢 ADECUADO)
  - Productos en stock bajo (🟡 BAJO)
  - Productos en stock crítico (🔴 CRÍTICO)
  - Valor total del inventario

- **Registrar Venta**: Actualiza stock automáticamente
  - Valida disponibilidad
  - Genera alarma si stock es bajo
  - Registra en historial

- **Registrar Surtido**: Reabastecimiento de productos
  - Aumenta stock disponible
  - Registra en historial de movimientos

- **Ajuste de Inventario Físico**: Sincronización con almacén real
  - Verifica conteo físico
  - Ajusta diferencias automáticamente
  - Reporta discrepancias

- **Notificación de Reabastecimiento**:
  - Identifica productos con bajo stock
  - Sugiere cantidad a reabastecer

- **Realizar Pedido a Proveedor**:
  - Interfaz con proveedores
  - Registro de pedidos
  - Cálculo de costos

#### Reportes Disponibles:

1. **Reporte de Movimientos**
   - Historial de todas las operaciones
   - Tipo de movimiento (Venta, Surtido, Ajuste)
   - Fecha y cantidad

2. **Reporte de Ventas**
   - Productos vendidos
   - Cantidad y monto total
   - Valor total de ventas

3. **Reporte de Pedidos**
   - Pedidos realizados a proveedores
   - Costo de reabastecimiento
   - Inversión total

### 3. **Gestión de Proveedores**

- **Información del Proveedor**
  - Nombre, contacto, dirección
  - Historial de pedidos

- **Realizar Pedidos**
  - Seleccionar productos
  - Especificar cantidades
  - Registrar costos

- **Reporte de Inversión**
  - Total gastado en surtido
  - Costo promedio por unidad

## 🏭 Niveles de Stock

El sistema monitorea automáticamente 3 niveles:

| Estado | Cantidad | Indicador |
|--------|----------|-----------|
| ADECUADO | > 30 unidades | 🟢 |
| BAJO | 11-30 unidades | 🟡 |
| CRÍTICO | ≤ 10 unidades | 🔴 |

Cuando stock llega a CRÍTICO, genera alerta automática.

## 💳 Métodos de Pago Soportados

### Tarjeta de Crédito
- Validación de 16 dígitos
- CVV de 3 dígitos
- Fecha de vencimiento (MM/YY)

### PayPal
- Email válido
- Contraseña (mínimo 6 caracteres)

### Transferencia Bancaria
- Código de confirmación de 6 dígitos
- Referencia automática del pedido

## 📊 Seguimiento de Inventario

### Tipos de Movimientos Registrados:

```
INGRESO        → Nuevo producto agregado
VENTA          → Producto vendido
SURTIDO        → Reabastecimiento
AJUSTE_POSITIVO → Corrección adicionales encontradas
AJUSTE_NEGATIVO → Corrección faltantes encontradas
```

## 🔄 Flujo de Datos

```
Cliente Realiza Compra
    ↓
InterfazCompra - 5 Pasos
    ↓
Procesar Pago (ProcesadorPago)
    ↓
Registrar Venta (Inventario)
    ↓
Actualizar Stock
    ↓
Generar Movimiento
    ↓
Verificar Stock Bajo → Generar Alerta
```

## 🛡️ Validaciones Implementadas

✅ Stock suficiente antes de venta
✅ No se puede saltar pasos en compra
✅ Validación de datos de pago
✅ Cantidad > 0 siempre
✅ Inventario físico vs sistema
✅ Alarma automática stock bajo
✅ Historial completo de movimientos

## 📈 Reportes Disponibles

1. **Inventario Actual** - Estado en tiempo real
2. **Movimientos** - Historial completo con fechas
3. **Ventas** - Análisis de productos vendidos
4. **Proveedores** - Historial de pedidos y costos
5. **Stock** - Alerta de productos bajos

## 💡 Casos de Uso

### Cliente
1. Navega productos disponibles
2. Agrega items al carrito
3. Revisa compra
4. Selecciona método de pago
5. Confirma compra
6. Recibe número de pedido

### Administrador
1. Verifica inventario actual
2. Registra ventas realizadas (si no vienen del sistema)
3. Realiza reabastecimiento
4. Ajusta por inventario físico
5. Genera reportes
6. Realiza pedidos a proveedores
7. Monitorea alertas de stock bajo

## 🔧 Tecnologías Utilizadas

- **Lenguaje**: Java
- **Compilador**: javac
- **Versión Java**: 8+
- **Estructuras de datos**: ArrayList, HashMap
- **Manejo de tiempo**: LocalDateTime

## 📝 Clases Principales

| Clase | Responsabilidad |
|-------|-----------------|
| `App` | Punto de entrada y menú principal |
| `InterfazCompra` | Flujo de compra cliente |
| `Carrito` | Gestión de items en compra |
| `Producto` | Modelo de producto |
| `Inventario` | Lógica de stock e inventario |
| `SistemaInventario` | Gestión completa del sistema |
| `ProcesadorPago` | Validación de pagos |
| `Proveedor` | Gestión de proveedores |
| `Movimiento` | Registro de transacciones |

## 🎯 Mejoras Futuras

- [ ] Persistencia en base de datos
- [ ] Interfaz gráfica (GUI)
- [ ] Integración con pasarela de pago real
- [ ] Sistema de usuarios con roles
- [ ] Devoluciones y cambios
- [ ] Multi-sucursal
- [ ] Integración con APIs de proveedores

## 👤 Autor

Sistema desarrollado para la tienda de cosméticos.

## 📄 Licencia

Uso interno - Sistema propietario
