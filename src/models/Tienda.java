package models;

import java.util.ArrayList;

public class Tienda {

    //region Atributos
    private String nombre;
    private int stockMax;
    private double saldoCaja;
    private ArrayList<Producto> productosStock; //TODO: modificar nombre lista
    //endregion

    //region Constructores
    public Tienda(){}

    public Tienda(String nombre, int stockMax, double saldoCaja, ArrayList<Producto> productosStock) {
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
