package prg2;

import java.util.Arrays;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);

		// Realizamos todo dentro de un try/catch que manda entrada invalida cuando se
		// lanza cualquier excepcion
		try {

			int size = sc.nextInt();
			sc.nextLine();
			// Comprueba que se introduce un num prositivo
			if (size <= 0) {
				entradaInvalida();
			}

			// Si el num es positivo crea una matriz de esas dimensiones y procede a llamr
			// al metodo que la lee
			else {
				int[][] mat = new int[size][size];
				String in = new String();
				readToMat(size, mat, in, sc, 0, 0, null);
				sc.close();
				System.out.println("Cerrao scanner");
				// temp
				print2D(mat);

				int[][] sqrt = new int[size][size];
				int[][] sqrtTrans = new int[size][size];
				matToSqrt(size, mat, sqrt, 0, 0);
				transRec(sqrt, sqrtTrans, size, 0, 0);

				// temp
				print2D(sqrt);
				print2D(sqrtTrans);

				if (isSame(sqrt, sqrtTrans, size, 0, 0)) {
					System.out.println("La matriz de tamaño " + size + " es de raíz entera simétrica.");
				} else {
					System.out.println("NOLOCO");
				}

			}
		} catch (Exception e) {
			entradaInvalida();
		}
	}

	private static void readToMat(int tam, int[][] mat, String input, Scanner scan, int x, int y, String[] out) {
		System.out.println("x:" + x + " y:" + y);

		if (y < tam) {

			if (x == 0) {
				System.out.println("SAPE");
				input = scan.nextLine();
				System.out.println(input);
				input = input.replaceAll(" +", "-");
				System.out.println(input);
				out = input.split("-");

				if (out.length != tam) {
					entradaInvalida();
				}
			}

			if (x < tam) {
				mat[y][x] = Integer.parseInt(out[x]);
				readToMat(tam, mat, input, scan, x + 1, y, out);

			} else {
				x = 0;
				readToMat(tam, mat, input, scan, x, y + 1, out);
			}
		}
	}

	/*
	 * Escribe los num entroducidos por la entrada estandar en una matriz de una
	 * tamaño dado
	 */
	private static void leerRec(int tam, int[][] input, Scanner scan, int x, int y) {
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
				sqrt[y][x] = sqrtRec(input[x][y], 1);
				matToSqrt(tam, input, sqrt, x + 1, y);

			} else {
				x = 0;
				matToSqrt(tam, input, sqrt, x, y + 1);
			}
		}
	}

	private static void transRec(int[][] mat, int[][] trans, int tam, int x, int y) {

		if (y < tam) {
			if (x < tam) {
				trans[x][y] = mat[y][x];
				transRec(mat, trans, tam, x + 1, y);

			} else {
				x = 0;
				transRec(mat, trans, tam, x, y + 1);
			}
		}

	}

	private static int sqrtRec(int num, int i) {
		if ((i * i) < num) {
			i++;
			return (sqrtRec(num, i));
		}

		else {
			if ((i * i) == num) {
				return i;
			} else {
				i--;
				return i;
			}
		}
	}

	
	
	private static boolean isSame(int[][] m,int[][] m2,int tam, int i, int j) {
        if (i < m.length) {
            if (j >= m.length) {
                return isSame(m,m2,tam, ++i, 0);
            } else {
                if ((m[i][j] != m2[i][j])) {
                    return false;
                } else {
                    return isSame(m,m2,tam, i, ++j);
                }
            }
        }
        return true;
    }
	
	
	
	
	
	
	/*
	private static boolean isSameMat(int[][] mat1, int[][] mat2, int tam, int x, int y) {

		if (y < tam) {
			if (x < tam) {
				if (mat1[x][y] != mat2[x][y]) {
					 return false;
				}
				return(isSameMat(mat1, mat2, tam, x + 1, y));

			} else {
				x = 0;
				isSameMat(mat1, mat2, tam, x, y + 1);
			}
		}

		return true;

	}
	*/

	private static void entradaInvalida() {
		System.out.println("Entrada inválida.");
		System.exit(-1);
	}

	// TEMPORAL
	public static void print2D(int mat[][]) {
		// Loop through all rows
		for (int[] row : mat) {

			// converting each row as string
			// and then printing in a separate line
			System.out.println(Arrays.toString(row));
		}
		System.out.println("\n");
	}

}
