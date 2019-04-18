package version1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

public class Pruebas {

	
	public static void main(String[] args) {
		//prueba();
		dataset();
		
	}

	public static void dataset() {
		String nombreFichero = "iris.csv";
		File file = new File(nombreFichero);
		try {
			Scanner inputStream = new Scanner(file);
			Dataset d = new Dataset(inputStream);
			
			//System.out.println("Ahora con atributo");
			d.cabezera();
			System.out.println(d.Min().toString());
			System.out.println(d.Max().toString());
			
			d.construirInstancia(2).print();
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