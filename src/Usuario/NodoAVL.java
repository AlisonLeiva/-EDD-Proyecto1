package Usuario;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

/**
 *
 * @author GAMING
 */
public class NodoAVL {

    int fe, alturaDer, alturaIzq;
    private String idUsuario;
    private NodoAVL hijoIzq, hijoDer;
    private ListaSimpleImagen ListaImagenes;

    public NodoAVL(String cod, ListaSimpleImagen imagenes) {
        this.idUsuario = cod;
        this.hijoDer = null;
        this.hijoIzq = null;
        this.fe = 0;
        this.alturaDer = 0;
        this.alturaIzq = 0;
        this.ListaImagenes = imagenes;
    }

    public NodoAVL() {

    }

    public String getIdUsuario() {
        return idUsuario;
    }

    public NodoAVL getHijoIzq() {
        return hijoIzq;
    }

    public void setHijoIzq(NodoAVL hijoIzq) {
        this.hijoIzq = hijoIzq;
    }

    public NodoAVL getHijoDer() {
        return hijoDer;
    }

    public void setHijoDer(NodoAVL hijoDer) {
        this.hijoDer = hijoDer;
    }

    public ListaSimpleImagen getListaImagenes() {
        return ListaImagenes;
    }

    public String toString() {
        return idUsuario;
    }
//***************************************************** METODOS PARA GRAFICAR ARBOL AVL ****************************************************************************

    public void Graficar8() {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Ilustracion8.txt");
            escritor = new PrintWriter(fichero);
            escritor.print(CodigoGraphviz());
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Ilustracion8.txt");
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o Ilustracion8.jpg Ilustracion8.txt");
            Thread.sleep(500);
            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Ilustracion8.jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Ilustracion8.txt");
        }
    }

    private String CodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=\"#F1ADA2\"];\n"
                + recorrerArbol()
                + "}\n";
    }

    public String recorrerArbol() {
        String etiqueta;
        if (hijoIzq == null && hijoDer == null) {
            etiqueta = "nodo" + idUsuario + " [ label =\"<C0>|" + toString() + "|<C1>\"];\n";
        } else {
            etiqueta = "nodo" + idUsuario + " [ label =\"<C0>|" + toString() + "|<C1>\"];\n";
        }
        if (hijoIzq != null) {
            etiqueta += hijoIzq.recorrerArbol() + "nodo" + idUsuario + ":C0->nodo" + hijoIzq.idUsuario + "\n";
        }
        if (hijoDer != null) {
            etiqueta += hijoDer.recorrerArbol() + "nodo" + idUsuario + ":C1->nodo" + hijoDer.idUsuario + "\n";
        }
        return etiqueta;
    }
}
