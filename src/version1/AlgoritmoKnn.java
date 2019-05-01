package version1;

import java.util.Scanner;
import java.util.ArrayList;

public class AlgoritmoKnn {
	private Dataset d;
	private ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
	private int k;
	Vecino aux = new Vecino();
	
	public AlgoritmoKnn(Scanner inputStream, Scanner evaluar , int k_) {// Construyo el Dataset, implemento los datos a evaluar e inicializo k
		 d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
		k =k_;
		//normalizar();
	}
	
	public void normalizar() {	// este metodo normaliza el dataset || pasarlo al DATASET
		for (int k =0;k < d.numRows()-1;k++) {
			for (int i=0; i < d.numCols()-1;i++) {
				d.set(k, i, ( (d.get(k,i)-d.min(i))/(d.max(i)-d.min(i))));
			}
		}
	}
	
	static public Double distancia(Instancia a,Instancia b) { // al llamar este metodo se empleara el metodo normalizar 
		Double aux =0.0;
		for (int i=0; i < a.size()-1;i++) {
			aux += Math.pow((a.get(i) - b.get(i)) , 2);
		}
		//System.out.println(Math.sqrt(aux));		// esto es solo para comrpbar como va la distancia
		//return Math.sqrt(aux);
		return aux;
	}
	
	public void buscarVecino() {
		Double dis = 0.0;
		for(int i=0;i<k;i++) {
			for (int j =0;j < d.getRowsEntrenamiento()-1;j++) {
				//System.out.println(distancia(d.construirInstancia(d.getPosE()-1),d.construirInstancia(j)));
				dis =distancia(d.construirInstancia(d.getPosE()),d.construirInstancia(j)); 
				if (dis<aux.getD()) {
					//System.out.println(distancia(d.construirInstancia(d.getPosE()-1),d.construirInstancia(j)));
					aux.set(j,dis);
				}
			}
			System.out.println(d.getPosE());
			d.construirInstancia(d.getPosE()).print();
		}
			
	}
	
	public void comprobarVecino() {		// comprobar que el vecino no se haya ya seleccionado
		
	}
	
	public void algoritmo() {		// aqui habra un bucle que hara tantas iteraciones como k tengamos , aqui se llamara a buscarVecino por cada iteracion
			buscarVecino();
		
	}
	
	public void verVecino() {
		System.out.println(aux.toString());
	}
	
	public void print() {
		d.print();
	}
	
}
