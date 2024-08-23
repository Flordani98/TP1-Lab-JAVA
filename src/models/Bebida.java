package models;

import java.time.LocalDate;

public class Bebida extends Comestible{
    private static final float MAX_PORCENTAJE_DESC = 10.0f;

    private static int contador = 1;

    //region atributos
    private float graduacionAlcohol;


    //endregion

    //region constructores

    public Bebida(String descripcion, float precio, float graduacionAlcohol,
                  Boolean esImportado, LocalDate fechaVencimiento, float calorias) {
        super(descripcion, precio,  fechaVencimiento, calorias, esImportado);
        this.graduacionAlcohol = graduacionAlcohol;

        super.setCodigo(generarCodigoProducto());

        Bebida.contador++;
    }

    public Bebida(String descripcion, int stock, float precio, float porcentajeGanancia, float porcentajeDescuento, Boolean estaDisponible, LocalDate fechaVencimiento, float calorias, float graduacionAlcohol, Boolean esImportado) {
        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible, fechaVencimiento, calorias, esImportado);
        this.graduacionAlcohol = graduacionAlcohol;
    }

    //endregion

    //region getters y setters

    public float getGraduacionAlcohol() {
        return graduacionAlcohol;
    }

    public void setGraduacionAlcohol(float graduacionAlcohol) {
        this.graduacionAlcohol = graduacionAlcohol;
    }



    //endregion

    public void calcularCalorias(){

        float totalCalorias = super.getCalorias();

        if(graduacionAlcohol > 4.5){
            totalCalorias *= 1.5f;
        }else if(graduacionAlcohol >= 2.1){
            totalCalorias *= 1.25f;
        }

        setCalorias(totalCalorias);
    }

    @Override
    public String generarCodigoProducto() {
        return "AC" + String.format("%03d", contador);
    }


    @Override
    public float aplicarDescuento(float porcentajeDescuento) {
        return aplicarDescuentoBase(porcentajeDescuento, MAX_PORCENTAJE_DESC, this.getClass().getSimpleName());
    }



    @Override
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Bebida" +
                "\n Graduación de Alcohol: %" + graduacionAlcohol;
    }
}
