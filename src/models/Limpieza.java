package models;

import models.Enums.TipoAplicacion;

public class Limpieza extends Producto{

    //utilizo un contador estatico es decir perteneciente a la clase y cada vez q se cree un producto limpieza
    //el contador sumara 1, y se concatenara ese numero al codigo del producto.
    //Tambien se podria crear un codigo de manera aleatoria, pero para este ejercicio me resulta mas práctico utilizar
    //variables estaticas.
    private static int contador = 1;
    private TipoAplicacion tipoAplicacion;


    //region constructores
    public Limpieza(String descripcion, int stock, float precio, float porcentajeGanancia,
                    TipoAplicacion tipoAplicacion) {

        super(descripcion, stock, precio, porcentajeGanancia);

        super.setCodigo(generarCodigoProducto());
        this.tipoAplicacion = tipoAplicacion;

        Limpieza.contador++;
    }



    //endregion

    //region getters y setters
    public TipoAplicacion getTipoAplicacion() {
        return tipoAplicacion;
    }

    public void setTipoAplicacion(TipoAplicacion tipoAplicacion) {
        this.tipoAplicacion = tipoAplicacion;
    }
    //endregion

    @Override
    public String generarCodigoProducto() {
        //si el contador es = a 1000 es decir que el codigo alfanumerico
        //se pasara de la longitud 5 por primera vez, se puede lanzar un msj de aviso (o excepción?)
        return "AZ" + String.format("%03d", contador);

    }

//    @Override
//    public String validarYFormatearCodigoProducto(String codigo) {
//    //verifica si el codigo pasado por parametro cumple con la regla impuesta para los codigos de limpieza:
//    // que sus primeros dos caracteres sean "AZ" y luego el resto de caracteres sean numeros enteros ya
//    //que tienen que indicar el numero de producto del articulo
//
////        boolean result = codigo.startsWith("AZ");
////        String digitos = codigo.substring(2);
//
//        //si el codigo no es valido:
////        String codigoValido = generarCodigoProducto(codigo);
//
//        return "codigoValido";
//    }

//    @Override
//    public boolean validarSiExisteCodigoProducto(String codigo) {
//        //debe verificar sino existe ya un producto con este mismo codigo pasado por parametro
//        return false;
//    }

    @Override
    public String toString() {
        return "Limpieza{" +
                "tipoAplicacion=" + tipoAplicacion +
                '}';
    }
}
