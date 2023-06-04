package Servicios;

import java.io.BufferedReader;
import java.util.Scanner;

import Grafo.Estacion;
import Grafo.GrafoDirigido;

public class Main {

	public static void main(String[] args) {
		//Nodo raiz = null;
		GrafoDirigido<String> grafoEstaciones = new GrafoDirigido<>();
		menu();
	}
	
	public static void menu() {
		System.out.println("Se debe cargar la información en la estructura.");
		System.out.println(
			"Elija una opción: \n" + 
			"1- Cargar información desde archivo definido en main. \n" +
			"2- Ingresar información por consola. \n" + 
			"3- Pasar mediante argumentos al programa ? \n"
		);
		String entrada = "";
		Scanner escanerEntrada = new Scanner(System.in);
		entrada = escanerEntrada.nextLine();
		int indice = Integer.parseInt(entrada);
		
		switch(indice) {
		case 1: {
			lecturaInformacion()
			System.out.println("elegi la 1");
			break;
		}
		case 2: {
			System.out.println("elegi la 2");
			break;
		}
		case 3: {
			System.out.println("elegi la 3");
			break;
		}
		default:{
			System.out.println("Opción incorrecta " + indice);
			menu();
		}
		}
	}
	
	public static void lecturaInformacion() {
		//String csvFile = './datasets/dataset1.csv'; 
		String line = "";
		String split = ";";
		
		try(BufferedReader br = new BufferedReader(newFileReader(csvFile))){
			line = br.readLine();
			line.split(split);
			
			while((line = br.readLine() != null)) {
				String[] elementos = line.split(split);
				if(elementos.length > 0) {
					//agregar a estructura
				}
			}
		}
	}
	

}
