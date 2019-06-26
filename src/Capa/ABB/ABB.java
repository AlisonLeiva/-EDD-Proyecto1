package Capa.ABB;

import Capa.Matriz;
import javax.swing.JOptionPane;

/**
 *
 * @author GAMING
 */
public class ABB {

    NodoABB raiz;
    boolean esHijoIzq = true;

    public NodoABB getRaiz() {
        return raiz;
    }

    public void Graficar4() {
        raiz.Graficar4();
    }

    public void Insertar(String idCapa, Matriz matriz) {
        NodoABB nuevo = new NodoABB(idCapa, matriz);
        if (raiz == null) {
            raiz = nuevo;
            return;
        }

        raiz = insertarNodo(raiz, nuevo);
    }

    private NodoABB insertarNodo(NodoABB raiz, NodoABB nuevo) {
        if (raiz == null) {
            raiz = nuevo;
        } else {
            if (raiz.idCapa.compareToIgnoreCase(nuevo.idCapa) < 0) {
                raiz.hijoDer = insertarNodo(raiz.hijoDer, nuevo);
            } else if (raiz.idCapa.compareToIgnoreCase(nuevo.idCapa) > 0) {
                raiz.hijoIzq = insertarNodo(raiz.hijoIzq, nuevo);
            } else {
                JOptionPane.showMessageDialog(null, "La capa " + nuevo.getIdCapa() + " ya fue agregado", "ERROR", JOptionPane.ERROR_MESSAGE);
            }
        }
        return raiz;
    }

    public NodoABB Buscar(String cod) {
        NodoABB aux = raiz;
        while (aux != null) {
            if (cod.equals(aux.idCapa)) {
                return aux;
            }
            if (aux.idCapa.compareTo(cod) > 0) {
                aux = aux.hijoIzq;
            } else {
                aux = aux.hijoDer;
            }
        }
        return null;
    }

    public String ObtenerArbol() {
        String cadena = "\nsubgraph Arbol{\nnode [shape = record, style=filled, fillcolor=\"#F1ADA2\"];\n";
        cadena += raiz.RecorrerArbol() + "\n";
        return cadena + "}";
    }

    public void GenerarImagen(String idCapa) {
        NodoABB graficar = Buscar(idCapa);
        if (graficar != null) {
            graficar.matriz.GenerarImagen(idCapa);
        }
    }

    public void GenerarCapa(String idCapa, Matriz matriz) {
        NodoABB auxCapa = Buscar(idCapa);
        if (auxCapa != null) {
            auxCapa.matriz.GenerarCapa(matriz);
        }
    }
}
