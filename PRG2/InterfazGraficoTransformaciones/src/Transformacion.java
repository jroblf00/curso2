
/**
 * @author Juan Carlos Robles Fernandez
 */

public class Transformacion {

	/**
	 * tamMatriz contiene el tamaño de las matrices.
	 */
	private int tamMatriz;
	
	/**
	 * adyacentes[][] contiene la informacion de si dos palabras son adyacentes.
	 */
	private int adyacentes[][];
	
	/**
	 * caminos[][] contiene el camino mas corto entre dos palabras.
	 */
	private int caminos[][];
	
	/**
	 * camino[] contiene el camino que hay que seguir en la transformacion de las dos palabras
	 * elegidas
	 */
	private int camino[];
	
	private int tamCamino;
	
	/**
	 * diccionario contiene las palabras con las que se puede aplicar la transformacion.
	 */
	private Diccionario diccionario;
	
	public Transformacion(Diccionario diccionario){
		
		this.tamMatriz = diccionario.tamanio();
		this.adyacentes = new int[this.tamMatriz][this.tamMatriz];
		this.caminos = new int[this.tamMatriz][this.tamMatriz];
		this.diccionario = diccionario;
		
	}
	
	/**
	 * Guarda en la matriz adyacentes si dos palabras son adyacentes
	 * 
	 */
	public void generarAdyacencia(){
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0;j<this.tamMatriz; j++) {
				
				if (i == j) {
					
					this.adyacentes[i][j] = Integer.MAX_VALUE;
					
				} 
					
				else {
				
					this.adyacentes[i][j] = compararPalabras(this.diccionario.acceder(i), this.diccionario.acceder(j));
				
				}	
			}
			
		}
		
	}
	
	/**
	 * Si son del mismo tamaño compara las letras, si tienen mas de 1 letra diferente devuelve infinito.
	 * si son de diferente tamaño devuelve infinito.
	 * 
	 * @param palabra1 una palabra a comparar
	 * @param palabra2 otra palabra a comparar
	 * @return la cantidad de letras diferentes que existen entre las dos palabras
	 */
	public int compararPalabras (String palabra1, String palabra2) {
		
		int diferencia = 0;
		
		if (palabra1.length() == palabra2.length()) {
			
			for (int i=0; i<palabra1.length(); i++) {
				
				if (palabra1.charAt(i) != palabra2.charAt(i)) {
					
					diferencia++;
					
				}
				
			}
			
		}
		
		else {
			
			diferencia = Integer.MAX_VALUE;
			
		}
		
		if (diferencia > 1) {
			
			diferencia = Integer.MAX_VALUE;
			
		}
		
		return diferencia;
		
	}
	
	/**
	 * Usa el algoritmo Floyd-Warshall para generar la matriz de palabras minimas (adyacentes) y la matriz
	 * con la ruta que se debe de seguir (caminos)
	 */
	public void generarCaminos () {
		
		generarAdyacencia();
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++) {
				
				this.caminos[i][j] = j;
				
			}
			
		}
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++) {
				
				for (int k=0; k<this.tamMatriz; k++) {
					
					if (this.adyacentes[j][i] != Integer.MAX_VALUE && this.adyacentes[i][k] != Integer.MAX_VALUE
							&& this.adyacentes[j][i] + this.adyacentes[i][k] < this.adyacentes[j][k]) {
						
						this.adyacentes[j][k] = this.adyacentes[j][i] + this.adyacentes[i][k];
						this.caminos[j][k] = this.caminos[j][i];
						
					}
					
				}
				
			}
			
			
			
		}
		
	}
	
	public void generarCaminos (int tamCamino) {
		
		generarAdyacencia();
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++) {
				
				this.caminos[i][j] = j;
				
			}
			
		}
		
		for (int i=0; i<this.tamMatriz; i++) {
			
			for (int j=0; j<this.tamMatriz; j++) {
				
				for (int k=0; k<this.tamMatriz; k++) {
					
					if (this.adyacentes[j][i] != Integer.MAX_VALUE && this.adyacentes[i][k] != Integer.MAX_VALUE
							&& this.adyacentes[j][i] + this.adyacentes[i][k] < this.adyacentes[j][k]) {
						
						this.adyacentes[j][k] = this.adyacentes[j][i] + this.adyacentes[i][k];
						this.caminos[j][k] = this.caminos[j][i];
						
					}
					
				}
				
			}
			
			
			
		}
		
	}
	
	/**
	 * Guarda en camino[] la ruta de palabras que se tiene que seguir
	 * 
	 * @param posicionPalabra1 palabra desde la que se sale
	 * @param posicionPalabra2 palabra a la que se llega
	 */
	public void obtenerCamino (int posicionPalabra1, int posicionPalabra2) {
		
		this.tamCamino = this.adyacentes[posicionPalabra1][posicionPalabra2];
		
		if (this.tamCamino == Integer.MAX_VALUE) {
			
			this.camino = null;
			
		}
		
		else {
			
			this.camino = new int[tamCamino+1];
			this.camino[0] = posicionPalabra1;
			
			for (int i=0; i<tamCamino; i++) {
				
				posicionPalabra1 = this.caminos[posicionPalabra1][posicionPalabra2];
				this.camino[i+1] = posicionPalabra1;
				
			}
			
		}
		
	}
	
	public void obtenerCamino (String palabra1, String palabra2) {
		
		int posicionPalabra1 = this.diccionario.buscarPosicion(palabra1);
		int posicionPalabra2 = this.diccionario.buscarPosicion(palabra2);
		
		if (this.diccionario.existePalabra(palabra1) && this.diccionario.existePalabra(palabra2)) { 
		
			this.tamCamino = this.adyacentes[posicionPalabra1][posicionPalabra2];
			
			if (this.tamCamino == Integer.MAX_VALUE) {
				
				this.camino = null;
				
			}
			
			else {
				
				this.camino = new int[tamCamino+1];
				this.camino[0] = posicionPalabra1;
				
				for (int i=0; i<tamCamino; i++) {
					
					posicionPalabra1 = this.caminos[posicionPalabra1][posicionPalabra2];
					this.camino[i+1] = posicionPalabra1;
					
				}
				
			}
		
		}
		
		else {
			
			this.camino = null;
			
		}
		
	}
	
	public int tamCamino () {
		
		return this.tamCamino;
		
	}
	
	public boolean hayCamino () {
		
		
		return this.camino != null;
		
	}
	
	public String getCamino () {
		
		StringBuffer buffer = new StringBuffer();
		
		for (int i=0; i<this.camino.length; i++) {
			
			buffer.append(this.diccionario.acceder(this.camino[i]));
			
		}
		
		return buffer.toString();
		
	}
	
	public String imprimirCamino () {
		
		StringBuffer imprimir = new StringBuffer(); 
		
		if (this.camino == null) {
			
			imprimir.append("Sin solución.");
		}
		
		else {
			
			for (int i=0; i<this.camino.length; i++) {
				
				imprimir.append(this.diccionario.acceder(this.camino[i]) + "\n");
				
			}
			
		}
				
		return imprimir.toString();
		
	}
	
}
