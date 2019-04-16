package version1;
import java.util.ArrayList;

public class AtributoNumerico extends Atributo{
	private Double min ;
	private Double max ;
	private ArrayList<Double> columna;
	
	public AtributoNumerico() {
		min = new Double(100000.0);
		max = new Double(0.0);
		columna = new ArrayList<Double>();
		
	}
	///// GETTERS /////
	public Double getMin() {
		return min;
	}
	public Double getMax() {
		return max;
	}
	public Double get(int i) {
		return columna.get(i);
	}
	public int numRows() {		// metodo para devolver el numero de columnas que tiene la matriz de datos
		return columna.size();
	}
	
	///// SETTERS /////
	public void add(Double a) {		//Para añadir nuevos elementos al atributo
		columna.add(a);
	}
	public void set(int i,Double a) {	// Para asignar un valor nuevo a la posición dada
		columna.set(i, a);
	}
	
	///// OPERACIONES /////
	public void limites() {		//Para calcular el min y el max
		for (Double a:columna) {
			comprobarValor(a);
		}
	}
	
	private void comprobarValor(Double a) {		// metodo para comprobar si el valor es un minimo o maximo
		// si se cumple la primera no se cumplira la segunda por tanto , ¿esto se puede mejorar?
		if (a < min) min = a;
		if (a > max) max = a;
	}
	
	
	
}
