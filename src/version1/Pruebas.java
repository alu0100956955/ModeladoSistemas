package version1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pruebas {

	
	public static void main(String[] args) {
		//prueba();
		dataset();
		//pruebaDistancia();
		
	}

	public static void pruebaDistancia() {
		String[] s1 = new String[]{"5.1","3.5","1.4","0.2"};
		String[] s2 = new String[]{"4.9","3","1.4","0.2"};		
		Instancia i1 = new Instancia(s1);
		Instancia i2 = new Instancia(s2);
		System.out.println(AlgoritmoKnn.distancia(i1, i2));
	}
	
	
	public static void dataset() {
		String nombreFichero2 = "evaluar_flor_3.csv";
		File file2 = new File(nombreFichero2);
		String nombreFichero = "iris.csv";
		File file = new File(nombreFichero);
		
		try {
			Scanner inputStream = new Scanner(file);
			/*
			Dataset d = new Dataset(inputStream);
			
			//System.out.println("Ahora con atributo");
			d.cabezera();
			System.out.println(d.Min().toString());
			System.out.println(d.Max().toString());
			
			d.construirInstancia(2).print();
			*/
			System.out.println("ahora el algoritmo");
			Scanner inputStream2 = new Scanner(file2);
			AlgoritmoKnn al = new AlgoritmoKnn(inputStream,inputStream2,2);
			//al.printDataset();
			al.algoritmo();
			//al.printVecinos();
			//al.printT();
			al.printEvaluar();
			inputStream.close();
			inputStream2.close();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
	}

	
	public static void prueba() {
		String nombreFichero = "iris.csv";
		File file = new File(nombreFichero);
		
		ArrayList<String> iden = new ArrayList<String>();	// array que contendra la prima fila con los nombre de las columnas
		
		try {
			Scanner inputStream = new Scanner(file);
			inputStream.useDelimiter(",");
			Boolean primera = new Boolean(true);
			String aux = new String();
			
			/*
			String[] someValue= "52.23";
			Double doubleVal = Double.parseDouble(someValue);
			System.out.println(doubleVal);
			*/
			
			while(inputStream.hasNext()) {
				if (primera) {/*
					while(!(inputStream.hasNextDouble())){
						String aux = inputStream.next();
						iden.add(aux);
						System.out.println(aux);
					}*/
					
					aux = inputStream.nextLine();
					System.out.println(aux);
				}
				primera = false;
				//System.out.println(iden.toString());
				String data = inputStream.nextLine();
				String[] values = data.split(",");
				//System.out.println(values.length);
				
				Fila f1 = new Fila(values);
				System.out.print(f1.datos.toString());
				System.out.print(f1.clase);
				//System.out.println(data);	// esto es para toda la linea
				//String[] values = data.split(",");
				//System.out.println(values[0]);
				
				/*
				if (inputStream.hasNextDouble()) {
				Double data = inputStream.nextDouble();
				System.out.println(data);}
				else {
					String data = inputStream.next();
				}*/
			}
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}

		// Prueba del tipo de dato FILA
		/*
		ArrayList<Double> prueba = new ArrayList<Double>();
		Double a = new Double(8.9);
		prueba.add(a);
		Fila p1 = new Fila();
		p1.clase = "prueba";
		p1.datos = prueba;
		System.out.println(p1.clase);
		System.out.println(p1.datos.toString());

		 */
	}
	
	
}

class Fila {
	public String clase;
	public ArrayList<Double> datos;
	
	public Fila(String[] fila) {
		this.datos = new ArrayList<Double>();
		for (int i=0; i< fila.length-1  ;i++) {
			datos.add(Double.parseDouble(fila[i]));
		}
		clase = fila[fila.length-1];
	}
}