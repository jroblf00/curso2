
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
	
	public Diccionario (ArrayList<String> diccionario) {
		
		this.diccionario = new ArrayList<String>();
		
		for (int i=0; i<diccionario.size(); i++) {
			
			this.diccionario.add(diccionario.get(i));
			
		}
		
	}
	
	
	/**
	 * Contructor
	 * 
	 * Crea un diccionario con los valores de otro diccionario
	 * 
	 * @param diccionario diccionario del que se cogen los valores
	 */
	public Diccionario (Diccionario diccionario) {
		
		this.diccionario = new ArrayList<String>();
		
		for (int i=0; i<diccionario.Tamanio(); i++) {
			
			this.diccionario.add(diccionario.Acceder(i));
			
		}
		
	}
	
	/**
	 * 
	 * @param palabra String de la palabra que se desea eliminar
	 */
	public void Eliminar (String palabra) {
		
		this.diccionario.remove(palabra);
		
	}
	
	/**
	 * 
	 * @param posicion posicion de la palabra que se desea eliminar
	 */
	public void Eliminar (int posicion) {
		
		this.diccionario.remove(posicion);
		
	}
	
	/**
	 * @param palabra string que se desea almacenar
	 */
	public void insertar (String palabra) {
		
		if (this.diccionario.size() <= MAX_PALABRAS) {
			
			this.diccionario.add(palabra);
			
		}		
		
	}
	
	public void Ordenar () {
		
		Collections.sort(this.diccionario);
		
	}
	
	
	/**
	 * Devuelve el string de la posicion indicada
	 * @param pos posicion a la que se desea acceder
	 * @return string de la posicion que se desea acceder
	 */
	public String Acceder (int pos) {
		
		return this.diccionario.get(pos);
		
	}
	
	public int Posicion (String palabra) {
		
		return this.diccionario.indexOf(palabra);
		
	}
	
	public int Tamanio () {
		
		return this.diccionario.size();
		
	}
	
	public int BuscarPosicion (String palabra) {
		
		return this.diccionario.indexOf(palabra);
		
	}
	
	public String toString () {
		
		return this.diccionario.toString();
		
	}
}
