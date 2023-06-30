package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import Grafo.Grafo;

public class ServicioDFS {
	private HashMap<String, String> colores; 
	private Grafo<?> grafo;

	public ServicioDFS(Grafo<?> grafo) {
		this.grafo = grafo;
		this.colores = new HashMap<>();		
	}
	
	public List<String> dfsForest() {
		Iterator<String> it = this.grafo.obtenerVertices();

		// Se marca cada vértice del grafo como no visitado
		while (it.hasNext()) {
			String verticeId = it.next();
			colores.put(verticeId, "blanco");
		}
		
		it = this.grafo.obtenerVertices();			
		String verticeId = it.next();
		
		return dfsVisit(verticeId);

	}	
	
	public List<String> dfsVisit(String verticeActual){
		List<String> salida = new ArrayList<>();
		
		// Marco al vértice como visitado
		colores.put(verticeActual, "amarillo");	

		// Si ya no hay ninguno blanco es porque llegue al final de un camino
		if(!colores.containsValue("blanco")) {
			salida.add(verticeActual);
			colores.put(verticeActual, "blanco");
			return salida;
		} else {
			// Por cada  adyacente del vertice
			Iterator<String> it = this.grafo.obtenerAdyacentes(verticeActual);
			
			while(it.hasNext()){
				String adyacente = it.next();				
				// Si el adyacente no fue visitado
				if(this.colores.get(adyacente).equals("blanco")) {
					// Busco los caminos desde el adyacente
					List<String> camino = dfsVisit(adyacente);					
					if(!camino.isEmpty()) {
						List<String> caminoCompleto = new ArrayList<>();
						caminoCompleto.add(verticeActual);
						caminoCompleto.addAll(camino);
						salida.addAll(caminoCompleto);				
					}
				}
			}
		}
			
		return salida;		
	}

}
