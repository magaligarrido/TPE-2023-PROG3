package Grafo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

import Servicios.MergeSort;

public class GrafoDirigido<T> implements Grafo<T> {
	// La key es nombre de una estación y el value la lista de tuneles salientes de la misma.
	protected HashMap<String, ArrayList<Tunel<T>>> estaciones;
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
		// Solo se puede borrar si existe
		if(this.estaciones.containsKey(estacionId)) {
									
			// Descuento los tuneles que se van a borrar junto a la estación
			cantidadDeTuneles = cantidadDeTuneles - this.estaciones.get(estacionId).size();				
			this.estaciones.remove(estacionId);
			
			// Por cada estación del grafo, elimino los tuneles que tengan 
			// como destino la estación eliminada
			// O(V * a)
			for(String estacion: this.estaciones.keySet()) { //O(V)
				
				ArrayList<Tunel<T>> tuneles = this.estaciones.get(estacion);	
				int i = 0;					
				
				while(i < tuneles.size()) {   //O(a)
					Tunel<T> tunel = tuneles.get(i);
					if(tunel.getEstacionDestino().equals(estacionId)) {
						tuneles.remove(i);
						cantidadDeTuneles--;					
					}
					i++;
				}
			} 
			
			System.out.println("Se elimino la estación: " + estacionId);

		} else {
			System.out.println("La estación " + estacionId + " no se puede borrar porque no existe en el grafo.");
		}
		
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
					 System.out.println("Se elimino el tunel: " + estacionId1 + "-" + estacionId2);
					 cantidadDeTuneles--; 
				 }
				        
			} 
			
		} else {
			System.out.println("El tunel " + estacionId1 + "-" + estacionId2 + " no se puede borrar porque no existe en el grafo.");

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
	// * Complejidad: O(V + a) donde V son todos los vértices (estaciones)
	// * del grafo que se recorren para obtener las listas de arcos que 
	// * contiene cada uno y retornarlas como iterador. Y a son los tuneles
	// * Es un método de gran complejidad pero no se utiliza mucho.	
	//**
	@Override
	public Iterator<Tunel<T>> obtenerTuneles() {
		ArrayList<Tunel<T>> resultadoTuneles = new ArrayList<Tunel<T>>();	
		for(ArrayList<Tunel<T>> tuneles: estaciones.values()) {
			for(Tunel<T> tunel : tuneles) {	
				if(!resultadoTuneles.contains(tunel)) {
					resultadoTuneles.add(tunel);
				}

			}			
		}
		return resultadoTuneles.iterator();
	}

	//** 
	// * Complejidad: O(a) donde a son todos los tuneles
	// * que parten de estacionId
	// * 
	@Override
	public Iterator<Tunel<T>> obtenerTuneles(String estacionId) {
		ArrayList<Tunel<T>> tuneles = estaciones.get(estacionId);	
		return estaciones.get(estacionId).iterator();
	}
	
	//** 
	// * Retorna un arreglo con todos los tuneles del grafo
	// * ordenados por el valor de distancia de menor a mayor.
	// * 
	public ArrayList<Tunel<T>> obtenerTunelesOrdenados(){
		ArrayList<Tunel<T>> salida = new ArrayList<>();
		int[] distancias;
		Tunel[] tuneles;
		int cantidad = this.cantidadTuneles();
		
		distancias = new int[cantidad];
		tuneles = new Tunel[cantidad];
	
		int i = 0;
		
		Iterator<Tunel<T>> it = this.obtenerTuneles();
		while(it.hasNext()) {
			Tunel<T> tunel = it.next();
			distancias[i] = tunel.getDistancia();
			tuneles[i] = tunel;
			i++;
		}
		
		MergeSort mergesort = new MergeSort();
		mergesort.sort(distancias, tuneles);
		
		for(Tunel<T> tunel : tuneles) {
			salida.add(tunel);
		}
				
		return salida;
	}
}