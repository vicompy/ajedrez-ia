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

    public nodoTablero raiz = new nodoTablero();
    private int profundidad;
    private int alfa;
    private int beta;

    public minimax(){
        
    }

    //public nodoTablero minimaxEval(int prof){
    public int[][] minimaxEval(int prof,int[][] tablero){
	nodoTablero mov = null;
        profundidad = prof;
        alfa = Integer.MIN_VALUE;
        beta = Integer.MAX_VALUE;
        int max;
	int cmax;
	max = Integer.MIN_VALUE;
        raiz.setPosicionPiezas(tablero);
        raiz.generarHijos();
	for(int i = 0; raiz.hijos != null && i < raiz.hijos.size(); i++){
		cmax = valorMax(raiz.hijos.get(i), profundidad, alfa, beta);
		if(cmax > max){
			max = cmax;
			mov = raiz.hijos.get(i);
		}
	}
	//return mov;
        return mov.posicionPiezas;
    }

    private int valorMax(nodoTablero nodo, int prof, int al, int be){
	nodo.generarHijos();
	if(nodo.hijos == null || prof == 0){
		return nodo.getEvaluacion();
	}
	else{
		for(int i = 0; i < nodo.hijos.size(); i++){
                    al = Math.max(al, valorMin(nodo.hijos.get(i), prof - 1, al, be));
                    if(al >= be)
                        return be;
		}
                return al;
	}
    }

    private int valorMin(nodoTablero nodo, int prof, int al, int be){
	nodo.generarHijos();
	if(nodo.hijos == null || prof == 0){
		return nodo.getEvaluacion();
	}
	else{
		for(int i = 0; i < nodo.hijos.size(); i++){
                    be = Math.min(be, valorMax(nodo.hijos.get(i), prof - 1, al, be));
                    if(al >= be)
                        return al;
		}
                return be;
	}

    }
}
