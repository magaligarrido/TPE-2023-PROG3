package Servicios;

import java.util.ArrayList;

import Grafo.Tunel;

public class Solucion {
	private ArrayList<Tunel<Integer>> camino;
	
	public Solucion() {
		this.camino = new ArrayList<>();
	}
	
	public int getSize() {
		return this.camino.size();
	}

}
