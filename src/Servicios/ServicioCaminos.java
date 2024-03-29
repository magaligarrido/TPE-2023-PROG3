package Servicios;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;
import Grafo.Tunel;

public class ServicioCaminos {

	/**
	 *  Complejidad: O(V + A)
	 *  V: Cantidad de vertices
	 *  A: Cantidad de arcos.
	 *  En el peor de los casos se deberen recorrer cada arco y cada vertice 
	 *  (exceptuando el caso que el limite implicado sea 0, haciendo esto el 
	 *  elemento determinante para el calculo de la complejidad en la operacion).
	**/

	private Grafo<?> grafo;
	private String origen;
	private String destino;
	private int lim;
	private HashMap<Tunel<?>, Boolean> arcosVisitados;
	private ArrayList<List<String>> camino;
	
	// Servicio caminos
	public ServicioCaminos(Grafo<?> grafo, String origen, String destino, int lim) {
		this.grafo = grafo;
		this.origen = origen;
		this.destino = destino;
		this.lim = lim;
		this.arcosVisitados = new HashMap<>();
		this.camino = new ArrayList<List<String>>();
	}

	public List<List<String>> caminos() {		
		Iterator<String> it = this.grafo.obtenerVertices();
		
		
		if (this.lim > 0 && this.grafo != null) {
			this.caminosVisit(new ArrayList<String>(), this.origen, 0);
		}
		return this.camino;
	}
	
	private void caminosVisit(ArrayList<String> list, String verticeActual, int count) {
		if (verticeActual.equals(this.destino) && count > 0) {
			ArrayList<String> tmp = new ArrayList<>();
			tmp.add(this.origen);
			tmp.addAll(list);
			this.camino.add(tmp);
		} else {
			for (Iterator<?> it = this.grafo.obtenerTuneles(verticeActual); it.hasNext();) {
				Tunel<?> arcoActual = (Tunel<?>) it.next();
				if (arcosVisitados.get(arcoActual) == null) {
					arcosVisitados.put(arcoActual, true);
					count += 1;
				
					if (count <= this.lim) {
						String verticeAdyacente = arcoActual.getEstacionDestino();
						list.add(verticeAdyacente);
						this.caminosVisit(list, verticeAdyacente, count);
						list.remove(verticeAdyacente);
					}
					arcosVisitados.replace((Tunel<String>) arcoActual, false);				
					count -= 1;
				}
			}
		}

	}

}