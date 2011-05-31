/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package ajedrez;

import java.util.LinkedList;

/**
 *
 * @author William
 */
public class heuristica {

    int oX, oY, dX, dY;
    int tablero[][];

    public heuristica(){
        
    }

    public void funcionEvaluacion(nodoTablero[] tableros, boolean turno){
        //para turno true -> blanco; false -> negro
        for(int i = 0; i < tableros.length; i++){
            nodoTablero tmp = tableros[i];
            int evaluacion = 0;
            evaluacion = evaluacion + evaluarPeones(tmp);
            evaluacion = evaluacion + evaluarCaballo(tmp);
            evaluacion = evaluacion + evaluarAlfil(tmp);
            evaluacion = evaluacion + evaluarTorre(tmp);
            evaluacion = evaluacion + evaluarReina(tmp);
        }
    }

    private int evaluarPeones(nodoTablero pTablero){
        int eval = 0;
        if(pTablero.turno == true){
            //piezas blancas
            for(int i = 0; i < 8; i++){
                for(int j = 0; i < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == 1){
                        //si se encuentra una pieza sumar 100
                        eval = eval + 100;
                        if(j == 3 || j == 4){
                            //si esta en el centro del tablero sumar 12
                            eval = eval + 12;
                        }
                        //suma dos puntos por cada casilla que haya avanzado
                        eval = eval + 2 * (6 - i);
                    }
                }
            }
        }
        else{
            //piezas negras
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == -1){
                        //si se encuentra una pieza sumar 100
                        eval = eval + 100;
                        if(j == 3 || j == 4){
                            //si esta en el centro del tablero sumar 12
                            eval = eval + 12;
                        }
                        //suma dos puntos por cada casilla que haya avanzado
                        eval = eval + 2 * (Math.abs(1 - i));
                    }
                }
            }
        }
        return eval;
    }

    private int evaluarCaballo(nodoTablero pTablero){
        int eval = 0;
        if(pTablero.turno == true){
            //piezas blancas
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == 2){
                        //si se encuentra una pieza sumar 315
                        eval = eval + 315;
                        //sumar entre -15 y 15 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 15;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 15;
                        }
                    }
                }
            }
        }
        else{
            //piezas negras
            for(int i = 0; i < 8; i++){
                for(int j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == -2){
                        //si se encuentra una pieza sumar 315
                        eval = eval + 315;
                        //sumar entre -15 y 15 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 15;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 15;
                        }
                    }
                }
            }
        }
        return eval;
    }

    private int evaluarAlfil(nodoTablero pTablero){
        int eval = 0;
        int i;
        int j;
        int a,b,m;
        int mov1,mov2,mov3,mov4=0;
        char[][] tabla = new char[9][9];

        if(pTablero.turno == true){
            //piezas blancas
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == 3){
                        //si se encuentra una pieza sumar 330
                        eval = eval + 330;
                        //sumar entre -15 y 15 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 15;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 15;
                        }

                        //la hubicación se encuentra en un alfil

                        a=i;
                        b=j;

                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//alfil
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//if alfil blanco
                }//for j
            }//for i
        }//if turno blancas
        else{
            //piezas negras
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == -3){
                        //si se encuentra una pieza sumar 330
                        eval = eval + 330;
                        //sumar entre -15 y 15 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 15;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 15;
                        }
                        //la hubicación se encuentra en un alfil

                        a=i;
                        b=j;
                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//alfil
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//si alfil negro
                }//for j
            }//for i
        }//turno negra
        return eval;
    }
    
    private int evaluarTorre(nodoTablero pTablero){
        int eval = 0;
        int i;
        int j;
        int a,b,m;
        int mov1,mov2,mov3,mov4=0;
        char[][] tabla = new char[9][9];

        if(pTablero.turno == true){
            //piezas blancas
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == 4){
                        //si se encuentra una pieza sumar 500
                        eval = eval + 500;

                        //la hubicación se encuentra en una torre

                        a=i;
                        b=j;

                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//Torre
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//if torre blanca
                }//for j
            }//for i
        }//if turno blancas
        else{
            //piezas negras
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == -4){
                        //si se encuentra una pieza sumar 500
                        eval = eval + 500;
                        //la hubicación se encuentra en una torre

                        a=i;
                        b=j;
                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//torre
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//si torre negra
                }//for j
            }//for i
        }//turno negra

        return eval;
    } //evaluarTorre



    private int evaluarReina(nodoTablero pTablero){
        int eval = 0;
        int i;
        int j;
        int a,b,m;
        int mov1,mov2,mov3,mov4=0;
        char[][] tabla = new char[9][9];

        if(pTablero.turno == true){
            //piezas blancas
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == 5){
                        //si se encuentra una pieza sumar 940
                        eval = eval + 940;
                        //sumar entre -10 y 10 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 10;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 10;
                        }

                        //la hubicación se encuentra en una reina

                        a=i;
                        b=j;

                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//Reina
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//if alfil blanco
                }//for j
            }//for i
        }//if turno blancas
        else{
            //piezas negras
            for(i = 0; i < 8; i++){
                for(j = 0; j < 8; j++){
                    if(pTablero.posicionPiezas[i][j] == -5){
                        //si se encuentra una pieza sumar 940
                        eval = eval + 940;
                        //sumar entre -10 y 10 dependiendo de que tan cercano este del centro del tablero
                        if(j == 3 || j == 4){
                            eval = eval + 10;
                        }
                        else if(j == 2 || j == 5){
                            eval = eval + 5;
                        }
                        else if(j == 1 || j == 6){
                            eval = eval - 5;
                        }
                        else{
                            eval = eval - 10;
                        }
                        //la hubicación se encuentra una reina

                        a=i;
                        b=j;
                        mov1=mov2=mov3=mov4=0;
                        for(i = 0; i < 8; i++){
                            m=a-i;
                            for(j = 0; j < 8; j++){
                                if(a==i && b==j){
                                    tabla[i][j]='A';//reina
                                }
                                else{
                                    tabla[i][j]=' ';
                                }

                                if(j+m==b || j-m==b){
                                    if(j+m==b){
                                        mov1=mov1+1;
                                        mov3=mov3+1;
                                    }
                                    if(j-m==b){
                                        mov2=mov2+1;
                                        mov4=mov4+1;
                                    }
                                    if(tabla[i][j]=='A'){
                                        mov3=0;
                                        mov4=0;
                                    }
                                }
                            }//for j
                        }//for i
                        mov1 = Math.abs(mov1-mov3)-1;
                        mov2 = Math.abs(mov2-mov4)-1;
                        if (mov1>mov2 && mov1>mov3 && mov1>mov4)
                            eval = eval+2*mov1;
                        else if (mov2>mov1 && mov2>mov3 && mov2>mov4)
                            eval = eval+2*mov2;
                        else if (mov3>mov1 && mov3>mov2 && mov3>mov4)
                            eval = eval+2*mov3;
                        else if (mov4>mov1 && mov4>mov2 && mov4>mov3)
                            eval = eval+2*mov4;
                    }//si alfil negro
                }//for j
            }//for i
        }//turno negra

        return eval;
    } //evaluarReina

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
            case 6:
            case -6:
                return validaRey();
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
            if(Math.abs(dX - oX) == Math.abs(dY - oY)){
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
            if(Math.abs(dX - oX) == Math.abs(dY - oY)){
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
// <editor-fold defaultstate="collapsed" desc="Generated Code">
    public nodoTablero[] generateChilds(int tablero[][], int tipo)
    {
        java.util.LinkedList<nodoTablero> jugada = new java.util.LinkedList<nodoTablero>();

        for(int i = 0; i < 8; i++)
        {
            for(int j = 0; j < 8; j++)
            {
                //se generan los hijos en funcion de estos dos criterios
                //ya que se hacen las posibles jugadas con la logica
                //del otro jugador
                if((((tipo > 0) && (tablero[i][j] < 0)) || ((tipo < 0) && (tablero[i][j] > 0))))
                {
                    //Peon
                    if((tablero[i][j] == -1) || (tablero[i][j] == 1))
                    {
                        //El tiro anterior es una pieza Blanca
                        //por lo tanto vamos a buscar posibilidades
                        //de tiro con piezas Negras
                        if((tipo > 0) && (tablero[i][j] < 0))
                        {
                            if(((i + 1) < 8) && ((j + 1) < 8))
                            {
                                if(getValidacionPieza(i, j, i + 1, j + 1, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;
                                    Ttablero[i][j] = 10;
                                    Ttablero[i + 1][j + 1] = -1;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }

                            if(((j + 1) < 8))
                            {
                                if(getValidacionPieza(i, j, i, j + 1, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;
                                    Ttablero[i][j] = 10;
                                    Ttablero[i][j + 1] = -1;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }
                        //El tiro anterior es una pieza Negra
                        //por lo tanto vamos a buscar posibilidades
                        //de tiro con piezas Blancas
                        else if((tipo < 0) && (tablero[i][j] > 0))
                        {
                            if(((i - 1) >= 0) && ((j - 1) >= 0))
                            {
                                if(getValidacionPieza(i, j, i - 1, j - 1, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;
                                    Ttablero[i][j] = 10;
                                    Ttablero[i - 1][j - 1] = 1;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }

                            if(((j - 1) >= 0))
                            {
                                if(getValidacionPieza(i, j, i, j - 1, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;
                                    Ttablero[i][j] = 10;
                                    Ttablero[i][j - 1] = 1;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }
                    }
                    //Caballo
                    else if((tablero[i][j] == -2) || (tablero[i][j] == 2))
                    {
                        if(((j + 2) < 8) &&((i + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i + 1, j + 2, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 1][j + 2] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 1][j + 2] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((j + 2) < 8) &&((i - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i - 1, j + 2, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 1][j + 2] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 1][j + 2] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i + 2) < 8) &&((j + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i + 2, j + 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 2][j + 1] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 2][j + 1] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 2) >= 0) && ((j + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i - 2, j + 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 2][j + 1] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 2][j + 1] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 2) >= 0) && ((j - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i - 2, j - 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 2][j - 1] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 2][j - 1] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i + 1) < 8) && ((j - 2) >= 0))
                        {
                            if(getValidacionPieza(i, j, i + 1, j - 2, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 1][j - 2] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 1][j - 2] = 2;

                                Ttablero[i][j] = 10;
                                
                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i + 2) < 8) && ((j - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i + 2, j - 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 2][j - 1] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 2][j - 1] = 2;

                                Ttablero[i][j] = 10;
                                
                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 1) >= 0) && ((j - 2) >= 0))
                        {
                            if(getValidacionPieza(i, j, i - 1, j - 2, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 1][j - 2] = -2;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 1][j - 2] = 2;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }
                    }
                    //Alfil
                    else if((tablero[i][j] == -3) || (tablero[i][j] == 3))
                    {
                        for(int x = (i + 1); x < 8; x++)
                        {
                            for(int y = (j + 1); y < 8; y++)
                            {
                                if(getValidacionPieza(i, j, x, y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] > 0)
                                        Ttablero[x][y] = 3;
                                    else if(tablero[i][j] < 0)
                                        Ttablero[x][y] = -3;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }

                        for(int x = (i - 1); x >= 0; x--)
                        {
                            for(int y = (j - 1); y >= 0; y--)
                            {
                                if(getValidacionPieza(i, j, x, y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;
                                    
                                    if(tablero[i][j] > 0)
                                        Ttablero[x][y] = 3;
                                    else if(tablero[i][j] < 0)
                                        Ttablero[x][y] = -3;
                                    
                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }
                    }
                    //Torre
                    else if(tablero[i][j] == -4)
                    {
                        //movimientos posibles
                        for(int x = 0; x < 7; x++)
                        {
                            if(((i + x) < 8))
                            {
                                if(getValidacionPieza(i, j, i + x, j, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[i + x][j] = -4;
                                    else if(tablero[i][j] > 0)
                                        Ttablero[i + x][j] = 4;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }

                        //movimientos posibles
                        for(int y = 0; y < 7; y++)
                        {
                            if(((j + y) < 8))
                            {
                                if(getValidacionPieza(i, j, i, j + y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[i][j + y] = -4;
                                    else if(tablero[i][j] > 0)
                                        Ttablero[i][j + y] = 4;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }
                    }
                    //Reina
                    else if(tablero[i][j] == -5)
                    {
                        //movimientos posibles
                        for(int x = 0; x < 7; x++)
                        {
                            if(((i + x) < 8))
                            {
                                if(getValidacionPieza(i, j, i + x, j, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[i + x][j] = -5;
                                    else if(tablero[i][j] > 0)
                                        Ttablero[i + x][j] = 5;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }

                        //movimientos posibles
                        for(int y = 0; y < 7; y++)
                        {
                            if(((j + y) < 8))
                            {
                                if(getValidacionPieza(i, j, i, j + y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[i][j + y] = -5;
                                    else if(tablero[i][j] > 0)
                                        Ttablero[i][j + y] = 5;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }

                        for(int x = (i + 1); x < 8; x++)
                        {
                            for(int y = (j + 1); y < 8; y++)
                            {
                                if(getValidacionPieza(i, j, x, y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[x][y] = -5;
                                    else if(tablero[i][j] > 0)
                                        Ttablero[x][y] = 5;
                                    
                                    Ttablero[i][j] = 10;
                                    
                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }

                        for(int x = (i - 1); x >= 0; x--)
                        {
                            for(int y = (j - 1); y >= 0; y--)
                            {
                                if(getValidacionPieza(i, j, x, y, tablero, tipo))
                                {
                                    int Ttablero[][] = new int[8][8];

                                    Ttablero = tablero;

                                    if(tablero[i][j] < 0)
                                        Ttablero[x][y] = -5;    
                                    else if(tablero[i][j] > 0)
                                        Ttablero[x][y] = 5;

                                    Ttablero[i][j] = 10;

                                    nodoTablero nodo = new nodoTablero();

                                    nodo.posicionPiezas = Ttablero;

                                    jugada.add(nodo);
                                }
                            }
                        }
                    }
                    //Rey
                    else if(tablero[i][j] == -6)
                    {
                        if(((i + 1) < 8) && ((j + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i + 1, j + 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 1][j + 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 1][j + 1] = 6;
                                
                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 1) >= 0) && ((j - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i - 1, j - 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 1][j - 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 1][j - 1] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 1) >= 0) && ((j + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i - 1, j + 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 1][j + 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 1][j + 1] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i + 1) < 8) && ((j - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i + 1, j - 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 1][j - 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 1][j - 1] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i + 1, j, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i + 1][j] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i + 1][j] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((i - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i - 1, j, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i - 1][j] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i - 1][j] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((j + 1) < 8))
                        {
                            if(getValidacionPieza(i, j, i, j + 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i][j + 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i][j + 1] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }

                        if(((j - 1) >= 0))
                        {
                            if(getValidacionPieza(i, j, i, j - 1, tablero, tipo))
                            {
                                int Ttablero[][] = new int[8][8];

                                Ttablero = tablero;

                                if(tablero[i][j] < 0)
                                    Ttablero[i][j - 1] = -6;
                                else if(tablero[i][j] > 0)
                                    Ttablero[i][j - 1] = 6;

                                Ttablero[i][j] = 10;

                                nodoTablero nodo = new nodoTablero();

                                nodo.posicionPiezas = Ttablero;

                                jugada.add(nodo);
                            }
                        }
                    }
                }
            }
        }

        return (nodoTablero[])jugada.toArray();
    }
// </editor-fold>

    private boolean validaReina(){

        boolean resultado = false;
        resultado = validaAlfil() || validaTorre();
        return resultado;
    }

    private boolean validaRey(){
        boolean resultado = false;
        int pieza = tablero[oX][oY];
        if(dX == oX + 1 && dY == oY){
            //se mueve hacia abajo
            resultado = true;
        }
        else if(dX == oX - 1 && dY == oY){
            //se mueve hacia arriba
            resultado = true;
        }
        else if(dX == oX && dY == oY + 1){
            //se mueve a la derecha
            resultado = true;
        }
        else if(dX == oX && dY == oY - 1){
            //se mueve a la izquierda
            resultado = true;
        }
        else if(dX == oX - 1 && dY == oY + 1){
            //se mueve en diagonal arriba derecha
            resultado = true;
        }
        else if(dX == oX + 1 && dY == oY + 1){
            //se mueve en diagonal abajo derecha
            resultado = true;
        }
        else if(dX == oX + 1 && dY == oY - 1){
            //se mueve en diagonal abajo izquierda
            resultado = true;
        }
        else if(dX == oX - 1 && dY == oY - 1){
            //se mueve en diagonal arriba izquierda
            resultado = true;
        }
        if(pieza > 0)
        {
            //es una pieza blanca
            //verificar si la casilla destino esta ocupada
            if(tablero[dX][dY] == 10 || tablero[dX][dY] < 0){
                //la casilla esta vacia o tiene una pieza oponente
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
            if(tablero[dX][dY] > 0){
                //la casilla esta vacia o tiene una pieza oponente
                resultado = resultado && true;
            }
            else{
                //la casilla esta ocupada por una pieza propia
                resultado = false;
            }
        }
        return resultado;
    }
}
