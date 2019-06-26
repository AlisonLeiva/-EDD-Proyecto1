package Imagen;

import Capa.ABB.ABB;

/**
 *
 * @author GAMING
 */
public class NodoCapa {

    public String idCapa;
    NodoCapa siguiente, anterior;

    public NodoCapa(String idCapa) {
        this.idCapa = idCapa;
        this.siguiente = null;
        this.anterior = null;
        
    }

    public NodoCapa() {

    }

    public NodoCapa getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoCapa siguiente) {
        this.siguiente = siguiente;
    }

    public NodoCapa getAnterior() {
        return anterior;
    }

    public void setAnterior(NodoCapa anterior) {
        this.anterior = anterior;
    }

}
