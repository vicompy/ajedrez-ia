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
    private heuristica value;

    public minimax(boolean turn, nodoTablero raiz, int dificultad)
    {
        to_down = size = 0;
        this.turn = turn;
        step = true;
        value = new heuristica();
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
            turn = !turn;
            raiz.movimientos[i].movimientos = raiz.
                    getMovsValid(raiz.movimientos[i], turn);
            
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

        if(step){
            hijos[0].movimientos = hijos[0].getMovsValid(raiz, freeTurn(hijos[0].turno));
            hijos[0].movimientos[0].esTerminal = true;
            value.ApplyUtility(hijos[0], hijos[0].movimientos);
        }

        for(int i = 1; i < hijos.length; i++)
        {
            if(step)
            {
                hijos[i].movimientos[0].esTerminal = true;
            }

            hijos[i].movimientos = hijos[i].getMovsValid(raiz, freeTurn(hijos[0].turno));
            value.ApplyUtility(hijos[i], hijos[i].movimientos);
        }

        step = false;

    }

}
