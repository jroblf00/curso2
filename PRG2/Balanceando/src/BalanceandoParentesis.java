import java.io.*;

public class BalanceandoParentesis {

	
	private static boolean balanceado;
	
	public static void main(String[] args) {

		InputStreamReader rd = new InputStreamReader(System.in);
		StringBuffer buffer = new StringBuffer();
		
		System.out.println("Introduce un texto: ");
		
		try {
		
			Almacenar(rd, buffer);
			ComprobarPorAbajo(buffer.toString(), ComprobarPosicion(buffer.toString()));
			
			if(balanceado) {
				
				System.out.println("Esta balanceado");
				
			}
			
			else {
				
				System.out.println("No esta balanceado");
				
			}
			
		
		} catch(IOException e) {
			
			System.out.println(e);
			
		}
	}

	
	public static void Almacenar(Reader rd, StringBuffer frase) throws IOException{
		
		int ch = rd.read();	
				
		if(ch != '$') {
			
			frase.append(Character.toChars(ch));
			Almacenar(rd, frase);
			
		}
		
	}
	
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
	
}

