package Problem02.LargestRectangle;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static int largestMatrixRows = 0;
    private static int largestMatrixColumns = 0;
    private static int largestMatrixRow = 0;
    private static int largestMatrixColumn = 0;
    public static void main(String[] args) {
        findLargestRectangle();
    }

    private static void printLargestRectangle(String[][]matrix) {
        int matrRows = matrix.length;
        int matrColumns = matrix[0].length;
        for(int row = 0; row < matrRows; row++) {
            for(int column = 0; column < matrColumns; column++)
            {
                boolean isElementFromLargestRectangle = ((row == largestMatrixRow || row == largestMatrixRow + largestMatrixRows - 1) &&
                        (column >= largestMatrixColumn && column <= largestMatrixColumn + largestMatrixColumns - 1)) ||

                        ((column == largestMatrixColumn + largestMatrixColumns - 1 || column == largestMatrixColumn) &&
                        (row >= largestMatrixRow && row <= largestMatrixRow + largestMatrixRows - 1));

                if(isElementFromLargestRectangle) {
                    matrix[row][column] = "[" + matrix[row][column] + "]";
                }

                System.out.printf("%5s", matrix[row][column]);
            }
            System.out.println();
        }
    }
    private static void findLargestRectangle() {
        Scanner scanner = new Scanner(System.in);
        List<String> input = new ArrayList<>();
        while(true) {
            String line = scanner.nextLine();
            if(line.equals("END")) {
                break;
            }

            input.add(line);
        }

        String[][] matrix = new String[input.size()][];
        for(int i = 0; i < input.size(); i++)
        {
            matrix[i] = input.get(i).replaceAll(",", " ").trim().split("\\s+");
        }

        int rows = matrix.length;
        int columns = matrix[0].length;

        getLargestRectangle(rows, columns, matrix);
        printLargestRectangle(matrix);
    }

    private static void getLargestRectangle(int rows, int columns, String[][] matrix) {
        int matrRows = matrix.length;
        int matrColumns = matrix[0].length;

        int area = rows * columns;
        if(area <= largestMatrixColumns * largestMatrixRows) {
            return;
        }

        for(int row = 0; row <= matrRows - rows; row++) {
            for(int column = 0; column <= matrColumns - columns; column++)
            {
                if(checkIfSubmatrixIsRectangle(row, column, rows, columns, matrix)) {
                    return;
                }
            }
        }

        if(columns >= rows) {
            getLargestRectangle(rows - 1, columns, matrix);
            getLargestRectangle(rows, columns - 1, matrix);
        } else {
            getLargestRectangle(rows, columns - 1, matrix);
            getLargestRectangle(rows - 1, columns, matrix);
        }

        getLargestRectangle(rows - 1, columns - 1, matrix);
    }

    private static boolean checkIfSubmatrixIsRectangle(int row, int col, int rows, int columns, String[][] matrix) {
        String compare = matrix[row][col];
        int rowIndex = row + 1;
        int colIndex = col;
        while(rowIndex < row + rows) {
            if(!matrix[rowIndex][colIndex].equals(compare)) {
                return false;
            }

            rowIndex++;
        }

        rowIndex = row;
        colIndex = col + columns - 1;
        while(rowIndex < row + rows) {
            if(!matrix[rowIndex][colIndex].equals(compare)) {
                return false;
            }

            rowIndex++;
        }

        rowIndex = row;
        colIndex = col + 1;
        while(colIndex < col + columns) {
            if(!matrix[rowIndex][colIndex].equals(compare)) {
                return false;
            }

            colIndex++;
        }

        rowIndex = row + rows - 1;
        colIndex = col;
        while(colIndex < col + columns) {
            if(!matrix[rowIndex][colIndex].equals(compare)) {
                return false;
            }

            colIndex++;
        }

        largestMatrixRows = rows;
        largestMatrixColumns = columns;
        largestMatrixRow = row;
        largestMatrixColumn = col;

        return true;
    }
}
