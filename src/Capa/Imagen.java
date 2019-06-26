package Capa;

/**
 *
 * @author GAMING
 */
public class Imagen {

    String color;
    int fila;
    int columna;
    int id;

    public Imagen(int idImagen, int x, int y, String color) {
        this.columna = x;
        this.fila = y;
        this.id = idImagen;
        this.color = color;
    }

    public Imagen(int x, int y, String color) {
        this.columna = x;
        this.fila = y;
        this.color = color;
        // System.out.println("Columa "+ x +  " Fila "+y+ " Color "+color );
    }

    @Override
    public String toString() {
        return color + " " + fila + " " + columna;
    }

}
