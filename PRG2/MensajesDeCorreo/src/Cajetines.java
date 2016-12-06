
public class Cajetines {

	private Cajetin[] cajetines;
	private int posicionHuecoVacio;
	
	public Cajetines (int tamanio) {
		
		this.cajetines = new Cajetin[tamanio];
		this.posicionHuecoVacio = 0;
		
		for(int i=0; i<this.cajetines.length; i++){
			
			this.cajetines[i] = new Cajetin();
			
		}
		
	}
	
	public boolean existeCarta(){
		
		return posicionHuecoVacio > 0;
		
	}
	
	public boolean existeCajetinVacio () {
			
		return posicionHuecoVacio < cajetines.length;
		
	}
	
	public int cantidadCartas () {
				
		return posicionHuecoVacio;
		
	}
	
	public void insetarCarta (char origen, char destino, int mensaje) {

		cajetines[posicionHuecoVacio].setOrigen(origen);
		cajetines[posicionHuecoVacio].setDestino(destino);
		cajetines[posicionHuecoVacio].setMensaje(mensaje);
		cajetines[posicionHuecoVacio].setEstado(true);
		posicionHuecoVacio++;
		
	}

	public Cajetin[] recogerCarta () {
		
		return this.cajetines;
		
	}

	public void eliminarCartas () {
		
		for (int i=0; i<this.cajetines.length; i++) {
			
			cajetines[i].setDefault();
			
		}
		posicionHuecoVacio = 0;
		
	}
	
}
