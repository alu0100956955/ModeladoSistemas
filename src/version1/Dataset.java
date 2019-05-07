package version1;
import java.util.Scanner;
import java.util.ArrayList;



public class Dataset {
	private String[] primera ;					// atributo que se encarga de guardar la primera fila
	private ArrayList<AtributoNumerico> matriz = new ArrayList<AtributoNumerico>();
	private AtributoCualitativo clases = new AtributoCualitativo();
	private int posE = 0;
	private Tipo tipos = new Tipo();
	
	// ---------CONSTRUCTORES-------//
	public Dataset(Scanner inputStream) {	// construir solo los datos de entrenamiento
		primera(inputStream);
		construirAtributos(primera.length-1);	// aqui se debe declarar el array de atributos
		añadirMatriz(inputStream);
	}
	
	public Dataset(Scanner inputStream , Scanner evaluar) {	// construye tanto los datos de entrenamiento como los de evaluar
		this(inputStream);
		datosEvaluar(evaluar);
	}
	
	//------ METODOS PARA EL CONSTRUTOR------//
	private void primera(Scanner inputStream) {	// extrae la primera linea que es donde se encuentran los textos
		String data = inputStream.nextLine();
		primera = data.split(",");
	}
	private void construirAtributos(int length) {		// en este metodo añadiremos a la matriz tantos atributos como haga falta
		for (int i=0;i< length ;i++) {
			AtributoNumerico a = new AtributoNumerico();
			matriz.add(a);
		}
	}
	private void añadirMatriz(Scanner inputStream) {		// Este metodo añade a la matriz nuevas filas
		 do{// realizar la construccion del array de instancia , darle tamaño al array de atributos , y rellenar el array de atributos
			lineaAtributo(construirLinea(inputStream));
		}while(inputStream.hasNext());
		limites();
	}
	
	private String[] construirLinea(Scanner inputStream) {		// construye una linea en formato String[]
		String data = inputStream.nextLine();
		String[] values = data.split(",");
		//System.out.println(values.toString());
		return values;
	}
	
	
	private void lineaAtributo(String[] values ) {		// añade los distintos elementos de la linea a cada uno de los atributos
		//int b = ( posE == 0)?0:posE;
		for(int i=0;i<values.length-1;i++) {
			matriz.get(i).add(Double.parseDouble(values[i]));
		}
		
		clases.add(values[values.length-1]);
		tipos.add(values[values.length-1]);
	}
	
	// --------- AÑADIR LINEAS A EVALUAR ---------//
	public void datosEvaluar(Scanner inputStream2) {	// en este metodo añadiremos a la matriz de datos los valores a evaluar
		posE = numRows();
		añadirMatriz(inputStream2);	
	}
	
	
	//---------- PREPROCESADO DE DATOS ------------//
	public void preprocesadoDatos(Boolean normalizar) {	// este metodo normaliza el dataset || estandariza
		for (int k =0;k < numRows();k++) {
			for (int i=0; i < numCols()-1;i++) {
				if (normalizar) set(k, i, ( (get(k,i)-min(i))/(max(i)-min(i))));
				else set(k , i ,(get(k,i) - matriz.get(k).media()/matriz.get(k).desviacionTipica() ));
				
			}
		}
	}
	
	
	
	//-------- METODOS PARA CALCULAR MIN & MAX ----------//
	public void limites() {		// Este metodo se encarga de generar el min y el max en cada atributo de la matriz
		for(AtributoNumerico a:matriz) {
			a.limites();
		}
	}
		
		
	// ---------- CONSTRUIR FILA --------------- //
	public Instancia construirInstancia(int i) {	// construye una instancia de la posicion indicada
		Instancia aux = new Instancia();
		for(AtributoNumerico a:matriz) {
			aux.add(a.get(i));
		}
		aux.setClase(clases.get(i));
		return aux;	
	}
	
	//-------- GETTERS -----------//
	public int numRows() {		// Este metodo devuelce el numero de filas
		return matriz.get(0).numRows();
	}
	public int numCols() {		// Este metodo devuelve el numero de columnas
		return primera.length;
	}
	public int getRowsEntrenamiento() {	// Este metodo nos indica cuales son la cantiad de filas que sirven de entrenamiento
		return (datosAE() -numRows())*(-1);
	}
	public int getPosE() {		// Este metodo devuelve la fila desde la cual los valores hay que evaluarlos
		return posE;
	}
	public int datosAE() {		// Este metodo nos devuelve la cantiad de datos a evaluar
		return (numRows()-getPosE());
	}
	public Double get(int i , int j) {
		return matriz.get(j).get(i);
	}
	
	
	//--------- GET MIN & MAX --------------//
	public Double max(int i) {
		return matriz.get(i).getMax();
	}
	public Double min(int i) {
		return matriz.get(i).getMin();
	}
	public ArrayList<Double> Min(){		// metodos muy IGUALES , hacer el metodo en un solo metodo y con un enumerable saber si hacer una o otra , aunque ocn bool es mas facil
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i=0; i< primera.length-1;i++) {
			aux.add(matriz.get(i).getMin());
		}
		return aux;
	}
	public ArrayList<Double> Max(){		// devuelve el vector con todos los valores maximos de cada columna
		ArrayList<Double> aux = new ArrayList<Double>();
		for (int i=0; i< primera.length-1;i++) {
			aux.add(matriz.get(i).getMax());
		}
		return aux;
	}
	
	public Double media(int i) {
		return matriz.get(i).media();
	}
	public Double desviacionTipica(int i) {
		return matriz.get(i).desviacionTipica();
	}
	
	
	//------------- SET ---------------//
	public void set(int i , int j , Double a) {		// metodo para introducir un nuevo elemento en la matriz de datos
		matriz.get(j).set(i, a);	// CONTROLAR QUE J NO SE SALGA DE LOS NUMEROS 
	}
	
	public void setClase(int i , String s ) {
		clases.set(i, s);		// metodo para introducir una nueva clase en el vector de clases
	}
	
	
	// -------- VISUALIZACION POR PANTALLA ------------//
	public void cabezera() {		// metodo que muestra la cabezera del fichero de entrenamiento
		String aux = new String();
		for (String a:primera) {
			aux += a + "|";
		}
		//aux += "\n";
		System.out.println(aux);
		
	}
	
	public String toString( int x , int y) {		// pasa a String la matriz de datos , hay que pasar los limites del dataset que se quiere imprimir
		String aux = new String();
		System.out.println(numRows());
		for (int i=x;i< y; i++){
			aux +=i +"º  "+ construirInstancia(i).toString() + "\n";
		}
		return aux;
	}
	
	public void print() {
		System.out.println(toString(0,numRows()-1));
	}
	
	public void printE() {
		System.out.println(toString(getPosE(),numRows()));
	}
	
	
	// --------OPERACIONES DE TIPO --------//
	
	public int pos(String s) {		// devuelve la posicion de la clase en la que se encuentra dicha clase
		return tipos.pos(s);
	}
	public int cantidad() {
		 return tipos.cantidad();
	 }
	public void tiposPrint() {
		tipos.print();
	}
	public String clase(int i) {		// cuidado con llamar a la misma funcion una vez dentro de otra
		return tipos.clase(i);
	}
	
}
