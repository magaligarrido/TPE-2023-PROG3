package Servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Grafo.GrafoDirigido;

public class Main {

	public static void main(String[] args) {
		GrafoDirigido<String> grafoEstaciones = new GrafoDirigido<>();
		menu(grafoEstaciones);			
		
		if(grafoEstaciones.cantidadEstaciones() > 0) {
			//Solución del problema mediante greedy					
			buscarSolucionGreedy(grafoEstaciones);
			
			//Solución del problema mediante backtracking
			buscarSolucionBacktracking(grafoEstaciones);	
		}
	}
	
	public static void buscarSolucionGreedy(GrafoDirigido<String> grafoEstaciones) {
		System.out.println("-------------");
		System.out.println("Greedy");
		
		System.out.println("las estaciones");
		System.out.println("los kms");
		System.out.println("X métrica\n");
	}
	
	public static void buscarSolucionBacktracking(GrafoDirigido<String> grafoEstaciones) {
		System.out.println("-------------");
		System.out.println("Backtracking");
		
		System.out.println("las estaciones");
		System.out.println("los kms");
		System.out.println("X métrica\n");
	}
	
	public static void menu(GrafoDirigido<String> grafoEstaciones) {
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
			menu(grafoEstaciones);
		}
		}
	}
	
	public static void solicitarArchivoPorConsola(GrafoDirigido<String> grafoEstaciones) {
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
			menu(grafoEstaciones);
		}
		}
	
		lecturaInformacion(csvFile, grafoEstaciones);
	}
	
	public static void lecturaRutaPorConsola(GrafoDirigido<String> grafoEstaciones) {
		System.out.println("------------- \n" + 
		"Ingrese la ruta del archivo para cargar la información: ");
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		lecturaInformacion(entrada, grafoEstaciones);
	}
	
	public static void lecturaArchivoDefinido(GrafoDirigido<String> grafoEstaciones) {		
		String csvFile = "./datasets/dataset1.txt"; 		
		lecturaInformacion(csvFile, grafoEstaciones);		
	}
	
	public static void lecturaInformacion(String csvFile, GrafoDirigido<String> grafoEstaciones) {
		long startTime = System.currentTimeMillis();	
		
		CSVReader csvReader = new CSVReader(csvFile);
		// Se llama a leer y cargar datos del archivo
		csvReader.read(grafoEstaciones);		
			
		long endTime = System.currentTimeMillis() - startTime;
		//System.out.println("Tiempo de ejecución para carga de información: " + Long.toString(endTime));
	}

	

}
