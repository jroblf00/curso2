import java.io.*;


/**
 *
 * @author Juan Carlos Robles Fernandez
 *
 */

public class BalanceandoParentesis {

	
	private static boolean balanceado;
	
	public static void main(String[] args) {

		InputStreamReader rd = new InputStreamReader(System.in);
		StringBuffer buffer = new StringBuffer();
		
		System.out.println("Introduce un texto: ");
		
		try {
		
			Almacenar(rd, buffer);
			ComprobarPorAbajo(buffer.toString(), ComprobarPosicion(buffer.toString()));
			System.out.println(ImprimirSolucion());
			
		
		} catch(IOException e) {
			
			System.out.println(e);
			
		}
	}

	
	/**
	 * 
	 * @param objeto reader y un buffer donde se almacenara la frase introducida por el teclado
	 *
	 * Almacena en un StringBuffer el teclado hasta que se introduce el caracter '$'
	 *
	 */

	public static void Almacenar(Reader rd, StringBuffer frase) throws IOException{
		
		int ch = rd.read();	
				
		if(ch != '$') {
			
			frase.append(Character.toChars(ch));
			Almacenar(rd, frase);
			
		}
		
	}

	/**
	 * 
	 * @param frase frase introducida por teclado
	 * @param posAbajo posicion del ultimo delimitador abierto
	 *
	 * busca el ultimo delimitador abierto y si lo encuentra llama a ComprobarPorAbrriba
	 *
	 */
	
	public static void ComprobarPorAbajo(String frase, int posAbajo) {
		
		if(posAbajo >= 0){
		
			if(frase.charAt(posAbajo) != '(' && frase.charAt(posAbajo) != '[' && 
					frase.charAt(posAbajo) != '{') {
			
				balanceado = true;
				ComprobarPorAbajo(frase, --posAbajo);
					
			}
			
			else {
				
				balanceado = false;
				ComprobarPorArriba(frase, posAbajo, ++posAbajo);
				
			}
		
		}
		
		else {
			
			ComprobarPorArriba(frase, posAbajo, ++posAbajo);
			
		}
		
	}
	
	/**
	 * 
	 * @param frase frase untroducida por el teclado
	 * @param posAbajo posicion en al que esta el delimitador abierto
	 * @param posArriba posicion de busqueda del delimitador que cierra
	 *
	 * busca donde se cierra el delimitador que esta abierto y si encuentra el delimitador
	 * que cierra o que cierra otro tipo de delimitador que no esta abierto, acaba
	 *
	 */

	public static void ComprobarPorArriba(String frase, int posAbajo, int posArriba) {
		
		StringBuffer fraseCopia = new StringBuffer(frase);
		
		if(posAbajo >= 0 && posArriba < frase.length()) {
		
			
			if((fraseCopia.charAt(posAbajo) == '(') && 
					((fraseCopia.charAt(posArriba) == ']') || fraseCopia.charAt(posArriba) == '}')) {
				
				balanceado = false;
				
			}
			
			else if((fraseCopia.charAt(posAbajo) == '[') && 
					((fraseCopia.charAt(posArriba) == ')' || fraseCopia.charAt(posArriba) == '}'))) {
				
				balanceado = false;
				
			}
			
			else if((fraseCopia.charAt(posAbajo) == '{') && 
					((fraseCopia.charAt(posArriba) == ']' || fraseCopia.charAt(posArriba) == ')'))) {
				
				balanceado = false;
				
			}
			
			else if((fraseCopia.charAt(posAbajo) == '(' && fraseCopia.charAt(posArriba) != ')') 
					|| (fraseCopia.charAt(posAbajo) == '[' && fraseCopia.charAt(posArriba) != ']') 
					|| (fraseCopia.charAt(posAbajo) == '{' && fraseCopia.charAt(posArriba) != '}')) {
				
				balanceado = false;
				ComprobarPorArriba(fraseCopia.toString(), posAbajo, ++posArriba);
				
			}
			
			else {
				
				fraseCopia.deleteCharAt(posArriba);
				fraseCopia.deleteCharAt(posAbajo);
				balanceado = true;
				ComprobarPorAbajo(fraseCopia.toString(), --posAbajo);
				
			}
		
		}
		
		else if(posAbajo < 0 && posArriba < frase.length() && balanceado == true) {
			
			if(fraseCopia.charAt(posArriba) != ')' && fraseCopia.charAt(posArriba) != ']' &&
					fraseCopia.charAt(posArriba) != '}') {
				
				balanceado = true;
				ComprobarPorArriba(fraseCopia.toString(), posAbajo, ++posArriba);
				
			}
			
			else {
				
				balanceado = false;
				
			}
			
		}
	
	}

	/**
	 * 
	 * @param frase frase introducida por teclado
	 * @return posicion del ultimo delimitador abierto
	 *
	 * comprueba cual es el ultimo delimitador abierto
	 * 
	 */

	public static int ComprobarPosicion(String frase) {
		
		int posicion = 0;
		
		if(frase.lastIndexOf('(') > posicion) {
			
			posicion = frase.lastIndexOf('(');
			
		}
		
		if(frase.lastIndexOf('[') > posicion) {
			
			posicion = frase.lastIndexOf('[');
			
		}
		
		if(frase.lastIndexOf('{') > posicion) {
			
			posicion = frase.lastIndexOf('{');
			
		}

		return posicion;
		
	}

	/**
	 * 
	 * @return string con la solucion final
	 * 
	 */
	
	public static String ImprimirSolucion(){
		
		String fraseFinal;
		
		if(balanceado) {
			
			fraseFinal = "Delimitadores balanceados.";
			
		}
		
		else {
			
			fraseFinal = "Delimitadores sin balancear.";
			
		}
		
		return fraseFinal;
		
	}
	
}
