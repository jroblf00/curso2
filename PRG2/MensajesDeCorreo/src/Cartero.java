
class Cartero implements Runnable {
	
	Departamento[] departamentos;
	int numeroCartero;
	boolean ocupado;
	
	public Cartero(int numeroCartero, Departamento[] departamento) {
		
		this.departamentos = departamento;
		this.numeroCartero = numeroCartero;
		
		System.out.println("El cartero " + numeroCartero + " se ha creado.");		
		
	}
	
	public void run () {
		
		try {
			
			while (true) {
				
				System.out.println("El cartero " + numeroCartero + " está descansando.");
				wait();
				dormir();
				
			}
			
		}
		
		catch (InterruptedException e) {}
		
	}
	
	public void dormir() throws InterruptedException {
		
		Thread.sleep(100);

		
	}
	
	
	boolean estaOcupado () {
		
		return ocupado;
		
	}
	
	void cambiarEstado (boolean estado) {
		
		this.ocupado = estado;
		
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void interrupt() {
		// TODO Auto-generated method stub
		System.out.println("El cartero " + this.numeroCartero + " ha sido destruido.");

	}

	public void join() {
		// TODO Auto-generated method stub
		
	}
}