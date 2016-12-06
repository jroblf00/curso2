class Cajetin {
	
	boolean ocupado;
	int mensaje;
	char departamentoDestino;
	char departamentoOrigen;

	/**
	 * Constructor
	 * 
	 * Por defecto no esta ocupado el cajetin.	
	 */
	public Cajetin () {
		
		this.ocupado = false;
		
	}
	
	/**
	 * 
	 * @param estado true si esta ocupado, false si no lo esta
	 */
	public void setEstado (boolean estado) {
		
		this.ocupado = estado;
		
	}
	
	public boolean getEstado () {
		
		return this.ocupado;
		
	}
	
	 /**
	  * 
	  * @param destino el nombre del departamento destino
	  */
	public void setDestino ( char destino) {
		
		this.departamentoDestino = destino;
		
	}
	
	public char getDestino () {
		
		return this.departamentoDestino;
		
	}
	
	/**
	 * 
	 * @param origen el nombre del departamento origen
	 */
	public void setOrigen (char origen) {
		
		this.departamentoOrigen = origen;
		
	}
	
	public char getOrigen () {
		
		return this.departamentoOrigen;
		
	}
	
	/**
	 * 
	 * @param mensaje el mensaje que contiene el cajetin
	 */
	public void setMensaje (int mensaje) {
		
		this.mensaje = mensaje;
		
	}
	
	public int getMensaje () {
		
		return this.mensaje;
		
	}
	
	/**
	 * Pone al cagetin por defecto, vacio.
	 */
	public void setDefault () {
		
		this.ocupado = false;
		
	}
	
}