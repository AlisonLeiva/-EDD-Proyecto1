/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto1;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Panel;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.net.URL;
import javax.swing.*;

public class Ventana {

    JFrame ventana = new JFrame();
    String dir = "/Proyecto1/Fondo.jpg";
    String dir2 = "/Proyecto1/Fondo2.jpg";
    URL urls = this.getClass().getResource(dir);
    URL urls2 = this.getClass().getResource(dir2);
    ImageIcon fondoPrinc = new ImageIcon(urls);
    ImageIcon fondo2 = new ImageIcon(urls2);
    Fondo panel1 = new Fondo(fondoPrinc.getImage());
    Fondo panel2 = new Fondo(fondo2.getImage());
    JButton btnCantidadCapas = new JButton();
    JComboBox comboCargaMasiva = new JComboBox();
    JComboBox comboVisualizacion = new JComboBox();
    JComboBox comboUsuarios = new JComboBox();
    JComboBox comboCapas = new JComboBox();
    JComboBox comboImagenes = new JComboBox();
    JComboBox comboImg_Usuario = new JComboBox();
    JComboBox comboTipoRecorrido = new JComboBox();
    JComboBox comboGuardarImagenes = new JComboBox();
    JTextField CantidadCapa = new JTextField();
    JTextField txtIdImagen = new JTextField();
    JTextField txtNuevaImagen = new JTextField();
    JTextField txtNombreUsuario = new JTextField();
    JButton btnImagen = new JButton();
    JButton btnCapa = new JButton();
    JButton btnUsuario = new JButton();
    JButton btnAgregar = new JButton();
    JButton btnModificar = new JButton();
    JRadioButton btnRadio = new JRadioButton();
    JRadioButton btnModImagen = new JRadioButton();
    JRadioButton btnEliImagen = new JRadioButton();

    public Ventana() {
        comboCargaMasiva.setBounds(255, 83, 120, 25);
        comboVisualizacion.setBounds(255, 122, 120, 25);

        comboUsuarios.setBounds(63, 34, 90, 20);
        comboCapas.setBounds(346, 34, 90, 20);
        comboGuardarImagenes.setBounds(146, 486, 90, 20);
        comboImagenes.setBounds(472, 34, 90, 20);
        comboImg_Usuario.setBounds(201, 34, 90, 20);
        comboTipoRecorrido.setBounds(260, 231, 100, 25);
        CantidadCapa.setBounds(260, 200, 50, 25);
        CantidadCapa.setBackground(Color.WHITE);

        txtIdImagen.setBounds(444, 455, 50, 25);
        txtIdImagen.setBackground(Color.WHITE);
        btnRadio.setBounds(212, 456, 25, 25);
        btnModImagen.setBounds(361, 516, 150, 25);
        btnEliImagen.setBounds(361, 537, 150, 25);
        btnModImagen.setText("Agregar Imagen");
        btnEliImagen.setText("Eliminar Imagen");
        txtNuevaImagen.setBounds(444, 485, 50, 25);
        txtNuevaImagen.setBackground(Color.WHITE);

        txtNombreUsuario.setBounds(146, 455, 50, 25);
        txtNombreUsuario.setBackground(Color.WHITE);

        btnImagen.setBounds(460, 293, 90, 20);
        btnImagen.setText("Generar");
        btnCapa.setText("Generar");

        btnUsuario.setText("Generar");
        btnCapa.setBounds(460, 325, 90, 20);
        btnUsuario.setBounds(460, 353, 90, 20);


        btnAgregar.setBounds(79, 585, 90, 20);
        btnAgregar.setText("Agregar");

        btnModificar.setBounds(442, 585, 90, 20);
        btnModificar.setText("Modificar");

        ventana.setBounds(0, 0, 1125, 685);
        ventana.add(panel1);
        ventana.add(panel2);
        panel1.setBounds(0, 0, ventana.getWidth(), ventana.getHeight());
        panel2.setBounds(500, 0, ventana.getWidth(), ventana.getHeight());
        ventana.repaint();

        btnCantidadCapas.setText("Generar Imagen");
        btnCantidadCapas.setBounds(407, 211, 130, 25);
        panel2.add(comboCargaMasiva);
        panel2.add(comboUsuarios);
        panel2.add(comboCapas);
        panel2.add(comboImagenes);
        panel2.add(comboVisualizacion);
        panel2.add(comboImg_Usuario);
        panel2.add(comboImg_Usuario);
        panel2.add(comboGuardarImagenes);
        panel2.add(comboTipoRecorrido);
        panel2.add(btnImagen);
        panel2.add(btnRadio);
        panel2.add(btnEliImagen);
        panel2.add(btnModImagen);
        panel2.add(btnCapa);
        panel2.add(btnUsuario);
        panel2.add(btnModificar);
        comboCargaMasiva.addItem("Capas");
        comboCargaMasiva.addItem("Imagenes");
        comboCargaMasiva.addItem("Usuarios");
        comboTipoRecorrido.addItem("InOrden");
        comboTipoRecorrido.addItem("PreOrden");
        comboTipoRecorrido.addItem("PostOrden");
        comboVisualizacion.addItem("Lista Imagenes(6)");
        comboVisualizacion.addItem("Arbol de Capas(4)");
        comboVisualizacion.addItem("Capa(2)");
        comboVisualizacion.addItem("Imagen y Arbol(7)");
        comboVisualizacion.addItem("Arbol de Usuarios(8)");
        panel2.add(btnCantidadCapas);
        panel2.add(CantidadCapa);
        panel2.add(txtNuevaImagen);
        panel2.add(txtIdImagen);
        panel2.add(txtNombreUsuario);
        panel2.add(btnAgregar);
        ventana.setLocationRelativeTo(null);
        ventana.setResizable(false);
        ventana.setLayout(null);
        ventana.setVisible(true);

        ventana.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent evt) {
                System.exit(0);
            }
        });
    }

    public void mostrar() {
        ventana.show();
    }

}
