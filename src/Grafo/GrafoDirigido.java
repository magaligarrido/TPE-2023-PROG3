package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class GrafoDirigido<T> implements Grafo<T> {
	// La key es nombre de una estación y el value la lista de tuneles salientes de la misma.
	private HashMap<String, ArrayList<Tunel<T>>> estaciones;
	private int cantidadDeTuneles;

	public GrafoDirigido() {
		this.estaciones = new HashMap<String,ArrayList<Tunel<T>>>();
	}
	
	@Override
	public void agregarEstacion(String estacionId) {
		// Se agrega la estación solo si no existe la clave estacionId
		this.estaciones.putIfAbsent(estacionId, new ArrayList<Tunel<T>>());	
		
	}

	@Override
	public void borrarEstacion(String estacionId) {	
		estaciones.forEach((k,v) -> {
			this.borrarTunel(k, estacionId);
		});	
			
	}

	@Override
	public void agregarTunel(String estacionId1, String estacionId2, int distancia) {
		// Para agregar un tunel tienen que existir las estaciones origen y destino.
		if(this.contieneEstacion(estacionId1) && this.contieneEstacion(estacionId2)) {			
			// Reutilizamos obtenerArco() para evitar si no es necesario recorrer todos los arcos.
			Tunel<T> tunel = obtenerTunel(estacionId1, estacionId2); 
			if (tunel == null) { // Significa que aun no existe el arco
				tunel = new Tunel<>(estacionId1, estacionId2, distancia);
				this.estaciones.get(estacionId1).add(tunel);
				cantidadDeTuneles++;
			}
		}	
	}
	
	//** 
	// * Complejidad: O(a) donde a es la lista de tuneles salientes
	// * de la estación origen. Se busca eliminar el tunel que tenga
	// * como destino la estacionId2.
	//**
	@Override
	public void borrarTunel(String estacionId1, String estacionId2) {
		// Si no existen las estaciones origen y destino, no existe el tunel.
		if(this.contieneEstacion(estacionId1) && this.contieneEstacion(estacionId2)) {
			
			// Booleano para no recorrer todos los tuneles de la estacionId1 si no es necesario
			boolean eliminado = false;
			
			Tunel<T> aux = null;
			Iterator<Tunel<T>> tuneles = this.estaciones.get(estacionId1).iterator();
			
			while (tuneles.hasNext() && !eliminado) { 
				aux = tuneles.next();			
				if (aux.getEstacionDestino().equals(estacionId2)) {		
					 this.estaciones.get(estacionId1).remove(aux);
					 eliminado = true;
					 cantidadDeTuneles--; 
				 }
				        
			} 
			
		}
		
	}

	//** 
	// * Complejidad: O(1) donde 1 es la estacionId1 que se busca
	// * en el HashMap de estaciones.
	//**
	@Override
	public boolean contieneEstacion(String estacionId) {
		return estaciones.containsKey(estacionId);
	}
	
	//** 
	// * Complejidad: O(a) donde a es la cantidad de arcos salientes
	// * del verticeId1 (origen). La búsqueda de verticeId1 en el
	// * HashMap es O(1) y sólo se busca dentro de la lista de los
	// * arcos que salen de él, consultando cada uno de ellos si su
	// * vértice destino es igual a verticeId2.
	//**
	@Override
	public boolean existeTunel(String estacionId1, String estacionId2) {
		ArrayList<Tunel<T>> tuneles = this.estaciones.get(estacionId1);
		if(tuneles != null) {
			for (Tunel<T> tunel : tuneles) {			
				if(tunel.getEstacionDestino().equals(estacionId2)) {
					return true;
				}
			}
		}	
		return false;
	}
	
	//** 
	// * Complejidad: O(a) donde a es la cantidad de arcos salientes
	// * del verticeId1 (origen). La búsqueda de verticeId1 en el
	// * HashMap es O(1) y sólo se busca dentro de la lista de los
	// * arcos que salen de él, consultando cada uno de ellos si su
	// * vértice destino es igual a verticeId2, de ser verdadero 
	// * se retorna el arco.
	//**
	@Override
	public Tunel<T> obtenerTunel(String estacionId1, String estacionId2) {
		ArrayList<Tunel<T>> tuneles = this.estaciones.get(estacionId1);
		if (tuneles != null) {
			for (Tunel<T> tunel : tuneles) {
				if(tunel.getEstacionDestino().equals(estacionId2)) {
					return tunel;
				}
			}		
		}
		return null;
	}

	//** 
	// * Complejidad: O(1) porque se utiliza el método size() 
	// * sobre el hashmap
	//**
	@Override
	public int cantidadEstaciones() {
		return estaciones.size();
	}

	//** 
	// * Complejidad: O(1) donde 1 es la variable cantidadDeArcos la cual
	// * se va actualizando en agregarArco() para no tener que calcularlo
	// * cada vez que se solicite.
	//**
	@Override
	public int cantidadTuneles() {
		return this.cantidadDeTuneles;
	}
	
	//** 
	// * Complejidad: O(V) donde V es la cantidad de claves 
	// * que hay en el hashmap.
	//**
	@Override
	public Iterator<String> obtenerVertices() {
		// Devuelve un conjunto con las claves que hay en el HashMap
		 return this.estaciones.keySet().iterator(); 
	}
	
	//** 
	// * Complejidad: O(V) donde V son todos los vértices del grafo
	// * que se recorren para obtener las listas de arcos que contiene
	// * cada uno y retornarlas como iterador. 
	//**
	@Override
	public Iterator<String> obtenerAdyacentes(String estacionId) {
		ArrayList<Tunel<T>> tunelesAdyacentes = estaciones.get(estacionId);
		ArrayList<String> adyacentes = new ArrayList<String>();
		for (Tunel<T> tunel :tunelesAdyacentes) {
			if(tunel != null) {
				adyacentes.add(tunel.getEstacionDestino());
			}
		}
		return adyacentes.iterator();
	}

	//** 
	// * Complejidad: O(V) donde V son todos los vértices (estaciones)
	// * del grafo que se recorren para obtener las listas de arcos que 
	// * contiene cada uno y retornarlas como iterador. 
	//**
	@Override
	public Iterator<Tunel<T>> obtenerTuneles() {
		ArrayList<Tunel<T>> resultadoTuneles = new ArrayList<Tunel<T>>();
		for(ArrayList<Tunel<T>> tuneles: estaciones.values()) {
			resultadoTuneles.addAll(tuneles);
		}
		return resultadoTuneles.iterator();
	}

	//** 
	// * Complejidad: O(V) donde V son todos los vértices (estaciones) del grafo
	// * 
	@Override
	public Iterator<Tunel<T>> obtenerTuneles(String estacionId) {
		return estaciones.get(estacionId).iterator();
	}

}