package Problem02.CrossFire;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int rows = scanner.nextInt();
        int columns = scanner.nextInt();
        scanner.nextLine();
        int[][] matrix = new int[rows][columns];
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = i * columns + j + 1;
            }
        }

        while(true) {
            String input = scanner.nextLine();
            if(input.equals("Nuke it from orbit")) {
                break;
            }

            String[] params = input.split("\\s");
            int row = Integer.parseInt(params[0]);
            int column = Integer.parseInt(params[1]);
            int radius = Integer.parseInt(params[2]);
            matrix = destroyCells(matrix, row, column, radius);
        }

        printMatrix(matrix);
    }

    private static void printMatrix(int[][] matrix) {
        int rows = matrix.length;
        for(int i = 0; i < rows; i++) {
            if(null == matrix[i]) {
                return;
            }
            int columns = matrix[i].length;
            for(int j = 0; j < columns - 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println(matrix[i][columns - 1]);
        }
    }

    private static int[][] destroyCells(int[][] matrix, int row, int column, int radius) {
        int rows = matrix.length;
        if (row + radius < 0 || row - radius > rows - 1) {
            return matrix;
        }
        int crossUp = row - radius > 0 ? row - radius : 0;
        int crossDown = row + radius < rows - 1 ? row + radius : rows - 1;
        for(int i = crossDown; i >= crossUp; i--) {
            rows = matrix.length;
            int columns = matrix[i].length;
            if(i == row) {
                if(column - radius > columns - 1 || column + radius < 0) {
                    continue;
                }
                int crossLeft = column - radius > 0 ? column - radius : 0;
                int crossRight = column + radius < columns - 1 ? column + radius : columns - 1;
                for(int j = crossLeft; j < crossLeft + columns - crossRight - 1; j++) {
                    matrix[row][j] = matrix[row][j + crossRight - crossLeft + 1];
                }

                int size = columns - (crossRight - crossLeft + 1);
                if(size == 0) {
                    for(int j = row; j < rows - 1 ; j++) {
                        matrix[j] = matrix[j + 1];
                    }

                    matrix[rows - 1] = null;
                    continue;
                }
                matrix[row] = Arrays.copyOfRange(matrix[row], 0, size);
                continue;
            }
            if(column == columns - 1 && column > 0) {
                matrix[i] = Arrays.copyOfRange(matrix[i], 0, columns - 1);
            }
            if(column == columns - 1 && column == 0) {
                for(int j = i; j < rows - 1 ; j++) {
                    matrix[j] = matrix[j + 1];
                }

                matrix[rows - 1] = null;
            }
            if(column >= 0 && column < columns - 1) {
                for(int j = column; j < columns - 1; j++) {
                    matrix[i][j] = matrix[i][j + 1];
                }

                matrix[i] = Arrays.copyOfRange(matrix[i], 0, columns - 1);
            }
        }
        int i;
        for(i = 0; i < rows; i++) {
            if(null == matrix[i]) {
                break;
            }
        }
        int[][] newMatrix = new int[i][];
        for(int j = 0; j < i; j++) {
            newMatrix[j] = matrix[j];
        }

        return newMatrix;
    }
}
