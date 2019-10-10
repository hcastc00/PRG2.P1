package prg2;

import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.Scanner;
public class Main{

	public static void main(String[] args){
		Scanner sc = new Scanner(System.in);

		//Realizamos todo dentro de un try/catch que manda entrada invalida cuando se lanza cualquier excepcion
		try {

			int size = sc.nextInt();

			//Comprueba que se introduce un num prositivo
			if (size <=0) {
				entradaInvalida();
			}

			//Si el num es positivo crea una matriz de esas dimensiones y procede a llamr al metodo que la lee
			else {
				int[][] mat = new int[size][size]; 
				Scanner sc1 = new Scanner(System.in);
				leerRec(size,mat,sc1,0,0);
				sc1.close();
				
				//temp
				print2D(mat);
				
				int[][] sqrt = new int[size][size];
				int[][] sqrtTrans = new int[size][size];
				matToSqrt(size,mat,sqrt,0,0);
				transRec(sqrt,sqrtTrans, size,0,0);
				
				//temp
				print2D(sqrt);
				print2D(sqrtTrans);
				
				if(isSameMat(sqrt,sqrtTrans,size,0,0)) {
					System.out.println("SILOCO");
				}
				else{
					System.out.println("NOLOCO");
				}
				
			}
		} catch (Exception e) {
			entradaInvalida();
		}
	}


	/*
	 * Escribe los num entroducidos por la entrada estandar en una matriz de una tamaño dado
	 */
	private static void leerRec(int tam, int[][] input, Scanner scan, int x, int y) {
		
		//TODO filtrar entrada

		if (y < tam) {
			if (x < tam) {
				input[y][x] = scan.nextInt();
				leerRec(tam, input, scan, x + 1, y);
				

			} else {
				x = 0;
				leerRec(tam, input, scan, x, y + 1);
			} 
		}

	}

	
private static void matToSqrt(int tam, int[][] input, int[][] sqrt, int x, int y) {

		if (y < tam) {
			if (x < tam) {
				sqrt[y][x] = sqrtRec(input[x][y],1);
				matToSqrt(tam, input, sqrt, x + 1, y);

			} else {
				x = 0;
				matToSqrt(tam, input, sqrt, x , y+1);
			} 
		}

	}

	
	private static void entradaInvalida() {
		System.out.println("Entrada invalida");
		System.exit(-1);
	}

	private static void transRec(int [][] mat, int[][] trans, int tam, int x, int y){

		if (y < tam) {
			if (x < tam) {
				trans[x][y] = mat[y][x];
				transRec(mat,trans, tam,x+1, y);

			} else {
				x = 0;
				transRec(mat, trans, tam, x, y+1);
			} 
		}

		
	}

	private static int sqrtRec(int num, int i) {
		if ((i*i) < num) {
			i++;
			return(sqrtRec(num,i));
		}

		else {
			if ((i*i)==num) {
				return i;
			}
			else {
				i--;
				return i;
			}
		}
	}
	
	private static boolean isSameMat(int[][] mat1, int[][] mat2, int tam,int  x, int y) {
		boolean out = true;
		
		if (y < tam) {
			if (x < tam) {
				if(mat1[x][y]!=mat2[x][y]) {
					out = false;
				}
				isSameMat(mat1,mat2, tam,x+1, y);

			} else {
				x = 0;
				isSameMat(mat1, mat2, tam, x, y+1);
			}
		}
		
		return out;
		
	}
	
	//TEMPORAL
	public static void print2D(int mat[][]) 
    { 
        // Loop through all rows 
        for (int[] row : mat) {
  
            // converting each row as string 
            // and then printing in a separate line 
            System.out.println(Arrays.toString(row)); 
        }
        System.out.println("\n");
    } 
  
	
}
