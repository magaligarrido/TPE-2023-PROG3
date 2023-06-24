package Servicios;

import java.util.ArrayList;

import Grafo.Tunel;

public class Solucion {
	private ArrayList<Tunel<String>> camino;
	private int distancia;
	
	public Solucion() {
		this.camino = new ArrayList<>();
	}
	
	public Solucion(ArrayList<Tunel<String>> camino, int distancia) {
		this.camino = new ArrayList<>(camino);
		this.distancia = distancia;
	}
	
	public int getSize() {
		return this.camino.size();
	}
	
	public ArrayList<String> getCamino() {
		ArrayList<String> salida = new ArrayList<>();
		for(Tunel<String> tunel : camino) {
			String estaciones = tunel.getEstacionOrigen() + "-"  + tunel.getEstacionDestino();
			salida.add(estaciones);
		}
		return salida;
	}
	
	public int getDistancia() {
		return this.distancia;
	}
	
	public String toString() {
		String salida = "";
		return salida;
	}

}
