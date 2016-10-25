package Ejercicios_Curso;

import java.util.Scanner;

public class Salto_Caballo {

    private int[][] tablero = new int[8][8];
    private int[][] posiciones = new int[64][2];
    private boolean SolucionFinal;
	private int[][] SALTOS = {{2, 1}, {1, 2}, {-1, 2}, {-2, 1},
	    {-2, -1}, {-1, -2}, {1, -2}, {2, -1}};
    private int pos_x, pos_y;
    
    /**
     * Este es el constructor. Simplemente inicializa variables.
     * 
     * @param x. Coordenada X de la posicion inicial del caballo.
     * @param y. Coordenada Y de la posicion inicial del caballo.
     * @throws Exception. Solo si la posicion inicial esta fuera del tablero.
     *  
     */
    public Salto_Caballo(int x, int y) throws Exception {
   
    	if ((x < 0) || (x >= 8) || (y < 0) || (y >= 8)) 
    		             throw new ArrayIndexOutOfBoundsException();
        
        this.pos_x = x;
        this.pos_y = y;
        inicializa();
     }

    private void inicializa()
    {
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                tablero[i][j] = 0;
                posiciones[8*(i)+j][0]=-1;
                posiciones[8*(i)+j][1]=-1;
            }
        }
        tablero[pos_x][pos_y] = 1;
        posiciones[0][0]=pos_x;
        posiciones[0][1]=pos_y;
        SolucionFinal = false;    	
    }
    
    public boolean soluciona() {
        saltoCaballo(pos_x, pos_y, 2);
        return SolucionFinal;
    }
    

    /**
     * 
     * @param x. Es la coordenada x de la ultima posicion del caballo.
     * @param y. Es la coordenada y de la ultima posicion del caballo.
     * @param i. Es el numero de movimiento.
     * 
     * Este m�todo calcula el siguiente posible movimiento del caballo,
     * comprueba si es v�lido, y si lo es, lo anota en los arrays internos.
     * 
     * Tras ello, si no es una soluci�n final, se llama recursivamente a s� 
     * mismo para continuar resolviendo el problema.
     * 
     * Al volver de la recursion, si no es una solucion final, limpia el 
     * ultimo movimiento de los arrays internos y prueba con otro movimiento.
     * 
     * Devuelve exito=false si no es posible encontrar una solucion o bien
     * exito=true y los arrays tablero y posiciones rellenos con esa soluci�n
     * 
     */
    private void saltoCaballo(int x, int y, int i) {
        int siguientex, siguientey;
        int k;
        
        //k es el contador que recorre los 8 posibles movimientos en 'L' del caballo
        k = 0; 
        do {
            k++;
            
            //Calculamos la siguiente posicion del caballo
            siguientex = x + SALTOS[k - 1][0];
            siguientey = y + SALTOS[k - 1][1];
            
            //determinamos si las nuevas coordenadas son validas
            if ((siguientex >= 0) && (siguientex < 8) && (siguientey >= 0) && (siguientey < 8)
                    && (tablero[siguientex][siguientey] == 0)) {
                
            //Anotamos el movimiento en nuestros arrays internos
            	tablero[siguientex][siguientey] = i;
                posiciones[i-1][0]=siguientex;
                posiciones[i-1][1]=siguientey;
                
            //Si hemos llegado a i=64 es que ya hemos hecho el ultimo movimiento,
            // en ese caso, 
                if (i < 64) {
                    saltoCaballo(siguientex, siguientey, i + 1);

                    // se analiza si se ha completado la soluci�n
                    if (!SolucionFinal) { 
                    	    // Si salimos de la recursion y no es una solucion final,
                    		// borramos la anotacion
                    	
                    		tablero[siguientex][siguientey] = 0;
                    		posiciones[i-1][0]=-1;
                    		posiciones[i-1][1]=-1;
                    }
                } 
                else 
                {
                    SolucionFinal = true; // tablero cubierto
                }
            }
        } while ((k < 8) && !SolucionFinal);
    }
    
    /**
     * Este metodo muestra por pantalla los pasos del caballo.
     */
    void mostrarSolucion() {

    	System.out.println("Tablero:");
        for (int i = 0; i < 8; i++) {
            for (int j = 0; j < 8; j++) {
                System.out.print(tablero[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
        System.out.println();
        System.out.println("Movimientos:");
        
        for (int i = 0; i < 64; i++) {
                System.out.print("{"+ new Integer(posiciones[i][0]+1).toString() + 
                		"," + new Integer (posiciones[i][1]+1).toString() + "},");
        }
        System.out.println();
    }    
    
    public static void main(String[] args) {
        int x, y;
        
        //Por la entrada estandar ponemos la casilla inicial. 
        //Para la posicion inicial 0,0 intenta mas de 8 millones de movimientos hasta
        //encontrar una posible soluci�n.
        Scanner s=new Scanner(System.in);
        System.out.println("Posicion inicial del caballo. ");
        System.out.print(" x = ");
        x=s.nextInt();
        System.out.print(" y = ");
        y=s.nextInt();
        
        Salto_Caballo salto_caballo;
		try {
			salto_caballo = new Salto_Caballo(x, y);
			if (salto_caballo.soluciona()) 
					salto_caballo.mostrarSolucion();
			else
				System.out.println("Desde esa posicion inicial "
						+ "no es posible solucionar el problema");
			
		} catch (Exception e) {
			e.printStackTrace();
		}
        
    }
}
