package Imagen;

/**
 *
 * @author GAMING
 */
public class NodoLista {

    private String idImagen;
    private NodoLista siguiente, anterior;
    private ListaSimple ListaCapas;

    public NodoLista(String idImagen, ListaSimple capas) {
        this.idImagen = idImagen;
        this.siguiente = null;
        this.anterior = null;
        this.ListaCapas = capas;
    }

    public NodoLista() {

    }

    public String getIdImagen() {
        return idImagen;
    }
    public NodoLista getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLista siguiente) {
        this.siguiente = siguiente;
    }

    public NodoLista getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoLista anterior) {
        this.anterior = anterior;
    }

    public ListaSimple getListaCapas() {
        return ListaCapas;
    }

}
