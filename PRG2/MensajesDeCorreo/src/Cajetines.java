
public class Cajetines {

	private Cajetin[] cajetines;
	private int posicionHuecoVacio;
	
	
	/**
	 * Constructor
	 * 
	 * Se asigna el tamaño de cajetin.
	 * 
	 * @param tamanio
	 */
	public Cajetines (int tamanio) {
		
		this.cajetines = new Cajetin[tamanio];
		this.posicionHuecoVacio = 0;
		
		for(int i=0; i<this.cajetines.length; i++){
			
			this.cajetines[i] = new Cajetin();
			
		}
		
	}
	
	/**
	 * Comprueba si cajetines contiene alguna carta.
	 * 
	 * @return true si hay, false si no hay
	 */
	public boolean existeCarta(){
		
		return posicionHuecoVacio > 0;
		
	}
	
	/**
	 * Comprueba si hay algun cajetin vacio.
	 * 
	 * @return true si hay cajetin vacio, si no false
	 */
	public boolean existeCajetinVacio () {
			
		return posicionHuecoVacio < cajetines.length;
		
	}
	
	/**
	 * 
	 * @return la cantidad de cartas que contiene cajetines
	 */
	public int cantidadCartas () {
				
		return posicionHuecoVacio;
		
	}
	
	/**
	 * Inserta una carta en el cajetin indicado
	 * 
	 * @param origen departamento de origen de la carta
	 * @param destino departamento destino de la carta
	 * @param mensaje el mensaje que contiene la carta
	 */
	public void insetarCarta (char origen, char destino, int mensaje) {

		cajetines[posicionHuecoVacio].setOrigen(origen);
		cajetines[posicionHuecoVacio].setDestino(destino);
		cajetines[posicionHuecoVacio].setMensaje(mensaje);
		cajetines[posicionHuecoVacio].setEstado(true);
		posicionHuecoVacio++;
		
	}

	/**
	 * 
	 * @return todas las cartas y sus datos
	 */
	public Cajetin[] recogerCarta () {
		
		return this.cajetines;
		
	}

	
	/**
	 * pone todos los huecos de cajetines como vacio
	 */
	public void eliminarCartas () {
		
		for (int i=0; i<this.cajetines.length; i++) {
			
			cajetines[i].setDefault();
			
		}
		posicionHuecoVacio = 0;
		
	}
	
}
