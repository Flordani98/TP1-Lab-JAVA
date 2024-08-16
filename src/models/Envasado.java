package models;

import models.Enums.TipoEnvase;

public class Envasado extends Producto{

    //region atributos
    private TipoEnvase tipoEnvase;
    private Boolean esImportado;
    //endregion

    //region constructores
    public Envasado(String codigo, String descripcion, int stock, float precio, float porcentajeGanancia,
                    Boolean estaDisponible, TipoEnvase tipoEnvase, Boolean esImportado) {
        super(codigo, descripcion, stock, precio, porcentajeGanancia, estaDisponible);
        this.tipoEnvase = tipoEnvase;
        this.esImportado = esImportado;
    }
    //endregion

    //region getters y setters
    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(TipoEnvase tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
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
        return "Envasado{" +
                "tipoEnvase=" + tipoEnvase +
                ", esImportado=" + esImportado +
                '}';
    }
}
