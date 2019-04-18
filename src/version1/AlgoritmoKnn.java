package version1;

import java.util.Scanner;
import java.util.ArrayList;

public class AlgoritmoKnn {
	private ArrayList<Vecino> vecinos = new ArrayList<Vecino>();
	private int k;
	
	public AlgoritmoKnn(Scanner inputStream, Scanner evaluar , int k) {// Construyo el Dataset, implemento los datos a evaluar e inicializo k
		Dataset d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
		k =0;
	}
	
	public Instancia normalizar(Instancia a) {	// este metodo deberia devolver la instancia normalizada
		
		return a;
	}
	public void distancia(Instancia a) { // al llamar este metodo se empleara el metodo normalizar 
		
	}
	
	public void buscarVecino() {		
		
	}
	
	public void comprobarVecino() {		// comprobar que el vecino no se haya ya seleccionado
		
	}
	
	public void algoritmo(int k) {		// aqui habra un bucle que hara tantas iteraciones como k tengamos , aqui se llamara a buscarVecino por cada iteracion
		for(int i=0;i<k;i++) {			// no deberia esta este bucle en buscar vecino y que el algoritmo se encarge de hacer las otras comprobaciones?
			buscarVecino();
		}
	}
	
}
