package prg2;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		Scanner sc = new Scanner(System.in);
		String inputString = new String(); // String necesaria para la lectura de la matriz

		// Realizamos todo dentro de un try/catch que manda entrada invalida cuando se
		// lanza calquier excepcion
		try {

			int size = sc.nextInt();
			sc.nextLine(); // Limpia buffer
			// Comprueba que se introduce un num prositivo
			if (size <= 0) {
				entradaInvalida();
			}

			// Si el num es positivo crea una matriz de esas dimensiones y procede a llamar
			// al metodo de lectura de la matriz
			else {
				int[][] inputMatrix = new int[size][size];
				readToMat(size, inputMatrix, inputString, sc, 0, 0, null);
				sc.close();

				int[][] sqrtMatrix = new int[size][size];
				int[][] sqrtTrans = new int[size][size];

				matToSqrt(size, inputMatrix, sqrtMatrix, 0, 0);
				transRec(size, sqrtMatrix, sqrtTrans, 0, 0);

				if (isSameMat(size, sqrtMatrix, sqrtTrans, 0, 0)) {
					System.out.println("La matriz de tamaño " + size + " es de raíz entera simétrica.");
				} else {
					System.out.println("La matriz de tamaño " + size + " no es de raíz entera simétrica.");
				}

			}
		} catch (Exception e) {
			entradaInvalida();
		}
	}

	/*
	 * Lee una matriz de forma recusrsiva filtrando la entrada como manda el
	 * enunciado.
	 */
	private static void readToMat(int size, int[][] matrix, String input, Scanner scan, int x, int y,
			String[] normalized) {
		if (y < size) {

			if (x == 0) {
				input = scan.nextLine();
				input = input.replaceAll(" +", ";");
				normalized = input.split(";");

				if (normalized.length != size) {
					entradaInvalida();
				}
			}

			if (x < size) {
				matrix[y][x] = Integer.parseInt(normalized[x]);
				if (matrix[y][x] < 0) {
					entradaInvalida();
				}
				readToMat(size, matrix, input, scan, x + 1, y, normalized);

			} else {
				x = 0;
				readToMat(size, matrix, input, scan, x, y + 1, normalized);
			}
		}
	}

	/*
	 * Obtiene de forma recursiva la matriz de raices enteras a partir de una dada
	 */
	private static void matToSqrt(int size, int[][] input, int[][] sqrt, int x, int y) {

		if (y < size) {
			if (x < size) {
				sqrt[y][x] = sqrtRec(input[y][x], 1);
				matToSqrt(size, input, sqrt, x + 1, y);

			} else {
				x = 0;
				matToSqrt(size, input, sqrt, x, y + 1);
			}
		}
	}

	/*
	 * Obtiene de forma recursiva la matriz transpuesta a partir de una dada
	 */
	private static void transRec(int size, int[][] input, int[][] trans, int x, int y) {

		if (y < size) {
			if (x < size) {
				trans[x][y] = input[y][x];
				transRec(size, input, trans, x + 1, y);

			} else {
				x = 0;
				transRec(size, input, trans, x, y + 1);
			}
		}

	}

	/*
	 * Comprueba de forma recursiva si dos matrices son iguales
	 */
	private static boolean isSameMat(int size, int[][] m, int[][] m2, int i, int j) {
		if (i < size) {
			if (j >= size) {
				return isSameMat(size, m, m2, ++i, 0);
			} else {
				if ((m[i][j] != m2[i][j])) {
					return false;
				} else {
					return isSameMat(size, m, m2, i, ++j);
				}
			}
		}
		return true;
	}

	/*
	 * Obtiene la raiz entera de un numero dado
	 */
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

	/*
	 * Devuelve por la entrada estandar "Entrada invalida" y termina la ejecucion
	 */
	private static void entradaInvalida() {
		System.out.println("Entrada inválida.");
		System.exit(-1);
	}
}
