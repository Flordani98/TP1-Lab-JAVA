package models;

public abstract class Producto {

    //region atributos
    private String codigo;
    private String descripcion;
    private int stock;
    private float precio;
    private float porcentajeGanancia;
    private Boolean estaDisponible;

    //endregion

    //region constructores
    public Producto() {
    }

    public Producto(String codigo, String descripcion, int stock, float precio,
                    float porcentajeGanancia, Boolean estaDisponible){
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.porcentajeGanancia = porcentajeGanancia;
        this.estaDisponible = estaDisponible;

    }

    //endregion

    //region Getters and Setters

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }

    public float getPorcentajeGanancia() {
        return porcentajeGanancia;
    }

    public void setPorcentajeGanancia(float porcentajeGanancia) {
        this.porcentajeGanancia = porcentajeGanancia;
    }

    public Boolean getEstaDisponible() {
        return estaDisponible;
    }

    public void setEstaDisponible(Boolean estaDisponible) {
        this.estaDisponible = estaDisponible;
    }

    //endregion

    //TODO: implementar equals y hashcode

    @Override
    public String toString() {
        return "Producto{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", porcentajeGanancia=" + porcentajeGanancia +
                ", estaDisponible=" + estaDisponible +
                '}';
    }
}
