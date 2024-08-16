package models;

public class Bebida extends Producto{

    //region atributos
    private float graduacionAlcohol;
    private Boolean esImportado;
    //endregion

    //region constructores
    public Bebida(String codigo, String descripcion, int stock, float precio, float porcentajeGanancia,
                  Boolean estaDisponible, float graduacionAlcohol, Boolean esImportado){
        super(codigo, descripcion, stock, precio, porcentajeGanancia, estaDisponible);
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
    public String toString() {
        return "Bebida{" +
                "graduacionAlcohol=" + graduacionAlcohol +
                ", esImportado=" + esImportado +
                '}';
    }
}
