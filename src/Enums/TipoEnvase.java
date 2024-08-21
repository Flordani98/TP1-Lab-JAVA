package Enums;

public enum TipoEnvase {
    LATA("Lata"),
    FRASCO("Frasco"),
    TETRA_PACK("Tetra Pack"),
    BLISTER("Blister"),
    BOTELLA_VIDRIO("Botella de vidrio"),
    BOTELLA_PLASTICO("Botella de plástico"),
    ENVASE_FLEXIBLE("Envase flexible"),
    ENVASE_PAPEL("Envase de papel");

    private final String nombre;

    TipoEnvase(String nombre) { this.nombre = nombre; }

    public String getNombre() { return this.nombre; }
}
