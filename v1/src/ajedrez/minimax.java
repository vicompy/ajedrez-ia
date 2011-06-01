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

    public nodoTablero raiz;
    private int profundidad;

    public nodoTablero minimaxEval(){
	nodoTablero mov = null;
        int max;
	int cmax;
	max = Integer.MIN_VALUE;
        raiz.generarHijos();
	for(int i = 0; raiz.hijos != null && i < raiz.hijos.size(); i++){
		cmax = valorMax(raiz.hijos.get(i), profundidad);
		if(cmax > max){
			max = cmax;
			mov = raiz.hijos.get(i);
		}
	}
	return mov;
    }

    private int valorMax(nodoTablero nodo, int prof){
	int vmax;
        nodo.generarHijos();
	if(nodo.hijos == null || prof == 0){
		return nodo.getEvaluacion();
	}
	else{
		vmax = Integer.MIN_VALUE;
		for(int i = 0; i < nodo.hijos.size(); i++){
			vmax = Math.max(vmax, valorMin(nodo.hijos.get(i), prof - 1));
		}
                return vmax;
	}
    }

    private int valorMin(nodoTablero nodo, int prof){
	int vmin;
        nodo.generarHijos();
	if(nodo.hijos == null || prof == 0){
		return nodo.getEvaluacion();
	}
	else{
		vmin = Integer.MAX_VALUE;
		for(int i = 0; i < nodo.hijos.size(); i++){
			vmin = Math.min(vmin, valorMax(nodo.hijos.get(i), prof - 1));
		}
                return vmin;
	}

    }


}
