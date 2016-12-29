
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
	 * Array que contiene las palabras del diccionario
	 */
	private ArrayList<String> diccionario;
	
	public Diccionario () {
		
		this.diccionario = new ArrayList<String>();	
		
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
}
