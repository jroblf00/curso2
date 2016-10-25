/**
Se pide un programa que lea texto por la entrada est�ndar hasta el primer s�mbolo dolar '$' y de como salida por la salida est�ndar en una l�nea completa el texto "Delimitadores balanceados." si los par�ntesis, corchetes y llaves se encuentran balanceados o, en otro caso, devuelva el texto "Delimitadores sin balancear.".  Una l�nea se dice completa si finaliza con el car�cter fin de l�nea.
En un texto, los par�ntesis ( ), corchetes [ ] y llaves { } se dicen balanceados si a cada s�mbolo de apertura de un tipo le corresponde un s�mbolo de cierre del mismo tipo, no hay s�mbolos de cierre extras y ademas se encuentran correctamente anidados, lo que significa que si se encuentra un s�mbolo de apertura de cualquier tipo no puede cerrarse ning�n s�mbolo de otro tipo hasta que se cierre el previamente abierto. Observar que esto no significa que no se puedan abrir varios s�mbolos de cualquier tipo sucesivamente. Lo que obliga es que se cierren en el orden inverso al que se abrieron.

La puntuaci�n final del ejercicio se dividir� por 2 si se realiza mediante bucles iterativos. Para obtener una puntuaci�n final completa no deber�n aparecer en el c�digo, ni siquiera en los comentarios, las palabras for, while o repeat.

Ejemplo

Entrada

Primer {ejercicio (1)} de programaci[�]n
Salida

Delimitadores balanceados. 

 */

/**
 * @author Adolfo
 */
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;

 class Balanceados {

	public static void main(String[] args) {
		Reader rd = new InputStreamReader(System.in);
		boolean balanceado = true;

		try{
			balanceado=leeEntrada(rd);
		} catch (IOException e){
			System.out.println("Delimitadores no balanceados.");
		}
		if (balanceado)
			System.out.println("Delimitadores balanceados.");
		else
			System.out.println("Delimitadores no balanceados.");
	}

	/**
	 * Lee el stream y devuelve true  si el stream contiene un texto balanceado seguido de un 
	 * corchete y false en otro caso 
	 * @param rd 
	 * @throws IOException
	 */
	private static boolean leeEntrada(Reader rd) throws IOException {
		int ch = rd.read();
		switch (ch){
		case '$': return true;
		case '(': if (cierraParentesis(rd)) return leeEntrada(rd); return false;
		case '[': if (cierraCorchetes(rd)) return leeEntrada(rd); return false;
		case '{': if (cierraLlaves(rd)) return leeEntrada(rd); return false;
		case ')': 
		case ']':
		case '}': return false;
		default:
			return leeEntrada(rd);
		}
	}

	/**
	 *  Devuelve true si rd contiene un texto balanceado seguido de una llave cerrada 
	 *  y false en otro caso 
	 * @param rd
	 * @return
	 */
	private static boolean cierraLlaves(Reader rd) throws IOException{
		int ch = rd.read();
		switch (ch){
		case '$': return false;
		case '(': if (cierraParentesis(rd)) return cierraLlaves(rd); return false;
		case '[': if (cierraCorchetes(rd)) return cierraLlaves(rd); return false;
		case '{': if (cierraLlaves(rd))return cierraLlaves(rd); return false;
		case ')': 
		case ']': return false;
		case '}': return true;
		default:
			return cierraLlaves(rd);
		}
	}

	 /**  Devuelve true si rd contiene un texto balanceado seguido de un corchete cerrado 
	 *  y false en otro caso 
	 * @param rd
	 * @return
	 */
	private static boolean cierraCorchetes(Reader rd) throws IOException{
		int ch = rd.read();
		switch (ch){
		case '$': return false;
		case '(': if (cierraParentesis(rd)) return cierraCorchetes(rd); return false; 
		case '[': if (cierraCorchetes(rd)) return cierraCorchetes(rd); return false;
		case '{': if (cierraLlaves(rd)) return cierraCorchetes(rd); return false;
		case ')':  return false;
		case ']': return true;
		case '}': return false;
		default:
			return cierraCorchetes(rd);
		}
	}

	/**  Devuelve true si rd contiene un texto balanceado seguido de un par�ntesis cerrado 
	 *  y false en otro crreader con el stream de caracteres
	 * @return true si el resto del stream comienza con [caracteres y] el cierre del parentesis
	 */
	private static boolean cierraParentesis(Reader rd) throws IOException{
		int ch = rd.read();
		switch (ch){
		case '$': return false;
		case '(': if (cierraParentesis(rd)) return cierraParentesis(rd); return false;
		case '[': if (cierraCorchetes(rd)) return cierraParentesis(rd); return false;
		case '{': if (cierraLlaves(rd)) return cierraParentesis(rd); return false;
		case ')': return true;
		case ']': 
		case '}': return false;
		default:
			return cierraParentesis(rd);
		}
	}
}
