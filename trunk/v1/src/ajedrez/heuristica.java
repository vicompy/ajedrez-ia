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

    public boolean validaPeon(int oX, int oY, int dX, int dY, int[][] tablero){
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
            else if(dX == oX + 1 && dY == oY - 1 && tablero[dX][dY] > 0){
                //se mueve en diagonal a la izquierda si hay una pieza del oponente
                resultado = true;
            }
            else if(dX == oX + 1 && dY == oY + 1 && tablero[dX][dY] > 0){
                //se mueve en diagonal a la derecha si hay una pieza del oponente
                resultado = true;
            }
        }
        return resultado;
    }

    public boolean validaTorre(int oX, int oY, int dX, int dY, int pieza){
        boolean resultado = false;
        if(pieza > 0)
        {
            //es una pieza blanca
            if(dX == oX - 1 && dY == oY){
                //se mueve una casilla hacia arriba
                resultado = true;
            }
        }
        else
        {
            //es una pieza negra
            if(dX == oX + 1 && dY == oY){
                //se mueve una casilla hacia abajo
                resultado = true;
            }
        }
        return resultado;
    }

    public boolean validaAlfil(){
        boolean resultado = false;
        return resultado;
    }

    public boolean validaCaballo(){
        boolean resultado = false;
        return resultado;
    }

    public boolean validaReina(){
        boolean resultado = false;
        return resultado;
    }

    public boolean validaRey(){
        boolean resultado = false;
        return resultado;
    }
}
