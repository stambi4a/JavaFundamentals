package Problem02;

import java.util.Arrays;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split("\\s+");
        int rows = Integer.parseInt(params[0]);
        int columns = Integer.parseInt(params[1]);
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

            params = input.split("\\s+");
            int row = Integer.parseInt(params[0]);
            int column = Integer.parseInt(params[1]);
            int radius = Integer.parseInt(params[2]);
            long crossUp = row - radius > 0 ? row - radius : 0;
            long crossDown = row + radius < rows - 1 ? row + radius : rows - 1;
            for(int i = (int)crossDown; i > row; i--) {
                if(i == - 1) {
                    break;
                }
                columns = matrix[i].length;
                if(column > columns - 1 || column < 0) {
                    continue;
                }

                 else if(columns == 1 && column == 0) {
                    for (int k = i + 1; k < rows; k++) {
                        matrix[row] = matrix[row + 1];
                    }

                    rows--;
                }

                else if(columns > 1) {
                    for(int j = column; j < columns - 1; j++) {
                        move(i, j, matrix);
                    }

                    matrix[i] = Arrays.copyOfRange(matrix[i], 0, columns - 1);
                }
            }

            if(row >= 0 && row < rows) {
                columns = matrix[row].length;
                long crossLeft = column - radius > 0 ? column - radius : 0;
                long crossRight = column + radius < columns - 1 ? column + radius: columns - 1 ;
                if(crossLeft == 0 && crossRight == columns - 1) {
                    for (int k = row + 1; k < rows; k++) {
                        matrix[row] = matrix[row + 1];
                    }

                    rows--;
                }
                else if(crossRight == columns - 1) {
                    matrix[row] = Arrays.copyOfRange(matrix[row], 0, (int)crossLeft);
                } else if(crossRight >= 0){
                    crossRight++;
                    while(crossRight < columns) {
                        matrix[row][(int)crossLeft] = matrix[row][(int)crossRight];
                        crossLeft++;
                        crossRight++;
                    }

                    matrix[row] = Arrays.copyOfRange(matrix[row], 0, (int)crossLeft);
                }
            }




            for(int i = row - 1; i >= crossUp; i--) {
                columns = matrix[i].length;
                if(column > columns - 1 || column < 0) {
                    continue;
                }

                else if(columns == 1 && column == 0) {
                    for (int k = i + 1; k < rows; k++) {
                        matrix[row] = matrix[row + 1];
                    }

                    rows--;
                }

                else if(columns > 1) {
                    for(int j = column; j < columns - 1; j++) {
                        move(i, j, matrix);
                    }

                    matrix[i] = Arrays.copyOfRange(matrix[i], 0, columns - 1);
                }
            }
        }

        printMatrix(matrix, rows);
    }

    private static void printMatrix(int[][] matrix, int rows) {
        for(int i = 0;i < rows; i++) {
            int columns = matrix[i].length;
            for(int j = 0; j < columns - 1; j++) {
                System.out.print(matrix[i][j] + " ");
            }

            System.out.println(matrix[i][columns - 1]);
        }
    }

    private static void move(int row, int column, int[][] matrix) {
        matrix[row][column] = matrix[row][column + 1];
    }
}
