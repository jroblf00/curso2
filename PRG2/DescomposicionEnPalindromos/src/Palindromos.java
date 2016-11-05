import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;

public class Palindromos {

	
	static ArrayList<String> lista_soluciones = new ArrayList<String>();
	
	public static void main (String[] args) throws IOException{
		
		StringBuffer buffer = new StringBuffer();
		InputStreamReader rd = new InputStreamReader(System.in);
		
		LeerTeclado(rd, buffer);
		
		if(buffer.toString().equals("")){
			
			System.out.println("La cadena es nula.");
			
		}
		
		else{
			
			
			Buscar(buffer.toString(), 0, 1);

			System.out.println("La palabra " + buffer.toString() +
					" se descompone en " + lista_soluciones.size() + " palindromos.");
			
		}

	}
	
	public static void LeerTeclado(Reader rd, StringBuffer teclado) throws IOException{
		
		char ch = (char)rd.read();
		
		

		if(ch != '\n'){
			teclado.append(ch);
			LeerTeclado(rd, teclado);
			
		}
		
	}	
	
	public static void Buscar(String frase, int posAbajo, int posArriba){
		
		if(posArriba >= frase.length() && posAbajo < frase.length()){
			
			++posAbajo;
			posArriba = posAbajo+1;
			
		}
		
		if(posAbajo < frase.length() && posArriba < frase.length()){
			
			if(frase.charAt(posAbajo) != frase.charAt(posArriba)){
				
				Buscar(frase, posAbajo, ++posArriba);
				
			}
			
			else {
				
				if(Comprobar(frase.substring(posAbajo, ++posArriba)) && 
					!lista_soluciones.contains(frase.substring(posAbajo, posArriba))){
					
					lista_soluciones.add(frase.substring(posAbajo, posArriba));
					
				}
				
				Buscar(frase, posAbajo, ++posArriba);
			}
			
		}
		
	}
	
	public static boolean Comprobar(String frase){
		
		StringBuffer copia = new StringBuffer(frase);
		String reverse = copia.reverse().toString();

		return frase.equals(reverse);
		
	}
	
}
