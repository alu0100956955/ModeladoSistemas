package version1;
import java.util.Scanner;
import java.util.ArrayList;



public class Dataset {
	private ArrayList<Instancia> datos = new ArrayList<Instancia>();
	private String[] primera ;					// atributo que se encarga de guardar la primera fila
	private ArrayList<AtributoNumerico> matriz = new ArrayList<AtributoNumerico>();
	private AtributoCualitativo clases = new AtributoCualitativo();
	private ArrayList<Double> min = new ArrayList<Double>();	// NO TIENE SENTIDO , esto debe ser parte de atributo
	private ArrayList<Double> max = new ArrayList<Double>();	// NO TIENE SENTIDO , esto debe ser parte de atributo
	
	
	/*
	public Dataset(Scanner inputStream ) {
		inputStream.useDelimiter(",");	
		Boolean first = new Boolean(true);	// valor para controlar si se ha recogido la primera fila
		while(inputStream.hasNext()) {
			if (first) {
				//primera = new String[] {inputStream.nextLine()};
				String data = inputStream.nextLine();
				primera = data.split(",");
				first = false;
			}	
			String data = inputStream.nextLine();		// cambiados por construirLinea()
			String[] values = data.split(",");
			Instancia f1 = new Instancia(values);	// cambiados por add()
			datos.add(f1);
		}
		crearMinyMax(primera.length -1);
		limites(datos);
	}*/
	
	public Dataset(Scanner inputStream) {
		inputStream.useDelimiter(",");	// esto se deberia de realizar fuera
		primera(inputStream);
		construirAtributos();	// aqui se debe declarar el array de atributos
		
		while(inputStream.hasNext()) {// realizar la construccion del array de instancia , darle tamaño al array de atributos , y rellenar el array de atributos
			add(construirLinea(inputStream));
		}
		crearMinyMax(primera.length -1);
		limites(datos);
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
	public void add(String[] values) {		// con la linea ya pasada a String[] declaramos el objeto Instancia , lo añadimos a la matriz datos y llamamos a lineaAtributo()
		Instancia f1 = new Instancia(values);
		datos.add(f1);
		lineaAtributo(values);
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
	
	
	public ArrayList<Double> getMin(){
		return min;
	}
	public ArrayList<Double> getMax(){
		return max;
	}
	
	public String toString() {		// pasa a String la matriz de datos
		String aux = new String();
		aux += datos.toString();
		return aux;
	}
	
	// -------------- pasar a atributo ------------------//
	
	private void crearMinyMax(int j) {		// esto debe estar en el atributo
		for(int i=0;i<j;i++) {
			min.add(1000.0);
			max.add(0.0);
		}
		
	}
	private void comprobarValor(Double d, int j) {		// en este metodo comprobare con el array de min y de max si se puede cambiar algun valor, EN EL ATRIBUTO
		if (d < min.get(j)) min.set(j,d);
		if (d > max.get(j)) max.set(j, d);
	}
	
	private void limites (ArrayList<Instancia> dat) {	// en este metodo calcularemos los limites min y max, ESTO DEBE ESTAR EN EL ATRIBUTO
		/*1º recorremos datos 
		 * 2º llamamos a comprobarValor para cada dato de datos ( o le pasamos datos directamente)*/
		for (int i =0; i < dat.size();i++) {
			//System.out.println(i);
			for (int j =0;j < dat.get(i).size();j++) {
				//System.out.print(datos.get(i).get(j));
				comprobarValor(dat.get(i).get(j),j);
			}
		}
		
	}
	
}
