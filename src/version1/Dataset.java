package version1;
import java.util.Scanner;
import java.util.ArrayList;



public class Dataset {
	private String[] primera ;					// atributo que se encarga de guardar la primera fila
	private ArrayList<AtributoNumerico> matriz = new ArrayList<AtributoNumerico>();
	private AtributoCualitativo clases = new AtributoCualitativo();

	
	
	
	
	public Dataset(Scanner inputStream) {
		inputStream.useDelimiter(",");	// esto se deberia de realizar fuera
		primera(inputStream);
		construirAtributos();	// aqui se debe declarar el array de atributos
		
		while(inputStream.hasNext()) {// realizar la construccion del array de instancia , darle tamaño al array de atributos , y rellenar el array de atributos
			lineaAtributo(construirLinea(inputStream));
		}
		limites();
	}
	
	private void construirAtributos() {		// en este metodo añadiremos a la matriz tantos atributos como haga falta
		for (int i=0;i< primera.length-1;i++) {
			AtributoNumerico a = new AtributoNumerico();
			matriz.add(a);
		}
	}
	private String[] construirLinea(Scanner inputStream) {		// construye una linea en formato String[]
		String data = inputStream.nextLine();
		String[] values = data.split(",");
		return values;
	}
	
	
	private void primera(Scanner inputStream) {	// extrae la primera linea que es donde se encuentran los textos
		String data = inputStream.nextLine();
		primera = data.split(",");
	}
	private void lineaAtributo(String[] values) {		// añade los distintos elementos de la linea a cada uno de los atributos
		for(int i=0;i<values.length-1;i++) {
			matriz.get(i).add(Double.parseDouble(values[i]));
		}
		clases.add(values[values.length-1]);
	}
	
	
	public void datosEntrenar() {	// en este metodo añadiremos a la matriz de datos los valores a evaluar
		/*1º crearemos un ArrayList<Instancia> <-- no renta mas meterlo en la de datos , pero entonces es mas dificil para buscarle clase?? (creoque no) , en caso de meterlo en datos cambiar limites
		 * 2º */
	}
	
	public void limites() {
		for(AtributoNumerico a:matriz) {
			a.limites();
		}
	}
	
	
	public ArrayList<Double> Min(){		// metodos muy iguales
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i=0; i< primera.length-1;i++) {
			aux.add(matriz.get(i).getMin());
		}
		return aux;
	}
	public ArrayList<Double> Max(){
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i=0; i< primera.length-1;i++) {
			aux.add(matriz.get(i).getMax());
		}
		return aux;
	}
	
	public Instancia construirInstancia(int i) {	// construye una instancia de la posicion indicada
		Instancia aux = new Instancia();
		for(AtributoNumerico a:matriz) {
			aux.add(a.get(i));
		}
		aux.setClase(clases.get(i));;
		return aux;
		
	}
	
	/*
	public String toString() {		// pasa a String la matriz de datos
		String aux = new String();
		aux += datos.toString();
		return aux;
	}*/
	
	
	
}
