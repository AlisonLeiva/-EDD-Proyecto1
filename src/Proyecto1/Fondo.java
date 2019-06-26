/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Proyecto1;
import java.awt.*;
import javax.swing.*;

public class Fondo extends JPanel {
     private  Image img;
     
     
     public Fondo (Image img){
        this.img = img;
        Dimension dimension = new Dimension(img.getWidth(null), img.getHeight(null));
        this.setPreferredSize(dimension);
        this.setMaximumSize(dimension);
        this.setSize(dimension);
        this.setLayout(null);
        
     }
     
     @Override
     protected void paintComponent (Graphics g){
         g.drawImage(img,0,0,this);
     }

    
}