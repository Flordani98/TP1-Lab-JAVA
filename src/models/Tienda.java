package models;

import java.util.ArrayList;

public class Tienda {

    //region Atributos
    private String nombre;
    private int stockMax;
    private double saldoCaja;
    private ArrayList<Producto> productosStock; //TODO: modificar nombre lista

    private ArrayList<String> listaProductosLimpieza; //Lista todos los productos de limpieza por su codigo
    private ArrayList<String> listaProductosEnvasados;
    private ArrayList<String> listaProductosBebidas;

    //endregion

    //region Constructores
    public Tienda(){}

    public Tienda(String nombre, int stockMax, double saldoCaja, ArrayList<Producto> productosStock) {
        this.nombre = nombre;
        this.stockMax = stockMax;
        this.saldoCaja = saldoCaja;
        this.productosStock = productosStock;
    }
    public Tienda(String nombre, int stockMax, double saldoCaja) {
        this.nombre = nombre;
        this.stockMax = stockMax;
        this.saldoCaja = saldoCaja;
        this.productosStock = productosStock;
    }
    //endregion

    //region getters y setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStockMax() {
        return stockMax;
    }

    public void setStockMax(int stockMax) {
        this.stockMax = stockMax;
    }

    public double getSaldoCaja() {
        return saldoCaja;
    }

    public void setSaldoCaja(double saldoCaja) {
        this.saldoCaja = saldoCaja;
    }

    public ArrayList<Producto> getProductosStock() {
        return productosStock;
    }

    public void setProductosStock(ArrayList<Producto> productosStock) {
        this.productosStock = productosStock;
    }


    //endregion

    public float calcularImporteTotalProducto(int stock, float precio ){
        return precio * stock;

    }

    public void verificarSaldoCaja(){

    }

    public void descontarSaldoCaja(float importe){
        this.saldoCaja = saldoCaja - importe;

    }
    public void agregarProducto(Producto productoNuevo) { //seria lo mismo que comprar Productos, estariamos agregando productos a la tienda

        float importeTotalProducto = calcularImporteTotalProducto(productoNuevo.getStock(), productoNuevo.getPrecio());

        if(importeTotalProducto <= saldoCaja){

            descontarSaldoCaja(importeTotalProducto);
            this.productosStock.add(productoNuevo);

            if (productoNuevo instanceof Limpieza) {
                this.listaProductosLimpieza.add(productoNuevo.getCodigo());
            }
            if(productoNuevo instanceof Bebida) {
                this.listaProductosBebidas.add(productoNuevo.getCodigo());
            }
            if(productoNuevo instanceof Envasado){
                this.listaProductosEnvasados.add(productoNuevo.getCodigo());

            }

        }else{
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
        }

        verificarSaldoCaja();


    }
    public void eliminarProducto(){
    }

    public void modificarProducto(){}

    public void mostrarListaProductos(){
    }

    public void venderProducto(){}



    @Override
    public String toString() {
        return "Tienda{" +
                "nombre='" + nombre + '\'' +
                ", stockMax=" + stockMax +
                ", saldoCaja=" + saldoCaja +
                ", productosStock=" + productosStock +
                '}';
    }
}
