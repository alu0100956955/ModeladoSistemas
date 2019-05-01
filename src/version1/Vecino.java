package version1;

public class Vecino {
	private Double distancia;
	private int posicion;
	
	//  CONSTRUCTORES
	public Vecino() {
		this(100000.0,0);
	}
	public Vecino(Double a,int i) {
		distancia =a;
		posicion=i;
	}
	
	
	// GETTERS
	public Double getD() {
		return distancia;
	}
	public int getP() {
		return posicion;
	}

	// SETTERS
	public void setD(Double a) {
		distancia =a;
	}
	public void setP(int a) {
		posicion =a;
	}
	public void set(int i , Double a) {
		distancia =a;
		posicion = i;
	}
	
	public String toString() {
		String aux = new String();
		aux += "[ " + getD() + " , " + getP() + " ]" ;
		return aux;
	}
	
	
}
