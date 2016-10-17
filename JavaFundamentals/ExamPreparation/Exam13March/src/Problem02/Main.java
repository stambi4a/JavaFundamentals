package Problem02;

import java.util.Scanner;

public class Main {
    private static final int HOTEL_REVENUE = 10;
    private static final int JAIL_TURNS = 2;
    private static int turns = 0;
    private static int jailTurns = 0;
    private static boolean isInJail = false;
    private static int hotels = 0;
    private static int money = 50;
    public static void main(String[] args) {
        char[][] field = inputField();
        playMonopolySpecialVariant(field);
    }

    private static void endGame() {
        System.out.printf("Turns %d%n", turns);
        System.out.printf("Money %d%n", money);
    }
    private static void refreshMoneyBalance() {
        money += hotels * HOTEL_REVENUE;
    }

    private static void advanceTurns() {
        turns++;
    }

    private static boolean checkIsInJail() {
        if(isInJail && jailTurns == 0) {
            isInJail = false;
            return false;
        }
        if(!isInJail) {
            return false;
        }
        if(isInJail && jailTurns > 0) {
            jailTurns--;
            return true;
        }

        return false;
    }

    private static void playMonopolySpecialVariant(char[][] field) {
        int[] coordinates = {0,0};
        encounterObject(field, coordinates);
        advanceTurns();
        while(true) {
            refreshMoneyBalance();
            if(!checkIsInJail()) {
                boolean gameIsFinished = traverseField(field, coordinates);
                if(!gameIsFinished) {
                    encounterObject(field, coordinates);
                    advanceTurns();
                    /*refreshMoneyBalance();*/
                }else {
                    break;
                }
            } else {
                advanceTurns();
                /*refreshMoneyBalance();*/
            }
        }

        endGame();
    }

    private static void encounterObject(char[][] field, int[] coordinates) {
        switch(field[coordinates[0]][coordinates[1]]) {
            case 'F' :{
            }

            break;
            case 'J' : {
                isInJail = true;
                jailTurns = JAIL_TURNS;
                System.out.printf("Gone to jail at turn %d.%n", turns);
            }

            break;
            case 'H' : {
                hotels++;
                System.out.printf("Bought a hotel for %d. Total hotels: %d.%n", money, hotels);
                money = 0;
            }

            break;
            case 'S' : {
                int spentMoney = (coordinates[0] + 1) *(coordinates[1] + 1);
                System.out.printf("Spent %d money at the shop.%n", money > spentMoney ? spentMoney : money);
                money = spentMoney >= money ? 0: money - spentMoney;

            }

            break;
            default :
                break;
        }
    }

    private static boolean traverseField(char[][] field, int[] coordinates) {
        int row = field.length;
        int column = field[0].length;
        if(coordinates[0] % 2 == 0) {
            if(coordinates[1] < column - 1) {
                coordinates[1]++;
            } else if(coordinates[0] < row - 1) {
                coordinates[0]++;
            } else {
                return true;
            }
        }else if(coordinates[0] % 2 == 1) {
            if(coordinates[1] > 0) {
                coordinates[1]--;
            } else if(coordinates[0] < row - 1) {
                coordinates[0]++;
            } else {
                return true;
            }
        }

        return false;
    }

    private static char[][] inputField() {
        Scanner scanner = new Scanner(System.in);
        String[] size = scanner.nextLine().split(" ");
        int rows = Integer.parseInt(size[0]);
        int columns = Integer.parseInt(size[1]);

        char[][] field = new char[rows][columns];
        for(int i = 0; i < rows; i++) {
            String line = scanner.nextLine();
            for(int j = 0; j < columns; j++) {
                field[i][j] = line.charAt(j);
            }
        }

        return field;
    }
}