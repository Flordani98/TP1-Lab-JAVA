package models;

import java.time.LocalDate;

public class Bebida extends Comestible{
    private static final float MAX_PORCENTAJE_DESC = 10.0f;

    private static int contador = 1;

    //region atributos
    private float graduacionAlcohol;
    private Boolean esImportado;


    //endregion

    //region constructores

    public Bebida(String descripcion, int stock, float precio, float porcentajeGanancia, float graduacionAlcohol,
                  Boolean esImportado, LocalDate fechaVencimiento, float calorias) {
        super(descripcion, stock, precio, porcentajeGanancia, fechaVencimiento, calorias);
        this.graduacionAlcohol = graduacionAlcohol;
        this.esImportado = esImportado;

        super.setCodigo(generarCodigoProducto());

        Bebida.contador++;
    }

    public Bebida(String descripcion, int stock, float precio, float porcentajeGanancia, float porcentajeDescuento, Boolean estaDisponible, LocalDate fechaVencimiento, float calorias, float graduacionAlcohol, Boolean esImportado) {
        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible, fechaVencimiento, calorias);
        this.graduacionAlcohol = graduacionAlcohol;
        this.esImportado = esImportado;
    }

    //endregion

    //region getters y setters

    public float getGraduacionAlcohol() {
        return graduacionAlcohol;
    }

    public void setGraduacionAlcohol(float graduacionAlcohol) {
        this.graduacionAlcohol = graduacionAlcohol;
    }

    public Boolean getEsImportado() {
        return esImportado;
    }

    public void setEsImportado(Boolean esImportado) {
        this.esImportado = esImportado;
    }

    //endregion


    @Override
    public String generarCodigoProducto() {
        return "AC" + String.format("%03d", contador);
    }



    @Override
    public float aplicarDescuento(float porcentajeDescuento) {
        return aplicarDescuentoBase(porcentajeDescuento, MAX_PORCENTAJE_DESC, this.getClass().getSimpleName());
    }

    @Override
    public void calcularPrecioFinal() {

    }


    @Override
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Bebida" +
                "\n Graduación de Alcohol: %" + graduacionAlcohol +
                "\n Es Importado: " + (esImportado ? "SÍ" : "NO");
    }
}
