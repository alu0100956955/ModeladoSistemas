package version1;
import java.util.Scanner;
import java.util.ArrayList;



public class Dataset {
	private String[] primera ;					// atributo que se encarga de guardar la primera fila
	private ArrayList<AtributoNumerico> matriz = new ArrayList<AtributoNumerico>();
	private AtributoCualitativo clases = new AtributoCualitativo();
	private int posE = 0;
	
	public Dataset(Scanner inputStream) {
		primera(inputStream);
		construirAtributos();	// aqui se debe declarar el array de atributos
		añadirMatriz(inputStream);
	}
	
	private void primera(Scanner inputStream) {	// extrae la primera linea que es donde se encuentran los textos
		String data = inputStream.nextLine();
		primera = data.split(",");
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
	
	
	private void lineaAtributo(String[] values) {		// añade los distintos elementos de la linea a cada uno de los atributos
		for(int i=0;i<values.length-1;i++) {
			matriz.get(i).add(Double.parseDouble(values[i]));
		}
		clases.add(values[values.length-1]);
	}
	private void añadirMatriz(Scanner inputStream) {		// Este metodo añade a la matriz nuevas filas
		while(inputStream.hasNext()) {// realizar la construccion del array de instancia , darle tamaño al array de atributos , y rellenar el array de atributos
			lineaAtributo(construirLinea(inputStream));
		}
		limites();
	}
	
	public void datosEvaluar(Scanner inputStream) {	// en este metodo añadiremos a la matriz de datos los valores a evaluar
		
		posE = numRows();
		añadirMatriz(inputStream);	
	}
	
	public void limites() {		// Este metodo se encarga de generar el min y el max en cada atributo de la matriz
		for(AtributoNumerico a:matriz) {
			a.limites();
		}
	}
	
	public int numRows() {		// Este metodo devuelce el numero de filas
		return matriz.get(0).numRows();
	}
	public int numCols() {		// Este metodo devuelve el numero de columnas
		return primera.length;
	}
	public int getRowsEntrenamiento() {	// este metodo nos indica cuales son la cantiad de filas que sirven de entrenamiento
		return (getPosE() -numRows())*(-1);
	}
	public int getPosE() {		// este metodo devuelve la fila desde la cual los valores hay que evaluarlos
		return posE;
	}
	
	
	public ArrayList<Double> Min(){		// metodos muy iguales , hacer el metodo en un solo metodo y con un enumerable saber si hacer una o otra , aunque ocn bool es mas facil
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
	
	
	public void cabezera() {
		String aux = new String();
		for (String a:primera) {
			aux += a + "|";
		}
		//aux += "\n";
		System.out.println(aux);
		
	}
	/*
	public String toString() {		// pasa a String la matriz de datos
		String aux = new String();
		aux += datos.toString();
		return aux;
	}*/
	
	
	
}
