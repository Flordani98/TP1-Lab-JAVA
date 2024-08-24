package models;

import Tools.ColorConsola;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;

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
    public void comprarProducto(Producto productoNuevo, int unidades) { //seria lo mismo que comprar Productos, estariamos agregando productos a la tienda

        productoNuevo.setStock(unidades);
        //
        float importeTotalProducto = productoNuevo.calcularImporteTotalProducto();

        if(importeTotalProducto > saldoCaja){
            System.out.println(ColorConsola.ROJO + "El producto no podrá ser agregado a la tienda por saldo insuficiente en la caja" + ColorConsola.RESET);
        }

        if(excedeLimiteStockMaximo(productoNuevo.getStock())){
            System.out.println(ColorConsola.ROJO + "No se pueden agregar nuevos productos a la tienda ya que se alcanzó el máximo de stock"  + ColorConsola.RESET);
        }

        if(importeTotalProducto <= saldoCaja && !(excedeLimiteStockMaximo(productoNuevo.getStock()))){

            descontarSaldoCaja(importeTotalProducto);
            this.listaProductosStock.add(productoNuevo);
            agregarProductoALaListaCorrespondiente(productoNuevo);
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

    //VENTA utilizando SCANNER, ingresado de datos por consola
    public void venderProductos(ArrayList<Producto> productos){

        boolean ventaRealizada = false;
        int unidadesSolicitadas = 0;
        int unidadesVendidas = 0;
        float totalVenta = 0;

        Scanner scan = new Scanner(System.in);
        Map<Producto, Integer> productosVendidos = new HashMap<>();
        ArrayList<String> mensajesVenta = new ArrayList<>();

        System.out.println("-------------------------------");
        System.out.println("\t\tVENTA DE PRODUCTOS");

        if(productos != null && esValidoCantidadProductosVenta(productos.size())){

            for (Producto producto : productos) {

                System.out.println("-------------------------------");
                System.out.println("Producto: " + producto.getCodigo() + " " +producto.getDescripcion());
                System.out.println("Ingrese las unidades deseadas:");
                unidadesSolicitadas = scan.nextInt();

                unidadesVendidas = venderProducto(producto.getCodigo(), producto.getDescripcion(), unidadesSolicitadas, mensajesVenta);

                if(unidadesVendidas == 0){
                    break;
                }

                productosVendidos.put(producto, unidadesVendidas);
                totalVenta += (producto.obtenerPrecioFinalVenta() * unidadesVendidas);
                ventaRealizada = true;

            }
            if(ventaRealizada){
                imprimirDetalleVenta(productosVendidos, totalVenta, mensajesVenta);
            }

        }else{
            System.out.println(ColorConsola.ROJO + "Solo se puede realizar la venta de 1, 2 o 3 articulos. Ingrese de 1 a 3 productos" + ColorConsola.RESET);
        }

        scan.close();

    }

    //VENTA utilizando MAP para guardar el producto con las unidades solicitadas
    public void venderProductos(Map<Producto, Integer> productos){

        boolean ventaRealizada = false;
        int unidadesVendidas = 0;
        float totalVenta = 0;

        Map<Producto, Integer> productosVendidos = new HashMap<>();
        ArrayList<String> mensajesVenta = new ArrayList<>();

        System.out.println("-------------------------------");
        System.out.println("\t\tVENTA DE PRODUCTOS");

        if(productos != null && esValidoCantidadProductosVenta(productos.size())){

            for (Map.Entry<Producto, Integer> entrada : productos.entrySet()) {
                Producto producto = entrada.getKey();
                int unidadesSolicitadas = entrada.getValue();

                unidadesVendidas = venderProducto(producto.getCodigo(), producto.getDescripcion(), unidadesSolicitadas, mensajesVenta);

                if(unidadesVendidas == 0){
                    break;
                }

                productosVendidos.put(producto, unidadesVendidas);
                totalVenta += (producto.obtenerPrecioFinalVenta() * unidadesVendidas);
                ventaRealizada = true;

            }
            if(ventaRealizada){
                imprimirDetalleVenta(productosVendidos, totalVenta, mensajesVenta);
            }

        }else{
            System.out.println(ColorConsola.ROJO + "Solo se puede realizar la venta de 1, 2 o 3 articulos. Ingrese de 1 a 3 productos" + ColorConsola.RESET);
        }


    }


    public boolean esValidoCantidadProductosVenta(int cantidadProductos){
        return (cantidadProductos >=1 && cantidadProductos <=3);
    }

    public int venderProducto(String codigo, String descripcion, int unidades, ArrayList<String> mensajesVenta){

        Producto producto = obtenerProductoDeListaSegunCodigo(codigo);
        int unidadesVendidas = 0;

        if(producto != null && producto.getEstaDisponible()){

            if(esUnidadValida(unidades)){

                if(unidades < producto.getStock()) {
                    sumarSaldoCaja(unidades * producto.obtenerPrecioFinalVenta());
                    producto.setStock(producto.getStock() - unidades);

                    unidadesVendidas = unidades;

                }else{
                    unidadesVendidas = producto.getStock();
                    sumarSaldoCaja(unidadesVendidas * producto.obtenerPrecioFinalVenta());
                    if(unidades != producto.getStock()){
                        mensajesVenta.add(ColorConsola.ROJO + "Hay productos con stock disponible menor al solicitado" + ColorConsola.RESET);
                    }
                    producto.setEstaDisponible(false);
                    producto.setStock(0);
                }
            }

           else{
                mensajesVenta.add(ColorConsola.ROJO +"Unidad ingresada no valida. Por favor ingrese un número de unidad del 1 al 12" + ColorConsola.RESET);

            }

        }else{
            mensajesVenta.add(ColorConsola.ROJO + "El producto " + codigo + " " + descripcion.toUpperCase() + " no se encuentra disponible para la venta" + ColorConsola.RESET);
        }

//        actualizarListaProductosDisponibles();

        return unidadesVendidas;

    }

    public void mostrarListaProductosVenta(){
        //Los productos son entidades creadas por fuera de la tienda, por lo que guardan el precio costo, al que la
        //tienda los compro.
        //Para mostrar el precio de venta final sin modificar el precio costo del producto, guardo el precio
        //costo en una variable y despues la asigno nuevamente. Se realiza de esta forma para no agregar a la clase
        //Producto, el atributo "precioCosto"
        float precioCosto = 0.0f;

        System.out.println("-----------------------------------");
        System.out.println(ColorConsola.AMARILLO + "\t\t\tLISTA DE PRODUCTOS PARA LA VENTA"+ ColorConsola.RESET );
        for(Producto producto : this.listaProductosStock){
            precioCosto = producto.getPrecio();
            producto.setPrecio(producto.obtenerPrecioFinalVenta());
            System.out.println("-----------------------------------");
            System.out.println(producto);
            System.out.println("-----------------------------------");
            producto.setPrecio(precioCosto);
        }
        System.out.println("-----------------------------------");
    }

    public void mostrarListaProductosComprados(){
        //muestra los productos que la tienda COMPRO con el precio que lo compro.
        System.out.println("-----------------------------------");
        System.out.println( ColorConsola.AMARILLO +"\t\t\tLISTA DE PRODUCTOS COMPRADOS"+ ColorConsola.RESET);
        System.out.println(ColorConsola.VERDE + "*NOTA: PRECIO ES EL VALOR COSTO, es decir el valor al que la tienda compro el producto" + ColorConsola.RESET);
        for(Producto producto : this.listaProductosStock){
            System.out.println("-----------------------------------");
            System.out.println(producto);
            System.out.println("-----------------------------------");
        }
        System.out.println("-----------------------------------");
    }

    //endregion


    //Listas de productos
    public void actualizarListaProductosDisponibles(){
        //recorre la lista y elimina los productos que no esten disponibles para la venta

        //NO la utilizare ya que releyendo los items del TP, no dice en ningun momento que debera ser
        //eliminado el producto de la lista, sino que solo debera ser eliminado el producto DE LA VENTA.
        this.listaProductosStock.removeIf(producto -> !producto.getEstaDisponible());

    }

    public boolean existeProductoSegunCodigo(String codigo){
        boolean resp = false;

        for(Producto producto : listaProductosStock){
            if (codigo.equals(producto.getCodigo())) {
                resp = true;
                break;
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
    public void imprimirDetalleLineaProductoVenta(Producto producto, int unidades){
        System.out.println(producto.getCodigo() + " " + producto.getDescripcion() + " " + unidades + "u. x $" + producto.obtenerPrecioFinalVenta());
    }
    public void imprimirDetalleLineaProductoCompra(Producto producto, int unidades){
        System.out.println(producto.getCodigo() + " " + producto.getDescripcion() + " " + unidades + "u. x $" + producto.getPrecio() + " = $" + unidades*producto.getPrecio());
    }

    public void imprimirDetalleVenta(Map<Producto, Integer> productosVendidos, float totalVenta, ArrayList<String> mensajesVenta){
        System.out.println("----------------------------------");
        System.out.println( ColorConsola.AMARILLO + "\t\t DETALLE DE VENTA" + ColorConsola.RESET);
        System.out.println("----------------------------------");
        productosVendidos.forEach(this::imprimirDetalleLineaProductoVenta);

        System.out.println("----------------------------------");
        mensajesVenta.forEach(System.out::println);
        System.out.println("----------------------------------");

        System.out.println(ColorConsola.VERDE + "TOTAL VENTA: $" + totalVenta + ColorConsola.RESET);
        System.out.println("----------------------------------\n");

    }

    public boolean esUnidadValida(int unidadesProducto){
        //verifica si las unidades que se ingresaron no exceden de las 12 unidades o son menores a 1 unidad, retorna true si cumple con los requisitos
        return (unidadesProducto >= 1 && unidadesProducto <= 12);
    }

    //Descuentos
    public void aplicarDescuentoProducto(String codigo, float porcentajeDescuento){
        float descuentoAplicado = 0.0f;
        Producto producto = obtenerProductoDeListaSegunCodigo(codigo);

        if( producto != null){
            if(porcentajeDescuento > 0){
                descuentoAplicado = producto.aplicarDescuento(porcentajeDescuento);
                if(descuentoAplicado > 0){
                    System.out.println(ColorConsola.VERDE +"Se ha aplicado al producto con codigo " + producto.getCodigo() +
                            " un descuento del %" + descuentoAplicado + ColorConsola.RESET);
                }
            }else{
                System.out.println(ColorConsola.ROJO +"El porcentaje de descuento debe ser mayor a 0" + ColorConsola.RESET);
            }
        }else{
            System.out.println(ColorConsola.ROJO + "Producto no encontrado, por favor verifique el código ingresado" + ColorConsola.RESET);
        }
    }

    //TODO: agregar metodo de eliminar descuentoProducto
    //cuando el usuario elimine el descuento del producto el precio final debe ser 0

    public void aplicarPorcentajeGanancia(String codigo, float porcentajeGanancia){
        float porcGananciaAplicado = 0.0f;
        Producto producto = obtenerProductoDeListaSegunCodigo(codigo);

        if(producto != null){
            if(porcentajeGanancia > 0){
                porcGananciaAplicado = producto.aplicarPorcentajeGanancia(porcentajeGanancia);
                if(porcGananciaAplicado > 0){
                    System.out.println(ColorConsola.VERDE + "Se ha aplicado al producto con codigo " + producto.getCodigo() +
                            " un porcentaje de ganancia del %" + porcGananciaAplicado + ColorConsola.RESET);
                }
            }else{
                System.out.println(ColorConsola.ROJO + "El porcentaje de ganancia debe ser mayor a 0" + ColorConsola.RESET);
            }
        }else{
            System.out.println(ColorConsola.ROJO + "Producto no encontrado, por favor verifique el código ingresado" + ColorConsola.RESET);
        }
    }

    public void mostrarDatosTienda(){
        System.out.println("---------------------------");
        System.out.println("\t\t\tMI TIENDA");
        System.out.println(this);
        System.out.println("---------------------------");
    }

    private void agregarProductoALaListaCorrespondiente(Producto producto) {
        if (producto instanceof Limpieza) {
            this.listaProductosLimpieza.add(producto.getCodigo());
        } else if (producto instanceof Bebida) {
            this.listaProductosBebidas.add(producto.getCodigo());
        } else if (producto instanceof Envasado) {
            this.listaProductosEnvasados.add(producto.getCodigo());
        }
    }


    @Override
    public String toString() {
        return "\t\t\t Tienda" +
                "\n Nombre: " + nombre +
                "\n Stock Máximo: " + stockMax +
                "\n Saldo de Caja: $" + saldoCaja;
    }
}
