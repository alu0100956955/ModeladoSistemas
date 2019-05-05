package version1;
import java.util.ArrayList;

public class Tipo {
	private ArrayList<String> tipos;
	
	
	public Tipo(){
		tipos = new ArrayList<String>();
	}
	
	public void add(String s) {
		if (tipos.size() ==0 || !comprobarClase(s) ){
			tipos.add(s);
		}
	}
	
	public Boolean comprobarClase(String s) {
		return tipos.contains(s);
	}
	
	public int pos(String s) {		// devuelve la posicion de la clase en la que se encuentra dicha clase
		return tipos.indexOf(s);
	}
	
	public String clase(int i) {
		return tipos.get(i);
	}
	
	public int cantidad() {
		return tipos.size();
	}
	
	public String toString() {
		String aux = new String();
		for ( String a:tipos) aux += " , " + a ;
		return aux;
	}
	public void print() {
		System.out.println(toString());
	}
	
}
