package Capa;

import java.awt.Desktop;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import javax.swing.JOptionPane;

public class Matriz {

    ListaSuperior columnas;
    ListaLateral filas;

    public ListaSuperior getColumnas() {
        return columnas;
    }

    public ListaLateral getFilas() {
        return filas;
    }

    public Matriz() {
        this.columnas = new ListaSuperior();
        this.filas = new ListaLateral();
    }

    public void Insertar(Imagen imagen) {
        NodoSuperior superior = columnas.Buscar(imagen.columna);
        NodoLateral lateral = filas.Buscar(imagen.fila);
        if (superior == null) {
            columnas.Insertar(imagen.columna);
        }
        if (lateral == null) {
            filas.Insertar(imagen.fila);
        }
        superior = columnas.Buscar(imagen.columna);
        lateral = filas.Buscar(imagen.fila);

        NodoMatriz nodo = new NodoMatriz(imagen);
        lateral.fila.Insertar(nodo);
        superior.columna.Insertar(nodo);
    }

    private void graficar(String id) {
        FileWriter fichero = null;
        PrintWriter escritor;
        //   System.out.println(CodigoGraphviz());

        try {
            fichero = new FileWriter("MatrizOrtogonal" + id + ".txt");
            escritor = new PrintWriter(fichero);
            escritor.print(CodigoGraphviz());
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo MatrizOrtogonal" + id + ".txt");
        }

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o  MatrizOrtogonal" + id + ".jpg  MatrizOrtogonal" + id + ".txt");
            Thread.sleep(500);
            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/MatrizOrtogonal" + id + ".jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo MatrizOrtogonal" + id + ".txt");
        }
    }

    private String CodigoGraphviz() {
        return "digraph matriz{\n"
                + "rankdir=UD;\n"
                + "node[shape=box];"
                + columnas.RecorridoListaSuperior()
                + EnlacesFila()
                + filas.RecorridoListaLateral()
                + EnlacesColumna()
                + EnlacesXY() + "\n}";
    }

    public String EnlacesFila() {
        String cadena = "";
        NodoLateral x = filas.inicio;
        while (x != null) {
            cadena += "{\nrank=same;\n";
            cadena += "L" + x.idFila + " [label =\"" + x.idFila + "\" fillcolor=\"mistyrose2\", style=\"filled\"];\n" + x.fila.Fila();
            x = x.siguiente;
            cadena += "\n}\n";
        }
        return cadena;
    }

    public String EnlacesColumna() {
        String cadena = "";
        NodoSuperior aux = columnas.inicio;
        for (int i = 0; i < columnas.size; i++) {
            cadena += aux.columna.Columna();
            aux = aux.siguiente;
            cadena += "\n";
        }
        return cadena;
    }

    public String EnlacesXY() {
        String cadena = "";
        NodoLateral x = filas.inicio;
        while (x != null) {
            cadena += "L" + x.idFila + " -> " + "Nodo" + x.fila.inicio.imagen.id + "\n";
            x = x.siguiente;
        }
        NodoSuperior y = columnas.inicio;
        while (y != null) {
            cadena += "S" + y.idColumna + " -> " + "Nodo" + y.columna.inicio.imagen.id + "\n";
            y = y.siguiente;
        }

        return cadena;
    }

    public void GenerarImagen(String id) {
        graficar(id);
        String cadena = "digraph immagen{\n"
                + "\n"
                + "node [shape=plaintext]\n"
                + "a [label=<<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">";
        NodoLateral lateral = filas.inicio;
        NodoSuperior superior;
        for (int x = 0; x < filas.size; x++) {
            superior = columnas.inicio;
            cadena += "\n<TR>\n";
            for (int y = 0; y < columnas.size; y++) {
                cadena += lateral.fila.GenerarTabla(lateral.idFila, superior.idColumna);
                superior = superior.getSiguiente();
            }
            cadena += "\n</TR>\n";

            lateral = lateral.getSiguiente();

        }
        cadena += "</TABLE>>];\n}";

        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("ImagenCapa_" + id + ".txt");
            escritor = new PrintWriter(fichero);
            escritor.print(cadena);
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo ImagenCapa_" + id + ".txt");
        }

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o  ImagenCapa_" + id + ".jpg  ImagenCapa_" + id + ".txt");
            Thread.sleep(700);
            JOptionPane.showMessageDialog(null, "Imagen " + id + " generada con éxito!!", "Reporte", JOptionPane.INFORMATION_MESSAGE);
            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/ImagenCapa_" + id + ".jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo ImagenCapa_" + id + ".txt");
        }

    }

    public void GenerarCapa(Matriz matriz) {
        NodoLateral lateral = filas.inicio;
        for (int x = 0; x < filas.size; x++) {
            lateral.fila.ObtenerPosiciones(matriz);
            lateral = lateral.getSiguiente();
        }
    }

    /* public static void main(String[] args) {
        Matriz matriz = new Matriz();
        int contador = 1;
        //COLUMNA - FILA
        matriz.Insertar(new Imagen(contador++, 9, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 10, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 11, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 12, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 13, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 13, 6, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 13, 7, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 13, 8, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 9, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 10, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 11, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 12, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 13, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 14, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 15, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 14, 3, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 14, 4, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 14, 5, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 14, 6, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 9, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 12, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 15, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 16, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 17, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 18, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 4, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 5, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 9, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 12, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 16, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 17, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 5, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 16, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 5, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 6, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 15, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 16, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 6, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 7, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 8, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 9, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 10, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 11, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 12, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 13, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 14, "#E74C3C"));
        matriz.Insertar(new Imagen(contador++, 3, 15, "#E74C3C"));
    //    matriz.graficar(contador);
    }*/
}
