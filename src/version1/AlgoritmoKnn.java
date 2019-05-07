package version1;

import java.util.Scanner;
import java.util.ArrayList;

public class AlgoritmoKnn {
	private Dataset d;			// conjunto de datos con el que va a trabajar el algoritmo
	private ArrayList<Vecino> vecinos = new ArrayList<Vecino>();		// lista que contiene los vecinos del dato a evaluar
	private ArrayList<Integer> cantidadTipos = new ArrayList<Integer>();	// con esto sabremos , de los vecinos , cuanta cantidad de cada tipo hay 
	private int distancia;
	
	
	/* Le paso al constructor del algoritmo tanto el fichero de entrenamiento como el de evaluar porque sin estos datos el algoritmo no hace nada , aunque tambien podriamos pasarle
	 *  un data set ya hecho ,hum
	 */
	public AlgoritmoKnn(Scanner inputStream, Scanner evaluar , int k_ , int distanciaElegida) {// Construyo el Dataset, implemento los datos a evaluar e inicializo k
		 d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
		//k =k_;
		asignarSize(k_);
		cantidadTipos();
		distancia = distanciaElegida;		
	}
	public AlgoritmoKnn(Dataset d_ , int k_ , int distanciaElegida) {
		d =d_;
		asignarSize(k_);
		cantidadTipos();
		distancia = distanciaElegida;
	}
	private void asignarSize(int k) {	// original al arraylist de vecinos solo se le puede asignar capacity pero lo que necesito es el size para eso esta este metodo
		for (int i =0 ; i<k ; i++) {
			Vecino aux = new Vecino();
			vecinos.add(aux);
		}
	}
	
	private void cantidadTipos() {		// para inicilizar el array de tipos
		for (int i =0 ; i< d.cantidad() ;i++) cantidadTipos.add(0);
	}
	
	
	//---------DISTANCIA----------//

	public Double distancia(Instancia a, Instancia b) {
		Double sumatorio =0.0,max =0.0 ,aux =0.0;
		for (int i=0; i < a.size()-1;i++) {
			aux = calculo(a.get(i),b.get(i));
			if (aux > max ) max = aux;
			sumatorio += aux;
		}
		if(distancia ==0) return Math.sqrt(sumatorio);		// distancia euclidea
		if(distancia == 2) return max;						// distancia manhattan
		return sumatorio;									// distancia chebychef
	}
	
	public Double calculo(Double a,Double b) {
		if (distancia == 0) return Math.pow((a - b) , 2);
		return valorAbsoluto(a-b);
	}
	
	private Double valorAbsoluto(Double a) {
		if (a < 0) return a *-1;
		return a;
	}
	
	public void escogerDistancia(int i) {
		distancia =i;
	}
	
	//---------ALGORITMO PRINCIPAL---------------//
	// k es la pos en la matriz, de la instancia que queremos buscarle vecinos 
	public void buscarVecino(int k) {		// metodo que se encarga de buscar los k vecinos mas cercanos de la instancia que le indiquemos
		Double dis = 0.0;
		for(int i=0;i<vecinos.size();i++) {
			Vecino aux = new Vecino();
			for (int j =0;j < d.getRowsEntrenamiento()-1;j++) {
				dis =distancia(d.construirInstancia(k),d.construirInstancia(j)); 	// tengo que cambiar la pos de la instancia a evaluar 
				if (!comprobarVecino(j) &&  dis<aux.getD()   ) {
					aux.set(j,dis,d.construirInstancia(j).clase());
				}
			}
			vecinos.set(i, aux);
			sumarTipo(aux);// poner aqui el metodo que se encarga de sumar en cantidad tipos
		}
	}
	
	private Boolean comprobarVecino(int j) {		// comprobar que el vecino no se encuentre ya dentro , devuelve FALSE si NO esta dentro de vecino
		for (Vecino a : vecinos) {	
			if (a.getP() == j) {
				return true;
			}
		}
		return false;
	}
	
	private void sumarTipo(Vecino aux) {
		cantidadTipos.set(d.pos(aux.getC()), cantidadTipos.get(d.pos(aux.getC()))+1);
	}
	private void vaciarTipo() {		// metodo para limpiar la cantidad de tipos de una clase a otra
		for ( int i= 0;i<cantidadTipos.size();i++) {
			cantidadTipos.set(i, 0);
		}
	}
	
	/* En algoritmo habra un bucle que hara tantas iteraciones como n datos a evaluar 
	 *  dentro de cada bucle se buscara a los vecinos , se seleccionara cual es la clase de ese dato a evaluar Y SE LIMPIARA EL ARRARY DE VECINOS
	 *  lo de limpiar es importante ya que la implementacion se realiza mediante add , tambien podemos realizar la imprementacion mediante set 
	 *   para ello solo tenemos que construir el vector de vecinos del tamaño de K en el constructor , es mas podriamos ahorrarnos guardar la k
	 */
	
	public void algoritmo(int metodoVotacion , Double u) {			//Debemos pasarle el metodo de votacion y la u
		int k = d.getPosE();
		for (int i =0;i<d.datosAE();i++) {
			buscarVecino(k);		// pasarle a vecino la pos del primer valor a evaluar 
			d.setClase(k, d.clase(seleccionarClase(metodoVotacion,u)));
			k++;
			vaciarTipo();
		}
	}
	
	/// ----- METODOS PARA EVALUAR -------//
	public int seleccionarClase(int k, Double u) {		// aqui se deberia poder elegir que tipo de votacion se quiere
		if (k == 1) return mayoriaAbsoluta();
		if (k == 2) return votacion(u);
		return mayoriaSimple();
		
	}
	
	public int mayoriaSimple() {
		return votacion(0.0);
	}
	public int mayoriaAbsoluta() {
		return votacion(0.5);
	}
	public int votacion(Double u) {
		int aux =0;
		int pos =0;
		for(int i =0;i< cantidadTipos.size();i++) {
			if( cantidadTipos.get(i) > getK()*u) {
				if (cantidadTipos.get(i) > aux) {
					aux = cantidadTipos.get(i);
					pos =i;
				}
			}
		}
		return pos;
	}
	
	public int getK() {
		return vecinos.size();
	}
	
	
	// los 4 siguientes metodos son inutiles fuera de esta clase ya que solo mostraran datos de la ultima instancia evaluada
	public String toStringV() {		
		String aux = new String();
		for (Vecino a : vecinos) {
			aux += a.toString();
		}
		return aux;
	}
	public void printVecinos() {		// imprime por pantalla los vecinos , en orden de mas cercano a mas lejano , deberia ser privado
		System.out.println(toStringV());
	}
	
	public String toStringT() {		// metodo inutil 
		String aux = new String();
		aux += "[ ";
		for (Integer a : cantidadTipos) aux += "," + a;
		aux += " ]";
		return aux;
	}
	public void printT() {		// metodo inutil
		System.out.println(toStringT());
		d.tiposPrint();
	}
	
	
	public void printDataset() {		// imprime por pantalla el data set
		d.print();
	}
	
	public void printEvaluar() {
		d.printE();
	}
}
