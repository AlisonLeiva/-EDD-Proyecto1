package Imagen;

import Capa.ABB.ABB;
import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;

public class ListaCircularDoble {

    NodoLista inicio, fin;
    int size;

    public NodoLista getInicio() {
        return inicio;
    }

    public ListaCircularDoble() {
        inicio = fin = null;
        size = 0;
    }

    public void Insertar(String id, ListaSimple lista) {
        NodoLista nuevo = new NodoLista(id, lista);
        if (!Existe(id)) {
            if (estaVacia()) {
                inicio = nuevo;
                inicio.setSiguiente(inicio);
                nuevo.setAnterior(fin);
                fin = nuevo;
            } else {
                if (id.compareToIgnoreCase(inicio.getIdImagen()) < 0) {
                    inicio.setAnterior(nuevo);
                    nuevo.setSiguiente(inicio);
                    nuevo.setAnterior(fin);
                    inicio = nuevo;
                    fin.setSiguiente(inicio);
                } else if (id.compareToIgnoreCase(fin.getIdImagen()) > 0) {
                    fin.setSiguiente(nuevo);
                    nuevo.setSiguiente(inicio);
                    nuevo.setAnterior(fin);
                    fin = nuevo;
                    inicio.setAnterior(fin);
                } else {
                    NodoLista aux = inicio;
                    do {
                        if (id.compareToIgnoreCase(aux.getSiguiente().getIdImagen()) < 0) {
                            NodoLista aux2 = aux.getSiguiente();
                            aux.setSiguiente(nuevo);
                            nuevo.setAnterior(aux);
                            nuevo.setSiguiente(aux2);
                            aux2.setAnterior(nuevo);
                            break;
                        }
                        aux = aux.getSiguiente();
                    } while (aux != inicio);
                }
            }
            size++;
        }
    }

    public boolean estaVacia() {
        return inicio == null;

    }

    public NodoLista Buscar(String cod) {
        NodoLista aux = inicio;
        for (int i = 0; i < size; i++) {
            if (aux.getIdImagen().equals(cod)) {
                return aux;
            }
            aux = aux.getSiguiente();

        }
        return null;
    }

    public boolean Existe(String img) {
        NodoLista aux = inicio;
        for (int i = 0; i < size; i++) {
            if (aux.getIdImagen().equals(img)) {
                return true;
            }
            aux = aux.getSiguiente();
        }
        return false;
    }

    public void Graficar6() {
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
                fichero = new FileWriter("Ilustracion6.txt");
                escritor = new PrintWriter(fichero);
                escritor.print("digraph G {\n"
                        + "ranksep = 0.5;\n"
                        + "node [fontname=\"Calibri Light\", width = 1.5];"
                        + Enlace_Lista_Capa("Todos", null)
                        + ObtenerNodoListasCircular()
                        + "}\n");
            

            fichero.close();
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Ilustracion6.txt");
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o Ilustracion6.jpg Ilustracion6.txt");
            Thread.sleep(700);
            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Ilustracion6.jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Ilustracion6.txt");
        }
    }

    public void Graficar7(String imagen, ABB arbol, String nombre) {

        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Ilustracion7_id" + imagen + ".txt");
            escritor = new PrintWriter(fichero);
            escritor.print(SeleccionarImagen(imagen, arbol));
            fichero.close();
        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Ilustracion7_id" + imagen + ".txt");
        }
        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o Ilustracion7_id" + imagen + ".jpg Ilustracion7_id" + imagen + ".txt");
            Thread.sleep(700);
            Desktop img = Desktop.getDesktop();
            img.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Ilustracion7_id" + nombre + ".jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Ilustracion7_id" + imagen + ".txt");
        }
    }

    private String Enlace_Lista_Capa(String cantidad, ABB arbol) {
        String cadena = "";
        NodoLista aux = inicio;

        if (cantidad.equals("Todos")) {
            do {
                if (!aux.getListaCapas().estaVacia()) {
                    cadena += aux.getListaCapas().ObtenerNodoCapas(aux.getIdImagen());
                    cadena += "NodoLista" + aux.getIdImagen() + " ->  nodo" + aux.getListaCapas().ObtenerCapa() + aux.getIdImagen() + "[style=\"solid\", color=\"red\" ];";
                }
                aux = aux.getSiguiente();
            } while (aux != inicio);
        } else {
            aux = inicio;
            do {
                if (cantidad.equals(aux.getIdImagen()) && (!aux.getListaCapas().estaVacia())) {
                    cadena = aux.getListaCapas().ObtenerNodoCapas(aux.getIdImagen());
                    cadena += "NodoLista" + aux.getIdImagen() + " -> nodo" + aux.getListaCapas().ObtenerCapa() + aux.getIdImagen() + "[style=\"solid\", color=\"red\" ];";
                    cadena += aux.getListaCapas().EnlaceArbol(aux.getIdImagen(), arbol);
                    break;
                }
                aux = aux.getSiguiente();
            } while (aux != inicio);
        }
        return cadena;
    }

    private String ObtenerNodoListasCircular() {
        String cadena = "{\nrank=same;";
        NodoLista aux = inicio;

        for (int i = 0; i < size; i++) {
            cadena += "NodoLista" + aux.getIdImagen() + " [label = \"" + aux.getIdImagen() + "\" fillcolor=\"mistyrose2\", style=\"filled\" ];\n";
            aux = aux.getSiguiente();
        }

        if (size == 1) {
            cadena += "NodoLista" + aux.getIdImagen() + "-> NodoLista" + aux.getIdImagen();

        } else {
            aux = inicio;
            do {
                cadena += "NodoLista" + aux.getIdImagen() + "->";
                aux = aux.getSiguiente();
                cadena += "NodoLista" + aux.getIdImagen() + "; \n";
            } while (aux != inicio);

            aux = fin;
            do {
                cadena = cadena + "NodoLista" + aux.getIdImagen() + "->";
                aux = aux.getAnterior();
                cadena = cadena + "NodoLista" + aux.getIdImagen() + "; \n";
            } while (aux != fin);
        }

        return cadena + "}";
    }

    private String SeleccionarImagen(String idImagen, ABB arbol) {
        return "digraph G {\n"
                + "ranksep = 0.5;\n"
                + "node [fontname=\"Calibri Light\", width = 1.5];"
                + "NodoLista" + idImagen + " [label = \"" + idImagen + "\" fillcolor=\"mistyrose2\", style=\"filled\" ];"
                + Enlace_Lista_Capa(idImagen, arbol)
                + "}\n";
    }

    public void ImagenExiste(String idImagen, ABB arbol) {
        NodoLista aux = Buscar(idImagen);
        if (aux != null) {
            aux.getListaCapas().GenerarImagen(idImagen, arbol);
        } 
    }

}
