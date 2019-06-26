package Capa;

/**
 *
 * @author GAMING
 */
public class ListaSuperior {

    NodoSuperior inicio, fin;
    int size;

    public NodoSuperior getInicio() {
        return inicio;
    }

    public int getSize() {
        return size;
    }

    public ListaSuperior() {
        inicio = fin = null;
        int size = 0;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void Insertar(int columna) {
        NodoSuperior nuevo = new NodoSuperior(columna);
        if (estaVacia()) {
            fin = inicio = nuevo;
        } else {
            if (columna < inicio.idColumna) {
                insertarInicio(nuevo);
            } else if (columna > fin.idColumna) {
                insertarFinal(nuevo);
            } else {
                insertarMedio(columna, nuevo);
            }
        }
        size++;

    }

    private void insertarInicio(NodoSuperior nuevo) {
        inicio.anterior = nuevo;
        nuevo.anterior = null;
        nuevo.siguiente = inicio;
        inicio = nuevo;
    }

    private void insertarFinal(NodoSuperior nuevo) {
        fin.siguiente = nuevo;
        nuevo.anterior = fin;
        nuevo.siguiente = null;
        fin = nuevo;
    }

    private void insertarMedio(int id, NodoSuperior nuevo) {
        NodoSuperior aux = inicio;
        for (int i = 0; i < size; i++) {
            if (id < aux.siguiente.idColumna) {
                NodoSuperior aux2 = aux.siguiente;
                aux.siguiente = nuevo;
                nuevo.anterior = aux;
                nuevo.siguiente = aux2;
                aux2.anterior = nuevo;
                break;
            }
            aux = aux.siguiente;
        }
    }

    public NodoSuperior Buscar(int columna) {
        NodoSuperior aux = inicio;
        while (aux != null) {
            if (aux.idColumna == columna) {
                return aux;
            }
            aux = aux.siguiente;
        }
        return null;
    }

    public String RecorridoListaSuperior() {
        String cadena = "\n{ \n rank=min;\nMt[label=\"matriz\" ];\n";
        NodoSuperior aux = inicio;
        cadena += "Mt -> S" + inicio.idColumna + ";\n";
        for (int i = 0; i < size; i++) {
            cadena = cadena + "S" + aux.idColumna + "[label=\"" + aux.idColumna + "\",rankdir=LR fillcolor=\"mistyrose2\", style=\"filled\"]; \n";
            aux = aux.siguiente;
        }
        aux = inicio;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "S" + aux.idColumna + "->";
            aux = aux.siguiente;
            cadena = cadena + "S" + aux.idColumna + "; \n";
        }
        aux = fin;
        for (int i = 0; i < size - 1; i++) {
            cadena = cadena + "S" + aux.idColumna + "->";
            aux = aux.anterior;
            cadena = cadena + "S" + aux.idColumna + "; \n";
        }
        cadena += "}\n";
        return cadena;
    }
}
