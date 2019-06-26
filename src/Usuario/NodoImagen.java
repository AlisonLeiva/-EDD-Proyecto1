package Usuario;

/**
 *
 * @author AlisonLeiva
 */
public class NodoImagen {

    private String idImagen;
    private NodoImagen siguiente;

    public NodoImagen(String idImagen) {
        this.idImagen = idImagen;
        this.siguiente = null;
    }

    public NodoImagen() {

    }

    public String getIdImagen() {
        return idImagen;
    }
    public NodoImagen getSiguiente() {
        return siguiente;
    }

    public void setSiguiente(NodoImagen siguiente) {
        this.siguiente = siguiente;
    }

}
