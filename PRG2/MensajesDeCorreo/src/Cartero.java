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
	
	
	/**
	 * Constructor
	 * 
	 * se asignan los departamentos y numero de cartero
	 * 
	 * @param numeroCartero
	 * @param departamento
	 */
	public Cartero(int numeroCartero, Departamento[] departamento) {

		this.departamentos = departamento;
		this.numeroCartero = numeroCartero;

		System.out.println("El cartero " + numeroCartero + " se ha creado.");

	}
	
	/**
	 * Comprueba si hay alguna carta disponible.
	 * En caso de que haya manda una notificacion para recoger la carta y los datos
	 * de remitente y destinatario.
	 * Coloca la carta en un cajetin vacio del destinatario, en caso de que este el cajetin
	 * lleno, espera hasta que este vacio.
	 * 
	 * Comprueba si hay ordenes de recoger las cartas del cajetin.
	 * Si hay recoge todas las cartas y se las cede a su departamento.
	 */
	public void run() {

		try {

			while (departamentos[0].isAlive()) {
				
				synchronized (departamentos) {

					System.out.println("El cartero " + numeroCartero + " est� descansando.");
					
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
								+ " de " + this.remitente + " a " + this.destino + "/ en el cajet�n.");
						
						System.out.println("El cartero " + numeroCartero + " est� descansando.");
					}
					
					this.posDep = comprobarRecogerCarta();
					if (this.posDep != -1) {
						
						this.departamentos[this.posDep].setRecogerCartas(false);
						this.cantidadCartasRecogidas = this.departamentos[this.posDep].cajetines.cantidadCartas();
						this.cartasRecogidas = this.departamentos[this.posDep].cajetines.recogerCarta();
						this.departamentos[this.posDep].cajetines.eliminarCartas();
						
						System.out.println("El cartero " + this.numeroCartero + " recoge los mensajes del cajet�n del departamento "
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

	/**
	 * Comprueba si un departamento desea enviar una carta.
	 * 
	 * @return el departamento que desea enviar la carta
	 * @throws InterruptedException
	 */
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

	/**
	 * Comprueba si hay que recoger las cartas.
	 * 
	 * @return el departamento que desea recoger las cartas
	 * @throws InterruptedException
	 */
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