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
    public nodoTablero padre;
    public nodoTablero[] movimientos;
    private int funcionUtilidad;
    public boolean turno;
    public boolean esTerminal;//true si es hoja
    public nodoTablero(){
        inicializarTablero();
        padre = null;
    }

    public void setFuncionUtilidad(int func){
        funcionUtilidad = func;
    }
    public int getFuncionUtilidad(){
        return funcionUtilidad;
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

    /** Metodo que se utiliza para construir todos los hijos
     *  esto es, los tiros posibles que se pueden hacer en base
     *  al jugador que le toque
     *
     *  @param raiz es el nodo raiz sobre el cual se tomara el criterio
     *              para hacer todos los hijos (tiros posibles)
     *  @param turn es el turno del jugador al que le toca tirar
     *              en esto nos basamos para armar los hijos
     *              si fuese <strong>true</strong> entonces se arman los
     *              hijos para las piezas blancas
     *              en caso sea <strong>false</strong> entonces se arman
     *              los hijos para las piezas negras
     *  @return retorna los hijos basados en el nodo <strong>raiz</strong>
     *          y en el <strong>turno</strong> del jugador
     */
    public nodoTablero[] getMovsValid(nodoTablero raiz, boolean turn)
    {
        //tiran las blancas
        if(turn)
        {
            ///aqui se crea el nivel y se retorna
        }
        //tiran las negras
        else
        {
            ///aqui se crea el nivel y se retorna
        }

        return null;
    }
}
