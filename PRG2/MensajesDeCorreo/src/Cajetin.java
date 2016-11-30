class Cajetin {
	
	public static boolean lleno;
	
	public Cajetin () {
		
		lleno = false;
		
	}
	
	public boolean estalleno () {
		
		return lleno;
		
	}
	
	public void cabiarEstado (boolean estadoCajetin) {
		
		this.lleno = estadoCajetin;
		
	}	
	
}