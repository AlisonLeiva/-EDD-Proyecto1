package Usuario;

import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

/**
 *
 * @author GAMING
 */
public class ArbolAVL {

    private NodoAVL raiz;
    boolean esHijoIzq = true;
    String cadena = "";

    public ArbolAVL() {
        raiz = null;
    }

    public NodoAVL getRaiz() {
        return raiz;
    }

    public void Graficar8() {
        raiz.Graficar8();
    }

    public void Insertar(String dato, ListaSimpleImagen imagen) {
        NodoAVL nuevo = new NodoAVL(dato, imagen);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        raiz = InsertarNodo(raiz, nuevo);

    }

    private NodoAVL InsertarNodo(NodoAVL raiz, NodoAVL nuevo) {
        if (raiz == null) {
            raiz = nuevo;
        } else {
            if (raiz.getIdUsuario().compareToIgnoreCase(nuevo.getIdUsuario()) < 0) {
                raiz.setHijoDer(InsertarNodo(raiz.getHijoDer(), nuevo));
            } else if (raiz.getIdUsuario().compareToIgnoreCase(nuevo.getIdUsuario()) > 0) {
                raiz.setHijoIzq(InsertarNodo(raiz.getHijoIzq(), nuevo));
            } else {
                JOptionPane.showMessageDialog(null, "El usuario " + nuevo.getIdUsuario() + " ya fue agregado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        CalcularAltura(raiz);
        raiz = balancear(raiz);
        return raiz;
    }

    private NodoAVL balancear(NodoAVL nodo) {
        if (nodo.fe == 2 && nodo.getHijoIzq().fe == 1) {
            //ROTACION IZQ-IZQ
            return RotacionIzqIzq(nodo);
        } else if (nodo.fe == 2 && nodo.getHijoIzq().fe == -1) {
            //ROTACION IZQ-DER
            nodo = RotacionIzqDer(nodo);
        } else if (nodo.fe == -2 && nodo.getHijoDer().fe == 1) {
            //ROTACION DER-IZQ
            nodo = RotacionDerIzq(nodo);
        } else if (nodo.fe == -2 && nodo.getHijoDer().fe == -1) {
            //ROTACION DER-DER  
            nodo = RotacionDerDer(nodo);
        }
        return nodo;
    }

    private NodoAVL RotacionIzqIzq(NodoAVL A) {
        NodoAVL B = A.getHijoIzq();   //B
        A.setHijoIzq(B.getHijoDer());    // A - > B2     
        B.setHijoDer(A);        // B -> A   
        CalcularAltura(A);
        CalcularAltura(B);
        return B;
    }

    private NodoAVL RotacionDerDer(NodoAVL A) {
        NodoAVL B = A.getHijoDer();   //B
        A.setHijoDer(B.getHijoIzq());    // A - > B2     
        B.setHijoIzq(A);
        CalcularAltura(A);
        CalcularAltura(B);        // B -> A        
        return B;
    }

    private NodoAVL RotacionIzqDer(NodoAVL A) {
        NodoAVL B = A.getHijoIzq();   // B
        NodoAVL C = B.getHijoDer();
        B.setHijoDer(C.getHijoIzq());
        C.setHijoIzq(B);
        A.setHijoIzq(C);
        CalcularAltura(C);
        CalcularAltura(B);
        return RotacionIzqIzq(A);
    }
    

    private NodoAVL RotacionDerIzq(NodoAVL A) {
        NodoAVL B = A.getHijoDer();   // B
        NodoAVL C = B.getHijoIzq();   // C
        B.setHijoIzq(C.getHijoDer());
        C.setHijoDer(B);
        A.setHijoDer(C);
        CalcularAltura(C);
        CalcularAltura(B);
        return RotacionDerDer(A);
    }

    private void CalcularAltura(NodoAVL nodo) {
        if (nodo.getHijoIzq() == null && nodo.getHijoDer() == null) {
            nodo.alturaIzq = 0;
            nodo.alturaDer = 0;
        } else if (nodo.getHijoDer() == null) {
            int mayor = Math.max(nodo.getHijoIzq().alturaIzq, nodo.getHijoIzq().alturaDer);
            nodo.alturaIzq = mayor + 1;
            nodo.alturaDer = 0;
        } else if (nodo.getHijoIzq() == null) {
            int mayor = Math.max(nodo.getHijoDer().alturaIzq, nodo.getHijoDer().alturaDer);
            nodo.alturaIzq = 0;
            nodo.alturaDer = mayor + 1;
        } else {
            int mayorDer = Math.max(nodo.getHijoDer().alturaIzq, nodo.getHijoDer().alturaDer);
            int mayorIzq = Math.max(nodo.getHijoIzq().alturaIzq, nodo.getHijoIzq().alturaDer);
            nodo.alturaIzq = mayorIzq + 1;
            nodo.alturaDer = mayorDer + 1;
        }
        nodo.fe = nodo.alturaIzq - nodo.alturaDer;
    }

//***************************************************** METODOS PARA GRAFICAR ARBOL AVL CON ELACE A LA LISTA ENLAZADA SIMPLE ****************************************************************************
    /*   public void Graficar9(ArbolAVL arbol) {
        ObtenerListaImagen(arbol.raiz);
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Ilustracion9.txt");
            escritor = new PrintWriter(fichero);
            escritor.print(CodigoGraphviz());
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Ilustracion9.txt");
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o Ilustracion9.jpg Ilustracion9.txt");
            Thread.sleep(500);
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Ilustracion9.txt");
        }
    }

    private String CodigoGraphviz() {
        return "digraph grafica{\n"
                + "rankdir=TB;\n"
                + "node [shape = record, style=filled, fillcolor=\"#F1ADA2\"];\n"
                + raiz.recorrerArbol()
                + cadena
                + "}\n";
    }

    private void ObtenerListaImagen(NodoAVL aux) {
        if (aux != null) {
            ObtenerListaImagen(aux.getHijoIzq());
            if (!aux.getListaImagenes().estaVacia()) {
                cadena += aux.getListaImagenes().ObtenerNodoImagen(aux.getIdUsuario());
                cadena += "nodo" + aux.getIdUsuario() + " -> " + aux.getIdUsuario() + aux.getListaImagenes().InicioLista() + "[style=\"solid\", color=\"red\" ];\n";
            }
            ObtenerListaImagen(aux.getHijoDer());
        }
    }*/
}
