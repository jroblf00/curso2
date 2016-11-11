import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

public class Palindromos {
	/**
	 * fraseCopia se utiliza como auxiliar del string ingresador por teclado
	 */
	static StringBuffer fraseAux = new StringBuffer();
	
	/**
	 * contadorPalindromos cuenta los palindromos encontrados
	 */
	
	static int contadorPalindromos = 0;
	static int contadorPalindromosAuxiliar = 0;
	
	public static void main (String[] args) throws IOException {
		
		StringBuffer fraseTeclado = new StringBuffer();
		InputStreamReader rd = new InputStreamReader(System.in);
		
		LeerTeclado(rd, fraseTeclado);
		
		fraseAux.insert(0, fraseTeclado.toString().toLowerCase());
		fraseTeclado.delete(0, fraseTeclado.length());
		fraseTeclado.insert(0, fraseAux);
		
		/**
		 * si no se ingresa nada por teclado se muestra "La cadena es nula"
		 */
		if(fraseTeclado.toString().equals("")) {
			
			System.out.println("La cadena es nula.");
			
		}
		
		else {
			
			/**
			 * Comprueba si la palabra en si es palindroma.
			 * En caso contrario se llama a la funcion buscar. 
			 * Se calculan todos los palindromos.
			 * Se llama a la funcion buscar con la cadena revertida.
			 * Se calculan todos los palindromos hayados.
			 * Mostramos por pantalla la cadena introducida por teclado y la cantidad minima de palindomos encontrados.
			 */
			
			if(Comprobar(fraseTeclado.toString())) {
				
				contadorPalindromos = 1;
				
			}
			
			else {
			
				fraseAux.delete(0, fraseAux.length());
				fraseAux.insert(0, fraseTeclado);			
				
				Buscar(0,1);
				
				contadorPalindromos = contadorPalindromos + fraseAux.length();
				contadorPalindromosAuxiliar = contadorPalindromos;
				contadorPalindromos = 0;
				
				fraseAux.delete(0, fraseAux.length());
				fraseAux.insert(0, fraseTeclado.reverse());
				
				Buscar(0,1);
				
				contadorPalindromos = contadorPalindromos + fraseAux.length();
				fraseTeclado.reverse();
			
				if(contadorPalindromosAuxiliar < contadorPalindromos){
					
					contadorPalindromos = contadorPalindromosAuxiliar;
					
				}
			
			}
			
			System.out.println("La palabra " + fraseTeclado.toString() +
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
			
			}
			
			LeerTeclado(rd, teclado);
		}
		
	}	
	
	/**
	 * Busca los palindromos
	 * @param posAbajo controla la posicion baja de la cadena
	 * @param posArriba controla la posicion alta de la cadena
	 */
	
	public static void Buscar(int posAbajo, int posArriba) {	

		if(posArriba >= fraseAux.length()){

			++posAbajo;
			posArriba = posAbajo+1;
			
		}
		
		if(posAbajo < fraseAux.length() && posArriba < fraseAux.length()){
			
			if(fraseAux.charAt(posAbajo) != fraseAux.charAt(posArriba)){
				
				Buscar(posAbajo, ++posArriba);
				
			}
			
			else {
				
				if(Comprobar(fraseAux.substring(posAbajo, ++posArriba))){
					
					fraseAux.delete(posAbajo, posArriba);
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
