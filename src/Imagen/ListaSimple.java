package Imagen;

import Capa.ABB.ABB;
import Capa.ABB.NodoABB;
import Capa.Matriz;
import Capa.NodoLateral;
import Capa.NodoMatriz;
import Capa.NodoSuperior;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author GAMING
 */
public class ListaSimple {

    public NodoCapa inicio, fin;

    public ListaSimple() {
        fin = inicio = null;
    }

    public boolean estaVacia() {
        return inicio == null;
    }

    public void Insertar(String id) {
        NodoCapa nuevo = new NodoCapa(id);
        if (estaVacia()) {
            inicio = fin = nuevo;
        } else {
            if (!Existe(id)) {
                fin.setSiguiente(nuevo);
                fin = nuevo;
            }
        }
    }

    public boolean Existe(String id) {
        NodoCapa aux = inicio;
        while (aux != null) {
            if (aux.idCapa.equalsIgnoreCase(id)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public String ObtenerNodoCapas(String id) {
        String cadena = "";
        NodoCapa aux = inicio;
        while (aux != null) {
            cadena += "nodo" + aux.idCapa + id + " [label = \"" + aux.idCapa + "\"fillcolor=\"lightblue\", style=\"filled\"];\n";
            aux = aux.getSiguiente();
        }
        aux = inicio;
        while (aux.siguiente != null) {
            cadena += "nodo" + aux.idCapa + id + "->";
            aux = aux.getSiguiente();
            cadena += "nodo" + aux.idCapa + id + "; \n";
        }
        return cadena;
    }

    public String ObtenerCapa() {
        return inicio.idCapa;
    }

    public String EnlaceArbol(String id, ABB arbol) {
        String cadena = "";
        NodoCapa aux = inicio;
        cadena += arbol.ObtenerArbol();
        while (aux != null) {
            NodoABB nodo = arbol.Buscar(aux.idCapa);
            if (nodo != null) {
                cadena += "nodo" + nodo.getIdCapa() + id + " -> " + "nodo" + nodo.getIdCapa() + "[style=\"solid\", color=\"red\" ];\n";
            }

            aux = aux.getSiguiente();
        }
        return cadena;
    }

    public void GenerarImagen(String id, ABB arbol) {
        NodoCapa aux = inicio;
        Matriz matriz = new Matriz();
        while (aux != null) {
            NodoABB nodo = arbol.Buscar(aux.idCapa);
            if (nodo != null) {
                arbol.GenerarCapa(nodo.getIdCapa(), matriz);
            }
            aux = aux.getSiguiente();
        }
        String cadena = "";
        if (estaVacia()) {
            System.out.println("ENTRO A IMAGEN NO EXISTEEEEEEEEE");
            cadena = "digraph imagen{\n"
                    + "node [shape=plaintext]\n"
                    + "a [label=<<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n"
                    + "<TR>\n"
                    + "<TD WIDTH=\"50\" HEIGHT=\"50\" BGCOLOR=\"#000000\"> </TD>\n"
                    + "</TR>\n"
                    + "</TABLE>>];\n}";
        } else {
            cadena = "digraph imagen{\n"
                    + "\n"
                    + "node [shape=plaintext]\n"
                    + "a [label=<<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">";
            NodoLateral lateral = matriz.getFilas().getInicio();
            NodoSuperior superior;
            for (int x = 0; x < matriz.getFilas().getSize(); x++) {
                superior = matriz.getColumnas().getInicio();
                cadena += "\n<TR>\n";
                for (int y = 0; y < matriz.getColumnas().getSize(); y++) {
                    cadena += lateral.getFila().GenerarTabla(lateral.getIdFila(), superior.getIdColumna());
                    superior = superior.getSiguiente();
                }
                cadena += "\n</TR>\n";

                lateral = lateral.getSiguiente();

            }
            cadena += "</TABLE>>];\n}";
        }

        FileWriter fichero;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Imagen" + id + ".txt");
            escritor = new PrintWriter(fichero);
            escritor.print(cadena);
            fichero.close();
        } catch (Exception e) {
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o  Imagen" + id + ".jpg  Imagen" + id + ".txt");
            Thread.sleep(700);
            JOptionPane.showMessageDialog(null, "Imagen " + id + " generada con éxito!!", "Reporte", JOptionPane.INFORMATION_MESSAGE);
            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Imagen" + id + ".jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar  Imagen" + id + ".jpg");
        }
    }

}
