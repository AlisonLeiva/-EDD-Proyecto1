package Capa;

/**
 *
 * @author GAMING
 */
public class ListaVertical {

    NodoMatriz inicio, fin;
    int size;

    public ListaVertical() {
        inicio = fin = null;
        size = 0;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public boolean Existe(int columna, int fila) {
        NodoMatriz aux = inicio;
        while (aux != null) {
            if (aux.imagen.columna == columna && aux.imagen.fila == fila) {
                return true;
            }
            aux = aux.abajo;
        }
        return false;
    }

    public void Insertar(NodoMatriz nuevo) {
        int codigo = nuevo.imagen.fila;
        if (!Existe(nuevo.imagen.columna, nuevo.imagen.fila)) {
            if (estaVacia()) {
                fin = inicio = nuevo;
            } else {
                if (codigo < inicio.imagen.fila) {
                    insertarInicio(nuevo);
                } else if (codigo > fin.imagen.fila) {
                    insertarFinal(nuevo);
                } else {
                    insertarMedio(codigo, nuevo);
                }
            }
            size++;
        }
    }

    private void insertarInicio(NodoMatriz nuevo) {
        inicio.arriba = nuevo;
        nuevo.arriba = null;
        nuevo.abajo = inicio;
        inicio = nuevo;
    }

    private void insertarFinal(NodoMatriz nuevo) {
        fin.abajo = nuevo;
        nuevo.arriba = fin;
        nuevo.abajo = null;
        fin = nuevo;
    }

    private void insertarMedio(int id, NodoMatriz nuevo) {
        NodoMatriz aux = inicio;
        while (aux.abajo != null) {
            if (id < aux.abajo.imagen.fila) {
                NodoMatriz aux2 = aux.abajo;
                aux.abajo = nuevo;
                nuevo.arriba = aux;
                nuevo.abajo = aux2;
                aux2.arriba = nuevo;
                break;
            }
            aux = aux.abajo;
        }
    }

    public String RecorrerListaVertical() {
        String cadena = "";
        NodoMatriz aux = inicio;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.abajo;
            cadena = cadena + "Nodo" + aux.imagen.id + "; \n";
        }
        aux = fin;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.arriba;
            cadena = cadena + "Nodo" + aux.imagen.id + "; \n";
        }
        return cadena;
    }

    public String Columna() {
        String cadena = "";
        NodoMatriz aux = inicio;
        while (aux.abajo != null) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.abajo;
            cadena = cadena + "Nodo" + aux.imagen.id + " [constraint=false]; \n";
        }
        aux = fin;
        while (aux.arriba != null) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.arriba;
            cadena = cadena + "Nodo" + aux.imagen.id + "; \n";
        }
        return cadena;
    }

}
