package models;

import java.time.LocalDate;

public class Bebida extends Comestible{

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
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Bebida" +
                "\n Graduación de Alcohol: %" + graduacionAlcohol +
                "\n Es Importado: " + (esImportado ? "SÍ" : "NO");
    }
}
