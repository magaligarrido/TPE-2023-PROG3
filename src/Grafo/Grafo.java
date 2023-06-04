package Grafo;

import java.util.Iterator;

public interface Grafo<T> {

	// Agrega una estación
	public void agregarVertice(String estacionId);

	// Borra una estación
	public void borrarVertice(String estacionId);

	// Agrega un tunel con cierta distancia, que conecta la estacionId1 con la estacionId2
	public void agregarTunel(String estacionId1, String estacionId2, int distancia);

	// Borra el tunel que conecta la estacionId1 con la estacionId2
	public void borrarTunel(String estacionId1, String estacionId2);

	// Verifica si existe una estacion
	public boolean contieneVertice(String verticeId);  

	// Verifica si existe un tunel entre dos estaciones
	public boolean existeTunel(String estacionId1, String estacionId2);
	
	// Obtener el arco que conecta el verticeId1 con el verticeId2
	public Tunel<T> obtenerTunel(String estacionId1, String estacionId2);

	// Devuelve la cantidad total de estaciones (vértices) en el grafo
	public int cantidadEstaciones();

	// Devuelve la cantidad total de tuneles en el grafo
	public int cantidadTuneles();

	// Obtiene un iterador que me permite recorrer todos los vertices (estaciones) almacenados en el grafo 
	public Iterator<String> obtenerVertices();

	// Obtiene un iterador que me permite recorrer todos los vertices adyacentes a estacionId 
	public Iterator<String> obtenerAdyacentes(String estacionId);

	// Obtiene un iterador que me permite recorrer todos los arcos del grafo
	public Iterator<Tunel<T>> obtenerTuneles();
		
	// Obtiene un iterador que me permite recorrer todos los tuneles que parten desde estacionId
	public Iterator<Tunel<T>> obtenerTuneles(String estacionId);
}
