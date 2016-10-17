package Problem03.DragonTrap;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
    private static final int MIN_PEOPLE_TO_ROTATE = 8;
    public static void main(String[] args) {
        trapTheDragon();
    }

    private static char[][] copyMatrix(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        char[][] original = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            System.arraycopy(matrix[i], 0, original[i], 0, columns);
        }

        return original;
    }

    private static void trapTheDragon() {
        Scanner scanner = new Scanner(System.in);
        char[][] matrix = inputMatrix(scanner);
        char[][] originalMatrix = copyMatrix(matrix);
        while(true) {
            String input = scanner.nextLine();
            if(input.equals("End")) {
                break;
            }

            String[] params = input.replaceFirst("\\(", "").replaceFirst("\\)", "").split(" ");
            rotatePeople(params, matrix);
        }

        printMatrix(matrix);
        int countChanges = countChangesInCells(matrix, originalMatrix);
        System.out.println("Symbols changed: " + countChanges);
    }

    private static void printMatrix(char[][] matrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns - 1; j++) {
                System.out.print(matrix[i][j]);
            }

            System.out.println(matrix[i][columns - 1]);
        }
    }

    private static int countChangesInCells(char[][] matrix, char[][] originalMatrix) {
        int rows = matrix.length;
        int columns = matrix[0].length;
        int countChanges = 0;
        for(int i = 0; i < rows; i++) {
            for(int j = 0; j < columns; j++) {
                if(matrix[i][j] != originalMatrix[i][j]) {
                    countChanges++;
                }
            }
        }

         return countChanges;
    }

    private static char[] getRotatingPeople(char[][] matrix, List<int[]> peopleCoordinates) {
        int length = peopleCoordinates.size();
        char[] people = new char[length];
        for(int i = 0; i < length; i++) {
            people[i] = matrix[peopleCoordinates.get(i)[0]][peopleCoordinates.get(i)[1]];
        }

        return people;
    }

  /*  private static char[] getRotatingPeopleCounterClockWise(char[][] matrix, List<int[]> peopleCoordinates) {
        int length = peopleCoordinates.size();
        char[] people = new char[length];
        for(int i = length - 1; i >= 0; i--) {
            people[length - i - 1] = matrix[peopleCoordinates.get(i)[0]][peopleCoordinates.get(i)[1]];
        }

        return people;
    }*/

    private static void rotatePeopleClockWise(char[][] matrix, List<int[]> peopleCoordinates, int countRotations) {
        int size = peopleCoordinates.size();
        char[] people = getRotatingPeople(matrix, peopleCoordinates);
        char[] originalPeople = Arrays.copyOf(people, size);
        char[] swap = Arrays.copyOfRange(people, size - countRotations, size);
        System.arraycopy(originalPeople, 0, people, countRotations , size - countRotations);
        System.arraycopy(swap, 0, people, 0, swap.length);
        for(int i = 0; i < size; i++) {
            matrix[peopleCoordinates.get(i)[0]][peopleCoordinates.get(i)[1]] = people[i];
        }
    }

    private static void rotatePeopleCounterClockWise(char[][] matrix, List<int[]> peopleCoordinates, int countRotations) {
        countRotations = -countRotations;
        int size = peopleCoordinates.size();
        char[] people = getRotatingPeople(matrix, peopleCoordinates);
        char[] originalPeople = Arrays.copyOf(people, size);
        char[] swap = Arrays.copyOfRange(people, 0, countRotations);
        System.arraycopy(originalPeople, countRotations, people, 0 , size - countRotations);
        System.arraycopy(swap, 0, people, size - countRotations, swap.length);
        for(int i = 0; i < size; i++) {
            matrix[peopleCoordinates.get(i)[0]][peopleCoordinates.get(i)[1]] = people[i];
        }
    }

    private static void rotatePeopleAroundDragon(char[][] matrix, List<int[]> peopleCoordinates, int countRotations) {
       if(countRotations > 0) {
           rotatePeopleClockWise(matrix, peopleCoordinates, countRotations);
       } else {
           rotatePeopleCounterClockWise(matrix, peopleCoordinates, countRotations);
       }
    }

    private static void rotatePeople(String[] params, char[][] matrix) {
        int row = Integer.parseInt(params[0]);
        int column = Integer.parseInt(params[1]);
        int radius = Integer.parseInt(params[2]);
        int count = Integer.parseInt(params[3]);
        List<int[]> peopleCoordinates = new ArrayList<>();
        checkCellsToTheEast(row, column, radius, matrix, peopleCoordinates);
        checkCellsToTheSouth(row, column, radius, matrix, peopleCoordinates);
        checkCellsToTheWest(row, column, radius, matrix, peopleCoordinates);
        checkCellsToTheNorth(row, column, radius, matrix, peopleCoordinates);
        int countOfPeopleToRotate = peopleCoordinates.size();
        int countRotations = countOfPeopleToRotate > 0 ? count % countOfPeopleToRotate : 0;
        int length = peopleCoordinates.size();
        if(countRotations != 0 && length > 0) {
            rotatePeopleAroundDragon(matrix, peopleCoordinates, countRotations);
        }
    }

    private static void checkCellsToTheWest(int row, int column, int radius, char[][] matrix, List<int[]> peopleCoordinates) {
        int cellRow = row + radius;
        int cellColumn = column - radius;
        int checkCount = MIN_PEOPLE_TO_ROTATE / 4 * radius;
        int rows = matrix.length;
        int columns = matrix[0].length;
        for(int i = 1; i <= checkCount ; i++) {
            if(cellRow < rows && cellColumn < columns && cellRow >= 0 && cellColumn >= 0) {
                peopleCoordinates.add(new int[] {cellRow, cellColumn});
            }

            cellRow--;
        }
    }

    private static void checkCellsToTheSouth(int row, int column, int radius, char[][] matrix, List<int[]> peopleCoordinates) {
        int cellRow = row + radius;
        int cellColumn = column + radius;
        int checkCount = MIN_PEOPLE_TO_ROTATE / 4 * radius;
        int rows = matrix.length;
        int columns = matrix[0].length;
        for(int i = 1; i <= checkCount ; i++) {
            if(cellRow < rows && cellColumn < columns && cellRow >= 0 && cellColumn >= 0) {
                peopleCoordinates.add(new int[] {cellRow, cellColumn});
            }

            cellColumn--;
        }
    }

    private static void checkCellsToTheEast(int row, int column, int radius, char[][] matrix, List<int[]> peopleCoordinates) {
        int cellRow = row - radius;
        int cellColumn = column + radius;
        int checkCount = MIN_PEOPLE_TO_ROTATE / 4 * radius;
        int rows = matrix.length;
        int columns = matrix[0].length;
        for(int i = 1; i <= checkCount ; i++) {
            if(cellRow < rows && cellColumn < columns && cellRow >= 0 && cellColumn >= 0) {
                peopleCoordinates.add(new int[] {cellRow, cellColumn});
            }

            cellRow++;
        }
    }

    private static void checkCellsToTheNorth(int row, int column, int radius, char[][] matrix, List<int[]> peopleCoordinates) {
        int cellRow = row - radius;
        int cellColumn = column - radius;
        int checkCount = MIN_PEOPLE_TO_ROTATE / 4 * radius;
        int rows = matrix.length;
        int columns = matrix[0].length;
        for(int i = 1; i <= checkCount ; i++) {
            if(cellRow < rows && cellColumn < columns && cellRow >= 0 && cellColumn >= 0) {
                peopleCoordinates.add(new int[] {cellRow, cellColumn});
            }

            cellColumn++;
        }
    }

    private static char[][] inputMatrix(Scanner scanner) {
        int rows = Integer.parseInt(scanner.nextLine());
        String line = scanner.nextLine();
        int columns = line.length();
        char[][] matrix = new char[rows][columns];
        for(int i = 0; i < columns; i++) {
            matrix[0][i] = line.charAt(i);
        }
        for(int i = 1; i < rows; i++) {
            line = scanner.nextLine();
            for(int j = 0; j < columns; j++) {
                matrix[i][j] = line.charAt(j);
            }
        }

        return matrix;
    }
}
