package version1;

import java.util.Scanner;

public class Interfaz {
	private Dataset d;
	private AlgoritmoKnn al;
	
	// ---------CONSTRUCTOR----------//
	public Interfaz(Scanner inputStream, Scanner evaluar , int k_ ,int distancia) {
		construirDataset(inputStream,evaluar);
		al = new AlgoritmoKnn(d,k_,distancia);
	}
	
	// -------METODOS AUXILIARES CONSTRUCTOR -------/
	public void construirDataset(Scanner inputStream, Scanner evaluar) {
		d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
	}
	
	
	public void evaluar(int metodoVotacion, Double u) {		// metodo que invocara al metodo que realizara el proceso de evaluacion
		al.algoritmo(metodoVotacion,u);		
		printEvaluar();
	}
	
	
	//--------   --------//
	public void escogerDistancia(int i) {
		al.escogerDistancia(i);
	}
	
	
	// ----- METODOS DE VISUALIZACION POR PANTALLA-----//
	public void printDataset() {		// imprime por pantalla el data set
		d.print();
	}
	
	public void printEvaluar() {	// imprime por pantalla las instancias a evaluar
		d.printE();
	}
	
	public int getK() {
		return al.getK();
	}
	public void printK() {
		System.out.println(al.getK());
	}
	
	public int NumRows() {
		return d.numRows();
	}
	public int NumCols() {
		return d.numRows();
	}
	
	
	public Double max(int i) {
		return d.max(i);
	}
	public Double min(int i) {
		return d.min(i);
	}
	
	public Double media(int i) {
		return d.media(i);
	}
	public Double desviacionTipica(int i) {
		return d.desviacionTipica(i);
	}
	
}
