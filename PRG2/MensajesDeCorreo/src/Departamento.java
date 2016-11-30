import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

class Departamento implements Runnable{
	
	public static NormalDistribution distribucionNormal;
	public static ExponentialDistribution distribucionExponencial;
	public static int nDptos;
	
	Cajetin[] cajetin;
	
	char letraDepartamento;
	boolean solicitaCartero = false;
	int mensaje = 0;
	int envio[];
	
	
	public Departamento (char letraDepartamento, int tamCajetines) {
		
		this.cajetin = new Cajetin[tamCajetines];
		this.letraDepartamento = letraDepartamento;
		
		for (int i = 0; i < tamCajetines; i++) {
			
			this.cajetin[i] = new Cajetin();
			
		}
		
		System.out.println("El de partamento " + this.letraDepartamento + " se ha creado.");
		
	}	
	
	public void run() {
		
		try {
			
			while (true) {
				
				System.out.println("El departamento " + letraDepartamento + " está descansando.");
				
				
			}
			
		}catch(InterruptedException e){}
		
	}
	
	public synchronized int[] solicitarCartero(){
		
		notify();
		
		envio[0] = nombreDepartamento;
		envio[1] = mensaje;
		
		return envio;
	}

	public void start() {
		// TODO Auto-generated method stub
		
	}

	public void interrupt() {
	
		System.out.println("El departamento "+ this.letraDepartamento +" ha sido destruido.");
	
	}

	public void join() {
		// TODO Auto-generated method stub
		
	}	
	
}