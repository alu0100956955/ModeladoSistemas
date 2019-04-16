package version1;
import java.util.ArrayList;

public class AtributoCualitativo extends Atributo{
	private ArrayList<String> clases;
	
	public AtributoCualitativo() {
		clases = new ArrayList<String>();
	}
	
	public void  add(String a) {
		clases.add(a);
	}
	
	public void set(int i , String a) {
		clases.set(i, a);
	}

	
	public String get(int i) {
		return clases.get(i);
	}
}
