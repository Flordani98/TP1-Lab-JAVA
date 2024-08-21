package models;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public abstract class Comestible extends Producto{
    private LocalDate fechaVencimiento;
    private float calorias;

    public Comestible(){}

    public Comestible(String descripcion, int stock, float precio, float porcentajeGanancia, LocalDate fechaVencimiento,
                      float calorias) {

        super(descripcion, stock, precio, porcentajeGanancia);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
    }

    public Comestible(String descripcion, int stock, float precio, float porcentajeGanancia,
                      float porcentajeDescuento, Boolean estaDisponible, LocalDate fechaVencimiento, float calorias) {

        super(descripcion, stock, precio, porcentajeGanancia, porcentajeDescuento, estaDisponible);
        this.fechaVencimiento = fechaVencimiento;
        this.calorias = calorias;
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

    @Override
    public String toString() {
        return super.toString() +
                "\n Fecha de vencimiento: " + formatearFechaLocalDate(fechaVencimiento) +
                "\n Calorias: " + calorias;
    }
}
