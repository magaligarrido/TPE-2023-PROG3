package Servicios;

import java.util.ArrayList;
import java.util.Iterator;

import Grafo.GrafoNoDirigido;
import Grafo.Tunel;

public class Backtracking {
	private Solucion mejorSolucion;
	private ArrayList<String> estaciones;
	
	/*
	 * Estado:  
	 * 		posicion: la estación en la que estamos parados
	 * 		
	 * 		distancia:¿
	 */ 
	
	/*
	 *  Estado final: cuando no tengo más estaciones que recorrer 
	 *                o mejorSolucion contiene los tuneles entre todas las estaciones
	 * 	Estado solucion: cuando utilice todas las estaciones sin superar los metros base
	 *  Solucion: arreglo con los pares de estaciones para hacer los tuneles
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
		if(estado.getSize() == (estaciones.size()-1) && estado.estacionesVisitadasSize() == (estaciones.size())) {			

			// Si es la primera solución que encuentro o es mejor que la que tenia
			if(this.mejorSolucion == null || this.mejorSolucion.getDistancia() > estado.getDistanciaCamino()) {
				this.mejorSolucion = new Solucion(estado.getCamino(), estado.getDistanciaCamino());			
			} 
			
		} else {
				Iterator<Tunel<String>> adyacentes = grafo.obtenerTuneles(estado.getEstacionActual());
				while(adyacentes.hasNext()) {
					Tunel<String> adyacente = adyacentes.next();					
					// Si la estacion destino es diferente a la estacion inicial 				
					if(!estado.existeTunelEnCamino(adyacente) && !estado.isVisitada(adyacente.getEstacionDestino()) && adyacente.getEstacionDestino() != estado.getEstacionInicial())  {
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
		// ya supera el costo de esa solucion encontrada .
		return (this.mejorSolucion!= null && estado.getDistanciaCamino() > this.mejorSolucion.getDistancia());
	}
}
	