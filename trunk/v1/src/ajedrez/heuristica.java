/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez;

/**
 *
 * @author William
 */
public class heuristica {

    int oX, oY, dX, dY;
    int tablero[][];

    public heuristica(){
        
    }

    public int funcEvaluacion(int tablero[][]){
        int pieza=0;
        int cBlancas=0;
        int cNegras= 0;
        for (int i=1; i<8; i++){
            for (int j=1; j<8; j++){
                pieza = tablero[i][j];
                if(pieza > 0)
                {
                    //es una pieza blanca
                    cBlancas=cBlancas+1;
                }else{
                    cNegras=cNegras+1;
                }
            }
        }
        
        int Utilidad =(cBlancas-cNegras)/(cBlancas+cNegras);
        return Utilidad;
    }


    public boolean getValidacionPieza(int oX, int oY, int dX, int dY, int[][] tablero, int tipoPieza){
        this.oX = oX;
        this.oY = oY;
        this.dX = dX;
        this.dY = dY;
        this.tablero = tablero;
        switch(tipoPieza){
            case 1:
            case -1:
                return validaPeon();
            case 2:
            case -2:
                return validaCaballo();
            case 3:
            case -3:
                return validaAlfil();
            case 4:
            case -4:
                return validaTorre();
            case 5:
            case -5:
                return validaReina();
        }
        return false;
    }

   // public boolean validaPeon(int oX, int oY, int dX, int dY, int[][] tablero){
     private boolean validaPeon(){
        boolean resultado = false;
        int pieza = tablero[oX][oY];
        if(pieza > 0)
        {
            //es una pieza blanca
            if(dX == oX - 1 && dY == oY && tablero[dX][dY] == 10){
                //se mueve una casilla hacia arriba si esta vacia
                resultado = true;
            }
            else if(dX == oX - 1 && dY == oY - 1 && tablero[dX][dY] < 0){
                //se mueve en diagonal a la izquierda si hay una pieza del oponente
                resultado = true;
            }
            else if(dX == oX - 1 && dY == oY + 1 && tablero[dX][dY] < 0){
                //se mueve en diagonal a la derecha si hay una pieza del oponente
                resultado = true;
            }
        }
        else
        {
            //es una pieza negra
            if(dX == oX + 1 && dY == oY && tablero[dX][dY] == 10){
                //se mueve una casilla hacia abajo si esta vacia
                resultado = true;
            }
            else if(dX == oX + 1 && dY == oY - 1 && tablero[dX][dY] > 0 && tablero[dX][dY] != 10){
                //se mueve en diagonal a la izquierda si hay una pieza del oponente
                resultado = true;
            }
            else if(dX == oX + 1 && dY == oY + 1 && tablero[dX][dY] > 0 && tablero[dX][dY] != 10){
                //se mueve en diagonal a la derecha si hay una pieza del oponente
                resultado = true;
            }
        }
        return resultado;
    }

    //public boolean validaTorre(int oX, int oY, int dX, int dY, int[][] tablero){
     private boolean validaTorre(){
        boolean resultado = false;
        int pieza = tablero[oX][oY];
        if(pieza > 0)
        {
            //es una pieza blanca
            if(oX != dX && oY == dY){
                //se mueve verticalmente
                resultado = true;
                if(dX > oX){
                    //desplazamiento hacia abajo
                    for(int i = oX + 1; i < dX; i++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][dY] != 10){
                            resultado = false;
                        }
                    }
                }
                else{
                    //desplazamiento hacia arriba
                    for(int i = oX - 1; i > dX; i--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][dY] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            else if(oX == dX && oY != dY){
                //se mueve horizontalmente
                resultado = true;
                if(dY > oY){
                    //desplazamiento hacia derecha
                    for(int i = oY + 1; i < dY; i++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[dX][i] != 10){
                            resultado = false;
                        }
                    }
                }
                else{
                    //desplazamiento hacia izquierda
                    for(int i = oY - 1; i > dY; i--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[dX][i] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            //verificar si la casilla destino esta ocupada
            if(tablero[dX][dY] == 10){
                //la casilla esta vacia
                resultado = resultado && true;
            }
            else if(tablero[dX][dY] < 0){
                //la casilla tiene una pieza oponente
                resultado = resultado && true;
            }
            else{
                //la casilla esta ocupada por una pieza propia
                resultado = false;
            }
        }
        else
        {
            //es una pieza negra
            if(oX != dX && oY == dY){
                //se mueve verticalmente
                resultado = true;
                if(dX > oX){
                    //desplazamiento hacia abajo
                    for(int i = oX + 1; i < dX; i++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][dY] != 10){
                            resultado = false;
                        }
                    }
                }
                else{
                    //desplazamiento hacia arriba
                    for(int i = oX - 1; i > dX; i--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][dY] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            else if(oX == dX && oY != dY){
                //se mueve horizontalmente
                resultado = true;
                if(dY > oY){
                    //desplazamiento hacia derecha
                    for(int i = oY + 1; i < dY; i++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[dX][i] != 10){
                            resultado = false;
                        }
                    }
                }
                else{
                    //desplazamiento hacia izquierda
                    for(int i = oY - 1; i > dY; i--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[dX][i] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            //verificar si la casilla destino esta ocupada
            if(tablero[dX][dY] == 10){
                //la casilla esta vacia
                resultado = resultado && true;
            }
            else if(tablero[dX][dY] > 0 && tablero[dX][dY] != 10){
                //la casilla tiene una pieza oponente
                resultado = resultado && true;
            }
            else{
                //la casilla esta ocupada por una pieza propia
                resultado = false;
            }
        }
        return resultado;
    }

    private boolean validaAlfil(){
        boolean resultado = false;
        int pieza = tablero[oX][oY];
        if(pieza > 0)
        {
            //es una pieza blanca
            if(oX != dX && oY != dY){
                //se mueve diagonalmente
                resultado = true;
                if(dX > oX && dY > oY){
                    //desplazamiento hacia diagonal abajo derecha
                    for(int i = oX + 1, j = oY + 1; i < dX && j < dY; i++, j++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX < oX && dY > oY){
                    //desplazamiento hacia diagonal arriba derecha
                    for(int i = oX - 1, j = oY + 1; i > dX && j < dY; i--, j++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX < oX && dY < oY){
                    //desplazamiento hacia diagonal arriba izquierda
                    for(int i = oX - 1, j = oY - 1; i > dX && j > dY; i--, j--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX > oX && dY < oY){
                    //desplazamiento hacia diagonal abajo izquierda
                    for(int i = oX + 1, j = oY - 1; i < dX && j > dY; i++, j--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            //verificar si la casilla destino esta ocupada
            if(tablero[dX][dY] == 10){
                //la casilla esta vacia
                resultado = resultado && true;
            }
            else if(tablero[dX][dY] < 0){
                //la casilla tiene una pieza oponente
                resultado = resultado && true;
            }
            else{
                //la casilla esta ocupada por una pieza propia
                resultado = false;
            }
        }
        else
        {
            //es una pieza negra
            if(oX != dX && oY != dY){
                //se mueve diagonalmente
                resultado = true;
                if(dX > oX && dY > oY){
                    //desplazamiento hacia diagonal abajo derecha
                    for(int i = oX + 1, j = oY + 1; i < dX && j < dY; i++, j++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX < oX && dY > oY){
                    //desplazamiento hacia diagonal arriba derecha
                    for(int i = oX - 1, j = oY + 1; i > dX && j < dY; i--, j++){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX < oX && dY < oY){
                    //desplazamiento hacia diagonal arriba izquierda
                    for(int i = oX - 1, j = oY - 1; i > dX && j > dY; i--, j--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
                else if(dX > oX && dY < oY){
                    //desplazamiento hacia diagonal abajo izquierda
                    for(int i = oX + 1, j = oY - 1; i < dX && j > dY; i++, j--){
                        //verificar si las casillas intermedias estan libres
                        if(tablero[i][j] != 10){
                            resultado = false;
                        }
                    }
                }
            }
            //verificar si la casilla destino esta ocupada
            if(tablero[dX][dY] == 10){
                //la casilla esta vacia
                resultado = resultado && true;
            }
            else if(tablero[dX][dY] > 0 && tablero[dX][dY] != 10){
                //la casilla tiene una pieza oponente
                resultado = resultado && true;
            }
            else{
                //la casilla esta ocupada por una pieza propia
                resultado = false;
            }
        }
        return resultado;
    }

    private boolean validaCaballo(){
        boolean resultado = false;
        int pieza = tablero[oX][oY];
        if((dX == oX - 1 && dY == oY - 2) ||
                (dX == oX - 2 && dY == oY - 1) ||
                (dX == oX - 2 && dY == oY + 1) ||
                (dX == oX - 1 && dY == oY + 2) ||
                (dX == oX + 1 && dY == oY + 2) ||
                (dX == oX + 2 && dY == oY + 1) ||
                (dX == oX + 2 && dY == oY - 1) ||
                (dX == oX + 1 && dY == oY - 2)){
            //el movimiento en L del caballo es valido
            resultado = true;
            if(pieza > 0)
            {
                //es una pieza blanca
                //verificar si la casilla destino esta ocupada
                if(tablero[dX][dY] == 10){
                    //la casilla esta vacia
                    resultado = resultado && true;
                }
                else if(tablero[dX][dY] < 0){
                    //la casilla tiene una pieza oponente
                    resultado = resultado && true;
                }
                else{
                    //la casilla esta ocupada por una pieza propia
                    resultado = false;
                }
            }
            else
            {
                //es una pieza negra
                //verificar si la casilla destino esta ocupada
                if(tablero[dX][dY] == 10){
                    //la casilla esta vacia
                    resultado = resultado && true;
                }
                else if(tablero[dX][dY] > 0 && tablero[dX][dY] != 10){
                    //la casilla tiene una pieza oponente
                    resultado = resultado && true;
                }
                else{
                    //la casilla esta ocupada por una pieza propia
                    resultado = false;
                }
            }
        }
        
        return resultado;
    }

    /** Este metodo sirve para calcular la utilidad de todos los hijos
     *  de una raiz en particular para finalmente recoger la mejor
     *  opcion segun el criterio que se aplique (MAX o MIN)
     *
     *  @param raiz es el nodo que recibira la utilidad que mas
     *              convenga segun MIN o MAX
     *  @param hijos son los hijos que le pertenecen al nodo raiz en
     *               particular
     */
    public void ApplyUtility(nodoTablero raiz, nodoTablero hijos[])
    {
        //La utilidad sera calculada para todos los hijos
        //luego subira segun sea el critero (MAX o MIN) la utilidad
        //para determinar si se trata de nodos terminales los hijos solo visitar
        //la variable esTerminal y con eso basta.
        //si llegase a ser terminal entonces se aplica la funcion utilidad
        //de lo contrario solo se hereda la utilidad segun criterio MIN o MAX
    }

    private boolean validaReina(){
        boolean resultado = false;
        resultado = validaAlfil() || validaTorre();
        return resultado;
    }

    public boolean validaRey(){
        boolean resultado = false;
        return resultado;
    }
}
