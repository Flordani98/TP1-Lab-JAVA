import Tools.ColorConsola;
import models.*;
import Enums.TipoAplicacion;
import Enums.TipoEnvase;

import javax.swing.*;
import java.time.LocalDate;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        /**NOTE: se crearon metodos estaticos en main para realizar pruebas independientes de las
        //funcionalidades del programa**/

        //region Pruebas de funcionalidades independientes

//        pruebaCompraProductos();
        pruebaVentaProductos();

        //endregion

        //region Menu - falto implementar
//        boolean salir = false;
//
//        while (!salir) {
//            String[] opciones = {"Comprar Producto", "Vender Productos","", "Salir"};
//            int opcion = JOptionPane.showOptionDialog(null, "Seleccione una opción", "Menú Principal",
//                    JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, opciones, opciones[0]);
//
//            switch (opcion) {
//                case 0:
//                    JOptionPane.showMessageDialog(null, "¡Este es un mensaje del menú!");
//                    break;
//                case 1:
//                    break;
//                case 2:
//                    salir = true;
//                    JOptionPane.showMessageDialog(null, "Saliendo del programa...");
//                    break;
//                default:
//                    JOptionPane.showMessageDialog(null, "Opción no válida. Intente de nuevo.");
//            }
//        }
        //endregion




    }

    public static void pruebaCompraProductos(){
        Tienda tienda = new Tienda("LOLO", 200, 500000);

        Limpieza producto1 = new Limpieza("Limpiador de cocina",  3500.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 6100.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("Jabon seisseme", 1200f, TipoAplicacion.MULTIUSO);

        // Productos Envasados
        Envasado producto4 = new Envasado("Conserva de lentejas", 1500.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        Envasado producto5 = new Envasado("Mermelada de frutilla", 2000.0f,   TipoEnvase.FRASCO, true, LocalDate.of(2023, 8, 20), 100.0f);

        // Bebidas
        Bebida producto6 = new Bebida("Cerveza artesanal", 3000.0f,   5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        Bebida producto7 = new Bebida("Jugo de naranja", 2900.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);


        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(ColorConsola.AMARILLO + "Prueba con productos en el cual las UNIDADES SOLICITADAS superan el STOCK de la tienda" + ColorConsola.RESET);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("DETALLE PRODUCTO A COMPRAR:");
        tienda.imprimirDetalleLineaProductoCompra(producto5, 205);
        tienda.comprarProducto(producto5, 205);
        tienda.mostrarDatosTienda();

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(ColorConsola.AMARILLO + "Prueba con productos en el cual su VALOR X LAS UNIDADES SOLICITADAS SUPERAN EL SALDO EN LA CAJA de la tienda"+ ColorConsola.RESET);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println("DETALLE PRODUCTO A COMPRAR:");
        tienda.imprimirDetalleLineaProductoCompra(producto1, 150);
        tienda.comprarProducto(producto1, 150);
        tienda.mostrarDatosTienda();

        System.out.println("---------------------------------------------------------------------------------------------------");
        System.out.println(ColorConsola.AMARILLO + "\t\t\tPrueba Compra de varias productos"+ ColorConsola.RESET);
        System.out.println("----------------------------------------------------------------------------------------------");
        System.out.println(ColorConsola.VERDE + "Saldo de la tienda antes de realizar las compras de los productos: $ " + tienda.getSaldoCaja() + ColorConsola.RESET);
        tienda.comprarProducto(producto1, 10);
        tienda.comprarProducto(producto2, 20);
        tienda.comprarProducto(producto3, 30);
        tienda.comprarProducto(producto4, 40);
        tienda.comprarProducto(producto5, 50);
        tienda.comprarProducto(producto6, 15);
        tienda.comprarProducto(producto7, 25);
        System.out.println(ColorConsola.VERDE + "total de productos comprados: $ 470.500" + ColorConsola.RESET);
        tienda.mostrarDatosTienda();
        tienda.mostrarListaProductosComprados();

    }

    public static void pruebaVentaProductos(){
        Tienda tienda = new Tienda("LOLO", 200, 500000);

        Limpieza producto1 = new Limpieza("Limpiador de cocina",  3500.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 6100.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("Jabon seisseme", 1200f, TipoAplicacion.MULTIUSO);

        // Productos Envasados
        Envasado producto4 = new Envasado("Conserva de lentejas", 1500.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        //PRODUCTO IMPORTADO:
        Envasado producto5 = new Envasado("Mermelada de frutilla", 2000.0f,   TipoEnvase.FRASCO, true, LocalDate.of(2023, 8, 20), 100.0f);

        // Bebidas
        Bebida producto6 = new Bebida("Cerveza artesanal", 3000.0f,   5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        //PRODUCTO IMPORTADO:
        Bebida producto7 = new Bebida("Jugo de naranja", 2900.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);

        tienda.comprarProducto(producto1, 10);
        tienda.comprarProducto(producto2, 20);
        tienda.comprarProducto(producto3, 30);
        tienda.comprarProducto(producto4, 40);
        tienda.comprarProducto(producto5, 50);
        tienda.comprarProducto(producto6, 15);
        tienda.comprarProducto(producto7, 25);

        tienda.aplicarPorcentajeGanancia(producto1.getCodigo(), 20);
        tienda.aplicarPorcentajeGanancia(producto2.getCodigo(), 3);
        tienda.aplicarPorcentajeGanancia(producto3.getCodigo(), 5);
        tienda.aplicarPorcentajeGanancia(producto4.getCodigo(), 30);
        tienda.aplicarPorcentajeGanancia(producto5.getCodigo(), 15);
        tienda.aplicarPorcentajeGanancia(producto6.getCodigo(), 10);
        tienda.aplicarPorcentajeGanancia(producto7.getCodigo(), 10);

        tienda.aplicarDescuentoProducto(producto1.getCodigo(), 10);
        tienda.aplicarDescuentoProducto(producto2.getCodigo(), 15);
        tienda.aplicarDescuentoProducto(producto4.getCodigo(), 20);
        tienda.aplicarDescuentoProducto(producto5.getCodigo(), 10);
        tienda.aplicarDescuentoProducto(producto6.getCodigo(), 25);
        tienda.aplicarDescuentoProducto(producto7.getCodigo(), 10);

        System.out.println("---------------------------------------------------------");
        System.out.println("TIENDA ANTES DE REALIZAR LA VENTA");
        tienda.mostrarDatosTienda();
        tienda.venderProductos(new HashMap<>(Map.of(producto1,10, producto2, 5, producto3, 7)));
        System.out.println("TIENDA DESPUES DE REALIZAR LA VENTA");
        tienda.mostrarDatosTienda();
        tienda.mostrarListaProductosVenta();
//
//        tienda.venderProductos(new HashMap<>(Map.of(producto4, 10, producto5, 10)));
//        tienda.mostrarDatosTienda();
//
//        tienda.venderProductos(new HashMap<>(Map.of(producto6, 4)));
//        tienda.mostrarDatosTienda();
//
//        tienda.venderProductos(new HashMap<>(Map.of(producto7, 5)));
//        tienda.mostrarDatosTienda();

    }
    public static void productosHardcodeados(){

        Tienda tienda = new Tienda("LOLO", 20000, 100000);
        //cuando creo los productos en el main y luego la tienda los compra, esos mismos productos no tienen q tener seteados
        //ni el stock, ni el porcentaje de ganancia, ni el de descuento.
        //ya que esos mismos atributos la tienda va a ser el que se lo setee. A excepción del stock, que van a ser las UNIDADES
        //que la tienda quiera comprar en el metodo de comprarProducto()
        Limpieza producto1 = new Limpieza("Limpiador de cocina",  3500.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 6100.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("Jabon seisseme", 1200f, TipoAplicacion.MULTIUSO);

        // Productos Envasados
        Envasado producto4 = new Envasado("Conserva de lentejas", 1500.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        Envasado producto5 = new Envasado("Mermelada de frutilla", 3200.0f,   TipoEnvase.FRASCO, true, LocalDate.of(2023, 8, 20), 100.0f);

        // Bebidas
        Bebida producto6 = new Bebida("Cerveza artesanal", 3000.0f,   5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        Bebida producto7 = new Bebida("Jugo de naranja", 2900.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);

    }
}