import java.util.Random;

class Cartero extends Thread {

	Departamento[] departamentos;
	Cajetin[] cartasRecogidas;
	int numeroCartero;
	int mensaje;
	char remitente;
	char destino;
	int posDep;
	int cantidadCartasRecogidas;

	public Cartero(int numeroCartero, Departamento[] departamento) {

		this.departamentos = departamento;
		this.numeroCartero = numeroCartero;

		System.out.println("El cartero " + numeroCartero + " se ha creado.");

	}

	public void run() {

		try {

			while (departamentos[0].isAlive()) {
				
				synchronized (departamentos) {

					System.out.println("El cartero " + numeroCartero + " está descansando.");
					
					this.posDep = comprobarEnvio();
					if (this.posDep != -1) { 
						
						this.departamentos[this.posDep].setCartaEnviar(false);
						this.departamentos[this.posDep].notificar();
						this.remitente = departamentos[this.posDep].remitente();
						this.mensaje = departamentos[this.posDep].recogerCarta();
						this.destino = departamentos[this.posDep].getDestino();
						this.departamentos[this.posDep].setCartaRecibida(true);
						
						while (!this.departamentos[this.destino-'A'].cajetines.existeCajetinVacio()){
							
							Thread.sleep(100);
							
						}
						
						this.departamentos[this.destino-'A'].cajetines.insetarCarta(this.remitente, 
								this.destino, this.mensaje);
						System.out.println("El cartero " + numeroCartero + " deposita el mensaje /Mensaje " + this.mensaje
								+ " de " + this.remitente + " a " + this.destino + "/ en el cajetín.");
						
						System.out.println("El cartero " + numeroCartero + " está descansando.");
					}
					
					this.posDep = comprobarRecogerCarta();
					if (this.posDep != -1) {
						
						this.departamentos[this.posDep].setRecogerCartas(false);
						this.cantidadCartasRecogidas = this.departamentos[this.posDep].cajetines.cantidadCartas();
						this.cartasRecogidas = this.departamentos[this.posDep].cajetines.recogerCarta();
						this.departamentos[this.posDep].cajetines.eliminarCartas();
						
						System.out.println("El cartero " + this.numeroCartero + " recoge los mensajes del cajetín del departamento "
								 + (char)(this.posDep+'A') +".");
						
						for (int i=0; i<this.cantidadCartasRecogidas; i++) {
							
							System.out.println("El departamento " + this.cartasRecogidas[i].getDestino() +
									" recibe el mensaje /Mensaje " + this.cartasRecogidas[i].getMensaje() +
									" de " + this.cartasRecogidas[i].getOrigen() + " a " + this.cartasRecogidas[i].getDestino()
									+ "/ del departamento " + this.cartasRecogidas[i].getOrigen() + ".");
							
						}
						
					}
				
				}
			}
		}

		catch (InterruptedException e) {}
		
		System.out.println("El cartero " + this.numeroCartero + " ha sido destruido.");
		
	}

	public synchronized int comprobarEnvio() throws InterruptedException {

		int pos = 0;

		do {
			
			Random randomNumber = new Random();
			randomNumber.setSeed(System.nanoTime());
			pos  = randomNumber.nextInt(departamentos.length);

			if (pos >= departamentos.length) {
				pos = -1;
				break;
			}
			
		} while (!departamentos[pos].getCartaEnviar());

		return pos;
	}

	public synchronized int comprobarRecogerCarta() throws InterruptedException {

		int pos = 0;
		
		while (!departamentos[pos].getRecogerCartas()) {

			pos++;

			if (pos >= departamentos.length) {
				pos = -1;
				break;
			}
		}

		return pos;

	}

}