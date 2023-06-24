package Servicios;

import java.util.ArrayList;
import java.util.HashMap;

import Grafo.Tunel;

public class Estado {
	private String estacionInicial;
	private String estacionActual;	
	private int distancia;
	private ArrayList<Tunel<String>> caminoActual;
	private HashMap<String, Boolean> estacionesVisitadas;
	
	public Estado(String origen) {		
		this.estacionInicial = origen;
		this.estacionActual = origen;
		this.distancia = 0;
		this.caminoActual = new ArrayList<>();	
		this.estacionesVisitadas = new HashMap<>();
	}
	
	public String getEstacionInicial() {
		return this.estacionInicial;
	}
	
	public void setEstacionInicial(String estacionInicial) {
		this.estacionInicial = estacionInicial;
	}
	
	public String getEstacionActual() {
		return this.estacionActual;
	}
	
	public void setEstacionActual(String estacionActual) {
		this.estacionActual = estacionActual;
	}
	
	public int getSize() {
		return this.caminoActual.size();
	}
	
	public boolean isVisitada(String estacionId) {
		return estacionesVisitadas.containsKey(estacionId);
	}
		
	public void marcarVisitada(String estacionId) {	
		this.estacionesVisitadas.put(estacionId, true);
	}
	
	public void desmarcarVisitada(String estacionId) {
		this.estacionesVisitadas.remove(estacionId);
	}
	
	public int estacionesVisitadasSize() {
		return this.estacionesVisitadas.size();
	}
	
	public void sumarDistancia(int d) {
		this.distancia = this.distancia + d;	
	}
	
	public void restarDistancia(int d) {
		this.distancia = this.distancia - d;
	}
	
	public int getDistanciaCamino( ) {
		return this.distancia;
	}
	
	public void agregarAlCamino(Tunel<String> tunel) {
		this.caminoActual.add(tunel);
		this.estacionActual = tunel.getEstacionDestino();
	}
	
	public boolean existeTunelEnCamino(Tunel<String> tunel) {
		// Como el tunel puede estar en diferente direccion
		Tunel<String> tunelRedireccionado = new Tunel<String>(tunel.getEstacionDestino(), tunel.getEstacionOrigen(), tunel.getDistancia());
		return (this.caminoActual.contains(tunel)) ||  (this.caminoActual.contains(tunelRedireccionado));
	}
	
	public void quitarUltimoDelCamino() {
		this.caminoActual.remove(this.caminoActual.size() - 1);
	}
	
	public ArrayList<Tunel<String>> getCamino() {
		ArrayList<Tunel<String>> camino = new ArrayList<>();
		
		for(Tunel<String> tunel : caminoActual) {
			camino.add(tunel);
		}
		
		return camino;
	}
	
	
}
