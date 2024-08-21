import models.*;
import Enums.TipoAplicacion;
import Enums.TipoEnvase;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) {

        Tienda tienda = new Tienda("LOLO", 200000, 100000);

        Limpieza producto1 = new Limpieza("Limpiador de cocina", 11, 100.0f, 30.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 75, 120.0f, 35.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("producto de prueba", 34, 1239f, 34f,  TipoAplicacion.MULTIUSO);

        // Producto de Envasado
        Envasado producto4 = new Envasado("Conserva de frijoles", 200, 45.0f, 20.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        Envasado producto5 = new Envasado("Mermelada de fresa", 150, 75.0f, 25.0f,  TipoEnvase.FRASCO, true, LocalDate.of(2024, 8, 20), 100.0f);

        // Producto de Bebida
        Bebida producto6 = new Bebida("Cerveza artesanal", 300, 200.0f, 35.0f,  5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        Bebida producto7 = new Bebida("Jugo de naranja", 400, 80.0f, 20.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);


        tienda.agregarProducto(producto1);
        tienda.agregarProducto(producto2);
        tienda.agregarProducto(producto3);
        tienda.agregarProducto(producto4);
        tienda.agregarProducto(producto5);
        tienda.agregarProducto(producto6);
        tienda.agregarProducto(producto7);

        tienda.mostrarListaProductosStock();

        System.out.println(tienda);

        tienda.venderProductos(new ArrayList<>(Arrays.asList(producto1, producto2, producto1)));
        System.out.println(tienda);

        tienda.mostrarListaProductosStock();

//        System.out.println(tienda.getListaProductosStock());



//        System.out.println("Bienvenido a tienda LOLO");
//        System.out.println("Ingrese una opcion:");
//        switch() {
//            case 1:
//            case 2:
//        }



    }
}