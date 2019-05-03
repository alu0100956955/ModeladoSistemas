package version1;

import java.util.Scanner;
import java.util.ArrayList;

public class AlgoritmoKnn {
	private Dataset d;			// conjunto de datos con el que va a trabajar el algoritmo
	//private ArrayList<Vecino> vecinos = new ArrayList<Vecino>();	// vecino mas cercanos
	//private int k;		// Variable para saber cuantos vecinos tenemos que hayar
	
	private ArrayList<Vecino> vecinos = new ArrayList<Vecino>();		// lista que contiene los vecinos del dato a evaluar
	private ArrayList<Integer> cantidadTipos ;	// con esto sabremos , de los vecinos , cuanta cantidad de cada tipo hay 
	
	
	
	/* Le paso al constructor del algoritmo tanto el fichero de entrenamiento como el de evaluar porque sin estos datos el algoritmo no hace nada , aunque tambien podriamos pasarle
	 *  un data set ya hecho ,hum
	 */
	public AlgoritmoKnn(Scanner inputStream, Scanner evaluar , int k_) {// Construyo el Dataset, implemento los datos a evaluar e inicializo k
		 d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
		//k =k_;
		asignarSize(k_);
		//  normalizar();    <----  ESTO TIENE QUE ESTAR EN Dataset
	}
	private void asignarSize(int k) {	// original al arraylist de vecinos solo se le puede asignar capacity pero lo que necesito es el size para eso esta este metodo
		for (int i =0 ; i<k ; i++) {
			Vecino aux = new Vecino();
			vecinos.add(aux);
		}
	}
	
	public void normalizar() {	// este metodo normaliza el dataset || pasarlo al DATASET
		for (int k =0;k < d.numRows();k++) {
			for (int i=0; i < d.numCols()-1;i++) {
				d.set(k, i, ( (d.get(k,i)-d.min(i))/(d.max(i)-d.min(i))));
			}
		}
	}
	
	static public Double distancia(Instancia a,Instancia b) { // Distancia euclidea
		Double aux =0.0;
		for (int i=0; i < a.size()-1;i++) {
			aux += Math.pow((a.get(i) - b.get(i)) , 2);
		}
		//System.out.println(Math.sqrt(aux));		// esto es solo para comrpbar como va la distancia
		return Math.sqrt(aux);
		//return aux;
	}
	
	public void buscarVecino() {		// debemos pasarle la pos del dato a buscarle vecino
		Double dis = 0.0;
		for(int i=0;i<vecinos.size();i++) {
			Vecino aux = new Vecino();
			for (int j =0;j < d.getRowsEntrenamiento()-1;j++) {
				//System.out.println(distancia(d.construirInstancia(d.getPosE()-1),d.construirInstancia(j)));
				dis =distancia(d.construirInstancia(d.getPosE()),d.construirInstancia(j)); 
				if (!comprobarVecino(j) &&  dis<aux.getD()   ) {
					//System.out.println(distancia(d.construirInstancia(d.getPosE()-1),d.construirInstancia(j)));
					aux.set(j,dis,d.construirInstancia(j).clase());
				}
			}
			vecinos.set(i, aux);
		}
		
		
		/*
		System.out.println(d.getPosE());
		d.construirInstancia(d.getPosE()).print();	*/
	}
	
	public Boolean comprobarVecino(int j) {		// comprobar que el vecino no se encuentre ya dentro , devuelve FALSE si NO esta dentro de vecino
		for (Vecino a : vecinos) {	
			if (a.getP() == j) {
				return true;
			}
		}
		return false;
	}
	
	/* En algoritmo habra un bucle que hara tantas iteraciones como n datos a evaluar 
	 *  dentro de cada bucle se buscara a los vecinos , se seleccionara cual es la clase de ese dato a evaluar Y SE LIMPIARA EL ARRARY DE VECINOS
	 *  lo de limpiar es importante ya que la implementacion se realiza mediante add , tambien podemos realizar la imprementacion mediante set 
	 *   para ello solo tenemos que construir el vector de vecinos del tamaño de K en el constructor , es mas podriamos ahorrarnos guardar la k
	 */
	
	public void algoritmo() {		
			buscarVecino();
		
	}
	
	
	public int getK() {
		return vecinos.size();
	}
	
	
	
	public String toStringV() {
		String aux = new String();
		for (Vecino a : vecinos) {
			aux += a.toString();
		}
		return aux;
	}
	
	public void printVecinos() {		// imprime por pantalla los vecinos , en orden de mas cercano a mas lejano
		System.out.println(toStringV());
	}
	public void printDataset() {		// imprime por pantalla el data set
		d.print();
	}
	
}
