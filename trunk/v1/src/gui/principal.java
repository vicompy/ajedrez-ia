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
import javax.swing.JOptionPane;

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
    private int wComidas = 0;
    private int bComidas = 0;
    private int turno = c.HUMANO;
    private heuristica heu = new heuristica();
    private minimax minmax = new minimax();

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

        //Coloco el turno al humano
        this.lbl_turno.setText("BLANCAS - Humano");
        this.turno = c.HUMANO;
        setLog("Turno: BLANCAS - Humano");

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

    private void paintPiezaComida(int tipo, int turno) {
        int x = 0;
        int y = 0;

        if (turno == c.HUMANO) {
            y = 100;
            x = wComidas * 50;
        } else {
            x = bComidas * 50;
        }

        if (turno == c.HUMANO && wComidas == 7) {
            wComidas = 0;
            x = 0;
            y = 150;
        }

        if (turno == c.PC && bComidas == 7) {
            bComidas = 0;
            x = 0;
            y = 50;
        }

        ImageIcon icon = null;
        icon = new ImageIcon(this.getClass().getResource("/resources/images/" + tipo + ".gif"));
        JButton comida = new JButton();
        comida.setIcon(icon);
        comida.setBounds(x, y, 50, 50);
        System.out.println("X: " + x + ", Y: " + y);
        pnl_comidas.add(comida);
        pnl_comidas.repaint();

        if (turno == c.HUMANO) {
            wComidas++;
        } else {
            bComidas++;
        }

        setLog("Pieza comida: " + c.getPiezaNombre(tipo));
        System.out.println("w: " + wComidas + ", b: " + bComidas);
    }

    public void setLog(String mensaje) {
        try {
            this.txta_bitacora.append(mensaje + System.getProperty("line.separator"));
        } catch (Exception e) {
        }
    }

    private void tiraPC(){
        tLogico = minmax.minimaxEval(3,tLogico);
        repaintPiezas(tLogico);
    }

    public void actionPerformed(ActionEvent e) {
        pieza temp = (pieza) e.getSource();

        if (!release) {
            oX = temp.getXposT();
            oY = temp.getYposT();
            tipoO = temp.getTipo();

            if (turno == c.PC && (tipoO <= -1 && tipoO >= -6)) {
                release = true;
            }

            if (turno == c.HUMANO && (tipoO >= 1 && tipoO <= 6)) {
                release = true;
            }
        } else {
            dX = temp.getXposT();
            dY = temp.getYposT();

            if (heu.getValidacionPieza(oX, oY, dX, dY, tLogico, tipoO)) { //revisa si el movimiento es valido
                //si me comi ua pieza la coloco en la parte de comidas
                //De una cambio de turno
                if (turno == c.HUMANO) {
                    if (tGui[dX][dY].getTipo() <= -1 && tGui[dX][dY].getTipo() >= -6) {//comio una negra
                        paintPiezaComida(temp.getTipo(), turno);
                    }
                    turno = c.PC;
                    lbl_turno.setText("NEGRAS - PC");

                } else {
                    if (tGui[dX][dY].getTipo() >= 1 && tGui[dX][dY].getTipo() <= 6) {//comio una negra
                        paintPiezaComida(temp.getTipo(), turno);
                    }
                    turno = c.HUMANO;
                    lbl_turno.setText("BLANCAS - Humano");
                }


                tLogico[oX][oY] = c.CASILLA_VACIA;
                tLogico[dX][dY] = tipoO;
                paintPieza(oX, oY, c.CASILLA_VACIA);
                paintPieza(dX, dY, tipoO);

                setLog(c.getPiezaNombre(tipoO) + " se mueve de X:" + oX + ", Y:" + oY
                        + " a X:" + dX + ", Y:" + dY);

                if (turno == c.HUMANO) {
                    setLog("Turno: BLANCAS - Humano");
                } else {
                    setLog("Turno: NEGRAS - PC");
                    tiraPC();
                }

                //Por ser probado, antes en Minimax
                //y luego aqui
                //heu.generateChilds(tLogico, tipoO);

                //comprueba si se esta en jaque
                int jaque = heu.isJaque(tipoO,turno);
                if(jaque==c.HUMANO){
                    JOptionPane.showMessageDialog(null, "Jaque a Blancas");
                    setLog("Jaque a Blancas");
                }else if(jaque==c.PC){
                    JOptionPane.showMessageDialog(null, "Jaque a Negras");
                    setLog("Jaque a Negras");
                }
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
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        lbl_turno = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txta_bitacora = new javax.swing.JTextArea();
        jPanel2 = new javax.swing.JPanel();
        pnl_comidas = new javax.swing.JPanel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Ajedrez");

        pnl_tablero.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        pnl_tablero.setPreferredSize(new java.awt.Dimension(400, 400));

        javax.swing.GroupLayout pnl_tableroLayout = new javax.swing.GroupLayout(pnl_tablero);
        pnl_tablero.setLayout(pnl_tableroLayout);
        pnl_tableroLayout.setHorizontalGroup(
            pnl_tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 396, Short.MAX_VALUE)
        );
        pnl_tableroLayout.setVerticalGroup(
            pnl_tableroLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 434, Short.MAX_VALUE)
        );

        jPanel1.setBorder(javax.swing.BorderFactory.createTitledBorder("Información"));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14));
        jLabel1.setText("Turno:");

        lbl_turno.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N

        txta_bitacora.setColumns(20);
        txta_bitacora.setRows(5);
        jScrollPane1.setViewportView(txta_bitacora);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 50, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbl_turno, javax.swing.GroupLayout.PREFERRED_SIZE, 251, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(lbl_turno, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder("Piezas"));
        jPanel2.setPreferredSize(new java.awt.Dimension(400, 200));

        pnl_comidas.setPreferredSize(new java.awt.Dimension(400, 200));

        javax.swing.GroupLayout pnl_comidasLayout = new javax.swing.GroupLayout(pnl_comidas);
        pnl_comidas.setLayout(pnl_comidasLayout);
        pnl_comidasLayout.setHorizontalGroup(
            pnl_comidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 375, Short.MAX_VALUE)
        );
        pnl_comidasLayout.setVerticalGroup(
            pnl_comidasLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 196, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_comidas, javax.swing.GroupLayout.DEFAULT_SIZE, 375, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addComponent(pnl_comidas, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(pnl_tablero, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 407, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, 234, Short.MAX_VALUE))
                    .addComponent(pnl_tablero, javax.swing.GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbl_turno;
    private javax.swing.JPanel pnl_comidas;
    private javax.swing.JPanel pnl_tablero;
    private javax.swing.JTextArea txta_bitacora;
    // End of variables declaration//GEN-END:variables
}
