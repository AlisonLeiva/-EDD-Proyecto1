package Capa;

/**
 *
 * @author GAMING
 */
public class ListaHorizontal {

    NodoMatriz inicio, fin;
    int size;

    public ListaHorizontal() {
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
            aux = aux.derecha;
        }
        return false;
    }

    public void Insertar(NodoMatriz nuevo) {
        int codigo = nuevo.imagen.columna;
        if (!Existe(nuevo.imagen.columna, nuevo.imagen.fila)) {
            if (estaVacia()) {
                fin = inicio = nuevo;
            } else {
                if (codigo < inicio.imagen.columna) {
                    insertarInicio(nuevo);
                } else if (codigo > fin.imagen.columna) {
                    insertarFinal(nuevo);
                } else {
                    insertarMedio(codigo, nuevo);
                }
            }
            size++;

        }
    }

    private void insertarInicio(NodoMatriz nuevo) {
        inicio.izquierda = nuevo;
        nuevo.izquierda = null;
        nuevo.derecha = inicio;
        inicio = nuevo;
    }

    private void insertarFinal(NodoMatriz nuevo) {
        fin.derecha = nuevo;
        nuevo.izquierda = fin;
        nuevo.derecha = null;
        fin = nuevo;
    }

    private void insertarMedio(int id, NodoMatriz nuevo) {
        NodoMatriz aux = inicio;
        while (aux.derecha != null) {
            if (id < aux.derecha.imagen.columna) {
                NodoMatriz aux2 = aux.derecha;
                aux.derecha = nuevo;
                nuevo.izquierda = aux;
                nuevo.derecha = aux2;
                aux2.izquierda = nuevo;
                break;
            }
            aux = aux.derecha;
        }
    }

    public String RecorrerListaHorizontal() {
        String cadena = "";
        NodoMatriz aux = inicio;
        for (int i = 0; i < size - 1; i++) {
            cadena += "Nodo" + aux.imagen.id + "->";
            aux = aux.derecha;
            cadena = cadena + "Nodo" + aux.imagen.id + "; \n";
        }
        aux = fin;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.izquierda;
            cadena = cadena + "Nodo" + aux.imagen.id + "; \n";
        }
        return cadena;
    }

    public String Fila() {
        String cadena = "";
        NodoMatriz aux = inicio;
        while (aux != null) {
            cadena += "Nodo" + aux.imagen.id + "[label =\"" + aux.imagen.color + "\" fillcolor=\"#C9E0E3\", style=\"filled\"]; \n";
            //  System.out.print(aux.imagen.color + "->");
            aux = aux.derecha;
        }
        aux = inicio;
        while (aux.derecha != null) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.derecha;
            cadena = cadena + "Nodo" + aux.imagen.id + " [constraint=false]; \n";
        }
        aux = fin;
        while (aux.izquierda != null) {
            cadena = cadena + "Nodo" + aux.imagen.id + "->";
            aux = aux.izquierda;
            cadena = cadena + "Nodo" + aux.imagen.id + " [constraint=false]; \n";
        }
        return cadena;
    }

    public String GenerarTabla(int fila, int columna) {
        if(estaVacia()){
           return "<TD WIDTH=\"50\" HEIGHT=\"50\" BGCOLOR=\"#000000\"> </TD>\n";
        }
        
        if (Existe(columna, fila)) {
            return "<TD WIDTH=\"50\" HEIGHT=\"50\" BGCOLOR=\"" + ExisteNodo(columna, fila) + "\"> </TD>\n";
        } else {
            return "<TD WIDTH=\"50\" HEIGHT=\"50\" BGCOLOR=\"#FFFFFF\"> </TD>\n";
        }
    }

    public void ObtenerPosiciones(Matriz matriz) {
        NodoMatriz aux = inicio;
        //PRIMERO se guardan los datos en la matriz
        while (aux != null) {
            matriz.Insertar(new Imagen(aux.imagen.columna, aux.imagen.fila, aux.imagen.color));
            aux = aux.derecha;
        }
    }
    public String ExisteNodo(int columna, int fila) {
        NodoMatriz aux = inicio;
        while (aux != null) {
            if (aux.imagen.columna == columna && aux.imagen.fila == fila) {
                return aux.imagen.color;
            }
            aux = aux.derecha;
        }
        return "";
    }
}
