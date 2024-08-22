package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Comestible extends Producto{

    private static final float MAX_PORCENT_GANANCIA = 20.0f;
    private static final float PORCENT_IMPUESTO_IMPORTACION = 12.0f;

    private LocalDate fechaVencimiento;
    private float calorias;
    private boolean esImportado;

    public Comestible(){}

    public Comestible(String descripcion, int stock, float precio, float porcentajeGanancia, LocalDate fechaVencimiento,
                      float calorias, boolean esImportado) {

        super(descripcion, stock, precio, porcentajeGanancia);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        this.esImportado = esImportado;
    }

    public Comestible(String descripcion, int stock, float precio, float porcentajeGanancia,
                      float porcentajeDescuento, Boolean estaDisponible, LocalDate fechaVencimiento, float calorias,
                      boolean esImportado) {

        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
        this.esImportado = esImportado;
    }

    public LocalDate getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(LocalDate fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getCalorias() {
        return calorias;
    }

    public void setCalorias(float calorias) {
        this.calorias = calorias;
    }

    public String formatearFechaLocalDate(LocalDate fecha){
        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        return fecha.format(formato);

    }

    public void aplicarImpuestoProductoImportado(){
        float precioActual = super.getPrecio();
        if(this.esImportado){
            super.setPrecio(precioActual + (precioActual * PORCENT_IMPUESTO_IMPORTACION / 100));
        }
    }

    @Override
    public void calcularPrecioFinal() {
        aplicarImpuestoProductoImportado();
        calcularPrecioFinalBase();
    }


    @Override
    public float aplicarPorcentajeGanancia(float porcentajeGanancia) {
        float porcentajeGananciaAplicado = 0.0f;
        if(porcentajeGanancia > 0){
            if(porcentajeGanancia <= MAX_PORCENT_GANANCIA){
               super.setPorcentajeGanancia(porcentajeGanancia);
               porcentajeGananciaAplicado = porcentajeGanancia;
            }else{
                System.out.println("El porcentaje de ganancia no debe superar el %" + MAX_PORCENT_GANANCIA);
            }
        }else{
            System.out.println("El porcentaje de ganancia debe ser mayor a 0");
        }
        return porcentajeGananciaAplicado;
    }

    @Override
    public String toString() {
        return super.toString() +
                "\n Fecha de vencimiento: " + formatearFechaLocalDate(fechaVencimiento) +
                "\n Calorias: " + calorias  +
                "\n Es Importado: " + (esImportado ? "SÍ" : "NO");
    }
}
