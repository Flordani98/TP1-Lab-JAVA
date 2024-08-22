package models;

import Enums.TipoAplicacion;

public class Limpieza extends Producto{

    private static final float MAX_PORCENTAJE_DESC = 20.0f;
    private static final float MAX_PORCENTAJE_GANANCIA = 25.0f;


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

    public Limpieza(String descripcion, int stock, float precio, float porcentajeGanancia, float porcentajeDescuento, Boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible);
        this.tipoAplicacion = tipoAplicacion;
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

    public boolean esTipoCocinaOMulituso(){
        return (this.tipoAplicacion.equals(TipoAplicacion.COCINA)  || this.tipoAplicacion.equals(TipoAplicacion.MULTIUSO));
    }

    public boolean esValidoPorcentGanancia(float porcentajeGanancia){
        boolean esValido = false;

        if(esTipoCocinaOMulituso() ) {
            esValido = porcentajeGanancia <= MAX_PORCENTAJE_GANANCIA;
        }else{
            esValido = porcentajeGanancia >= 10 && porcentajeGanancia <= MAX_PORCENTAJE_GANANCIA;
        }

        return esValido;
    }

    @Override
    public String generarCodigoProducto() {
        //si el contador es = a 1000 es decir que el codigo alfanumerico
        //se pasara de la longitud 5 por primera vez, se puede lanzar un msj de aviso (o excepción?)
        return "AZ" + String.format("%03d", contador);

    }

    @Override
    public float aplicarDescuento(float porcentajeDescuento) {
        return aplicarDescuentoBase(porcentajeDescuento, MAX_PORCENTAJE_DESC, this.getClass().getSimpleName());
    }

    @Override
    public float aplicarPorcentajeGanancia(float porcentajeGanancia) {
        float porcentajeGananciaAplicado = 0.0f;
        String mensaje = esTipoCocinaOMulituso() ? "El porcentaje de ganancia debe tener un minimo del %10 y no debe superar el %" + MAX_PORCENTAJE_GANANCIA
                : "El porcentaje de ganancia no debe superar el %" + MAX_PORCENTAJE_GANANCIA;

        if(porcentajeGanancia > 0){
            if(esValidoPorcentGanancia(porcentajeGanancia)){
                super.setPorcentajeGanancia(porcentajeGanancia);
                porcentajeGananciaAplicado = porcentajeGanancia;
            }else{
                System.out.println(mensaje);
            }

        }else{
            System.out.println("El porcentaje de ganancia debe ser mayor a 0");
        }

        return porcentajeGananciaAplicado;
    }


    @Override
    public void calcularPrecioFinal() {
        calcularPrecioFinalBase();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Limpieza" +
                "\n Tipo de Aplicacion: " + tipoAplicacion.getNombre();
    }
}
