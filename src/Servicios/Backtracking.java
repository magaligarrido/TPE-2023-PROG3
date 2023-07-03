package Servicios;

import java.util.ArrayList;
import java.util.Iterator;

import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;
import Grafo.Tunel;

public class Backtracking {
	private Solucion mejorSolucion;
	private ArrayList<String> estaciones;
	private ArrayList<String> tuneles;
	
	/*
	 * Estado:  
	 * 		estacion actual
	 * 		camino de tuneles
	 * 		distancia del camino
	 * 		estaciones visitadas									
	 */ 
	
	/*
	 *  Estado final:    cuando el camino contiene la cantidad de tuneles 
	 *  			     que se necesitan para conectar todas las estaciones
	 *  			     y cuando se visitaron todas las estaciones
	 *  
	 * 	Estado solucion: cuando se visitaron todas las estaciones para
	 * 					 asegurarnos que van a estar todas conectadas. 
	 * 
	 *  Mejor solucion:  si aun no tengo solucion se guarda, si ya existe una 
	 *  				 queda la que tenga menor distancia
	 */
	
	public Solucion back(GrafoNoDirigido<String> grafo) {		
		this.mejorSolucion = null;	
		estaciones = new ArrayList<>();
		
		Iterator<String> it = grafo.obtenerVertices();
		while(it.hasNext()) {
			String estacion = it.next();
			estaciones.add(estacion);
		}			
		
		String origen = estaciones.get(0);
		Estado estado = new Estado(origen);
		estado.marcarVisitada(origen);
		this.back(grafo, estado);
		return this.mejorSolucion;
	}
	
	public void back(GrafoNoDirigido<String> grafo, Estado estado) {
		//Si es estado final	
		if(estado.getSize() == (estaciones.size()-1)) {			
			// Si es solucion
			if(estado.estacionesVisitadasSize() == (estaciones.size())) {
				// La guardo solo si es la primera que encuentro o es mejor que la que tenia
				if(this.mejorSolucion == null || this.mejorSolucion.getDistancia() > estado.getDistanciaCamino()) {
					this.mejorSolucion = new Solucion(estado.getCamino(), estado.getDistanciaCamino());	
					//System.out.println(this.mejorSolucion.getDistancia());
					//System.out.println(this.mejorSolucion.getCamino());
				} 
			}
			
		} else {
				// Estrategia de obtener solo los túneles de la estación
				// actual para evitar un árbol de exploración tan grande
				 
				Iterator<Tunel<String>> tuneles = grafo.obtenerTuneles(estado.getEstacionActual());
				
				// Estrategia de obtener TODOS los túneles del grafo para
				// lograr los mejores resultados en los grafos más pequeños
			////	Iterator<Tunel<String>> tuneles = grafo.obtenerTuneles();
				while(tuneles.hasNext()) {
					
					Tunel<String> adyacente = tuneles.next();
					
					// Si la estacion destino es diferente a la estacion inicial y no fue visitada		
					// y si no existe ya el tunel en el camino
					if(!estado.existeTunelEnCamino(adyacente) && !estado.isVisitada(adyacente.getEstacionDestino()))  {						
						estado.agregarAlCamino(adyacente);
						estado.setEstacionActual(adyacente.getEstacionDestino());
						estado.sumarDistancia(adyacente.getDistancia());
						estado.marcarVisitada(adyacente.getEstacionDestino());
						
						// Si no tengo que podar
						if(!poda(estado)) {
							back(grafo, estado);
						} 						
						estado.quitarUltimoDelCamino();
						estado.setEstacionActual(adyacente.getEstacionOrigen());	
						estado.restarDistancia(adyacente.getDistancia());	
						estado.desmarcarVisitada(adyacente.getEstacionDestino());
				 
					}
				}

		}
		
	}	
	
	private boolean poda(Estado estado) {	
		// Si ya tenia guardada una solucion para comparar y el costo del camino actual
		// ya supera el costo de esa solucion encontrada poda.
		return (this.mejorSolucion!= null && estado.getDistanciaCamino() > this.mejorSolucion.getDistancia());
	}
}
	