import java.util.Random;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

class Departamento extends Thread {

	public static NormalDistribution distribucionNormal;
	public static ExponentialDistribution distribucionExponencial;
	public static int nDptos;

	public Cajetines cajetines;
	private char letraDepartamento;
	private boolean cartaEnviar = false;
	private boolean cartaRecibida = true;
	private boolean recogerCartas = false;
	private int mensaje = 0;
	private char departamentoAleatorio;
	private Random randomNumber = new Random();
	
	public Departamento(char letraDepartamento, int tamCajetines) {
		
		this.letraDepartamento = letraDepartamento;
		this.cajetines = new Cajetines(tamCajetines);

		System.out.println("El departamento " + this.letraDepartamento + " se ha creado.");

	}

	public void run() {

		try {

			while (true) {
				
				synchronized (this) {
					
					while(cartaRecibida){
						
						prepararCarta();
						pedirCartero();
						
					}
					
				}
				
			}

		} catch (InterruptedException e) {

			System.out.println("El departamento " + this.letraDepartamento + " ha sido destruido.");

		}

	}

	public synchronized void notificar() {

		notifyAll();

	}
	
	public int recogerCarta(){
		 
		return this.mensaje;
		
	}
	
	public char remitente() {
		
		return this.letraDepartamento;
		
	}
	
	public void setCartaEnviar (boolean estado) {
		
		this.cartaEnviar = estado;
		
	}
	
	public boolean getCartaEnviar () {
		
		return this.cartaEnviar;
		
	}
	
	public void setCartaRecibida (boolean estado) {
		
		this.cartaRecibida = estado;
		
	}
	
	public boolean getCartaRecibida () {
		
		return this.cartaRecibida;
		
	}
	
	public char getDestino () {
		
		return this.departamentoAleatorio;
		
	}
	
	public void setRecogerCartas (boolean estado) {
		
		this.recogerCartas = estado;
		
	}
	
	public synchronized boolean getRecogerCartas () throws InterruptedException {
		
		Thread.sleep((long)Math.abs(distribucionExponencial.sample())*10);
		
		if(this.cajetines.existeCarta()) {
			
			this.recogerCartas = true;
			
		}
		
		else {
			
			this.recogerCartas = false;
			
		}
		
		return this.recogerCartas;
		
	}
	
	public synchronized void prepararCarta() throws InterruptedException {
		
		do{
			
			randomNumber.setSeed(System.nanoTime());
			this.departamentoAleatorio = (char)randomNumber.nextInt(nDptos);	
			this.departamentoAleatorio+='A';
			
		} while(this.departamentoAleatorio == letraDepartamento);
		
		this.cartaEnviar = true;
		this.mensaje++;
		
	}
	
	public synchronized void pedirCartero () throws InterruptedException {
		
		this.cartaRecibida = false;
		System.out.println("Departamento " + letraDepartamento + " desea enviar el mensaje " + this.mensaje + 
				" al departamento " + this.departamentoAleatorio + ".");
		Thread.sleep((long) Math.abs(distribucionNormal.sample())*10);
		this.wait();
		
	}

}