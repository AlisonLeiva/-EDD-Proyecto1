package Capa;

/**
 *
 * @author GAMING
 */
public class NodoLateral {

    int idFila;
    NodoLateral siguiente, anterior;
    ListaHorizontal fila;

    public NodoLateral(int id) {
        this.idFila = id;
        siguiente = null;
        anterior = null;
        fila = new ListaHorizontal();
    }

    public int getIdFila() {
        return idFila;
    }

    public NodoLateral getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoLateral siguiente) {
        this.siguiente = siguiente;
    }

    public ListaHorizontal getFila() {
        return fila;
    }
}
