import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Palindromos {
	/**
	 * fraseCopia se utiliza como auxiliar del string ingresador por teclado
	 */
	static StringBuffer fraseCopia = new StringBuffer();
	
	/**
	 * contadorPalindromos cuenta los palindromos encontrados
	 */
	
	static int contadorPalindromos = 0;
	static int contadorPalindromosAuxiliar = 0;
	
	public static void main (String[] args) throws IOException {
		
		StringBuffer buffer = new StringBuffer();
		InputStreamReader rd = new InputStreamReader(System.in);
		
		LeerTeclado(rd, buffer);		
		/**
		 * si no se ingresa nada por teclado se muestra "La cadena es nula"
		 */
		if(buffer.toString().equals("")) {
			
			System.out.println("La cadena es nula.");
			
		}
		
		else {
			
			/**
			 * se llama a la funcion buscar. 
			 * Se calculan todos los palindromos.
			 * Se llama a la funcion buscar con la cadena revertida.
			 * Se calculan todos los palindromos hayados.
			 * Mostramos por pantalla la cadena introducida por teclado y la cantidad minima de palindomos encontrados.
			 */
			
			fraseCopia.insert(0, buffer);			
			Buscar(0,1);
			contadorPalindromos = contadorPalindromos + fraseCopia.length();
			contadorPalindromosAuxiliar = contadorPalindromos;
			contadorPalindromos = 0;
			
			fraseCopia.delete(0, fraseCopia.length());
			fraseCopia.insert(0, buffer.reverse());
			Buscar(0,1);
			contadorPalindromos = contadorPalindromos + fraseCopia.length();
			buffer.reverse();
			
			if(contadorPalindromosAuxiliar < contadorPalindromos){
				
				contadorPalindromos = contadorPalindromosAuxiliar;
				
			}
			
			System.out.println("La palabra " + buffer.toString() +
					" se descompone en " + contadorPalindromos + " palindromos.");
			
		}
		
	}
	
	/**
	 * Precondicion solo ingresa las letras del abecedario español sin acentos.
	 * 
	 * Ingresa la cadena introducida por el teclado ignorando todo caracter que no este dentro del
	 * abecedario español
	 * 
	 * @param rd
	 * @param teclado string donde se guardara la cadena introducida por teclado
	 * @throws IOException
	 */
	
	public static void LeerTeclado(Reader rd, StringBuffer teclado) throws IOException {
		
		char ch = (char)rd.read();
		
		if(ch != '\n'){
			
			if((ch > 64 && ch < 91) || (ch > 96 && ch < 123)){
				
				teclado.append(ch);
				LeerTeclado(rd, teclado);
			
			}
		}
		
		teclado.toString().toLowerCase();
		
	}	
	
	/**
	 * Busca los palindromos
	 * @param posAbajo controla la posicion baja de la cadena
	 * @param posArriba controla la posicion alta de la cadena
	 */
	
	public static void Buscar(int posAbajo, int posArriba) {	

		if(posArriba >= fraseCopia.length()){

			++posAbajo;
			posArriba = posAbajo+1;
			
		}
		
		if(posAbajo < fraseCopia.length() && posArriba < fraseCopia.length()){
			
			if(fraseCopia.charAt(posAbajo) != fraseCopia.charAt(posArriba)){
				
				Buscar(posAbajo, ++posArriba);
				
			}
			
			else {
				
				if(Comprobar(fraseCopia.substring(posAbajo, ++posArriba))){
					
					fraseCopia.delete(posAbajo, posArriba);
					contadorPalindromos += 1;
					
				}
				
				Buscar(posAbajo, ++posArriba);
			}
			
		}
		
	}
	/**
	 * Comprueba si una cadena es palindroma
	 * @param frase cadena para comprobar si es palindroma
	 * @return true si es palindromo, false si no lo es
	 */
	public static boolean Comprobar(String frase){
		
		StringBuffer copia = new StringBuffer(frase);
		String reverse = copia.reverse().toString();

		return frase.equals(reverse);
		
	}
	
}
