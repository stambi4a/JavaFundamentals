package Problem02.ParkingSystem;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        parkCars();
    }

    private static void parkCars() {
        Scanner scanner = new Scanner(System.in);
        String input = scanner.nextLine();
        boolean[][] parking = createParking(input);
        while(true) {
            input = scanner.nextLine();
            if(input.equals("stop")) {
                break;
            }

            tryParkACar(input, parking);
        }
    }

    private static void tryParkACar(String input, boolean[][] parking) {
        String[] params = input.split(" ");
        int rows = parking.length;
        int columns = parking[0].length;
        int enterRow = Integer.parseInt(params[0]);
        int parkRow = Integer.parseInt(params[1]);
        int parkColumn = Integer.parseInt(params[2]);
        int coveredDistance = Math.abs(parkRow - enterRow) + 1;
        int stretch = 0;
        while(true) {
            boolean rowIsFull = parkColumn + stretch >= columns && parkColumn - stretch <= 0;
            if(rowIsFull) {
                System.out.printf("Row %d full%n", parkRow);
                return;
            }

            int distanceToParkingColumn = parkColumn - stretch;
            if(distanceToParkingColumn > 0 && !parking[parkRow][distanceToParkingColumn]) {
                parking[parkRow][distanceToParkingColumn] = true;
                coveredDistance += distanceToParkingColumn;
                break;
            } else {
                distanceToParkingColumn = parkColumn + stretch;
                if(distanceToParkingColumn < columns && !parking[parkRow][distanceToParkingColumn]) {
                    parking[parkRow][distanceToParkingColumn] = true;
                    coveredDistance += distanceToParkingColumn;
                    break;
                }

                stretch++;
            }
        }

        System.out.println(coveredDistance);

    }

    private static boolean[][] createParking(String input) {
        String[] params = input.split(" ");
        int rows = Integer.parseInt(params[0]);
        int columns = Integer.parseInt(params[1]);
        boolean[][] parking = new boolean[rows][columns];

        return parking;
    }
}
