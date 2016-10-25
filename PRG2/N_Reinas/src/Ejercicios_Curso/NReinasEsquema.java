package Ejercicios_Curso;

/**
 * Implementa en Java la soluci�n al problema de las n reinas dado en los apuntes de clase.
 */
import java.util.ArrayList;

public class NReinasEsquema {

/**
 * almacena el tama�o del tablero. Eso determina el n�mero de reinas a colocar.
 */
	int tam;
 
 NReinasEsquema(int n){
     tam = n;
    }
 
 /**
  * Devuelve verdadero si y s�lo si hay conflicto por columna o por diagonal entre la reina colocada en la fila k de tab con cualquier
  * otra reina colocada en las filas 0 a k-1.
  * @param tab la posici�n de las reinas en columnas hasta la fila k
  * @param k fila de la reina a evaluar con las reinas anteriores
  * @return true hay conflicto entre el valor tab[k] y cualquier tab[i] con i < k por fila o por diagonal, false si no lo hay.
  */
 public boolean hayConflicto (int[] tab,int k){
        int i = 0;
        while (i < k){
            if (tab[i] == tab[k] || Math.abs(tab[i]-tab[k]) == k-i)
                return true;
            i++;
        }
        return false;            
     }
 
 /**
  * Devuelve todas las soluciones posibles al problema de las n reinas con tam=n
  * @param soluciones Almacena las soluciones encontradas.
  */
 public void reinas (ArrayList<int[]> soluciones){
     int[] solucion = new int[tam];
     for (int i=0; i<tam;i++)
         solucion[i] = -1;
     
     reinasVueltaAtras(solucion,0,soluciones);
  
     }
 /**
  * A�ade a sols todas las soluciones encontradas al problema de las n-reinas a partir de la soluci�n parcial dada en sol hasta la 
  * posici�n i-1. 
  * @param sol soluci�n parcial construida hasta el momento. Precondici�n: !hayConflcito(sol,fila-1)
  * @param fila fila que toca poner una nueva reina. Precondici�n: 0 <= fila < tam-1 
  * @param sols
  */
 private void reinasVueltaAtras(int[] sol, int fila, ArrayList<int[]> sols){
     for (int i=0; i<tam; i++){
         sol[fila] = i;
         if (!hayConflicto(sol,fila))
             if (fila==tam-1){
                 int[] nuevaSol = new int[tam];
                 for (int j=0; j<tam;j++)
                     nuevaSol[j] = sol[j];
                 sols.add(nuevaSol);
             }
            else
                 reinasVueltaAtras(sol,fila+1,sols);
     }
 }
 
 public static void main (String[] args){
     int n=4; //Probamos con 4 por defecto
     if (args.length > 0)
         n = Integer.parseInt(args[0]);
     
     NReinasEsquema nr = new NReinasEsquema(n);
     ArrayList<int[]> soluciones = new ArrayList<int[]>();
     
     nr.reinas(soluciones);
     System.out.println(nr.toStringSoluciones(soluciones));
 }

    public String toStringSoluciones(ArrayList<int[]> arrayList) {
        StringBuffer sal = new StringBuffer();
        for (int[] sol:arrayList){
            sal.append("(");
            for (int i=0; i<tam-1; i++){
                sal.append(sol[i]);
                sal.append(",");
            }
            sal.append(sol[tam-1]);
            sal.append(")\n");
        }
        return sal.toString();
    }
}
