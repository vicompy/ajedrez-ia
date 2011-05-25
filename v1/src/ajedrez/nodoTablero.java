/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez;

/**
 *
 * @author William
 */
public class nodoTablero {

    public int[][] posicionPiezas = new int[8][8];
    private nodoTablero[] movimientos;
    private int funcionUtilidad;
    private int turno;
    public boolean esTerminal;//true si es hoja
    public nodoTablero(){
        inicializarTablero();
    }

    public void setFuncionUtilidad(int func){
        funcionUtilidad = func;
    }
    public int getFuncionUtilidad(){
        return funcionUtilidad;
    }

    public void setTurno(int turno){
        this.turno = turno;
    }

    public int getTurno(){
        return turno;
    }

    public nodoTablero[] getMovimientos(){
        return movimientos;
    }

    public void setEstadoTerminal(boolean estado){
        esTerminal = estado;
    }

    public boolean getEstadoTerminal(){
        return esTerminal;
    }

    private void inicializarTablero()
    {
        for(int i = 0; i < 8; i++){
            for(int j = 0; j < 8; j++){
                posicionPiezas[i][j] = 0;
            }
        }
    }

    public void setPieza(int posX, int posY, int pieza){
        posicionPiezas[posX][posY] = pieza;
    }

    public int getPieza(int posX, int posY){
        return posicionPiezas[posX][posY];
    }

    public int getFreeSpaces() {

        int free_spaces = 0;
        int tablero[][] = this.posicionPiezas;

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if(tablero[x][y] == 0)
                {
                    free_spaces++;
                }
            }
        }

        return free_spaces;
    }
}
