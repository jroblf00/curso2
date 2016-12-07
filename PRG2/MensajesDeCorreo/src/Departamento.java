import java.util.Random;

import org.apache.commons.math3.distribution.ExponentialDistribution;
import org.apache.commons.math3.distribution.NormalDistribution;

/**
 * 
 * @author Juan Carlos Robles Fernandez
 * 
 */

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
	
	/**
	 * Constructor
	 * 
	 * Se asigna letra y cajetines al departamento.
	 * 
	 * @param letraDepartamento
	 * @param tamCajetines
	 */
	public Departamento(char letraDepartamento, int tamCajetines) {
		
		this.letraDepartamento = letraDepartamento;
		this.cajetines = new Cajetines(tamCajetines);

		System.out.println("El departamento " + this.letraDepartamento + " se ha creado.");

	}

	/**
	 * Prepara una carta y se la pasa a cartero. 
	 * Mientras no haya recibido la carta el cartero no podra preparar otra.
	 */
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
	
	/**
	 * @return el numero de mensaje que se va a enviar
	 */
	
	public int recogerCarta(){
		 
		return this.mensaje;
		
	}
	
	/**
	 * @return la letra del departamento
	 */
	public char remitente() {
		
		return this.letraDepartamento;
		
	}
	
	/**
	 * @param estado, true si hay una carta preparada, si no false
	 */
	public void setCartaEnviar (boolean estado) {
		
		this.cartaEnviar = estado;
		
	}
	
	public boolean getCartaEnviar () {
		
		return this.cartaEnviar;
		
	}
	
	/**
	 * @param estado true si se ha recibido la carta, si no false
	 */
	public void setCartaRecibida (boolean estado) {
		
		this.cartaRecibida = estado;
		
	}
	
	public boolean getCartaRecibida () {
		
		return this.cartaRecibida;
		
	}
	
	/**
	 * @return el departamento destino de la carta
	 */
	public char getDestino () {
		
		return this.departamentoAleatorio;
		
	}
	
	/**
	 * @param estado, true si hay que recoger cartas, si no false
	 */
	public void setRecogerCartas (boolean estado) {
		
		this.recogerCartas = estado;
		
	}
	
	/**
	 * Consulta a cajetines si poseen alguna carta.
	 * Si posee carta indica que hay cartas para recoger.
	 * 
	 * @return recogerCartas
	 * @throws InterruptedException
	 */
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
	
	/**
	 * Se elige un departamento aleatoriamente y se prepara la carta.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void prepararCarta() throws InterruptedException {
		
		do{
			
			randomNumber.setSeed(System.nanoTime());
			this.departamentoAleatorio = (char)randomNumber.nextInt(nDptos);	
			this.departamentoAleatorio+='A';
			
		} while(this.departamentoAleatorio == letraDepartamento);
		
		this.cartaEnviar = true;
		this.mensaje++;
		
	}
	
	/**
	 * Se pone a la espera a que un cartero le notifique.
	 * 
	 * @throws InterruptedException
	 */
	public synchronized void pedirCartero () throws InterruptedException {
		
		this.cartaRecibida = false;
		System.out.println("Departamento " + letraDepartamento + " desea enviar el mensaje " + this.mensaje + 
				" al departamento " + this.departamentoAleatorio + ".");
		Thread.sleep((long) Math.abs(distribucionNormal.sample())*10);
		this.wait();
		
	}

}