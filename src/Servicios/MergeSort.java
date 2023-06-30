package Servicios;

import Grafo.Tunel;

public class MergeSort {
	private int[] distancias;
	private Tunel[] tuneles;
	private int[] auxDistancias;
	private Tunel[] auxTuneles;
	private int size;
	
	public void sort(int[] distancias, Tunel[] tuneles) {
		this.distancias = distancias;
		this.tuneles = tuneles;
		this.size = distancias.length;
		this.auxDistancias = new int[size];		
		this.auxTuneles = new Tunel[size];
		mergesort(0, size - 1);
	}
	
	public void mergesort(int low, int high) {
		if (low < high) {
			int middle = (low + high) / 2;
			mergesort(low, middle);
			mergesort(middle + 1, high);
			merge(low, middle, high);
		}
	}
	
	public void merge(int low, int middle, int high) {
		for(int i = low; i <= high; i++) {
			auxDistancias[i] = distancias[i];
			auxTuneles[i] = tuneles[i];
		}
		
		int i = low;
		int j = middle + 1;
		int k = low;
		
		while(i <= middle && j <= high) {
			if(auxDistancias[i] <= auxDistancias[j]) {
				distancias[k] = auxDistancias[i];
				tuneles[k] = auxTuneles[i];
				i++;
			} else {
				distancias[k] = auxDistancias[j];
				tuneles[k] = auxTuneles[j];
				j++;
			}
			k++;
		}
		
		while (i <= middle) {
			distancias[k] = auxDistancias[i];
			tuneles[k] = auxTuneles[i];
			k++;
			i++;
		}
		
		while (j <= high) {
			distancias[k] = auxDistancias[j];
			tuneles[k] = auxTuneles[j];
			k++;
			j++;
		}				
	}
	
}
