package version1;

import java.util.Scanner;

public class AlgoritmoKnn {

	
	public AlgoritmoKnn(Scanner inputStream) {
		// Construyo el Dataset, implemento los datos a evaluar , y miro los limites
		Dataset d = new Dataset(inputStream);
		d.datosEntrenar();
		//d.limites();
	}
	
	public void normalizar() {	// este metodo deberia devolver la instancia normalizada
		
	}
	public void distancia(Instancia a) { // al llamar este metodo se empleara el metodo normalizar 
		
	}
	public void buscarVecino() {		// busca vecino , pero como ignora los ya recogidos?, con comprobarVecino , si devuelve false ese vecino aun no ha sido seleccionado 
										// vale pero como busacamos el otro que sea mas cercano?, antes de mejorar el vecino comprobar asi si devuelve true significa que el que tenemos
										// es el mas cercano , bueno no este depende de como recorramos el dataset, ya esta si devuelve true ignoramos esa instancia y seguimos buscando
										// tendra un bucle que recorra el dataset (por instancias) realizando llamadas a instancia y comprobarVecino
		
	}
	
	public void comprobarVecino() {		// comprobar que el vecino no se haya ya seleccionado
		
	}
	
	public void algoritmo() {		// aqui habra un bucle que hara tantas iteraciones como k tengamos , aqui se llamara a buscarVecino por cada iteracion
		
	}
	
}
