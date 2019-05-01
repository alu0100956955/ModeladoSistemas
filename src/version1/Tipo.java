package version1;
import java.util.ArrayList;

public class Tipo {
	private ArrayList<String> tipos;
	
	
	Tipo(){
		tipos = new ArrayList<String>();
	}
	
	void add(String s) {
		if (tipos.size() ==0 || comprobarClase(s) ){
			tipos.add(s);
		}
	}
	
	Boolean comprobarClase(String s) {
		return tipos.contains(s);
	}
	
	int pos(String s) {		// devuelve la posicion de la clase en la que se encuentra dicha clase
		return tipos.indexOf(s);
	}
	
	String clase(int i) {
		return tipos.get(i);
	}
	
}
