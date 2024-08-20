package models;

import java.time.LocalDate;

public abstract class Comestible extends Producto{
    private LocalDate fechaVencimiento;
    private float calorias;

    public Comestible(){}

    public Comestible(String descripcion, int stock, float precio, float porcentajeGanancia, LocalDate fechaVencimiento, float calorias) {
        super(descripcion, stock, precio, porcentajeGanancia);
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

    @Override
    public String toString() {
        return "\n Fecha de vencimiento: " + fechaVencimiento +
                "\n Calorias: " + calorias;
    }
}
