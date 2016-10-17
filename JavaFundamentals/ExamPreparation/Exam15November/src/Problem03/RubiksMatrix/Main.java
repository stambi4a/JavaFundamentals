package Problem03.RubiksMatrix;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        playWithRubicCube();
    }

    private static void playWithRubicCube() {
        Scanner scanner = new Scanner(System.in);
        String[] params = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(params[0]);
        int columns = Integer.parseInt(params[1]);
        int[][] rubicMatrix = new int[rows][columns];
        for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                rubicMatrix[i][j] = (i * columns) + j + 1;
            }
        }

        int countLines = Integer.parseInt(scanner.nextLine());
        int index = 1;
        while(index <= countLines) {
            String input = scanner.nextLine();
            params = input.split(" ");
            moveVector(rubicMatrix, params);

            index++;
        }

        rearrangeRubicMatrix(rubicMatrix);


    }

    private static void rearrangeRubicMatrix(int[][] rubicMatrix) {
        int rows = rubicMatrix.length;
        int columns = rubicMatrix[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                int value = rubicMatrix[i][j];
                int properValue = i * rows + j + 1;
                if(value != properValue) {
                    for(int k = 0; k < rows; k++) {
                        for (int l = 0; l < columns; l++) {
                            if(i != k || l != j) {
                                int local = rubicMatrix[k][l];
                                if(local == properValue) {
                                    int swap = value;
                                    rubicMatrix[i][j] = properValue;
                                    rubicMatrix[k][l] = swap;
                                    System.out.printf("Swap (%d, %d) with (%d, %d)", i, j, k, l);
                                    System.out.println();
                                    break;
                                }
                            }
                        }

                        if(rubicMatrix[i][j] == properValue) {
                            break;
                        }
                    }
                } else {
                    System.out.println("No swap required");
                }
            }
        }
    }

    private static void printRubicMatrix (int[][] rubicMatrix) {
        int rows = rubicMatrix.length;
        int columns = rubicMatrix[0].length;
         for (int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                System.out.print(rubicMatrix[i][j] + " ");
            }
            System.out.println();
        }
    }

    private static void moveRowRight(int[][] rubicMatrix, int moves, int row) {
        int columns = rubicMatrix[0].length;
        int step = moves%columns;
        if(step == 0) {
            return;
        }

        int[] swap = new int[step];
        for(int i = step - 1; i >= 0; i--) {
            swap[i] = rubicMatrix[row][columns - (step - i)];
        }

        for(int i = columns - 1; i >= step; i--) {
            rubicMatrix[row][i] = rubicMatrix[row][i - step];
        }

        for(int i = 0; i < step; i++) {
            rubicMatrix[row][i] = swap[i];
        }
    }

    private static void moveRowLeft(int[][] rubicMatrix, int moves, int row) {
        int columns = rubicMatrix[0].length;
        int step = moves%columns;
        if(step == 0) {
            return;
        }

        int[] swap = new int[step];
        for(int i = 0; i < step; i++) {
            swap[i] = rubicMatrix[row][i];
        }

        for(int i = 0; i < columns - step; i++) {
            rubicMatrix[row][i] = rubicMatrix[row][i + step];
        }

        for(int i = 0; i < step; i++) {
            rubicMatrix[row][columns - step + i] = swap[i];
        }
    }

    private static void moveColumnUp(int[][] rubicMatrix, int moves, int column) {
        int rows = rubicMatrix[0].length;
        int step = moves%rows;
        if(step == 0) {
            return;
        }

        int[] swap = new int[step];
        for(int i = step - 1; i >= 0; i--) {
            swap[i] = rubicMatrix[i][column];
        }

        for(int i = 0; i < rows - step; i++) {
            rubicMatrix[i][column] = rubicMatrix[i + step][column];
        }

        for(int i = 0; i < step; i++) {
            rubicMatrix[rows - step + i][column] = swap[i];
        }
    }

    private static void moveColumnDown(int[][] rubicMatrix, int moves, int column) {
        int rows = rubicMatrix[0].length;
        int step = moves%rows;
        if(step == 0) {
            return;
        }

        int[] swap = new int[step];
        for(int i = 0; i < step; i++) {
            swap[i] = rubicMatrix[rows - (step - i)][column];
        }

        for(int i = rows - 1; i >= step; i--) {
            rubicMatrix[i][column] = rubicMatrix[i - step][column];
        }

        for(int i = 0; i < step; i++) {
            rubicMatrix[i][column] = swap[i];
        }
    }

    private static void moveVector(int[][] rubicMatrix, String[] params) {
        int vector = Integer.parseInt(params[0]);
        int moves = Integer.parseInt(params[2]);
        switch(params[1]) {
            case "right" : {
                moveRowRight(rubicMatrix, moves, vector);
            }

            break;

            case "left" : {
                moveRowLeft(rubicMatrix, moves, vector);
            }

            break;

            case "up" : {
                moveColumnUp(rubicMatrix, moves, vector);
            }

            break;

            case "down" : {
                moveColumnDown(rubicMatrix, moves, vector);
            }

            break;
        }
    }
}
