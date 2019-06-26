package Usuario;

import javax.swing.JOptionPane;

/**
 *
 * @author GAMING
 */
public class ListaSimpleImagen {

    private NodoImagen inicio, fin;

    public ListaSimpleImagen() {
        fin = inicio = null;
    }

    public NodoImagen getInicio() {
        return inicio;
    }

    public boolean estaVacia() {
        return inicio == null;

    }

    public void Insertar(String id) {
        NodoImagen nuevo = new NodoImagen(id);
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            if (Existe(id)) {
                JOptionPane.showMessageDialog(null, "La imagen " + id + " ya fue agregada", "ERROR", JOptionPane.ERROR_MESSAGE);
            } else {
                fin.setSiguiente(nuevo);
                fin = nuevo;
            }
        }
    }

    public boolean Existe(String id) {
        NodoImagen aux = inicio;
        while (aux != null) {
            if (aux.getIdImagen().equalsIgnoreCase(id)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public void Eliminar(String id) {
        if (Existe(id) && inicio != null) {
            if (id.equals(inicio.getIdImagen())) {
                inicio = inicio.getSiguiente();
            } else {
                NodoImagen aux = inicio;
                NodoImagen aux2 = inicio.getSiguiente();
                while (aux2 != null) {
                    if (aux2.getIdImagen().equals(id)) {
                        aux.setSiguiente(aux2.getSiguiente());
                        JOptionPane.showMessageDialog(null, "La imagen " + id + " fue eliminada de la lista", "Reporte", JOptionPane.INFORMATION_MESSAGE);
                        break;
                    }
                    aux = aux.getSiguiente();
                    aux2 = aux2.getSiguiente();
                }
            }

        }
    }

    public String ObtenerNodoImagen(String id) {
        String cadena = "";
        NodoImagen aux = inicio;
        while (aux != null) {
            cadena += id + aux.getIdImagen() + " [label = \"" + aux.getIdImagen() + "\"fillcolor=\"lightblue\", style=\"filled\"];\n";
            aux = aux.getSiguiente();
        }
        aux = inicio;
        while (aux.getSiguiente() != null) {
            cadena += id + aux.getIdImagen() + "->";
            aux = aux.getSiguiente();
            cadena += id + aux.getIdImagen() + "; \n";
        }
        return cadena;
    }

    public String InicioLista() {
        return inicio.getIdImagen();
    }

}
