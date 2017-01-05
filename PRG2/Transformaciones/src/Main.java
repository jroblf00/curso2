import java.util.ArrayList;
import java.util.Scanner;
import java.util.StringTokenizer;

/**
 * @author Juan Carlos Robles Fernandez
 */

public class Main {

	public static void main(String[] args) {
		
		/*
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
		transformacion.generarCaminos();
		transformacion.obtenerCamino(diccionario.BuscarPosicion(inicio), diccionario.BuscarPosicion(fin));
		System.out.println(transformacion.imprimirCamino());
		//transformacion.CalcularLongitudMinina();
		//transformacion.CalcularLongitudMinina();
		//transformacion.imprimir();
		//System.out.println(diccionario.toString());
		//System.out.println(transformacion.MostrarCamino(inicio, fin));
		*/
		
		ArrayList<String> palabras = new ArrayList<String>();
		palabras.add("casa");
		palabras.add("lasa");
		palabras.add("pasa");
		palabras.add("masa");
		palabras.add("abrasa");
		palabras.add("paso");
		palabras.add("bueñue");
		palabras.add("poco");
		palabras.add("pozo");
		palabras.add("postal");
		palabras.add("paco");
		
		Diccionario diccionario = new Diccionario(palabras);
		Transformacion tf = new Transformacion(diccionario);
		tf.generarCaminos();
		tf.obtenerCamino(palabras.indexOf("casa"), palabras.indexOf("caso"));
		System.out.println(tf.imprimirCamino());
	}

}
