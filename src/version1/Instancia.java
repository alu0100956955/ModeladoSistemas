package version1;

import java.util.ArrayList;

public class Instancia {
	private String clase;
	private ArrayList<Double> datos;
	
	public Instancia(String[] fila) {
		this.datos = new ArrayList<Double>();
		for (int i=0; i< fila.length-1  ;i++) {
			datos.add(Double.parseDouble(fila[i]));
		}
		clase = fila[fila.length-1];
	}
	
	public int size() {	// esto devulve el tamaño del vector de datos
		return datos.size();
	}
	public String clase() {
		return clase;
	}
	
	public Double get(int i) {
		return datos.get(i);
	}
	
	
}
