import models.Bebida;
import Enums.TipoAplicacion;
import Enums.TipoEnvase;
import models.Envasado;
import models.Limpieza;
import models.Tienda;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
//        Bebida bebida = new Bebida();
//        Limpieza producto = new Limpieza("producto de prueba", 3, 1239, 34, true, TipoAplicacion.MULTIUSO);
//        Limpieza producto2 = new Limpieza("producto de prueba", 20, 1239, 34, true, TipoAplicacion.MULTIUSO);
//        Limpieza producto3 = new Limpieza("producto de prueba", 34, 1239, 34, true, TipoAplicacion.MULTIUSO);
//
//        System.out.println(producto.getCodigo());
//        System.out.println(producto2.getCodigo());
//        System.out.println(producto3.getCodigo());

        Limpieza producto1 = new Limpieza("Limpiador de cocina", 50, 100.0f, 30.0f, TipoAplicacion.COCINA);
        Limpieza producto2 = new Limpieza("Desinfectante de baño", 75, 120.0f, 35.0f, TipoAplicacion.BANIO);
        Limpieza producto3 = new Limpieza("producto de prueba", 34, 1239f, 34f,  TipoAplicacion.MULTIUSO);

        // Producto de Envasado
        Envasado producto4 = new Envasado("Conserva de frijoles", 200, 45.0f, 20.0f,  TipoEnvase.LATA, false, LocalDate.of(2025, 6, 15), 80.0f);
        Envasado producto5 = new Envasado("Mermelada de fresa", 150, 75.0f, 25.0f,  TipoEnvase.FRASCO, true, LocalDate.of(2024, 8, 20), 100.0f);

        // Producto de Bebida
        Bebida producto6 = new Bebida("Cerveza artesanal", 300, 200.0f, 35.0f,  5.5f, true, LocalDate.of(2024, 12, 31), 150.0f);
        Bebida producto7 = new Bebida("Jugo de naranja", 400, 80.0f, 20.0f,  0.0f, false, LocalDate.of(2024, 9, 10), 123.3f);



        Tienda tienda = new Tienda("LOLO", 200, 500000);
        tienda.agregarProducto(producto1);

//        System.out.println("Bienvenido a tienda LOLO");
//        System.out.println("Ingrese una opcion:");
//        switch() {
//            case 1:
//            case 2:
//        }



    }
}