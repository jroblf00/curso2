import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Juan Carlos Robles Fernandez
 */

public class Main {

	public static void main(String[] args) {
		
		Transformacion transformacion;
		Diccionario diccionario = new Diccionario();
		
		Scanner sc = new Scanner(System.in);		
		ArrayList<String> palabrasATransformar = new ArrayList<String>();
		String buffer;

		
		do{
			
			buffer = sc.nextLine();
			
			if (buffer.compareTo("") != 0)
				diccionario.insertar(buffer);
		
		}while(buffer.compareTo("") != 0);
		
		while (sc.hasNextLine()) {
		
			buffer = sc.nextLine();
			
			if (buffer.isEmpty()) {
				
				break;
				
			}
			
			StringTokenizer st = new StringTokenizer(buffer," ");
			palabrasATransformar.add(st.nextToken());
			palabrasATransformar.add(st.nextToken());
			
		}
		
		sc.close();
		
		transformacion = new Transformacion(diccionario);
		
		for (int i=0; i<palabrasATransformar.size(); i+=2) {
		
			if (diccionario.existePalabra(palabrasATransformar.get(i)) &&
					diccionario.existePalabra(palabrasATransformar.get(i+1))) {
			
				transformacion.generarCaminos();
				transformacion.obtenerCamino(diccionario.buscarPosicion(palabrasATransformar.get(i)),
						diccionario.buscarPosicion(palabrasATransformar.get(i+1)));
				System.out.print(transformacion.imprimirCamino());
		
				if (i < palabrasATransformar.size()-2) {
					
					System.out.println();
					
				}
				
			}
			
			else {
				
				System.out.println("Sin solución.");
				
			}
		
		}
		
	}

}
