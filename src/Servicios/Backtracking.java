package Servicios;

import Grafo.GrafoDirigido;

public class Backtracking {
	private Solucion mejorSolucion;
	//private ArrayList<String> estaciones;
	
	public Solucion back(GrafoDirigido<String> grafo) {
		
		this.mejorSolucion = null;		
		//Estado estado = new Estado();
		//this.back(grafo, estado);
		return this.mejorSolucion;
	}
	
	public void back(GrafoDirigido<String> grafo, Estado estado) {
		if((this.mejorSolucion == null) ||
		(this.mejorSolucion.getSize() < (grafo.cantidadEstaciones() - 1))) {
			//if(!grafo.get(estado.getEstacionActual())) {
				
			//}
		}
	}
}
