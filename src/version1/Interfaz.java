package version1;

import java.util.Scanner;

public class Interfaz {
	private Dataset d;
	
	
	public Interfaz(Scanner inputStream, Scanner evaluar , int k_) {
		construirDataset(inputStream,evaluar);
		
	}
	
	
	private void construirDataset(Scanner inputStream, Scanner evaluar) {
		d = new Dataset(inputStream);
		d.datosEvaluar(evaluar);		// aqui se añaden los datos a evaluar al dataset
	}
}
