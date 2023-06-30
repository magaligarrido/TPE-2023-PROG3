package Servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;
import Grafo.Tunel;

public class Main {

	public static void main(String[] args) {
		GrafoDirigido<String> grafo = new GrafoDirigido<>();
		GrafoNoDirigido<String> grafoEstaciones = new GrafoNoDirigido<>();
		menu(grafo, grafoEstaciones);	
		
		if(grafoEstaciones.cantidadEstaciones() > 0) {
			//Solución del problema mediante greedy					
			buscarSolucionGreedy(grafoEstaciones);
			
			//Solución del problema mediante backtracking
			buscarSolucionBacktracking(grafoEstaciones);	
		}
	}
	
	public static void buscarSolucionGreedy(GrafoNoDirigido<String> grafoEstaciones) {
		Timer timer = new Timer();
		timer.start();
		
		Greedy greedy = new Greedy();
		Solucion sol = greedy.greedy(grafoEstaciones);
		System.out.println("-------------");
		System.out.println("Greedy");
		
		if(sol != null) {
			System.out.println(sol.getCamino());
			System.out.println(sol.getDistancia() + " kms");
			System.out.println("Tiempo de ejecución: " + timer.stop() + " milisegundos");
		} else {
			System.out.println("No se pudo encontrar una solución");
		}
	}
	
	public static void buscarSolucionBacktracking(GrafoNoDirigido<String> grafoEstaciones) {
		Timer timer = new Timer();
		timer.start();
		Backtracking backtracking = new Backtracking();
		Solucion sol = backtracking.back(grafoEstaciones);
		
		System.out.println("-------------");
		System.out.println("Backtracking");
	
		if(sol != null) {
			System.out.println(sol.getCamino());
			System.out.println(sol.getDistancia() + " kms");
			System.out.println("Tiempo de ejecución: " + timer.stop() + " milisegundos");
		} else {
			System.out.println("No se pudo encontrar una solución");
		}	
	}
	
	public static void menu(GrafoDirigido<String> grafo, GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("TP ESPECIAL PROGRAMACION 3");
		System.out.println(
			"Elija una opción: \n" + 
			"1- Primera parte del trabajo --> Implementación de grafo y servicios BFS y DFS \n" +
			"2- Segunda parte del trabajo --> Implementación de algoritmos greedy y backtracking \n"
		);
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		int indice = Integer.parseInt(entrada);
		
		switch(indice) {
		case 1: {			
			menuParteUno(grafo);
			break;
		}
		case 2: {
			menuParteDos(grafoEstaciones);
			break;
		}
		default:{
			System.out.println("Opción incorrecta \n");
			menu(grafo, grafoEstaciones);
		}
		}
	}
	
	public static void menuParteUno(GrafoDirigido<String> grafo) {
		String csvFile = "./datasets/dataset1.txt"; 		
		lecturaInformacion(csvFile, grafo);			
		
		System.out.println("Cantidad de vertices: " + grafo.cantidadEstaciones());
		System.out.println("Cantidad de arcos: " + grafo.cantidadTuneles() + "\n");
		
		// Prueba del borrado de una estación y sus arcos
		//grafo.borrarEstacion("E2");	
		//System.out.println("Cantidad de vertices: " + grafo.cantidadEstaciones());
		//System.out.println("Cantidad de arcos: " + grafo.cantidadTuneles());	
	
		// Prueba del borrado de un tunel
		//grafo.borrarTunel("E1", "E3");
		//System.out.println("Cantidad de arcos: " + grafo.cantidadTuneles());
		
		
		/// **** Recorridos DFS  **** ///		
		Timer timer = new Timer();
		timer.start();
		
		ServicioDFS servicioDFS = new ServicioDFS(grafo);
		List<String> recorridoDFS = servicioDFS.dfsForest();
		
		System.out.println("-----------------");
		System.out.println("Recorrido por DFS");
		imprimirRecorrido(recorridoDFS);
		System.out.println("Tiempo de ejecución: " + timer.stop() + " milisegundos");

		/// **** Recorridos BFS  **** ///	
		timer.start();
		
		ServicioBFS servicioBFS = new ServicioBFS(grafo);
		List<String> recorridoBFS = servicioBFS.bfsForest();
		
		System.out.println("-----------------");
		System.out.println("Recorrido por BFS");
		imprimirRecorrido(recorridoBFS);
		System.out.println("Tiempo de ejecución: " + timer.stop() + " milisegundos");
	
		String origen = "E1";
		String destino = "E4";
		int limite = 2;
		
		ServicioCaminos servicioCaminos = new ServicioCaminos(grafo, origen, destino, limite);
		List<List<String>> caminos = servicioCaminos.caminos();
		System.out.println("-----------------");
		System.out.println("Caminos");
		for(List<String> camino: caminos) {
			imprimirRecorrido(camino);
		}
		
	}
	
	public static void menuParteDos(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("Se debe cargar la información en la estructura.");
		
		System.out.println(
			"Elija una opción: \n" + 
			"1- Cargar información desde archivo definido en main. \n" +
			"2- Ingresar ruta de archivo por consola. \n" + 
			"3- Pasar mediante argumentos al programa ?"
		);
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		int indice = Integer.parseInt(entrada);
		
		switch(indice) {
		case 1: {			
			lecturaArchivoDefinido(grafoEstaciones);
			break;
		}
		case 2: {
			lecturaRutaPorConsola(grafoEstaciones);
			break;
		}
		case 3: {
			solicitarArchivoPorConsola(grafoEstaciones);
			break;
		}
		default:{
			System.out.println("Opción incorrecta \n");
			menuParteDos(grafoEstaciones);
		}
		}
	}
	
	public static void solicitarArchivoPorConsola(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("------------- \n" + 
			"Elija un archivo para cargar la información: \n" +
			"1 --> dataset1 \n" +
			"2 --> dataset2 \n" +
			"3 --> dataset3");
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		int indice = Integer.parseInt(entrada);
		
		String csvFile = "";
		
		switch(indice) {
		case 1: {			
			csvFile = "./datasets/dataset1.txt"; 		
			break;
		}
		case 2: {
			csvFile = "./datasets/dataset2.txt"; 		
			break;
		}
		case 3: {
			csvFile = "./datasets/dataset3.txt"; 		
			break;
		}
		default:{
			System.out.println("Opción incorrecta \n");
			menuParteDos(grafoEstaciones);
		}
		}
	
		lecturaInformacion(csvFile, grafoEstaciones);
	}
	
	public static void lecturaRutaPorConsola(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("------------- \n" + 
		"Ingrese la ruta del archivo para cargar la información: (por ej. ./datasets/dataset1.txt)");
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		lecturaInformacion(entrada, grafoEstaciones);
	}
	
	public static void lecturaArchivoDefinido(GrafoNoDirigido<String> grafoEstaciones) {		
		String csvFile = "./datasets/dataset1.txt"; 		
		lecturaInformacion(csvFile, grafoEstaciones);		
	}
	
	public static void lecturaInformacion(String csvFile, GrafoNoDirigido<String> grafo) {
		long startTime = System.currentTimeMillis();	
		
		CSVReader csvReader = new CSVReader(csvFile);
		// Se llama a leer y cargar datos del archivo
		csvReader.read(grafo);		
			
		long endTime = System.currentTimeMillis() - startTime;
	}
	
	public static void lecturaInformacion(String csvFile, GrafoDirigido<String> grafo) {
		long startTime = System.currentTimeMillis();	
		
		CSVReader csvReader = new CSVReader(csvFile);
		// Se llama a leer y cargar datos del archivo
		csvReader.read(grafo);		
			
		long endTime = System.currentTimeMillis() - startTime;
		
	}
	
	private static void imprimirRecorrido(List<String> recorrido) {
		String salida = "";
		for(String v: recorrido) {
			if(salida.contentEquals("")) {
				salida += v;
			} else {
				salida += " -> " + v;
			}
		}
		System.out.println(salida);
	}

	

}
