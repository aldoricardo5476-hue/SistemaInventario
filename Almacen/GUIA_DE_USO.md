# GUÍA DE USO - SISTEMA DE INVENTARIO

## 🎮 ACCESO AL SISTEMA

Al ejecutar el programa, verá el menú principal:

```
╔════════════════════════════════════════╗
║   SISTEMA TIENDA DE COSMÉTICOS         ║
╚════════════════════════════════════════╝

¿Qué desea hacer?

1 - Acceder como CLIENTE (Realizar compras)
2 - Acceder como ADMINISTRADOR (Gestionar inventario)
3 - Salir
```

---

## 👥 MODO CLIENTE - PROCESO DE COMPRA

### Paso 1: Ver Productos Disponibles

```
┌─ PASO 1: VER PRODUCTOS DISPONIBLES ─┐
└─────────────────────────────────────┘

[1] Labial Rojo - $15.99 (Stock: 50)
[2] Máscara de Pestañas - $22.50 (Stock: 30)
[3] Base Maquillaje - $28.00 (Stock: 25)
[4] Sombra de Ojos - $18.50 (Stock: 40)
[5] Crema Hidratante - $32.00 (Stock: 35)
```

**Acción**: Presione ENTER para continuar

---

### Paso 2: Agregar Productos al Carrito

```
┌─ PASO 2: AGREGAR PRODUCTOS AL CARRITO ─┐
└──────────────────────────────────────┘

Ingrese ID del producto (0 para terminar): 4
Cantidad: 2
✓ Sombra de Ojos agregado al carrito

¿Agregar otro producto? (s/n): s
Ingrese ID del producto (0 para terminar): 1
Cantidad: 3
✓ Labial Rojo agregado al carrito

¿Agregar otro producto? (s/n): n
```

**Validaciones**:
- ✓ No permite cantidad 0 o negativa
- ✓ Verifica que haya stock disponible
- ✓ Permite múltiples productos

---

### Paso 3: Revisar Carrito

```
┌─ PASO 3: REVISAR CARRITO ─┐
└────────────────────────┘

=== CARRITO DE COMPRAS ===
Sombra de Ojos x2 = $37.00
Labial Rojo x3 = $47.97
------------------------
Total: $84.97

Opciones:
1 - Continuar con la compra
2 - Eliminar un producto
3 - Vaciar carrito (CANCELAR COMPRA)

Seleccione opción: 1
```

**Opciones Disponibles**:
- ✓ Ver resumen con totales
- ✓ Eliminar productos individuales
- ✓ Cancelar compra completamente

---

### Paso 4: Procesar Pago

```
┌─ PASO 4: PROCESAR PAGO ─┐
└─────────────────────────┘

Total a pagar: $84.97

Métodos de pago disponibles:
1 - Tarjeta de crédito
2 - PayPal
3 - Transferencia bancaria

Seleccione método (1-3): 1
```

#### Opción 1: Tarjeta de Crédito
```
Ingrese número de tarjeta (16 dígitos): 1234567890123456
Ingrese CVV (3 dígitos): 123
Ingrese fecha de vencimiento (MM/YY): 12/25
✓ Pago con tarjeta procesado exitosamente
```

#### Opción 2: PayPal
```
Ingrese correo de PayPal: cliente@email.com
Ingrese contraseña de PayPal: Contraseña123
✓ Pago con PayPal procesado exitosamente
```

#### Opción 3: Transferencia Bancaria
```
Cuenta bancaria: XXXXX1234
Banco: Banco Principal
Referencia: COMPRA-1701614112345
Ingrese código de confirmación (6 dígitos): 123456
✓ Pago por transferencia procesado exitosamente
```

---

### Paso 5: Confirmación

```
┌─ PASO 5: CONFIRMACIÓN ─┐
└──────────────────────┘

✓ COMPRA COMPLETADA EXITOSAMENTE

═══════════════════════════════════════
Número de pedido: #000001
Fecha y hora: 02/12/2025 19:16:52
═══════════════════════════════════════

=== CARRITO DE COMPRAS ===
Sombra de Ojos x2 = $37.00
Labial Rojo x3 = $47.97
------------------------
Total: $84.97

═══════════════════════════════════════
✓ Gracias por su compra!
═══════════════════════════════════════

¿Desea realizar otra compra? (s/n): n
```

---

## 🔧 MODO ADMINISTRADOR - GESTIÓN DE INVENTARIO

### Menú Principal

```
╔════════════════════════════════════════╗
║  SISTEMA DE GESTIÓN DE INVENTARIO      ║
╚════════════════════════════════════════╝

MENÚ PRINCIPAL
1 - Verificar inventario actual
2 - Registrar venta
3 - Registrar surtido (reabastecimiento)
4 - Ajuste de inventario físico
5 - Notificación de reabastecimiento
6 - Realizar pedido a proveedor
7 - Ver reporte de movimientos
8 - Ver reporte de ventas
9 - Ver reporte de pedidos
0 - Salir
```

---

### 1. Verificar Inventario Actual

```
╔════════════════════════════════════════╗
║        VERIFICACIÓN DE INVENTARIO      ║
╚════════════════════════════════════════╝

ID  | Producto             | Stock  | Mínimo | Óptimo | Estado
────┼──────────────────────┼────────┼────────┼────────┼──────────
1   | Labial Rojo          | 50     | 10     | 30     | 🟢 ADECUADO
2   | Máscara de Pestañas  | 30     | 10     | 30     | 🟡 BAJO
3   | Base Maquillaje      | 25     | 10     | 30     | 🟡 BAJO
4   | Sombra de Ojos       | 20     | 10     | 30     | 🟡 BAJO
5   | Crema Hidratante     | 35     | 10     | 30     | 🟢 ADECUADO
────┴──────────────────────┴────────┴────────┴────────┴──────────
Valor total del inventario: $3664.50
```

**Indicadores**:
- 🟢 ADECUADO: Stock > 30
- 🟡 BAJO: Stock 11-30
- 🔴 CRÍTICO: Stock ≤ 10

---

### 2. Registrar Venta

```
╔════════════════════════════════════════╗
║        REGISTRAR VENTA                 ║
╚════════════════════════════════════════╝

[Muestra inventario actual]

Ingrese ID del producto a vender: 4
Ingrese cantidad vendida: 5
✓ Venta registrada exitosamente

⚠️  ALERTA DE STOCK BAJO
Producto: Sombra de Ojos
Stock actual: 15
Reabastecimiento necesario
```

**Validaciones**:
- ✓ Verifica que producto exista
- ✓ Valida que hay stock suficiente
- ✓ Genera alerta si cae a crítico
- ✓ Actualiza automáticamente

---

### 3. Registrar Surtido

```
╔════════════════════════════════════════╗
║     REGISTRAR SURTIDO (REABASTECIMIENTO)║
╚════════════════════════════════════════╝

[Muestra proveedores disponibles]

Ingrese ID del producto a surtir: 4
Ingrese cantidad a surtir: 20
✓ Surtido registrado para: Sombra de Ojos

✓ Surtido registrado y stock actualizado
```

**Resultado**:
- Stock se incrementa automáticamente
- Se registra en historial de movimientos
- Se actualiza valor total del inventario

---

### 4. Ajuste de Inventario Físico

```
╔════════════════════════════════════════╗
║    AJUSTE DE INVENTARIO FÍSICO         ║
╚════════════════════════════════════════╝

⚠️  VERIFICACIÓN DE INVENTARIO FÍSICO

Ingrese los datos de verificación física:

[Muestra inventario actual]

Ingrese ID del producto a ajustar: 2
Ingrese cantidad real encontrada en almacén: 28

✓ Ajuste de inventario completado
✓ Se removieron 2 unidades de Máscara de Pestañas
```

**Casos**:
- Si cuenta real > sistema: AJUSTE_POSITIVO
- Si cuenta real < sistema: AJUSTE_NEGATIVO
- Si son iguales: Sin cambios

---

### 5. Notificación de Reabastecimiento

```
╔════════════════════════════════════════╗
║    NOTIFICACIÓN DE REABASTECIMIENTO    ║
╚════════════════════════════════════════╝

Productos que requieren reabastecimiento:

  • Máscara de Pestañas - Stock actual: 8 - Sugerido: 22 unidades
  • Base Maquillaje - Stock actual: 15 - Sugerido: 15 unidades
  • Sombra de Ojos - Stock actual: 10 - Sugerido: 20 unidades
```

---

### 6. Realizar Pedido a Proveedor

```
╔════════════════════════════════════════╗
║      REALIZAR PEDIDO A PROVEEDOR       ║
╚════════════════════════════════════════╝

Proveedor: Distribuidora Cosméticos Premium
Contacto: contacto@cosmeticos.com

Productos disponibles para surtido:
1 - Labial Rojo - $12.00 c/u
2 - Máscara de Pestañas - $18.00 c/u
3 - Base Maquillaje - $22.00 c/u
4 - Sombra de Ojos - $15.00 c/u
5 - Crema Hidratante - $26.00 c/u

Ingrese ID del producto a pedir: 2
Ingrese cantidad a solicitar: 50

✓ Pedido realizado al proveedor: Distribuidora Cosméticos Premium
  Producto: Máscara de Pestañas
  Cantidad: 50 unidades
  Costo total: $720.00
  Fecha: 02/12/2025 19:18
```

---

### 7. Reporte de Movimientos

```
╔════════════════════════════════════════╗
║       REPORTE DE MOVIMIENTOS          ║
╚════════════════════════════════════════╝

Fecha/Hora           | Producto       | Tipo    | Cantidad
─────────────────────┼────────────────┼─────────┼──────────
02/12/2025 19:12    | Sombra de Ojos | VENTA   | 20
02/12/2025 19:14    | Sombra de Ojos | SURTIDO | 20
02/12/2025 19:16    | Labial Rojo    | VENTA   | 5
```

---

### 8. Reporte de Ventas

```
╔════════════════════════════════════════╗
║         REPORTE DE VENTAS             ║
╚════════════════════════════════════════╝

Producto             | Cantidad | Precio Unit. | Total
─────────────────────┼──────────┼──────────────┼──────────
Sombra de Ojos       | 20       | $18.50       | $370.00
Labial Rojo          | 5        | $15.99       | $79.95
Máscara de Pestañas  | 3        | $22.50       | $67.50
─────────────────────┴──────────┴──────────────┴──────────
TOTAL VENTAS: $517.45
```

---

### 9. Reporte de Pedidos a Proveedores

```
╔════════════════════════════════════════╗
║       REPORTE DE PEDIDOS               ║
╚════════════════════════════════════════╝

Proveedor: Distribuidora Cosméticos Premium

Producto             | Cantidad | P. Unit. | Costo Total | Fecha
─────────────────────┼──────────┼──────────┼─────────────┼─────────────
Sombra de Ojos       | 20       | $14.80   | $296.00     | 02/12/2025
Máscara de Pestañas  | 50       | $14.40   | $720.00     | 02/12/2025
─────────────────────┴──────────┴──────────┴─────────────┴─────────────
TOTAL INVERTIDO EN SURTIDO: $1,016.00
```

---

## 💡 CASOS DE USO COMPLETOS

### Caso 1: Cliente Compra y Stock Se Actualiza

1. **Cliente compra**: 5 unidades de Labial Rojo
2. **Sistema registra**: VENTA en inventario
3. **Stock se actualiza**: 50 → 45
4. **Aparece en reportes**: Venta registrada con valor
5. **Administrador verifica**: Ve la venta en "Reporte de Ventas"

### Caso 2: Detectar Stock Bajo y Reabastecer

1. **Cliente compra mucho**: Stock baja a 8 unidades
2. **Sistema alerta**: 🔴 CRÍTICO
3. **Admin ve notificación**: Necesita reabastecerse
4. **Admin realiza pedido**: 50 unidades al proveedor
5. **Se registra gasto**: Aparece en "Reporte de Pedidos"
6. **Admin registra surtido**: Stock sube a 58
7. **Sistema recalcula**: Vuelve a 🟢 ADECUADO

### Caso 3: Ajuste por Inventario Físico

1. **Sistema muestra**: 30 unidades de Máscara
2. **Admin cuenta físicamente**: Solo hay 28 unidades
3. **Admin registra ajuste**: Cantidad real = 28
4. **Sistema ajusta**: 30 → 28
5. **Se registra**: AJUSTE_NEGATIVO de 2 unidades
6. **Historial actualizado**: Queda registro de la discrepancia

---

## ⚠️ VALIDACIONES Y ALERTAS

| Validación | Acción |
|------------|--------|
| Stock < 1 | No permite vender |
| Stock = CRÍTICO | Genera alerta ⚠️ |
| Cantidad 0 | Rechaza |
| Tarjeta < 16 dígitos | Rechaza pago |
| CVV inválido | Rechaza pago |
| Email sin @ | Rechaza PayPal |
| Código < 6 dígitos | Rechaza transferencia |

---

## 📊 DATOS CALCULADOS AUTOMÁTICAMENTE

- **Valor Total Inventario**: Suma de (cantidad × precio) por producto
- **Total Ventas**: Suma de todas las ventas del día
- **Total Invertido**: Suma de todos los pedidos a proveedores
- **Stock Crítico**: Automáticamente cuando ≤ 10 unidades
- **Sugerencia Reabastecimiento**: Stock óptimo - stock actual

---

**Versión**: 1.0
**Última actualización**: 2 de diciembre de 2025
