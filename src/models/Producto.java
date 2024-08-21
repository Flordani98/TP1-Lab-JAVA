package models;

public abstract class Producto {

    //region atributos
    private String codigo;
    private String descripcion;
    private int stock;
    private float precio;
    private float porcentajeGanancia;
    private float porcentajeDescuento;
    private Boolean estaDisponible;

    //endregion

    //region constructores
    public Producto() {
    }

    public Producto(String descripcion, int stock, float precio,
                    float porcentajeGanancia){
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.porcentajeGanancia = porcentajeGanancia;
        this.estaDisponible = true;

    }

    public Producto(String descripcion, int stock, float precio, float porcentajeGanancia, float porcentajeDescuento, Boolean estaDisponible) {
        this.descripcion = descripcion;
        this.stock = stock;
        this.precio = precio;
        this.porcentajeGanancia = porcentajeGanancia;
        this.porcentajeDescuento = porcentajeDescuento;
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

    public float getPorcentajeDescuento() {
        return porcentajeDescuento;
    }

    public void setPorcentajeDescuento(float porcentajeDescuento) {
        this.porcentajeDescuento = porcentajeDescuento;
    }

    //endregion

    protected float aplicarDescuentoBase(float porcentajeDescuento, float maxDescuento, String tipoProducto){
        float descuentoAplicado = 0.0f;
        if(porcentajeDescuento > 0 && porcentajeDescuento <= maxDescuento){
           this.setPorcentajeDescuento(porcentajeDescuento);
            descuentoAplicado = porcentajeDescuento;
        }else{
            System.out.println("El porcentaje de descuento para el tipo de producto " + tipoProducto +
                    "no tiene que ser mayor a %" + maxDescuento);
        }

        return descuentoAplicado;
    }

    public abstract String generarCodigoProducto();

//    public abstract String validarYFormatearCodigoProducto(String codigo);

//    public abstract boolean validarSiExisteCodigoProducto(String codigo);

    public abstract float aplicarDescuento(float porcentajeDescuento);


    //TODO: implementar equals y hashcode
    public float calcularImporteTotalProducto(){
        return this.precio * this.stock;
    }

    public String formatearUnidadStock(){
        return (this.stock > 1) ? " unidades" : " unidad";
    }




    @Override
    public String toString() {
        return "\n\t\t\t Producto" +
                "\n Codigo: " + codigo +
                "\n Descripcion: " + descripcion +
                "\n Stock: " + stock + formatearUnidadStock() +
                "\n Precio: $" + precio +
                "\n Porcentaje Ganancia: %" + porcentajeGanancia +
                "\n Esta Disponible: " + (estaDisponible ? "SÍ" : "NO");
    }
}
