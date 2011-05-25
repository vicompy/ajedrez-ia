/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;

/**
 *
 * @author Rodrigo
 */
public class pieza extends javax.swing.JButton implements ActionListener {

    private int tipo;
    private int xpos;
    private int ypos;
    private int ocupa;
    private Color color;
    private Image imagen;

    public Image getImagen() {
        return imagen;
    }

    public void setImagen(Image imagen) {
        this.imagen = imagen;
    }

    public pieza() {
    }

    public void setValues(){
        this.setBackground(getColor());
        this.setBounds(getXpos(), getYpos(), 50, 50);
    }

    public void actionPerformed(ActionEvent e) {
        JButton temp = (JButton) e.getSource() ;
    }

    public int getOcupa() {
        return ocupa;
    }

    public void setOcupa(int ocupa) {
        this.ocupa = ocupa;
    }

    public int getTipo() {
        return tipo;
    }

    public void setTipo(int tipo) {
        this.tipo = tipo;
    }

    public int getXpos() {
        return xpos;
    }

    public void setXpos(int xpos) {
        this.xpos = xpos;
    }

    public int getYpos() {
        return ypos;
    }

    public void setYpos(int ypos) {
        this.ypos = ypos;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
