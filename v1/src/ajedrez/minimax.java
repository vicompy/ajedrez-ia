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
    private int Tutility;

    public minimax(boolean turn, nodoTablero raiz, int dificultad)
    {
        to_down = size = 0;
        this.turn = turn;
        step = true;
        Tutility = 0;
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
        raiz.movimientos = raiz.getMovsValid(raiz, turn);
        size = raiz.movimientos.length;
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

            ///en esta parte encuentra los nodos terminales
            ///para luego realizar valorMin, valorMax segun convenga

            hijos[0].movimientos = hijos[0].getMovsValid(raiz, freeTurn(hijos[0].turno));
            hijos[0].movimientos[0].esTerminal = true;

            //aplicamos la utilidad con la condicion que sean nodos terminales
            value.ApplyUtility(hijos[0].movimientos);

            //aplicamos lo de valor de utilidad al sub-arbol mas a la izquierda
            ApplyCriteriaLeft(hijos[0], hijos[0].movimientos);
        }

        for(int i = 1; i < hijos.length; i++)
        {

            //se crean los nodos con los movimientos validos
            //segun la condicion del nodo raiz
            hijos[i].movimientos = hijos[i].getMovsValid(raiz, freeTurn(hijos[0].turno));

            if(step)
            {
                //se le configura a estos hijos como nodos termianles
                hijos[i].movimientos[0].esTerminal = true;

                //aplicamos la utilidad con la condicion que sean nodos terminales
                value.ApplyUtility(hijos[i].movimientos);
            }
        }

        if(step)
        {
            //se aplica el recorrido para terminar el arbol en funcion
            //de ir poniendo la utilidad que corresponde a los demas nodos
            for(int i = 0; i < hijos.length; i++)
            {
                
            }
        }
        else{

        }

        step = false;

    }

    public void ApplyCriteriaLeft(nodoTablero raiz, nodoTablero hijos[])
    {
        int utility = 0;

        //esto quiere decir que
        //el padre son Negras
        //tomaremos a las Negras como MIN
        if(!raiz.turno)
        {
            int temp_utility = 100000;

            for(int i = 0; i < hijos.length; i++)
            {
                if(hijos[i].funcionUtilidad < temp_utility)
                {
                    temp_utility = hijos[i].funcionUtilidad;
                }
            }

            utility = temp_utility;
        }
        //Esto quiere decir que el padre son Blancas
        //Tomaremos las Blancas como MAX
        else
        {
            int temp_utility = -100000;

            for(int i = 0; i < hijos.length; i++)
            {
                if(hijos[i].funcionUtilidad > temp_utility)
                {
                    temp_utility = hijos[i].funcionUtilidad;
                }
            }

            utility = temp_utility;
        }

        raiz.funcionUtilidad = new Integer(utility);
        Tutility = new Integer(utility);
    }

    public void ApplyCriteriaLeftRight(nodoTablero raiz, nodoTablero hijos[])
    {
        int utility = 0;

        //esto quiere decir que
        //el padre son Negras
        //tomaremos a las Negras como MIN
        if(!raiz.esTerminal)
        {
            int temp_utility = 100000;

            for(int i = 0; i < hijos.length; i++)
            {
                //si esto es asi es que el nodo padre
                //del nodo raiz es MAX
                if(!raiz.esTerminal)
                {
                    if(temp_utility < hijos[i].funcionUtilidad)
                    {
                        temp_utility = hijos[i].funcionUtilidad;

                        if(Tutility > temp_utility)
                        {
                            //aqui vamos a hacer la poda
                            //segun el criterio de la utilidad
                            //del padre de la raiz
                            nodoTablero final_hijos[] = new nodoTablero[i];

                            for(int j = 0; j < i; j++)
                            {
                                final_hijos[j] = hijos[j];
                            }

                            raiz.movimientos = final_hijos;
                            
                            i = hijos.length;
                        }
                    }
                }
                //si pasa por aqui quiere decir que el
                //nodo padre del nodo raiz es MIN
                else
                {
                    
                }

                if(hijos[i].funcionUtilidad < temp_utility)
                {
                    temp_utility = hijos[i].funcionUtilidad;
                }
            }
        }
    }

}
