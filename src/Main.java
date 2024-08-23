import models.*;
import Enums.TipoAplicacion;
import Enums.TipoEnvase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Tienda tienda = new Tienda("LOLO", 20000, 100000);

        //cuando creo los productos en el main y luego la tienda los compra, esos mismos productos no tienen q tener seteados
        //ni el stock, ni el porcentaje de ganancia, ni el de descuento.
        //ya que esos mismos atributos la tienda va a ser el que se lo setee. A excepción del stock, que van a ser las UNIDADES
        //que la tienda quiera comprar en el metodo de comprarProducto()
        Limpieza producto1 = new Limpieza("Limpiador de cocina",  100.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 120.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("producto de prueba", 1239f, TipoAplicacion.MULTIUSO);

        // Producto de Envasado
        Envasado producto4 = new Envasado("Conserva de frijoles", 45.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        Envasado producto5 = new Envasado("Mermelada de fresa", 75.0f,   TipoEnvase.FRASCO, true, LocalDate.of(2024, 8, 20), 100.0f);

        // Producto de Bebida
        Bebida producto6 = new Bebida("Cerveza artesanal", 200.0f,   5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        Bebida producto7 = new Bebida("Jugo de naranja", 80.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);

        tienda.comprarProducto(producto1, 10);
//        tienda.comprarProducto(producto2, 11);
//        tienda.comprarProducto(producto3, 2);
//        tienda.comprarProducto(producto4, 3);
        tienda.comprarProducto(producto5, 5);
//        tienda.comprarProducto(producto6, 7);
        tienda.comprarProducto(producto7, 4);


        tienda.mostrarListaProductosComprados();

        System.out.println("---------------------------");
        System.out.println("\t\t\tMI TIENDA");
        System.out.println(tienda);
        System.out.println("---------------------------");
//
//        tienda.aplicarPorcentajeGanancia(producto2.getCodigo(), 9);
        tienda.aplicarPorcentajeGanancia(producto1.getCodigo(), 25);
        tienda.mostrarListaProductosVenta();
//        tienda.venderProductos(new ArrayList<>(Arrays.asList(producto1, producto2, producto1)));
//        System.out.println(tienda);
//

//        System.out.println(tienda.getListaProductosStock());



//        System.out.println("Bienvenido a tienda LOLO");
//        System.out.println("Ingrese una opcion:");
//        switch() {
//            case 1:
//            case 2:
//        }



    }
}