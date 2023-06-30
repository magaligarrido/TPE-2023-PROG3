package Grafo;

import java.util.ArrayList;
import java.util.Iterator;

import Servicios.MergeSort;

public class GrafoNoDirigido<T> extends GrafoDirigido<T> {

	@Override
	public void agregarTunel(String estacionId1, String estacionId2, int distancia) {
		super.agregarTunel(estacionId1, estacionId2, distancia);
		super.agregarTunel(estacionId2, estacionId1, distancia);
	}
	
	@Override
	public void borrarTunel(String estacionId1, String estacionId2) {
		super.borrarTunel(estacionId1, estacionId2);
		super.borrarTunel(estacionId2, estacionId1);
	}
	
	@Override
	public int cantidadTuneles() {
		return super.cantidadTuneles() / 2;
	}
	
	// Adaptación del método obtenerTuneles sobre un grafo no dirigido
	@Override
	public Iterator<Tunel<T>> obtenerTuneles() {
		ArrayList<Tunel<T>> resultadoTuneles = new ArrayList<Tunel<T>>();	
		for(ArrayList<Tunel<T>> tuneles: estaciones.values()) {
			for(Tunel<T> tunel : tuneles) {	
				Tunel<T> tunelRedireccionado = new Tunel<T>(tunel.getEstacionDestino(), tunel.getEstacionOrigen(), tunel.getDistancia());
				if(!resultadoTuneles.contains(tunel) && !resultadoTuneles.contains(tunelRedireccionado)) {
					resultadoTuneles.add(tunel);
				}
			}			
		}
		return resultadoTuneles.iterator();
	}
	
	//@Override
	//public Iterator<Tunel<T>> obtenerTuneles(String estacionId) {
	//	ArrayList<Tunel<T>> tunelesDeLaEstacion = estaciones.get(estacionId);
	//	for(Tunel<T> tunel : tunelesDeLaEstacion) {
	//		Tu
	//	}
	//}

	
}