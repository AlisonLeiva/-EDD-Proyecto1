package Capa;

/**
 *
 * @author GAMING
 */
public class NodoMatriz {

    Imagen imagen;
    NodoMatriz derecha, izquierda, arriba, abajo;

    public NodoMatriz(Imagen imagen) {
        this.imagen = imagen;
        derecha = null;
        izquierda = null;
        arriba = null;
        abajo = null;

    }

    public NodoMatriz getDerecha() {
        return derecha;
    }

    public void setDerecha(NodoMatriz siguiente) {
        this.derecha = siguiente;
    }

    public NodoMatriz getAbajo() {
        return abajo;
    }

    public void setAbajo(NodoMatriz abajo) {
        this.abajo = abajo;
    }
}
