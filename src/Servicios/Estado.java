package Servicios;

import java.util.ArrayList;
import java.util.HashMap;

import Grafo.Tunel;

public class Estado {
	private String estacionInicial;
	private String estacionActual;	
	private ArrayList<Tunel<Integer>> caminoActual;
	private HashMap<Integer, String> estacionesVisitadas;
	
	public Estado(String estacionInicial) {
		this.estacionInicial = estacionInicial;
		this.estacionActual = estacionInicial;
		this.caminoActual = new ArrayList<>();
		this.estacionesVisitadas = new HashMap();
	}
	
	public String getEstacionActual() {
		return this.estacionActual;
	}
	
	
}
