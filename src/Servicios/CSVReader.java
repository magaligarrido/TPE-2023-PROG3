package Servicios;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import Grafo.GrafoDirigido;
import Grafo.GrafoNoDirigido;

public class CSVReader {

	private String path;
	
	public CSVReader(String path) {
		this.path = path;
	}
	
	// Para los grafos no dirigidos
	public void read(GrafoNoDirigido<String> grafoEstaciones) {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent();
		
		for (String[] line: lines) {		
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String origen = line[0].trim(); // estacionOrigen
			String destino = line[1].trim(); // estacionDestino
			Integer etiqueta = Integer.parseInt(line[2].trim()); // metros de tunel		
	
			// Se carga el grafo con los datos obtenidos
			grafoEstaciones.agregarEstacion(origen);	
			grafoEstaciones.agregarEstacion(destino);
			grafoEstaciones.agregarTunel(origen, destino, etiqueta);	
		
		}
		
	}
	
	// Para los grafos dirigidos
	public void read(GrafoDirigido<String> grafoEstaciones) {
		
		// Obtengo una lista con las lineas del archivo
		// lines.get(0) tiene la primer linea del archivo
		// lines.get(1) tiene la segunda linea del archivo... y así
		ArrayList<String[]> lines = this.readContent();
		
		for (String[] line: lines) {		
			// Cada linea es un arreglo de Strings, donde cada posicion guarda un elemento
			String origen = line[0].trim(); // estacionOrigen
			String destino = line[1].trim(); // estacionDestino
			Integer etiqueta = Integer.parseInt(line[2].trim()); // metros de tunel		
	
			// Se carga el grafo con los datos obtenidos
			grafoEstaciones.agregarEstacion(origen);	
			grafoEstaciones.agregarEstacion(destino);
			grafoEstaciones.agregarTunel(origen, destino, etiqueta);	
		
		}
		
	}

	private ArrayList<String[]> readContent() {
		ArrayList<String[]> lines = new ArrayList<String[]>();

		File file = new File(this.path);		
		if (file.exists()) {
			FileReader fileReader = null;
			BufferedReader bufferedReader = null;
			try {
				fileReader = new FileReader(file);
				bufferedReader = new BufferedReader(fileReader);
				String line = null;
				while ((line = bufferedReader.readLine()) != null) {
					line = line.trim();
					lines.add(line.split(";"));
				}
			} catch (Exception e) {
				e.printStackTrace();			
				if (bufferedReader != null)
					try {
						bufferedReader.close();
					} catch (IOException e1) {
						e1.printStackTrace();
					}
			}
			
		} else {
			System.out.println("ERROR: La ruta no existe.");
		}
		
		return lines;
	}

}
