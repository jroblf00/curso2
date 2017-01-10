
/** 
 * @author Juan Carlos Robles Fernandez
 */

import java.util.ArrayList;
import java.util.Collections;

public class Diccionario {
	
	/**
	 * MAX_PALABRAS palabras maximas admitidas en el diccionario
	 */
	private int MAX_PALABRAS = 25143;
	
	/**
	 * diccionario contiene las palabras del diccionario
	 */
	private ArrayList<String> diccionario;
	
	public Diccionario () {
		
		this.diccionario = new ArrayList<String>();	
		
	}
	
	public Diccionario (ArrayList<String> diccionario) {
		
		if (diccionario.size() <= MAX_PALABRAS) { 
		
			this.diccionario = new ArrayList<String>(diccionario);
		
		}
			
	}
	
	/**
	 * @param palabra string que se desea almacenar
	 */
	public void insertar (String palabra) {
		
		if (this.diccionario.size() <= MAX_PALABRAS) {
			
			this.diccionario.add(palabra);
			
		}		
		
	}
	
	public void ordenar () {
		
		Collections.sort(this.diccionario);
		
	}
	
	
	/**
	 * Devuelve el string de la posicion indicada
	 * @param pos posicion a la que se desea acceder
	 * @return string de la posicion que se desea acceder
	 */
	public String acceder (int pos) {
		
		return this.diccionario.get(pos);
		
	}
	
	public int tamanio () {
		
		return this.diccionario.size();
		
	}
	
	public int buscarPosicion (String palabra) {
		
		return this.diccionario.indexOf(palabra);
		
	}
	
	public boolean existePalabra (String palabra) {
		
		return this.diccionario.contains(palabra);
		
	}
	
	public String toString () {
		
		return this.diccionario.toString();
		
	}
}
