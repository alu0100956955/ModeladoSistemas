package version1;

public class Vecino {
	private Double distancia;
	private int posicion;
	public String clase;
	
	//  CONSTRUCTORES
	public Vecino() {
		this(100000.0,2500,"xx");
	}
	public Vecino(Double a,int i,String s) {
		distancia =a;
		posicion=i;
		clase = new String(s);
	}
	
	
	// GETTERS
	public Double getD() {
		return distancia;
	}
	public int getP() {
		return posicion;
	}
	public String getC() {
		return clase;
	}

	// SETTERS
	public void setD(Double a) {
		distancia =a;
	}
	public void setP(int a) {
		posicion =a;
	}
	public void setC(String s) {
		clase = s;
	}
	public void set(int i , Double a,String s) {
		distancia =a;
		posicion = i;
		clase = s ;
	}
	
	public String toString() {
		String aux = new String();
		aux += "[ " + getD() + " , " + getP() + " , " + getC() + " ]" ;
		return aux;
	}
	
	
}
