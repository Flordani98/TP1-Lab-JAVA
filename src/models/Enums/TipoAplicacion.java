package models.Enums;

public enum TipoAplicacion {
    COCINA("Cocina"),
    BANIO("Baño"),
    ROPA("Ropa"),
    MULTIUSO ("Multiuso");

    //los enum son inmutables, por lo que su atributo nombre, tambien debe serlo, por esa razon se declara como final
    private final String nombre;

    TipoAplicacion(String nombre){
        this.nombre = nombre;
    }

    public String getNombre(){
        return nombre;
    }

}
