/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez;

/**
 *
 * @author William
 */
public class minimax {


    /** Metodo que MinMax que devuelve el movimiento
     *  Actual despues de generar sus nodos hijos
     *  @param actual tablero con el estado actual (el que llevamos)
     *  @param turn si MIN tira primero sera <strong>false</strong>
     *              de lo contrario si tira MAX sera <strong>true</strong>
     *  @return retorna el tiro que le corresponde al nodo actual
     */
    public void MaxMin(nodoTablero actual, boolean MAX)
    {
        
    }

    public int[][] init_MAX(nodoTablero actual, int pieza)
    {
        int[][] movimiento = new int[8][8];
        int max, maxc; max = maxc = 0;

        max = -100000;

        int cantidad = actual.getFreeSpaces();

        nodoTablero hijos[] = move_possible(movimiento, cantidad, pieza);

        for(int i = 0; i < cantidad; i++)
        {
            
        }

        return movimiento;
    }

    /** Metodo que se encarga de devolver todos los hijos
     *  para el nodo actual
     *  @param tablero es el tablero sobre el cual nos basaremos
     *                 para crear todos los hijos
     *  @param size es la cantidad de hijos que vamos a crear
     *              en ese nivel
     *  @param pieza es la pieza que sera puesta en el tablero
     *  @return retorna los hijos del nodo actual
     */
    public nodoTablero[] move_possible(int tablero[][], int size, int pieza)
    {
        nodoTablero hijos[] = new nodoTablero[size];
        int index = 0;

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                if(tablero[x][y] == 0){

                    //aqui se deben poner todas las cosas
                    //que deseemos agregarle al nodo
                    //como tambien validad si es movimiento valido
                    //algo asi como isValid(tablero,pieza)
                    //para poner actual.valido = true o false
                    hijos[index] = new nodoTablero();
                    hijos[index].posicionPiezas = getTablero(tablero, pieza, x, y);
                    index++;
                }
            }
        }

        return hijos;
    }

    /** Metodo para crear el tablero con el nuevo tiro (sin validar si es valido)
     *  principalmente solo el tablero con el nuevo tiro se pone pero
     *  se pueden hacer modificaciones
     *  @param actual recibe el tablero de la jugada actual para config
     *                el nuevo tablero ya con el tiro
     *  @param pieza es el ID que le corresponde a la pieza que hemos de poner
     *  @param px es la posicion en x del nuevo tiro posible
     *  @param py es la posicion en y del nuevo tiro posible
     *  @return retorna el tablero con el nuevo tiro agregado
     */
    public int[][] getTablero(int[][] actual, int pieza, int px, int py)
    {
        int[][] tablero = new int[8][8];

        for(int x = 0; x < 8; x++)
        {
            for(int y = 0; y < 8; y++)
            {
                tablero[x][y] = actual[x][y];
            }
        }

        tablero[px][py] = pieza;

        return tablero;
    }

}
