package models;

import java.util.ArrayList;

public class Tienda {

    //region Atributos
    private String nombre;
    private int stockMax;
    private double saldoCaja;
    private ArrayList<Producto> listaProductosStock;

    private ArrayList<String> listaProductosLimpieza = new ArrayList<>(); //Lista todos los productos de limpieza por su codigo
    private ArrayList<String> listaProductosEnvasados = new ArrayList<>();
    private ArrayList<String> listaProductosBebidas = new ArrayList<>();

    //endregion

    //region Constructores
    public Tienda(){}

    public Tienda(String nombre, int stockMax, double saldoCaja, ArrayList<Producto> listaProductosStock) {
        this.nombre = nombre;
        this.stockMax = stockMax;
        this.saldoCaja = saldoCaja;
        this.listaProductosStock = listaProductosStock;
    }
    public Tienda(String nombre, int stockMax, double saldoCaja) {
        this.nombre = nombre;
        this.stockMax = stockMax;
        this.saldoCaja = saldoCaja;
        this.listaProductosStock = new ArrayList<>();
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

    public ArrayList<Producto> getListaProductosStock() {
        return listaProductosStock;
    }

    public void setListaProductosStock(ArrayList<Producto> listaProductosStock) {
        this.listaProductosStock = listaProductosStock;
    }


    //endregion

    //region métodos de ALTA(COMPRA), BAJA, MODIFICACIÓN Y VENTA de productos
    public void agregarProducto(Producto productoNuevo) { //seria lo mismo que comprar Productos, estariamos agregando productos a la tienda

        float importeTotalProducto = productoNuevo.calcularImporteTotalProducto();

        if(importeTotalProducto <= saldoCaja && !(excedeLimiteStockMaximo(productoNuevo.getStock()))){

            descontarSaldoCaja(importeTotalProducto);
            this.listaProductosStock.add(productoNuevo);

            if (productoNuevo instanceof Limpieza) {
                this.listaProductosLimpieza.add(productoNuevo.getCodigo());
            }
            if(productoNuevo instanceof Bebida) {
                this.listaProductosBebidas.add(productoNuevo.getCodigo());
            }
            if(productoNuevo instanceof Envasado){
                this.listaProductosEnvasados.add(productoNuevo.getCodigo());
            }

        }else if(importeTotalProducto > saldoCaja){
            System.out.println("El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja");
        }else{
            System.out.println("No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock");
        }


    }
    public void eliminarProducto(Producto producto){
        this.listaProductosStock.remove(producto);
    }
    public void eliminarProductoSegunCodigo(String codigo){

        if(existeProductoSegunCodigo(codigo)){
            Producto producto = obtenerProductoDeListaSegunCodigo(codigo);
            this.listaProductosStock.remove(producto);
        }

    }
    public void modificarProducto(){}

    public void venderProductos(ArrayList<Producto> productos){

        float totalVenta = 0;

        if(productos.size() < 1 || productos.size() >3){ //no cumple con cantidad requerida o excede cantidad de productos para vender
            System.out.println("Solo se puede realizar la venta de 1, 2 o 3 articulos. Ingrese de 1 a 3 productos");
            //TODO: se podria agregar un excepcion aca, para que salga de ese metodo y no continue el codigo de abajo
        }

        for (Producto producto : productos) {
            System.out.println(producto.getDescripcion());
            System.out.println("Ingrese las unidades deseadas:");
            int unidades = 0; //hardcodeado

            //region opcion anterior:
            //continua vendiendo el resto de productos por mas que un producto no este disponible o las unidades no sean correctas:
//            if(venderProducto(producto.getCodigo(), producto.getDescripcion(), unidades)){
//                imprimirDetalleLineaProducto(producto, unidades);
//                totalVenta = totalVenta + (producto.getPrecio() * unidades);
//            }
            //endregion

            //otra manera de realizar lo de arriba solo que si la venta de algun producto pasado no se puede realizar se sale del bucle:
            if(!venderProducto(producto.getCodigo(), producto.getDescripcion(), unidades)){
                break;
            }
            imprimirDetalleLineaProducto(producto, unidades);
            totalVenta = totalVenta + (producto.getPrecio() * unidades);

        }

        System.out.println("TOTAL VENTA: " + totalVenta);

    }

    public boolean venderProducto(String codigo, String descripcion, int unidades){

        Producto producto = obtenerProductoDeListaSegunCodigo(codigo);
        boolean ventaRealizada = false;

        if(producto != null && producto.getEstaDisponible()){

            if(esUnidadValida(unidades)){

                if(unidades < producto.getStock()) {
                    sumarSaldoCaja(unidades * producto.getPrecio());
                    //descontarUnidadesProducto()
                    producto.setStock(producto.getStock() - unidades);

                }else{
                    sumarSaldoCaja(producto.getStock() * producto.getPrecio());

                    producto.setStock(0);
                    producto.setEstaDisponible(false);
                }
                ventaRealizada = true;
            }

           else{
                System.out.println("Unidad ingresada no valida. Por favor ingrese un número de unidad del 1 al 12");

            }

        }else{
            System.out.println("El producto " + codigo + " " + descripcion + "no se encuentra disponible para la venta");

        }

        actualizarListaProductosDisponibles();

        return ventaRealizada;

    }

    public void mostrarListaProductosStock(){
        for(Producto producto : this.listaProductosStock){
            System.out.println("-----------------------------------");
            System.out.println(producto);
        }
    }

    //endregion


    //Listas de productos
    public void actualizarListaProductosDisponibles(){
        //recorre la lista y elimina los productos que no esten disponibles para la venta
        this.listaProductosStock.removeIf(producto -> !producto.getEstaDisponible());

    }

    public boolean existeProductoSegunCodigo(String codigo){
        boolean resp = false;

        for(Producto producto : listaProductosStock){
            if(codigo.equals(producto.getCodigo())){
                resp = true;
            }
        }
        return resp;
    }
    public Producto obtenerProductoDeListaSegunCodigo(String codigo){
        Producto productoEncontrado = null;

        for (Producto producto : listaProductosStock) {
            if (codigo.equals(producto.getCodigo())) {
                productoEncontrado = producto;
                break;
            }
        }
        return productoEncontrado;
    }
    public int obtenerSumaStockTotalListaProductos(ArrayList<Producto> listaProductosStock){

        int totalStock = 0;

        for (Producto producto : listaProductosStock) {
            totalStock = totalStock + producto.getStock();
        }

        return totalStock;
    }
    public boolean excedeLimiteStockMaximo(int stockProducto){
        //devuelve true si el stock del producto pasado por parametro, supera el stock maximo.
        boolean resp = false;
        int stockFinal = obtenerSumaStockTotalListaProductos(this.listaProductosStock) + stockProducto;

        if(stockFinal > this.stockMax){
            resp = true;
        }

        return resp;
    }
    public void mostrarListaProductos(){
    }
    //podriamos hacer metodos el cual sean obtenerProductoEnvasadoPorCodigo, etc. para no crear una variable Producto como null


    //Saldo de Caja
    public void sumarSaldoCaja(float importe){
        this.saldoCaja = saldoCaja + importe;
    }
    public void descontarSaldoCaja(float importe){
        this.saldoCaja = saldoCaja - importe;

    }
    public void mostrarSaldoCaja(){
        System.out.println("Su saldo de caja es: " + saldoCaja);
    }

    //Venta
    public void imprimirDetalleLineaProducto(Producto producto, int unidades){
        System.out.println(producto.getCodigo() + " " + producto.getDescripcion() + " " + unidades + " x " + producto.getPrecio());
    }

    public boolean esUnidadValida(int unidadesProducto){
        //verifica si las unidades que se ingresaron no exceden de las 12 unidades o son menores a 1 unidad, retorna true si cumple con los requisitos
        return (unidadesProducto >= 1 && unidadesProducto <= 12);
    }


    @Override
    public String toString() {
        return "Tienda{" +
                "nombre='" + nombre + '\'' +
                ", stockMax=" + stockMax +
                ", saldoCaja=" + saldoCaja +
                ", productosStock=" + listaProductosStock +
                '}';
    }
}
