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
public class pieza extends javax.swing.JLabel {

    private int tipo;
    private int xpos;
    private int ypos;
    private int ocupa;

    public pieza(int color) {

        if (color == 0) {
            this.setBackground(Color.WHITE);
        } else {
            this.setBackground(Color.BLACK);
        }
        // this.setBounds(x, y, tipo, tipo);
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
}
