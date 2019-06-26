package Capa;

public class NodoSuperior {

    int idColumna;
    NodoSuperior siguiente, anterior;
    ListaVertical columna;

    public NodoSuperior(int id) {
        this.idColumna = id;
        siguiente = null;
        anterior = null;
        columna = new ListaVertical();
    }

    public int getIdColumna() {
        return idColumna;
    }

    public NodoSuperior getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoSuperior siguiente) {
        this.siguiente = siguiente;
    }
}
