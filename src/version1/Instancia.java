package version1;

import java.util.ArrayList;

public class Instancia {
	private String clase;
	private ArrayList<Double> datos;
	
	public Instancia() {
		this.datos = new ArrayList<Double>();
		clase = new String();
	}
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
	public void add(Double e) {
		datos.add(e);
	}
	public void set(int i , Double a){
		datos.set(i,a);
	}
	public void setClase(String a) {
		clase =a;
	}
	public String toString() {
		String aux = new String();
		for (Double a:datos) {
			aux += a+" ";
		}
		aux += clase;
		return aux;
	}
	
	public Double distancia() {
		Double aux =0.0;
		for(int i =0; i< size() ; i++) {
			aux += get(i);
		}
		System.out.println(aux);
		return aux;
	}
	public void print() {
		System.out.print("["+toString()+"]\n");
	}
}
