package Servicios;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;
import Grafo.Tunel;

public class Greedy {

	// El objetivo es unir todas las estaciones mediante tuneles pero
	// logrando la menor distancia posible.
	
	// Candidatos: todos los tuneles del grafo que conectan las estaciones
	// Solucion: los tuneles que conectan todas las estaciones sin repetirlas.
	
	private HashMap<String, Boolean> estacionesConectadas;
	private int cantidadEstaciones;
	
	public Solucion greedy(GrafoDirigido<String> grafo) {
		estacionesConectadas = new HashMap<>();
		Iterator<String> it = grafo.obtenerVertices();
		while(it.hasNext()) {
			String estacion = it.next();
			estacionesConectadas.put(estacion, false);
		}			
		
		cantidadEstaciones = grafo.cantidadEstaciones();
		Solucion s = new Solucion();

		// Obtengo todos los tuneles del grafo ordenados de menor a mayor por valor de distancia
		ArrayList<Tunel<String>> tuneles = grafo.obtenerTunelesOrdenados();		
	
		while(!tuneles.isEmpty() && !esSolucion(s)) {
			
			Tunel<String> candidato = tuneles.get(0);
			tuneles.remove(candidato);			
				
			if(esFactible(s, candidato)){								
				s.agregar(candidato);				
				estacionesConectadas.replace(candidato.getEstacionDestino(), true);
			}			
		
		}

		if(esSolucion(s)) {
			return s;
		}
		
		return null;
	}
	
	private boolean esSolucion(Solucion s) {
		// Si pude conectar todas las estaciones
		if(s.getCamino().size() == cantidadEstaciones -1 ) {
			return true;
		}else {
			return false;
		}
		
	}
	
	private boolean esFactible(Solucion s, Tunel<String> candidato) {	
		// Si la estacion destino aun no fue conectada retorna true		
		return (!estacionesConectadas.get(candidato.getEstacionDestino()));
	}
	
}
