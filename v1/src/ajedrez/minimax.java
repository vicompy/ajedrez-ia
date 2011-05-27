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


    private int to_down, size;
    private boolean turn;
    private boolean step;

    public minimax(boolean turn, nodoTablero raiz, int dificultad)
    {
        to_down = size = 0;
        this.turn = turn;
        step = true;
        BuildTree(raiz, dificultad);
    }

    /** Este metodo configura la creacion del arbol
     *
     *  @param raiz es la raiz del arbol a construir
     *  @param dificultad para ver la dificultad del juego, si es 1 la
     *                    dificultad es <strong>Dificil</strong>, si es 2
     *                    la dificultad es <strong>Medio</strong> y si es 3
     *                    la dificultad es <strong>Facil</strong>
     */
    public void BuildTree(nodoTablero raiz, int dificultad)
    {
        to_down = dificultad*3 - 2;
        size = dificultad*3;
        raiz.movimientos = raiz.getMovsValid(raiz, turn);
        raiz.turno = freeTurn(turn);

        for(int i = 0; i < size; i++)
        {
            raiz.movimientos[i].movimientos = raiz.
                    getMovsValid(raiz.movimientos[i], !turn);
            
            BuildLevels(raiz.movimientos[i], raiz.movimientos[i].movimientos);
            to_down = dificultad*3 - 2;
            step = true;
        }
    }

    /** Metodo para liberar la variable turno
     *
     *  @param turn es el turno a liberar
     *
     *  @return retorna la variable turno ya liberada
     */
    public boolean freeTurn(boolean turn)
    {
        boolean pturn = turn;

        return pturn;
    }

    /** Este metodo construye el arbol
     *
     *  @param raiz es el nodo raiz sobre el cual se construye el arbol
     *
     *  @param hijos son los hijos del nodo raiz
     */
    public void BuildLevels(nodoTablero raiz, nodoTablero[] hijos)
    {
        if(to_down > 1)
        {
            turn = !turn;

            hijos[0].movimientos = hijos[0].getMovsValid(raiz, turn);
            hijos[0].turno = freeTurn(turn);

            to_down--;

            BuildLevels(hijos[0], hijos[0].movimientos);
        }
        
        for(int i = 1; i < hijos.length; i++)
        {
            hijos[i].movimientos = hijos[i].getMovsValid(raiz, freeTurn(hijos[0].turno));
        }

        if(step){
            hijos[0].movimientos = hijos[0].getMovsValid(raiz, turn);
            step = false;
        }

    }

    

    /** Metodo que MinMax que devuelve el movimiento
     *  Actual despues de generar sus nodos hijos
     *  @param actual tablero con el estado actual (el que llevamos)
     *  @param turn si MIN tira primero sera <strong>false</strong>
     *              de lo contrario si tira MAX sera <strong>true</strong>
     *  @return retorna el tiro que le corresponde al nodo actual
     */
    public void MaxMin(nodoTablero actual, boolean MAX, int pieza)
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

    public int valorMAX(nodoTablero actual, nodoTablero hijos[])
    {
        int vmax = 0;

        if(actual.esTerminal){
            return getUtility(actual);
        }
        else{
            vmax = -100000;
        }

        int size = hijos.length;

        for(int i = 0; i < size; i++)
        {
            
        }

        return vmax;
    }

    public int valorMIN(nodoTablero actual, nodoTablero hijos[])
    {
        int vmin = 0;

        if(actual.esTerminal){
            return getUtility(actual);
        }
        else{
            vmin = 100000;
        }
        
        return vmin;
    }

    /** Este metodo sirve para calcular la utlidad del nodo
     *  @param actual es el nodo actual al que se le sacara
     *                la utilidad
     *  @return retorna la utilidad del nodo
     */
    public int getUtility(nodoTablero actual)
    {
        int utility = 0;
        int tablero[][] = actual.posicionPiezas;

        //aqui a quien le toco la heuristica tiene que calcular
        //la utlidad que consiste en una sencilla resta:
        //la cantidad de posibilidades de ganar del Humano
        //menos la cantida de ganar de la maquina

        return utility;
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
