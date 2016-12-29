
/**
 * @author Juan Carlos Robles Fernandez
 */

public class Transformacion {
	
	/**
	 * tamaño de la matriz
	 */
	private int tamMatriz;
	
	/**
	 * matriz con la longitud minima entre las palabras
	 */
	private int longitudMinimaEntrePalabras[][];
	
	/**
	 * matriz con el camino que se debe seguir para la longitud minima
	 */
	private int caminoEntrePalabras[][];
	
	Diccionario diccionario;
	
	/**
	 * Constructor
	 * 
	 * Asigna a la matriz longitudMinimaEntrePalabras la primera conexion entre las
	 * diferentes palabras del diccionario.
	 * 0 si hay conexion directa entre dos palabras.
	 * -1 si no existe conexion.
	 * 
	 * Inicializa la matriz caminoEntrePalabras a 0.
	 */
	
	public Transformacion (Diccionario diccionario) {
		
		this.diccionario = diccionario;
		
		this.tamMatriz = this.diccionario.tamanio();
		
		this.longitudMinimaEntrePalabras = new int [this.tamMatriz][this.tamMatriz];
		this.caminoEntrePalabras = new int [this.tamMatriz][this.tamMatriz];
		
		for (int i=0;i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++) {
				
				if (CompararPalabras(this.diccionario.acceder(i), this.diccionario.acceder(j)) == 1) {
					
					this.longitudMinimaEntrePalabras[i][j] = 0;
					
				}
				
				else  {
					
					this.longitudMinimaEntrePalabras[i][j] = -1;
					
				} 
				
			}
			
		}
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++){
				
				this.caminoEntrePalabras[i][j] = 0;
				
			}
			
		}
		
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
	
	public void imprimir () {
		
		for (int i=0; i<tamMatriz; i++) {
			
			for (int j=0; j<tamMatriz; j++) {
				
				System.out.print(longitudMinimaEntrePalabras[i][j]+"\t");
			}
			
			System.out.println();
			
		}		
		
	}
	
}
