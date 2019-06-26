package Capa;

/**
 *
 * @author GAMING
 */
public class ListaLateral {

    NodoLateral inicio, fin;
    int size;

    public ListaLateral() {
        inicio = fin = null;
        size = 0;
    }

    public NodoLateral getInicio() {
        return inicio;
    }

    public int getSize() {
        return size;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void Insertar(int codigo) {
        NodoLateral nuevo = new NodoLateral(codigo);
        if (estaVacia()) {
            fin = inicio = nuevo;
        } else {
            if (codigo < inicio.idFila) {
                insertarInicio(nuevo);
            } else if (codigo > fin.idFila) {
                insertarFinal(nuevo);
            } else {
                insertarMedio(codigo, nuevo);
            }
        }
        size++;

    }

    private void insertarInicio(NodoLateral nuevo) {
        inicio.anterior = nuevo;
        nuevo.anterior = null;
        nuevo.siguiente = inicio;
        inicio = nuevo;
    }

    private void insertarFinal(NodoLateral nuevo) {
        fin.siguiente = nuevo;
        nuevo.anterior = fin;
        nuevo.siguiente = null;
        fin = nuevo;
    }

    private void insertarMedio(int id, NodoLateral nuevo) {
        NodoLateral aux = inicio;
        for (int i = 0; i < size; i++) {
            if (id < aux.siguiente.idFila) {
                NodoLateral aux2 = aux.siguiente;
                aux.siguiente = nuevo;
                nuevo.anterior = aux;
                nuevo.siguiente = aux2;
                aux2.anterior = nuevo;
                break;
            }
            aux = aux.siguiente;
        }
    }

    public NodoLateral Buscar(int fila) {
        NodoLateral aux = inicio;
        while (aux != null) {
            if (aux.idFila == fila) {
                return aux;
            }
            aux = aux.getSiguiente();

        }
        return null;
    }

    public String RecorridoListaLateral() {
        String cadena = "";
        NodoLateral aux = inicio;
        cadena += "Mt -> L" + inicio.idFila + "\n";
        for (int i = 0; i < size; i++) {
            cadena = cadena + "L" + aux.idFila + "[label=\"" + aux.idFila + "\",rankdir=LR]; \n";
            aux = aux.siguiente;
        }
        aux = inicio;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "L" + aux.idFila + "->";
            aux = aux.siguiente;
            cadena = cadena + "L" + aux.idFila + "; \n";
        }
        aux = fin;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "L" + aux.idFila + "->";
            aux = aux.anterior;
            cadena = cadena + "L" + aux.idFila + "; \n";
        }
        return cadena;
    }

}
