
import java.util.ArrayList;

/**
 * @author Juan Carlos Robles Fernandez
 */

public class Transformacion {
	
	/**
	 * contiene las palabas con la solucion
	 */
	//private ArrayList<String> solucion;
	
	/**
	 * Indica si se ha encontrado la solucion final
	 */
	private boolean haySolucion;
	
	public Transformacion () {
		
		//this.solucion = new ArrayList<String>();
		this.haySolucion = false;
		
	}
	
	public void IniciarTransformacion (String palabraInicio, String palabraFinal, Diccionario diccionario) {
		
		ArrayList <String> solucion = new ArrayList<String>();
		BuscarCombinacion(palabraInicio, palabraFinal, diccionario, solucion);		
		
	}
	
	/**
	 * Comprueba si las dos palabras introducidas son iguales para acabar la secuencia, si no añade
	 * la palabra a un diccionario y busca todas sus adyacentes en 1 letra. Con el nuevo diccionario y 
	 * la nueva palabra llama de manera recursiva a BuscarCombinacion.
	 * 
	 * @param palabraInicio palabra desde que se arranca la secuencia de busqueda
	 * @param palabraFinal palabra a la que se quiere llegar
	 * @param diccionario diccionario con todas las palabras disponibles
	 */
	public void BuscarCombinacion (String palabraInicio, String palabraFinal, Diccionario diccionario, 
			ArrayList<String> solucion) {
			
		Diccionario diccionarioDuplicado = new Diccionario(diccionario);
		Diccionario diccionarioAux;
		//ArrayList<Integer> diccionarioPosiciones = new ArrayList<Integer>();
		String palabraAux;
		
		
		if (palabraInicio.equals(palabraFinal)) {
			
			this.haySolucion = true;
			solucion.add(palabraFinal);
			for (int i=0; i<solucion.size(); i++) {
				
				System.out.println(solucion.get(i));
				
			}
		}
		
		else {
			
			diccionarioAux = new Diccionario (BuscarPalabrasAdyacentes (palabraInicio, diccionarioDuplicado));
			solucion.add(palabraInicio);
			diccionario.Eliminar(palabraInicio);
			
			for (int i=0; i<diccionarioAux.Tamanio(); i++) {
				//palabraAux = diccionarioDuplicado.Acceder(diccionarioPosiciones.get(i));
				BuscarCombinacion(diccionarioAux.Acceder(i), palabraFinal, diccionario, solucion);
				
			} 
			
		}
		
	}
	
	/**
	 * Busca en un diccionario las palabras que difieren en 1 letra.
	 * 
	 * @param palabra palabra para comparar con el diccionario
	 * @param diccionario diccionario con las palabras deseadas a comparar
	 * @return la lista de palabras que difieren en 1 letra con la palabra a comparar
	 */
	
	public ArrayList<String> BuscarPalabrasAdyacentes(String palabra ,Diccionario diccionario) {
		
		ArrayList <String> palabrasAdyacentes = new ArrayList<String>();
		
		for (int i=0; i<diccionario.Tamanio(); i++) {
			
			if (CompararPalabras(palabra, diccionario.Acceder(i)) == 1) {
				
				palabrasAdyacentes.add(diccionario.Acceder(i));
				
			}
			
		} 
		
		return palabrasAdyacentes;
		
	}
	
	public ArrayList<Integer> BuscarPalabrasAdyacentesInt(String palabra ,Diccionario diccionario) {
		
		ArrayList <Integer> palabrasAdyacentes = new ArrayList<Integer>();
		
		for (int i=0; i<diccionario.Tamanio(); i++) {
			
			if (CompararPalabras(palabra, diccionario.Acceder(i)) == 1) {
				
				palabrasAdyacentes.add(i);
				
			}
			
		} 
		
		return palabrasAdyacentes;
		
	}
	
	/**
	 * Si las palabras son iguales realiza la operacion.
	 * Si no son iguales retorna -1.
	 * 
	 * @param palabra1 un string a comparar
	 * @param palabra2 oto string a comparar
	 * @return el numero de letras desiguales entre las dos palabras comparadas
	 */
	
	public int CompararPalabras(String palabra1, String palabra2) {
		
		int cont = 0;
		
		if (palabra1.length() == palabra2.length()) {
				
			for (int i=0; i<palabra1.length(); i++) {
								
				if (palabra1.charAt(i) != palabra2.charAt(i)) {
					
					cont++;
					
				}

			}
		
		}
		
		else {
			
			return -1;
			
		}
		
		return cont;
	}
	
	public boolean HaySolucion () {
		
		return this.haySolucion;
		
	}
	
}
