package Proyecto1;

import Capa.ABB.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.*;
import Capa.*;
import Imagen.*;
import Usuario.*;
import java.awt.Desktop;
import java.io.*;
import javax.swing.filechooser.FileNameExtensionFilter;

/**
 *
 * @author AlisonLeiva
 */
public class Principal {

    Ventana ventana;
    ABB arbol;
    ListaCircularDoble listaDoble;
    ArbolAVL arbolAVL;
    int contadorCapas = 1;

    public Principal() {
        ventana = new Ventana();
        arbol = new ABB();
        listaDoble = new ListaCircularDoble();
        arbolAVL = new ArbolAVL();

        ventana.comboCargaMasiva.addActionListener((ActionEvent e) -> {
            try {
                OpenFile();
            } catch (FileNotFoundException ex) {
                Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
            }
        });
        ventana.comboVisualizacion.addActionListener((ActionEvent e) -> {
            if (ventana.comboVisualizacion.getSelectedItem().toString().equals("Lista Imagenes(6)")) {
                listaDoble.Graficar6();
            } else if (ventana.comboVisualizacion.getSelectedItem().toString().equals("Arbol de Capas(4)")) {
                arbol.Graficar4();
            } else if (ventana.comboVisualizacion.getSelectedItem().toString().equals("Capa(2)")) {
                arbol.GenerarImagen(ventana.comboCapas.getSelectedItem().toString());
            } else if (ventana.comboVisualizacion.getSelectedItem().toString().equals("Imagen y Arbol(7)")) {
                listaDoble.Graficar7(ventana.comboImagenes.getSelectedItem().toString(), arbol, ventana.comboImagenes.getSelectedItem().toString().trim());
            } else if (ventana.comboVisualizacion.getSelectedItem().toString().equals("Arbol de Usuarios(8)")) {
                arbolAVL.Graficar8();
            }
        });
        ventana.btnCapa.addActionListener((ActionEvent e) -> {
            String idCapa = ventana.comboCapas.getSelectedItem().toString();
            arbol.GenerarImagen(idCapa);
        });

        ventana.btnImagen.addActionListener((ActionEvent e) -> {
            String idImagen = ventana.comboImagenes.getSelectedItem().toString();
            listaDoble.ImagenExiste(idImagen, arbol);
        });
        ventana.btnUsuario.addActionListener((ActionEvent e) -> {
            String idUsuario = ventana.comboUsuarios.getSelectedItem().toString();
            String idImagen = ventana.comboImg_Usuario.getSelectedItem().toString();
            GenerarImagenUsuario(arbolAVL.getRaiz(), idUsuario, idImagen);
        });

        ventana.btnCantidadCapas.addActionListener((ActionEvent e) -> {
            int cantidad = Integer.parseInt(ventana.CantidadCapa.getText());
            String recorrido = ventana.comboTipoRecorrido.getSelectedItem().toString();
            Matriz matriz = new Matriz();
            GenerarImagenRecorrido(cantidad, recorrido, matriz);
        });

        ventana.comboImagenes.addActionListener((ActionEvent e) -> {
            if (ventana.btnRadio.isSelected()) {
                ventana.comboGuardarImagenes.addItem(ventana.comboImagenes.getSelectedItem().toString());
            }
        });
        ventana.comboGuardarImagenes.addActionListener((ActionEvent e) -> {
            if (ventana.btnRadio.isSelected() && !ventana.btnRadio.getText().isEmpty()) {
                ventana.txtIdImagen.setText(ventana.comboImg_Usuario.getSelectedItem().toString());
            }
        });
        ventana.comboUsuarios.addActionListener((ActionEvent e) -> {
            if (ventana.btnRadio.isSelected()) {
                ventana.txtNombreUsuario.setText(ventana.comboUsuarios.getSelectedItem().toString());
            }
            ventana.comboImg_Usuario.removeAllItems();
            ObtenerImgUsuario(arbolAVL.getRaiz());

        });

        ventana.btnAgregar.addActionListener((ActionEvent e) -> {
            ventana.btnRadio.setSelected(false);
            AgregarUsuario();
            ventana.btnRadio.setSelected(false);
            LLenarComboboxUsuarios(arbolAVL.getRaiz());
        });

        ventana.btnModificar.addActionListener((ActionEvent e) -> {
            ventana.btnRadio.setSelected(false);
            if (ventana.btnModImagen.isSelected()) {
                AgregarImagenAUsuario(arbolAVL.getRaiz());
            } else if (ventana.btnEliImagen.isSelected()) {
                EliminarImagenDeUsuario(arbolAVL.getRaiz());
            }
            ventana.txtNombreUsuario.setText("");
            ventana.txtNuevaImagen.setText("");
            ventana.txtIdImagen.setText("");
            ventana.comboUsuarios.removeAllItems();
            ventana.comboGuardarImagenes.removeAllItems();
            LLenarComboboxUsuarios(arbolAVL.getRaiz());
        });
    }

    private void AgregarUsuario() {
        String usuario = ventana.txtNombreUsuario.getText();
        ListaSimpleImagen listaImagen = new ListaSimpleImagen();
        for (int i = 0; i < ventana.comboGuardarImagenes.getItemCount(); i++) {
            String imagen = ventana.comboGuardarImagenes.getItemAt(i).toString();
            listaImagen.Insertar(imagen);
        }
        arbolAVL.Insertar(usuario, listaImagen);
        ventana.txtNombreUsuario.setText("");
        ventana.comboUsuarios.removeAllItems();
        ventana.comboGuardarImagenes.removeAllItems();
    }

    private void ObtenerImgUsuario(NodoAVL aux) {
        if (aux != null && ventana.comboUsuarios.getItemCount() != 0) {
            ObtenerImgUsuario(aux.getHijoIzq());
            if (aux.getIdUsuario().equals(ventana.comboUsuarios.getSelectedItem().toString())) {
                NodoImagen aux2 = aux.getListaImagenes().getInicio();
                while (aux2 != null) {
                    ventana.comboImg_Usuario.addItem(aux2.getIdImagen());
                    aux2 = aux2.getSiguiente();
                }
            }
            ObtenerImgUsuario(aux.getHijoDer());
        }
    }

    private void EliminarImagenDeUsuario(NodoAVL aux) {
        if (aux != null) {
            EliminarImagenDeUsuario(aux.getHijoIzq());
            if (aux.getIdUsuario().equals(ventana.txtNombreUsuario.getText())) {
                NodoImagen aux2 = aux.getListaImagenes().getInicio();
                while (aux2 != null) {
                    if (aux2.getIdImagen().equals(ventana.txtIdImagen.getText())) {
                        aux.getListaImagenes().Eliminar(aux2.getIdImagen());
                        break;
                    }
                    aux2 = aux2.getSiguiente();
                }
            }
            EliminarImagenDeUsuario(aux.getHijoDer());
        }
    }

    private void AgregarImagenAUsuario(NodoAVL aux) {
        if (aux != null) {
            AgregarImagenAUsuario(aux.getHijoIzq());
            if (aux.getIdUsuario().equals(ventana.txtNombreUsuario.getText())) {
                aux.getListaImagenes().Insertar(ventana.txtNuevaImagen.getText());
                return;
            }
            AgregarImagenAUsuario(aux.getHijoDer());
        }
    }

    public void GenerarImagenUsuario(NodoAVL aux, String idUsuario, String idImagen) {
        if (aux != null) {
            GenerarImagenUsuario(aux.getHijoIzq(), idUsuario, idImagen);
            if (aux.getIdUsuario().equals(idUsuario)) {
                NodoImagen aux2 = aux.getListaImagenes().getInicio();
                while (aux2 != null) {
                    if (aux2.getIdImagen().equals(idImagen) && listaDoble.Existe(idImagen)) {
                        listaDoble.ImagenExiste(idImagen, arbol);
                        break;
                    }
                    aux2 = aux2.getSiguiente();
                }
                //METODO PARA GRAFICA NEGRA
                if (aux2 == null) {
                    GenerarImagen(null, idImagen, true);
                }

            }
            GenerarImagenUsuario(aux.getHijoDer(), idUsuario, idImagen);
        }
    }

    private void OpenFile() throws FileNotFoundException {
        JFileChooser JFC = new JFileChooser();
        JFC.setFileFilter(new FileNameExtensionFilter("todos los archivos *.im *usr *cap", "im", "cap", "usr"));
        int abrir = JFC.showDialog(null, "Abrir");
        if (abrir == JFileChooser.APPROVE_OPTION) {
            String PATH = JFC.getSelectedFile().getAbsolutePath();
            FileReader FR = new FileReader(JFC.getSelectedFile());
            BufferedReader BR = new BufferedReader(FR);
            if (PATH.endsWith(".cap") || PATH.endsWith(".CAP")) {
                LeerArchivoCapas(BR);
            } else if (PATH.endsWith(".im") || PATH.endsWith(".IM")) {
                LeerArchivoImagenes(BR);
            } else if (PATH.endsWith(".usr") || PATH.endsWith(".USR")) {
                LeerArchivoUsuarios(BR);
            } else {
                JOptionPane.showMessageDialog(null, "Incorrecto:" + PATH, "Formato no es correcto", JOptionPane.ERROR_MESSAGE);
            }

        }
    }

    private void LeerArchivoCapas(BufferedReader BR) {
        try {
            String linea;
            String texto = "";
            while ((linea = BR.readLine()) != null) {
                texto += linea;
            }
            String[] bloque = texto.trim().replace("{", "\n").replace(";", "\n").trim().split("}");
            int contador = 1;
            for (int i = 0; i < bloque.length; i++) {
                String[] ids = bloque[i].trim().split("\n");
                String idCapa = ids[0];
                Matriz capa = new Matriz();
                for (int j = 1; j < ids.length; j++) {
                    String[] posicion = ids[j].trim().split(",");
                    String fila = posicion[0];
                    String columna = posicion[1];
                    String color = posicion[2];
                    System.out.println("(" + fila + "," + columna + ")");
                    capa.Insertar(new Imagen(contador++, Integer.parseInt(columna.replace("\"", "").trim()), Integer.parseInt(fila.replace("\"", "").trim()), color));
                }
                arbol.Insertar(idCapa, capa);
            }
            arbol.Graficar4();
            JOptionPane.showMessageDialog(null, "Capas ingresadas con exito", "Reporte", JOptionPane.INFORMATION_MESSAGE);
            LLenarComboboxCapas(arbol.getRaiz());
        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "mensaje", JOptionPane.ERROR);
        }

    }

    private void LeerArchivoUsuarios(BufferedReader BR) {
        try {
            String linea;
            String texto = "";
            while ((linea = BR.readLine()) != null) {
                texto += linea + "\n";
            }
            String[] bloque = texto.trim().split(";");
            for (String blo : bloque) {
                String[] ids = blo.trim().split(",");
                String Usuario = "";
                ListaSimpleImagen listaImagen = new ListaSimpleImagen();

                for (String a : ids) {
                    if (a.contains(":")) {
                        String[] id = a.trim().split(":");
                        if (id.length == 2) {
                            Usuario = id[0];
                            listaImagen.Insertar(id[1].trim());
                        } else {
                            Usuario = a.replace(":", "");
                        }
                    } else {
                        listaImagen.Insertar(a.trim());
                    }
                }
                arbolAVL.Insertar(Usuario.trim(), listaImagen);
            }
            ventana.comboUsuarios.removeAllItems();
            LLenarComboboxUsuarios(arbolAVL.getRaiz());
            JOptionPane.showMessageDialog(null, "Usuarios ingresados con exito", "Reporte", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "mensaje", JOptionPane.ERROR);
        }
    }

    private void LeerArchivoImagenes(BufferedReader BR) {
        try {
            String linea;
            String texto = "";
            while ((linea = BR.readLine()) != null) {
                texto += linea + "\n";
            }
            String[] bloque = texto.trim().split("}");
            for (String blo : bloque) {
                ListaSimple capa = new ListaSimple();

                String[] ids = blo.replace("{", "@").trim().split(",");
                String idImagen = "";
                for (String a : ids) {
                    if (a.contains("@")) {
                        String[] id = a.trim().split("@");
                        if (id.length == 2) {
                            capa.Insertar(id[1].trim());
                            idImagen = id[0].trim();
                        } else {
                            idImagen = a.replace("@", "");
                        }
                    } else {
                        capa.Insertar(a.trim());
                    }
                }
                System.out.println(idImagen);
                listaDoble.Insertar(idImagen, capa);
            }
            LLenarComboboxImagenes();
            JOptionPane.showMessageDialog(null, "Imagenes ingresadas con exito", "Reporte", JOptionPane.INFORMATION_MESSAGE);

        } catch (IOException e) {
            JOptionPane.showMessageDialog(null, e.getMessage(), "mensaje", JOptionPane.ERROR);
        }

    }

    private void LLenarComboboxUsuarios(NodoAVL aux) {
        if (aux != null) {
            LLenarComboboxUsuarios(aux.getHijoIzq());
            ventana.comboUsuarios.addItem(aux.getIdUsuario());
            LLenarComboboxUsuarios(aux.getHijoDer());
        }
    }

    private void LLenarComboboxCapas(NodoABB aux) {
        if (aux != null) {
            LLenarComboboxCapas(aux.getHijoIzq());
            ventana.comboCapas.addItem(aux.getIdCapa());
            LLenarComboboxCapas(aux.getHijoDer());
        }
    }

    private void LLenarComboboxImagenes() {
        ventana.comboImagenes.removeAllItems();
        NodoLista aux = listaDoble.getInicio();
        do {
            ventana.comboImagenes.addItem(aux.getIdImagen());
            aux = aux.getSiguiente();
        } while (aux != listaDoble.getInicio());

    }

    private void GenerarImagenRecorrido(int cantidad, String recorrido, Matriz matriz) {
        if (recorrido.equals("PreOrden")) {
            System.out.println(recorrido);
            GenerarImagenPreOrden(arbol.getRaiz(), cantidad, matriz);
            contadorCapas = 1;
        } else if (recorrido.equals("InOrden")) {
            GenerarImagenInOrden(arbol.getRaiz(), cantidad, matriz);
            contadorCapas = 1;

        } else if (recorrido.equals("PostOrden")) {
            GenerarImagenPostOrden(arbol.getRaiz(), cantidad, matriz);
            contadorCapas = 1;
        }
        GenerarImagen(matriz, recorrido, false);
    }

    private void GenerarImagen(Matriz matriz, String recorrido, boolean NoExiste) {
        String cadena = "";
        if (NoExiste) {
            cadena = "digraph imagen{\n"
                    + "node [shape=plaintext]\n"
                    + "a [label=<<TABLE BORDER=\"1\" CELLBORDER=\"0\" CELLSPACING=\"0\">\n"
                    + "<TR>\n"
                    + "<TD WIDTH=\"50\" HEIGHT=\"50\" BGCOLOR=\"#000000\"> </TD>\n"
                    + "</TR>\n"
                    + "</TABLE>>];\n"
                    + "}";
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
        FileWriter fichero = null;
        PrintWriter escritor;
        try {
            fichero = new FileWriter("Imagen" + recorrido + ".txt");
            escritor = new PrintWriter(fichero);
            escritor.print(cadena);
            fichero.close();

        } catch (Exception e) {
            System.err.println("Error al escribir el archivo Imagen" + recorrido + ".txt");
        }

        try {
            Runtime rt = Runtime.getRuntime();
            rt.exec("dot -Tjpg -o  Imagen" + recorrido + ".jpg  Imagen" + recorrido + ".txt");
            Thread.sleep(500);

            Desktop imagen = Desktop.getDesktop();
            imagen.open(new File("C:/Users/GAMING/Documents/NetBeansProjects/[EDD]Proyecto1/Imagen" + recorrido + ".jpg"));
        } catch (Exception ex) {
            System.err.println("Error al generar la imagen para el archivo Imagen" + recorrido + ".txt");
        }

    }

    public void GenerarImagenInOrden(NodoABB aux, int cantidad, Matriz matriz) {
        if (aux != null && contadorCapas <= cantidad) {
            GenerarImagenInOrden(aux.getHijoIzq(), cantidad, matriz);
            if (contadorCapas <= cantidad) {
                System.out.println(aux.getIdCapa());
                arbol.GenerarCapa(aux.getIdCapa(), matriz);
                contadorCapas++;
            }
            GenerarImagenInOrden(aux.getHijoDer(), cantidad, matriz);
        }
    }

    public void GenerarImagenPreOrden(NodoABB aux, int cantidad, Matriz matriz) {
        if (aux != null && contadorCapas <= cantidad) {
            if (contadorCapas <= cantidad) {
                System.out.println(aux.getIdCapa());
                arbol.GenerarCapa(aux.getIdCapa(), matriz);
                contadorCapas++;
            }
            GenerarImagenPreOrden(aux.getHijoIzq(), cantidad, matriz);
            GenerarImagenPreOrden(aux.getHijoDer(), cantidad, matriz);
        }
    }

    public void GenerarImagenPostOrden(NodoABB aux, int cantidad, Matriz matriz) {
        if (aux != null && contadorCapas <= cantidad) {
            GenerarImagenPostOrden(aux.getHijoIzq(), cantidad, matriz);
            GenerarImagenPostOrden(aux.getHijoDer(), cantidad, matriz);
            if (contadorCapas <= cantidad) {
                System.out.println(aux.getIdCapa());
                arbol.GenerarCapa(aux.getIdCapa(), matriz);
                contadorCapas++;
            }
        }
    }
}
