/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * principal.java
 *
 * Created on 23/05/2011, 09:32:39 PM
 */
package gui;

import java.awt.Color;
import ajedrez.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 *
 * @author Rodrigo
 */
public class principal extends javax.swing.JFrame implements ActionListener {

    private pieza[][] tGui = new pieza[8][8];
    private int tLogico[][] = new int[8][8];
    private constantes c = new constantes();
    private boolean release = false;
    private int oX, oY, dX, dY;
    private int tipoO;

    private heuristica heu = new heuristica();

    /** Creates new form principal */
    public principal() {
        initComponents();
        this.setLocationRelativeTo(null);
        makeTablero();
    }

    private void makeTablero() {
        pieza temp;
        for (int j = 0; j < 8; j++) {
            for (int i = 0; i < 8; i++) {
                temp = new pieza();
                temp.setXpos(i * 50);
                temp.setYpos(j * 50);
                temp.setXposT(j);
                temp.setYposT(i);
                if ((i + j) % 2 == 0) {
                    temp.setColor(Color.BLACK);
                } else {
                    temp.setColor(Color.WHITE);
                }
                temp.addActionListener(this);
                temp.setValues();
                tGui[j][i] = temp;
                this.pnl_tablero.add(tGui[j][i]);
            }
        }
        initTableros();
    }

    private void initTableros() {
        int j = 0;
        int i = 0;

        //tablero logico inicial

        //piezas negras
        tLogico[0][0] = c.bTORRE;
        tLogico[0][1] = c.bCABALLO;
        tLogico[0][2] = c.bALFIL;
        tLogico[0][3] = c.bREINA;
        tLogico[0][4] = c.bREY;
        tLogico[0][5] = c.bALFIL;
        tLogico[0][6] = c.bCABALLO;
        tLogico[0][7] = c.bTORRE;

        tGui[0][0].setTipo(c.bTORRE);
        tGui[0][1].setTipo(c.bCABALLO);
        tGui[0][2].setTipo(c.bALFIL);
        tGui[0][3].setTipo(c.bREINA);
        tGui[0][4].setTipo(c.bREY);
        tGui[0][5].setTipo(c.bALFIL);
        tGui[0][6].setTipo(c.bCABALLO);
        tGui[0][7].setTipo(c.bTORRE);

        for (i = 0; i < 8; i++) {
            tLogico[1][i] = c.bPEON;
            tGui[1][i].setTipo(c.bPEON);
        }

        //casillas vacias
        for (j = 2; j < 6; j++) {
            for (i = 0; i < 8; i++) {
                tLogico[j][i] = c.CASILLA_VACIA;
                tGui[j][i].setTipo(c.CASILLA_VACIA);
            }
        }

        //piezas balncas
        tLogico[7][0] = c.wTORRE;
        tLogico[7][1] = c.wCABALLO;
        tLogico[7][2] = c.wALFIL;
        tLogico[7][3] = c.wREINA;
        tLogico[7][4] = c.wREY;
        tLogico[7][5] = c.wALFIL;
        tLogico[7][6] = c.wCABALLO;
        tLogico[7][7] = c.wTORRE;

        tGui[7][0].setTipo(c.wTORRE);
        tGui[7][1].setTipo(c.wCABALLO);
        tGui[7][2].setTipo(c.wALFIL);
        tGui[7][3].setTipo(c.wREINA);
        tGui[7][4].setTipo(c.wREY);
        tGui[7][5].setTipo(c.wALFIL);
        tGui[7][6].setTipo(c.wCABALLO);
        tGui[7][7].setTipo(c.wTORRE);

        for (i = 0; i < 8; i++) {
            tLogico[6][i] = c.wPEON;
            tGui[6][i].setTipo(c.wPEON);
        }

//         for (int x = 0; x < 8; x++) {
//            for (int y = 0; y < 8; y++) {
//                System.out.println("X: "+x+", Y: "+y+", VAL: "+tLogico[x][y]);
//             }
//            }
        repaintPiezas(tLogico);
    }

    private void paintPieza(int x, int y, int tipo) {

        if (tipo != c.CASILLA_VACIA) {
            ImageIcon icon = null;
           // System.out.println("TIPO: " + tipo + ", X:" + x + ", Y:" + y);
            icon = new ImageIcon(this.getClass().getResource("/resources/images/" + tipo + ".gif"));
            tGui[x][y].setBackground(null);
            tGui[x][y].setIcon(icon);
            tGui[x][y].setTipo(tipo);
        } else {
            tGui[x][y].setIcon(null);
            tGui[x][y].setValues();
            tGui[x][y].setTipo(tipo);
        }
    }

    private void repaintPiezas(int mtablero[][]) {
        for (int x = 0; x < 8; x++) {
            for (int y = 0; y < 8; y++) {
                if (mtablero[x][y] == c.CASILLA_VACIA) {
                    paintPieza(x, y, c.CASILLA_VACIA);
                } else {
                    paintPieza(x, y, mtablero[x][y]);
                }
            }
        }
    }

    public void actionPerformed(ActionEvent e) {
        pieza temp = (pieza) e.getSource();

        if (!release) {
            oX = temp.getXposT();
            oY = temp.getYposT();
            tipoO = temp.getTipo();
            release = true;
            //System.out.println("PIEZA: "+temp.getTipo());
            //System.out.println("oX: "+temp.getXposT()+", oY: "+temp.getYposT());
        } else {
            dX = temp.getXposT();
            dY = temp.getYposT();

            if(/*(tLogico[dX][dY]==c.CASILLA_VACIA) &&*/
                    heu.getValidacionPieza(oX, oY, dX, dY, tLogico, tipoO)){ //revisa si el movimiento es valido
                //System.out.println("oX "+oX+", oY "+oY);
                //System.out.println("dX "+dX+", dY "+dY);
                tLogico[oX][oY] = c.CASILLA_VACIA;
                tLogico[dX][dY] = tipoO;
                paintPieza(oX,oY,c.CASILLA_VACIA);
                paintPieza(dX,dY,tipoO);
            }
            release = false;
        }
    }

    /** This method is called from within the constructor to
     * initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is
     * always regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        pnl_tablero = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajedrez");

        pnl_tablero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_tablero.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout pnl_tableroLayout = new javax.swing.GroupLayout(pnl_tablero);
        pnl_tablero.setLayout(pnl_tableroLayout);
        pnl_tableroLayout.setHorizontalGroup(
            pnl_tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );
        pnl_tableroLayout.setVerticalGroup(
            pnl_tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 596, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(281, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(38, 38, 38)
                .addComponent(pnl_tablero, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JPanel pnl_tablero;
    // End of variables declaration//GEN-END:variables
}
