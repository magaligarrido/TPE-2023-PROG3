package Grafo;

public class Tunel<T> {
	
	private String estacionOrigen;
	private String estacionDestino;
	private int distancia;

	public Tunel(String estacionOrigen, String estacionDestino, int distancia) {
		this.estacionOrigen = estacionOrigen;
		this.estacionDestino = estacionDestino;
		this.distancia = distancia;
	}
	
	public String getEstacionOrigen() {
		return this.estacionOrigen;
	}
	
	public String getEstacionDestino() {
		return this.estacionDestino;
	}

	public int getDistancia() {
		return this.distancia;
	}
}