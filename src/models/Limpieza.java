package models;

import models.Enums.TipoAplicacion;

public class Limpieza extends Producto{
    private TipoAplicacion tipoAplicacion;

    //region constructores
    public Limpieza(String codigo, String descripcion, int stock, float precio, float porcentajeGanancia,
                    Boolean estaDisponible, TipoAplicacion tipoAplicacion) {
        super(codigo, descripcion, stock, precio, porcentajeGanancia, estaDisponible);
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

    @Override
    public String toString() {
        return "Limpieza{" +
                "tipoAplicacion=" + tipoAplicacion +
                '}';
    }
}
