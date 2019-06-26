package Capa.ABB;

import Capa.Matriz;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author GAMING
 */
public class NodoABB {

    String idCapa;
    Matriz matriz;
    NodoABB hijoIzq, hijoDer;

    public NodoABB(String cod, Matriz matriz) {
        this.matriz = matriz;
        this.idCapa = cod;
        this.hijoDer = null;
        this.hijoIzq = null;
    }

    public NodoABB() {

    }

    public Matriz getMatriz() {
        return matriz;
    }

    public String getIdCapa() {
        return idCapa;
    }

    public NodoABB getHijoIzq() {
        return hijoIzq;
    }

    public NodoABB getHijoDer() {
        return hijoDer;
    }

//***************************************************** METODOS PARA GRAFICAR ARBOL BINARIO DE BUSQUEDA ****************************************************************************
    public void Graficar4() {
        try {
            FileWriter fichero = new FileWriter("Ilustracion4.txt");
            PrintWriter print = new PrintWriter(fichero);
            print.print(CodigoGraphviz());
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Ilustracion4.txt");
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o " + "Ilustracion4.jpg" + " Ilustracion4.txt");
            Desktop img = Desktop.getDesktop();
            img.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Ilustracion4.jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Ilustracion4.txt");
        }
    }

    private String CodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=\"#F1ADA2\"];\n"
                + RecorrerArbol()
                + "}\n";
    }

    public String RecorrerArbol() {
        String etiqueta;
        if (hijoIzq == null && hijoDer == null) {
            etiqueta = "nodo" + idCapa + " [ label =\"<C0>|" + idCapa + "|<C1>\"];\n";
        } else {
            etiqueta = "nodo" + idCapa + " [ label =\"<C0>|" + idCapa + "|<C1>\"];\n";
        }
        if (hijoIzq != null) {
            etiqueta = etiqueta + hijoIzq.RecorrerArbol()
                    + "nodo" + idCapa + ":C0->nodo" + hijoIzq.idCapa + "\n";
        }
        if (hijoDer != null) {
            etiqueta = etiqueta + hijoDer.RecorrerArbol()
                    + "nodo" + idCapa + ":C1->nodo" + hijoDer.idCapa + "\n";
        }
        return etiqueta;
    }
}
