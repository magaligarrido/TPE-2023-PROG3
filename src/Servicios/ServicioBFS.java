package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;

public class ServicioBFS {

	private Grafo<?> grafo;
	private HashMap<String, Boolean> visitados;
	
	public ServicioBFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.visitados = new HashMap<>();
	}
	
	public List<String> bfsForest() {
		Iterator<String> it = this.grafo.obtenerVertices();

		// Se marca cada vértice del grafo como no visitado
		while (it.hasNext()) {
			String v = it.next();
			visitados.put(v, false);
		}
		
		it = this.grafo.obtenerVertices();			
		String verticeId = it.next();
				
		return bfsVisit(verticeId);
	}
	
	public List<String> bfsVisit(String verticeActual){
		List<String> salida = new ArrayList<>();
		List<String> fila = new ArrayList<>();
		
		// Marco al vértice como visitado y lo agrego en la fila
		visitados.put(verticeActual, true);	
		fila.add(verticeActual);
		salida.add(verticeActual);
	
		while(!fila.isEmpty()) {
			// Tomamos el vertice x de la fila y lo borramos
			String x = fila.remove(0);
		
			
			Iterator<String> adyacentes = this.grafo.obtenerAdyacentes(x);
			// Para cada vertice y adyacente a x
			while(adyacentes.hasNext()) {
				String y = adyacentes.next();
				
				// Si no esta visitado
				if(!this.visitados.get(y)) {
					// Lo marco como visitado y lo agrego a la fila
					this.visitados.put(y, true);
					fila.add(y);
					salida.add(y);
				}
			}
							
		}

		return salida;		
	}
	
}