class Cajetin {
	
	boolean ocupado;
	int mensaje;
	char departamentoDestino;
	char departamentoOrigen;
	
	public Cajetin () {
		
		this.ocupado = false;
		
	}
	
	public void setEstado (boolean estado) {
		
		this.ocupado = estado;
		
	}
	
	public boolean getEstado () {
		
		return this.ocupado;
		
	}
	
	public void setDestino ( char destino) {
		
		this.departamentoDestino = destino;
		
	}
	
	public char getDestino () {
		
		return this.departamentoDestino;
		
	}
	
	public void setOrigen (char origen) {
		
		this.departamentoOrigen = origen;
		
	}
	
	public char getOrigen () {
		
		return this.departamentoOrigen;
		
	}
	
	public void setMensaje (int mensaje) {
		
		this.mensaje = mensaje;
		
	}
	
	public int getMensaje () {
		
		return this.mensaje;
		
	}
	
	public void setDefault () {
		
		this.ocupado = false;
		
	}
	
}