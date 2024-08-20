package models;

import Enums.TipoEnvase;

import java.time.LocalDate;

public class Envasado extends Comestible{

    private static int contador = 1;
    //region atributos
    private TipoEnvase tipoEnvase;
    private Boolean esImportado;


    //endregion

    //region constructores

    public Envasado(String descripcion, int stock, float precio, float porcentajeGanancia, TipoEnvase tipoEnvase,
                    Boolean esImportado, LocalDate fechaVencimiento, float calorias) {
        super(descripcion, stock, precio, porcentajeGanancia, fechaVencimiento, calorias);
        this.tipoEnvase = tipoEnvase;
        this.esImportado = esImportado;

        super.setCodigo(generarCodigoProducto());

        Envasado.contador++;
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
    public String generarCodigoProducto() {
        return "AB" + String.format("%03d", contador);
    }


    @Override
    public String toString() {
        return super.toString() +
                "\n" +
                "\n Tipo de producto: Envasado" +
                "\n Tipo de envase: " + tipoEnvase +
                "\n Es Importado: " + (esImportado ? "SÍ" : "NO");
    }
}
