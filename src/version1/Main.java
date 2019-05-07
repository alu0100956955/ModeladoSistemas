package version1;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Main {

	
	public static void main(String[] args) {
		
		algoritmo();
		
		
	}
	
	
	public static  void algoritmo() {
		Scanner sc = new Scanner(System.in), sc2 = new Scanner(System.in);
		String s1 = new String(), s2 = new String();		// s1 fichero para entrenar , s2 fichero para evaluar
		int k =0,distancia=0;
		System.out.print("introduce el nombre del fichero para ENTRENAR: ");
		s1 = sc.nextLine();
		System.out.print("introduce el nombre del fichero para EVALUAR: ");
		s2 = sc.nextLine();
		
		File file = new File(s1);
		File file2 = new File(s2);
		
		try {
			Scanner inputStream = new Scanner(file);
			Scanner inputStream2 = new Scanner(file2);
			System.out.print("Cuantos vecinos desea buscar ?: ");
			k = sc2.nextInt();
			System.out.print("Que distancia desea emplear ? 0.Eculidea , 1.Manhattan ó 2.Chebychef : ");
			distancia = sc2.nextInt();
			Interfaz in = new Interfaz(inputStream,inputStream2,k,distancia);
			
			in.evaluar(0,0.0);
			
		}catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}

}



