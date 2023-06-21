package Grafo;

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

}