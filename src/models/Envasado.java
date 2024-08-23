package models;

import Enums.TipoEnvase;

import java.time.LocalDate;

public class Envasado extends Comestible{

    private static final float MAX_PORCENTAJE_DESC = 15.0f;

    private static int contador = 1;
    //region atributos
    private TipoEnvase tipoEnvase;



    //endregion

    //region constructores

    public Envasado(String descripcion, float precio, TipoEnvase tipoEnvase,
                    Boolean esImportado, LocalDate fechaVencimiento, float calorias) {
        super(descripcion, precio, fechaVencimiento, calorias, esImportado);
        this.tipoEnvase = tipoEnvase;

        super.setCodigo(generarCodigoProducto());

        Envasado.contador++;
    }

    public Envasado(String descripcion, int stock, float precio, float porcentajeGanancia, float porcentajeDescuento, Boolean estaDisponible, LocalDate fechaVencimiento, float calorias, TipoEnvase tipoEnvase, Boolean esImportado) {
        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible, fechaVencimiento, calorias, esImportado);
        this.tipoEnvase = tipoEnvase;
    }

    //endregion

    //region getters y setters
    public TipoEnvase getTipoEnvase() {
        return tipoEnvase;
    }

    public void setTipoEnvase(TipoEnvase tipoEnvase) {
        this.tipoEnvase = tipoEnvase;
    }


    //endregion


    @Override
    public String generarCodigoProducto() {
        return "AB" + String.format("%03d", contador);
    }


    @Override
    public float aplicarDescuento(float porcentajeDescuento) {
        return aplicarDescuentoBase(porcentajeDescuento, MAX_PORCENTAJE_DESC, this.getClass().getSimpleName());
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Envasado" +
                "\n Tipo de envase: " + tipoEnvase.getNombre();
    }
}
