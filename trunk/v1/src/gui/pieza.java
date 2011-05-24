/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.awt.Color;

/**
 *
 * @author Rodrigo
 */
public class pieza extends javax.swing.JButton {

    private int tipo;
    private int xpos;
    private int ypos;
    private int ocupa;
    private Color color;

    public pieza() {
    }

    public void setValues(){
        this.setBackground(getColor());
        this.setBounds(getXpos(), getYpos(), 50, 50);
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
