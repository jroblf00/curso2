import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Juan Carlos Robles Fernandez
 */

public class Main {

	public static void main(String[] args) {
		
		
		Scanner sc = new Scanner(System.in);		
		Diccionario diccionario = new Diccionario();
		String buffer;
		String inicio;
		String fin;
		
		do{
			
			buffer = sc.nextLine();
			
			if (buffer.compareTo("") != 0)
				diccionario.insertar(buffer);
		
		}while(buffer.compareTo("") != 0);
		
		buffer = sc.nextLine();
		StringTokenizer st = new StringTokenizer(buffer," ");
		inicio = st.nextToken();
		fin = st.nextToken();
		
		sc.close();
		
		diccionario.ordenar();
		
		Transformacion transformacion = new Transformacion(diccionario);
		transformacion.CalcularLongitudMinina();
		transformacion.imprimir();
		
	}

}
