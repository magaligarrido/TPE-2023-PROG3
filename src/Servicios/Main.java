package Servicios;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

import Grafo.GrafoNoDirigido;

public class Main {

	public static void main(String[] args) {
		GrafoNoDirigido<String> grafoEstaciones = new GrafoNoDirigido<>();
		menu(grafoEstaciones);	
		
		
		if(grafoEstaciones.cantidadEstaciones() > 0) {
			//Soluci�n del problema mediante greedy					
			buscarSolucionGreedy(grafoEstaciones);
			
			//Soluci�n del problema mediante backtracking
			buscarSolucionBacktracking(grafoEstaciones);	
		}
	}
	
	public static void buscarSolucionGreedy(GrafoNoDirigido<String> grafoEstaciones) {
		Timer timer = new Timer();
		timer.start();
		
		System.out.println("-------------");
		System.out.println("Greedy");
		System.out.println("Tiempo de ejecuci�n: " + timer.stop() + " milisegundos");
			
		System.out.println("las estaciones");
		System.out.println("los kms");
		System.out.println("X m�trica\n");
	}
	
	public static void buscarSolucionBacktracking(GrafoNoDirigido<String> grafoEstaciones) {
		Timer timer = new Timer();
		timer.start();
		//Backtracking backtracking = new Backtracking(grafoEstaciones);
		System.out.println("-------------");
		System.out.println("Backtracking");
		System.out.println("Tiempo de ejecuci�n: " + timer.stop() + " milisegundos");

		System.out.println("las estaciones");
		System.out.println("los kms");
		System.out.println("X m�trica\n");
		
	}
	
	public static void menu(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("Se debe cargar la informaci�n en la estructura.");
		System.out.println(
			"Elija una opci�n: \n" + 
			"1- Cargar informaci�n desde archivo definido en main. \n" +
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
			System.out.println("Opci�n incorrecta \n");
			menu(grafoEstaciones);
		}
		}
	}
	
	public static void solicitarArchivoPorConsola(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("------------- \n" + 
			"Elija un archivo para cargar la informaci�n: \n" +
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
			System.out.println("Opci�n incorrecta \n");
			menu(grafoEstaciones);
		}
		}
	
		lecturaInformacion(csvFile, grafoEstaciones);
	}
	
	public static void lecturaRutaPorConsola(GrafoNoDirigido<String> grafoEstaciones) {
		System.out.println("------------- \n" + 
		"Ingrese la ruta del archivo para cargar la informaci�n: ");
		String entrada = "";
		Scanner entradaScanner = new Scanner(System.in);
		entrada = entradaScanner.nextLine();
		lecturaInformacion(entrada, grafoEstaciones);
	}
	
	public static void lecturaArchivoDefinido(GrafoNoDirigido<String> grafoEstaciones) {		
		String csvFile = "./datasets/dataset1.txt"; 		
		lecturaInformacion(csvFile, grafoEstaciones);		
	}
	
	public static void lecturaInformacion(String csvFile, GrafoNoDirigido<String> grafoEstaciones) {
		long startTime = System.currentTimeMillis();	
		
		CSVReader csvReader = new CSVReader(csvFile);
		// Se llama a leer y cargar datos del archivo
		csvReader.read(grafoEstaciones);		
			
		long endTime = System.currentTimeMillis() - startTime;
		//System.out.println("Tiempo de ejecuci�n para carga de informaci�n: " + Long.toString(endTime));
	}

	

}
