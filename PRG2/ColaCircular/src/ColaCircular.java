import org.omg.IOP.TAG_MULTIPLE_COMPONENTS;

public  class ColaCircular
{
	private static  final int TAM_COLA = 10;
	private int tam_cola;
	private  int inicio = 0;	//pos primer elemento si nelems>0
	private int nelems = 0;		//pos nuevo elemento: (inicio+nlems) %TAM_COLA
	private Object[] cola;
	
	ColaCircular(int tam)
	{
		tam_cola=tam;
		cola=new Object[tam_cola];
	}
	
	ColaCircular()
	{
		tam_cola=TAM_COLA;
		cola=new Object[tam_cola];
	}
	

	public synchronized void encolar (Object item) throws InterruptedException
	{
		// 
		//
		// COMPROBAR SI LA COLA ESTA LLENA. SI LO ESTA, LLAMAR A WAIT
		// E INDICARLO POR PANTALLA
		
		if (nelems >= tam_cola) {
			
			wait();
			System.out.println("La cola esta llena");
			
		}		
		
		cola[ (inicio+nelems++) %tam_cola] = item;
		
		// 
		//
		// Notificar a todos los hilos que esten en espera
		
		notifyAll();
		
	}

	public synchronized Object desencolar() throws InterruptedException
	{
		// 
		//
		// COMPROBAR SI LA COLA ESTA VACIA. SI LO ESTA, LLAMAR A WAIT
		// E INDICARLO POR PANTALLA
		
		if (nelems == 0) {
			
			wait();
			System.out.println("Esperando porque la cola esta vacia");
			
		}
		
		Object o =  cola[inicio];
		inicio = ( inicio + 1) % tam_cola;
		nelems=nelems-1;

		//
				//
				// Notificar a todos los hilos que esten espera
		
		notifyAll();
				
		return o;
	}
	
	public static void main(String args[]){
			final int lista_numeros[]={234,543,123,123,445,123,123,765,234};
			ColaCircular c=new ColaCircular(10);
			
			for (int i=1; i<99; i++)
			{
				new acesor(c, i, lista_numeros).start();
			}
		}
	
}


class acesor extends Thread{
		int[] listanum;
		int internalVal;
		ColaCircular c;
		
		public acesor(ColaCircular n, int i, int[] listanum)
		{
			this.c=n;
			this.setName("Nuevo Hilo "+ i);
			this.listanum=listanum;
			internalVal=10000*i;
		}
		
		public void run()
		{
			//
			
			//Recorremos la lista de numeros. Dentro del bucle:
			
			//1.- Añadimos objetos Integer con el valor internalVal+listanum
			//a la Cola Circular
			
			//2.- Dormimos el numero de milisegundos marcado en listanum[i]
			
			//3.- Desencolamos un numero de la Cola.
			
			//NOTA: INDICAR POR PANTALLA LO QUE SE VA HACIENDO EN CADA MOMENTO
			
			for (int i = 0; i < listanum.length; i++ ) {
				
				try {
					int tmp = internalVal + listanum[i];
					System.out.println("El hilo "+getName()+" encola el numero "+tmp);
					c.encolar(new Integer(internalVal + listanum[i]));
					
					System.out.println("El hilo "+getName()+" dormira "+listanum[i]+" milisegundos");
					Thread.sleep(listanum[i]);
					
					Integer tmp2 = (Integer)c.desencolar();
					System.out.println("El hilo "+getName()+" desencola el numero "+tmp2);
					
				} catch (InterruptedException e) {
					
					// TODO Auto-generated catch block
					e.printStackTrace();
					
				}
				
			}
			
		}
	}
	
